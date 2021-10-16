package com.home.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.home.entity.Account;
import com.home.entity.Address;
import com.home.entity.Cart;
import com.home.entity.Order;
import com.home.entity.OrderItem;
import com.home.entity.Product;
import com.home.service.AccountService;
import com.home.service.AddressService;
import com.home.service.CartService;
import com.home.service.OrderService;
import com.home.service.ProductCategoryService;
import com.home.service.ProductService;
import com.util.MenuUtil;


/**
 * ǰ̨����������
 * @author jpf
 *
 */
@RequestMapping("/order")
@Controller
public class HomeOrderController {
	
	@Autowired
	private AccountService accountService;
	@Autowired
	private ProductCategoryService productCategoryService;
	@Autowired
	private ProductService productService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private CartService cartService;
	@Autowired
	private AddressService addressService;

	/**
	 * �����б�ҳ��
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public ModelAndView list(ModelAndView model,Integer page,HttpServletRequest request){
		model.addObject("productCategoryList", MenuUtil.getTreeCategory(productCategoryService.findList(new HashMap<String, Object>())));
		model.addObject("allCategoryId","shop_hd_menu_all_category");
		Account onlineAccount = (Account)request.getSession().getAttribute("account");
		Map<String, Object> queryMap = new HashMap<String, Object>();
		if(page == null || page.intValue() <= 0){
			page = 1;
		}
		queryMap.put("offset", (page -1) * 5);
		queryMap.put("pageSize", 5);
		queryMap.put("userId", onlineAccount.getId());
		queryMap.put("orderBy", "createTime");
		queryMap.put("sort", "desc");
		List<Order> orderList = orderService.findList(queryMap);
		for(Order order:orderList){
			order.setOrderItems(orderService.findOrderItemList(order.getId()));
		}
		model.addObject("orderList", orderList);
		model.addObject("currentUser", "current_");
		model.addObject("page", page);
		model.setViewName("home/order/list");
		return model;
	}
	
	/**
	 * ȷ���ջ�
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/finish_order",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> finishOrder(Long id){
		Map<String, String> ret = new HashMap<String, String>();
		ret.put("type", "error");
		if(id == null){
			ret.put("msg", "��ѡ��Ҫ�ջ��Ķ���");
			return ret;
		}
		Order order = orderService.findById(id);
		if(order == null){
			ret.put("msg", "���������ڣ�");
			return ret;
		}
		if(order.getStatus() != Order.ORDER_STATUS_SENT){
			ret.put("msg", "��ǰ����״̬�����ܸ�!");
			return ret;
		}
		order.setStatus(Order.ORDER_STATUS_FINISH);
		if(orderService.edit(order) <= 0){
			ret.put("msg", "�༭ʧ�ܣ�����ϵ����Ա!");
			return ret;
		}
		ret.put("type", "success");
		return ret;
	}
	
	/**
	 * ��Ӷ���
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(Long addressId,
			@RequestParam(name="remark",required=false)String remark,
			HttpServletRequest request){
		Map<String, String> ret = new HashMap<String, String>();
		Account onlineAccount = (Account)request.getSession().getAttribute("account");
		//���´����ݿ��ȡ
		onlineAccount = accountService.findById(onlineAccount.getId());
		ret.put("type", "error");
		if(addressId == null){
			ret.put("msg", "��ѡ���ջ���ַ");
			return ret;
		}
		Address address = addressService.findById(addressId);
		if(address == null){
			ret.put("msg", "��ַ�����ڣ�");
			return ret;
		}
		//�����û�ȥ��ѯ���ﳵ
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("userId", onlineAccount.getId());
		List<Cart> cartList = cartService.findList(queryMap);
		if(cartList == null){
			ret.put("msg", "���û����ﳵ��û����Ʒ!");
			return ret;
		}
		Order order = new Order();
		double totalMoney = 0;
		int totalNum = 0;
		for(Cart cart:cartList){
			OrderItem orderItem = new OrderItem();
			orderItem.setImageUrl(cart.getImageUrl());
			orderItem.setMoney(cart.getMoney());
			orderItem.setName(cart.getName());
			orderItem.setNum(cart.getNum());
			orderItem.setPrice(cart.getPrice());
			orderItem.setProductId(cart.getProductId());
			totalMoney += cart.getMoney();
			totalNum += cart.getNum();
			order.getOrderItems().add(orderItem);
		}
		order.setAddress(address.getAddress() + " " + address.getName() + "(��) " + address.getPhone());
		order.setMoney(totalMoney);
		order.setProductNum(totalNum);
		order.setSn("O"+System.currentTimeMillis());
		order.setStatus(Order.ORDER_STATUS_WAITING);
		order.setUserId(onlineAccount.getId());
		order.setRemark(remark);
		order.setCreateTime(new Date());
		double balance2 = onlineAccount.getBalance();
		if(totalMoney>balance2) {
			ret.put("msg", "�û�����,���ֵ��!");
			return ret;
		}
		//�����û����۳���Ʒ�۸�
		double defaultMoney=(double) (balance2-totalMoney);
		onlineAccount.setBalance(defaultMoney);
		//�����û�������Ʒ��ʣ�����
		if(accountService.editBalance(onlineAccount)<=0) {
			ret.put("msg", "����ʧ�ܣ�����ϵ����Ա!");
			return ret;
		}
		request.getSession().setAttribute("account",onlineAccount);
		if(orderService.add(order) <= 0){
			ret.put("msg", "���ʧ�ܣ�����ϵ����Ա!");
			return ret;
		}
		//��չ��ﳵ��������Ʒ����
		for(Cart cart:cartList){
			Product product = productService.findById(cart.getProductId());
			product.setStock(product.getStock() - cart.getNum());
			product.setSellNum(product.getSellNum() + cart.getNum());
			//������Ʒ��桢����
			productService.updateNum(product);
		}
		//��չ��ﳵ
		cartService.deleteByUid(onlineAccount.getId());
		
		
		
		ret.put("type", "success");
		ret.put("oid", order.getId()+"");
		return ret;
	}
	
	/**
	 * �µ��ɹ�չʾҳ��
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/order_success",method = RequestMethod.GET)
	public ModelAndView orderSuccess(ModelAndView model,Long orderId,HttpServletRequest request){
		model.addObject("productCategoryList", MenuUtil.getTreeCategory(productCategoryService.findList(new HashMap<String, Object>())));
		model.addObject("allCategoryId","shop_hd_menu_all_category");
		model.addObject("currentCart", "current_");
		model.addObject("order", orderService.findById(orderId));
		model.setViewName("home/cart/order_success");
		return model;
	}
	
	/**
	 * ��Ʒ����ҳ��
	 * @param model
	 * @param pid
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/comment",method = RequestMethod.GET)
	public ModelAndView comment(ModelAndView model,Long pid,HttpServletRequest request){
		model.addObject("productCategoryList", MenuUtil.getTreeCategory(productCategoryService.findList(new HashMap<String, Object>())));
		model.addObject("allCategoryId","shop_hd_menu_all_category");
		model.addObject("currentUser", "current_");
		model.addObject("product", productService.findById(pid));
		model.setViewName("home/order/comment");
		return model;
	}
	
}
