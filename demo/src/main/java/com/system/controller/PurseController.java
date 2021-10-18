package com.system.controller;

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
import com.home.entity.Purse;
import com.home.service.ProductCategoryService;
import com.home.service.PurseService;
import com.util.MenuUtil;


@RequestMapping("/purse")
@Controller
public class PurseController {
	@Autowired
	private PurseService purseService;
	@Autowired
	private ProductCategoryService productCategoryService;
	
	/**
	 * 用户充值页面
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public ModelAndView purse(ModelAndView model,HttpServletRequest request){
		Account onlineAccount = (Account)request.getSession().getAttribute("account");
		model.addObject("user", onlineAccount);
		/*List<Purse> purseList = purseService.findById(onlineAccount.getId());
		if(purseList.size()>0) {
			Purse purse = purseList.get(0);
			model.addObject("purse", purse);
		}*/
		model.addObject("productCategoryList", MenuUtil.getTreeCategory(productCategoryService.findList(new HashMap<String, Object>())));
		model.addObject("allCategoryId","shop_hd_menu_all_category");
		model.addObject("currentUser", "current_");
		model.setViewName("home/purse/purse");
		return model;
	}
	
	/**
	 * 确认充值金额
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/recharge",method=RequestMethod.POST)
	public Map<String,String> recharge(@RequestParam("balance")double balance,HttpServletRequest request){
		HashMap<String, String> ret = new HashMap<String,String>();
		ret.put("type", "error");
		Purse purse = new Purse();
		if(balance<=0) {
			ret.put("msg", "金额为正整数");
			return ret;
		}
		Account onlineAccount = (Account)request.getSession().getAttribute("account");
		purse.setUserId(onlineAccount.getId().intValue());
		purse.setRecharge(balance);
		purse.setState(0);
		if(purseService.add(purse)<=0) {
			ret.put("msg", "充值失败请联系管理员");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "充值成功,等待管理员审核");
		return ret;
	}

	/**
	 * 充值记录页面
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/record",method = RequestMethod.GET)
	public ModelAndView record(ModelAndView model,HttpServletRequest request,Integer page){
		Account account = (Account)request.getSession().getAttribute("account");
		if(account!=null) {
			Map<String, Object> queryMap = new HashMap<String,Object>();
			if(page == null || page.intValue() <= 0){
				page = 1;
			}
			queryMap.put("offset", (page -1) * 5);
			queryMap.put("pageSize", 7);
			queryMap.put("userId", account.getId());
			List<Purse> findRecord = purseService.findRecord(queryMap);
			model.addObject("RecordList", findRecord);
			model.addObject("page", page);
		}
		model.addObject("productCategoryList", MenuUtil.getTreeCategory(productCategoryService.findList(new HashMap<String, Object>())));
		model.addObject("allCategoryId","shop_hd_menu_all_category");
		model.setViewName("home/purse/record");
		return model;
	}
}
