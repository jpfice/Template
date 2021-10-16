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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.home.entity.Account;
import com.home.entity.Cart;
import com.home.entity.Product;
import com.home.entity.Purse;
import com.home.service.AddressService;
import com.home.service.CartService;
import com.home.service.ProductCategoryService;
import com.home.service.ProductService;
import com.home.service.PurseService;
import com.util.MenuUtil;

/**
 * CartController
 * @author jpf
 *
 */
@RequestMapping("/cart")
@Controller
public class CartController {

	@Autowired
	private ProductCategoryService productCategoryService;
	@Autowired
	private ProductService productService;
	@Autowired
	private CartService cartService;
	@Autowired
	private AddressService addressService;
	@Autowired
	private PurseService purseService;
	/**
	 * ���ﳵ�б�ҳ��
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public ModelAndView list(ModelAndView model,HttpServletRequest request){
		model.addObject("productCategoryList", MenuUtil.getTreeCategory(productCategoryService.findList(new HashMap<String, Object>())));
		model.addObject("allCategoryId","shop_hd_menu_all_category");
		Account onlineAccount = (Account)request.getSession().getAttribute("account");
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("userId", onlineAccount.getId());
		model.addObject("cartList", cartService.findList(queryMap));
		model.addObject("currentCart", "current_");
		model.setViewName("home/cart/list");
		return model;
	}
	
	/**
	 * �ύ�����ڶ���
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/list_2",method = RequestMethod.GET)
	public ModelAndView list2(ModelAndView model,HttpServletRequest request){
		model.addObject("productCategoryList", MenuUtil.getTreeCategory(productCategoryService.findList(new HashMap<String, Object>())));
		model.addObject("allCategoryId","shop_hd_menu_all_category");
		Account onlineAccount = (Account)request.getSession().getAttribute("account");
		List<Purse> purse = purseService.findById(onlineAccount.getId());
		if(purse.size()>0) {
			Purse purse2 = purse.get(0);
			request.getSession().setAttribute("purseT", purse2);
			model.addObject("purse",purse2);
		}
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("userId", onlineAccount.getId());
		model.addObject("cartList", cartService.findList(queryMap));
		model.addObject("currentCart", "current_");
		model.addObject("addressList", addressService.findList(queryMap));
		
		model.setViewName("home/cart/list_2");
		return model;
	}
	
	
	/**
	 * ��ӹ��ﳵ
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(Cart cart,HttpServletRequest request){
		Map<String, String> ret = new HashMap<String, String>();
		Account onlineAccount = (Account)request.getSession().getAttribute("account");
		ret.put("type", "error");
		if(cart == null){
			ret.put("msg", "��ѡ����ȷ����Ʒ��Ϣ");
			return ret;
		}
		if(cart.getProductId() == null){
			ret.put("msg", "��ѡ��Ҫ��ӵ���Ʒ��");
			return ret;
		}
		if(cart.getNum() == 0){
			ret.put("msg", "����д��Ʒ����");
			return ret;
		}
		Product product = productService.findById(cart.getProductId());
		if(product == null){
			ret.put("msg", "��Ʒ������");
			return ret;
		}
		//������Ʒ���û�ȥ��ѯ����Ʒ�Ƿ��ѱ���ӵ����ﳵ
		Map<String, Long> queryMap = new HashMap<String, Long>();
		queryMap.put("userId", onlineAccount.getId());
		queryMap.put("productId", product.getId());
		Cart existCart = cartService.findByIds(queryMap);
		if(existCart != null){
			//��ʾ�����Ʒ�Ѿ�����ӵ����ﳵ��ֻ����������ͽ���
			existCart.setNum(existCart.getNum() + cart.getNum());
			existCart.setMoney(existCart.getNum() * existCart.getPrice());
			if(cartService.edit(existCart) <= 0){
				ret.put("msg", "��Ʒ�ѱ���ӵ����ﳵ����������������!");
				return ret;
			}
			ret.put("type", "success");
			return ret;
		}
		cart.setImageUrl(product.getImageUrl());
		cart.setMoney(product.getPrice() * cart.getNum());
		cart.setName(product.getName());
		cart.setPrice(product.getPrice());
		cart.setUserId(onlineAccount.getId());
		cart.setCreateTime(new Date());
		if(cartService.add(cart) <= 0){
			ret.put("msg", "���ʧ�ܣ�����ϵ����Ա!");
			return ret;
		}
		ret.put("type", "success");
		return ret;
	}
	
	/**
	 * ���¹��ﳵ��Ʒ����
	 * @param cartId
	 * @param num
	 * @return
	 */
	@RequestMapping(value = "/update_num",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateNum(Long cartId,Integer num){
		Map<String, String> ret = new HashMap<String, String>();
		ret.put("type", "error");
		Cart cart = cartService.findById(cartId);
		if(cart == null){
			ret.put("msg", "��ѡ����ȷ����Ʒ��Ϣ");
			return ret;
		}
		if(num == null){
			ret.put("msg", "����д��Ʒ����");
			return ret;
		}
		Product product = productService.findById(cart.getProductId());
		if(product == null){
			ret.put("msg", "���ﳵ��Ϣ����");
			return ret;
		}
		if(cart.getNum() + num.intValue() > product.getStock()){
			ret.put("msg", "��Ʒ�������ܳ����������");
			return ret;
		}
		cart.setNum(cart.getNum() + num);
		cart.setMoney(cart.getNum() * cart.getPrice());
		if(cartService.edit(cart) <= 0){
			ret.put("msg", "��Ʒ�ѱ���ӵ����ﳵ����������������!");
			return ret;
		}
		ret.put("type", "success");
		return ret;
	}
	
	/**
	 * ɾ�����ﳵ��Ʒ
	 * @param cartId
	 * @return
	 */
	@RequestMapping(value = "/delete",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(Long cartId){
		Map<String, String> ret = new HashMap<String, String>();
		ret.put("type", "error");
		if(cartId == null){
			ret.put("msg", "��ѡ��Ҫɾ������Ʒ");
			return ret;
		}
		if(cartService.delete(cartId) <= 0){
			ret.put("msg", "ɾ����������ϵ����Ա!");
			return ret;
		}
		ret.put("type", "success");
		return ret;
	}
	
}
