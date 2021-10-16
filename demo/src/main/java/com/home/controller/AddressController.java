package com.home.controller;

import java.util.Date;
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
import com.home.entity.Address;
import com.home.service.AddressService;

/**
 * AddressController
 * @author jpf
 *
 */
@RequestMapping("/address")
@Controller
public class AddressController {
	
//	@Autowired
//	private AccountService accountService;
//	@Autowired
//	private ProductCategoryService productCategoryService;
//	@Autowired
//	private ProductService productService;
	@Autowired
	private AddressService addressService;
	/**
	 * �ջ���ַ�б�ҳ��
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public ModelAndView index(ModelAndView model,HttpServletRequest request){
		//model.addObject("productCategoryList", MenuUtil.getTreeCategory(productCategoryService.findList(new HashMap<String, Object>())));
		model.addObject("allCategoryId","shop_hd_menu_all_category");
		Account onlineAccount = (Account)request.getSession().getAttribute("account");
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("userId", onlineAccount.getId());
		model.addObject("addressList", addressService.findList(queryMap));
		model.addObject("currentUser", "current_");
		model.setViewName("home/address/list");
		return model;
	}
	
	
	/**
	 * ����ջ���ַ
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(Address address,HttpServletRequest request){
		Map<String, String> ret = new HashMap<String, String>();
		Account onlineAccount = (Account)request.getSession().getAttribute("account");
		ret.put("type", "error");
		if(address == null){
			ret.put("msg", "��ѡ����ȷ���ջ���Ϣ");
			return ret;
		}
		if(StringUtils.isEmpty(address.getName())){
			ret.put("msg", "����д�ջ��ˣ�");
			return ret;
		}
		if(StringUtils.isEmpty(address.getAddress())){
			ret.put("msg", "����д�ջ���ַ��");
			return ret;
		}
		if(StringUtils.isEmpty(address.getPhone())){
			ret.put("msg", "����д�ֻ��ţ�");
			return ret;
		}
		address.setUserId(onlineAccount.getId());
		address.setCreateTime(new Date());
		if(addressService.add(address) <= 0){
			ret.put("msg", "���ʧ�ܣ�����ϵ����Ա!");
			return ret;
		}
		ret.put("type", "success");
		return ret;
	}
	
	@RequestMapping(value = "/edit",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(Address address,HttpServletRequest request){
		Map<String, String> ret = new HashMap<String, String>();
		Account onlineAccount = (Account)request.getSession().getAttribute("account");
		ret.put("type", "error");
		if(address == null){
			ret.put("msg", "��ѡ����ȷ���ջ���Ϣ");
			return ret;
		}
		Address existAddress = addressService.findById(address.getId());
		if(existAddress == null){
			ret.put("msg", "�����ڸõ�ַ��");
			return ret;
		}
		if(StringUtils.isEmpty(address.getName())){
			ret.put("msg", "����д�ջ��ˣ�");
			return ret;
		}
		if(StringUtils.isEmpty(address.getAddress())){
			ret.put("msg", "����д�ջ���ַ��");
			return ret;
		}
		if(StringUtils.isEmpty(address.getPhone())){
			ret.put("msg", "����д�ֻ��ţ�");
			return ret;
		}
		address.setUserId(onlineAccount.getId());
		if(addressService.edit(address) <= 0){
			ret.put("msg", "�༭ʧ�ܣ�����ϵ����Ա!");
			return ret;
		}
		ret.put("type", "success");
		return ret;
	}
	
	/**
	 * ɾ���ջ���ַ
	 * @param favoriteId
	 * @return
	 */
	@RequestMapping(value = "/delete",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(Long id){
		Map<String, String> ret = new HashMap<String, String>();
		ret.put("type", "error");
		if(id == null){
			ret.put("msg", "��ѡ��Ҫɾ���ĵ�ַ");
			return ret;
		}
		if(addressService.delete(id) <= 0){
			ret.put("msg", "ɾ����������ϵ����Ա!");
			return ret;
		}
		ret.put("type", "success");
		return ret;
	}
	
}
