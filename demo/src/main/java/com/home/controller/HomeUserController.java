package com.home.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.home.entity.Account;
import com.home.service.AccountService;
import com.home.service.ProductCategoryService;
import com.util.MenuUtil;



/**
 * ǰ̨�û����Ŀ�����
 * @author jpf
 *
 */
@RequestMapping("/user")
@Controller
public class HomeUserController {
	
	@Autowired
	private AccountService accountService;
	@Autowired
	private ProductCategoryService productCategoryService;
	//@Autowired
	//private OrderService orderService;
	
	/**
	 * �û�����ҳ��
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/info",method = RequestMethod.GET)
	public ModelAndView info(ModelAndView model,HttpServletRequest request){
		model.addObject("productCategoryList", MenuUtil.getTreeCategory(productCategoryService.findList(new HashMap<String, Object>())));
		model.addObject("allCategoryId","shop_hd_menu_all_category");
		Account onlineAccount = (Account)request.getSession().getAttribute("account");
		model.addObject("user", onlineAccount);
		model.addObject("currentUser", "current_");
		model.setViewName("home/user/info");
		return model;
	}
	
	/**
	 * �޸�����ҳ��
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/update_pwd",method = RequestMethod.GET)
	public ModelAndView updatePwd(ModelAndView model){
		model.addObject("productCategoryList", MenuUtil.getTreeCategory(productCategoryService.findList(new HashMap<String, Object>())));
		model.addObject("allCategoryId","shop_hd_menu_all_category");
		model.addObject("currentUser", "current_");
		model.setViewName("home/user/update_pwd");
		return model;
	}
	
	/**
	 * �޸������ύ
	 * @param password
	 * @param newPassword
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/update_pwd",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updatePassword(String password,String newPassword,
			HttpServletRequest request){
		Map<String, String> ret = new HashMap<String, String>();
		Account onlineAccount = (Account)request.getSession().getAttribute("account");
		ret.put("type", "error");
		if(StringUtils.isEmpty(password)){
			ret.put("msg", "�����벻��Ϊ�գ�");
			return ret;
		}
		if(StringUtils.isEmpty(newPassword)){
			ret.put("msg", "�����벻��Ϊ�գ�");
			return ret;
		}
		if(!onlineAccount.getPassword().equals(password)){
			ret.put("msg", "���������");
			return ret;
		}
		onlineAccount.setPassword(newPassword);
		if(accountService.edit(onlineAccount) <= 0){
			ret.put("msg", "�޸�ʧ�ܣ�����ϵ����Ա��");
			return ret;
		}
		ret.put("type", "success");
		return ret;
	}
	
	/**
	 * ��������
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "/update_info",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateInfo(Account account,
			HttpServletRequest request){
		Map<String, String> ret = new HashMap<String, String>();
		Account onlineAccount = (Account)request.getSession().getAttribute("account");
		ret.put("type", "error");
		if(account == null){
			ret.put("msg", "����д��ȷ����Ϣ");
			return ret;
		}
		if(StringUtils.isEmpty(account.getEmail())){
			ret.put("msg", "�����ַ����Ϊ�գ�");
			return ret;
		}
		if(StringUtils.isEmpty(account.getTrueName())){
			ret.put("msg", "��ʵ��������Ϊ�գ�");
			return ret;
		}
		onlineAccount.setEmail(account.getEmail());
		onlineAccount.setTrueName(account.getTrueName());
		onlineAccount.setSex(account.getSex());
		if(accountService.edit(onlineAccount) <= 0){
			ret.put("msg", "�޸�ʧ�ܣ�����ϵ����Ա��");
			return ret;
		}
		ret.put("type", "success");
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
		//model.addObject("order", orderService.findById(orderId));
		model.setViewName("home/cart/order_success");
		return model;
	}
}
