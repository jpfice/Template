package com.home.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.home.entity.Account;
import com.home.entity.Purse;
import com.home.service.AccountService;
import com.home.service.PurseService;
import com.util.Page;

//import net.sf.json.JSONArray;


@RequestMapping("/admin/purse")
@Controller
public class RecordController {

	@Autowired
	private PurseService purserService;
	
	@Autowired
	private AccountService accountService;
	
	/**
	 * 后台充值列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/lists",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model) {
		//List<Account> accountList = accountService.findList(null);	
		//model.addObject("accountList", JSONArray.fromObject(accountList));
		model.setViewName("/record/list");
		return model;
	}
	
	/**
	 * 查询充值列表
	 * @param name
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> list(@RequestParam(name="name",defaultValue="",required=false)String name,
			@RequestParam(name="state",required=false)Integer state,
				Page page,Model model
			){
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		if(!"".equals(name)) {
			queryMap.put("name", name);
		}
		if(state!=null) {
				queryMap.put("state", state);
		}
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		//List<Purse> findList = purserService.findList(queryMap);
		ret.put("rows", purserService.findList(queryMap));
		ret.put("total", purserService.getTotal(queryMap));
		return ret;
	}
	
	
	/**
	 * 审核结果
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/examine",method=RequestMethod.POST)
	public Map<String,Object> examine(@RequestParam("id")Integer id,
			@RequestParam("uid")Integer uid,
			@RequestParam("recharge")double recharge,
			@RequestParam("state")Integer state){
		Map<String, Object> ret = new HashMap<String, Object>();
		if(id==0) {
			ret.put("type", "error");
			ret.put("msg", "请选择至少一条数据");
			return ret;
		}
		
		Purse selectPurse = purserService.findListByid(id);
		selectPurse.setState(state);
		
		if(purserService.examine(selectPurse)<=0) {
			ret.put("type", "error");
			ret.put("msg", "审核失败,请联系管理员");
			return ret;
		}
		if(state==1) {
		Account account = accountService.findById(uid.longValue());
			account.setBalance(account.getBalance()+recharge);
			if(accountService.editBalance(account)<=0) {
				ret.put("type", "error");
				ret.put("msg", "充值失败,请联系管理员");
				return ret;
			}
		}
		ret.put("type", "success");
		ret.put("msg", "审核成功");
		return  ret;
	}
}
