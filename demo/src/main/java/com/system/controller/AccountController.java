package com.system.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.system.entity.Account;
import com.system.service.AccountService;
import com.util.Page;

/**
 * 客户管理控制器
 * @author jpf
 *
 */
@RequestMapping("/account")
@Controller
public class AccountController {
	
	private static final Logger logger = LoggerFactory.getLogger(AccountController.class); 
	
	@Autowired
	private AccountService accountService;
	
	/**
	 * 客户列表页
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
		logger.info("----->>>>toAccountListPage Start -------------");
		model.setViewName("account/list");
		logger.info("----->>>>toAccountListPage End -------------");
		return model;
	}
	
	/**
	 * 查询客户列表
	 * @param name
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> list(@RequestParam(name="name",defaultValue="")String name,
			@RequestParam(name="sex",defaultValue="")Integer sex,
			@RequestParam(name="status",defaultValue="")Integer status,Page page){
		logger.info("----->>>>AccountList Start -------------");
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("name", name);
		if(sex != null){
			queryMap.put("sex", sex);
		}
		if(status != null){
			queryMap.put("status", status);
		}
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		ret.put("rows", accountService.findList(queryMap));
		ret.put("total", accountService.getTotal(queryMap));
		logger.info("----->>>>AccountList End -------------");
		return ret;
	}
	
	
	/**
	 * 添加客户
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(Account account){
		logger.info("----->>>>AddAccount Start -------------");
		Map<String, Object> ret = new HashMap<String, Object>();
		if(account == null){
			ret.put("type", "error");
			ret.put("msg", "请填写正确的客户信息");
			return ret;
		}
		if(StringUtils.isEmpty(account.getName())){
			ret.put("type", "error");
			ret.put("msg", "请填写客户名称");
			return ret;
		}
		if(StringUtils.isEmpty(account.getPassword())){
			ret.put("type", "error");
			ret.put("msg", "请填写客户登录密码!");
			return ret;
		}
		if(isExist(account.getName(), 0l)){
			ret.put("type", "error");
			ret.put("msg", "该用户名已存在!");
			return ret;
		}
		account.setCreateTime(new Date());
		if(accountService.add(account) <= 0){
			ret.put("type", "error");
			ret.put("msg", "添加失败，请联系管理员!");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "添加成功!");
		logger.info("----->>>>AddAccount End -------------");
		return ret;
	}
	
	/**
	 * 编辑客户
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> edit(Account account){
		logger.info("----->>>>EditAccount Start -------------");
		Map<String, Object> ret = new HashMap<String, Object>();
		if(account == null){
			ret.put("type", "error");
			ret.put("msg", "请填写正确的客户信息");
			return ret;
		}
		if(StringUtils.isEmpty(account.getName())){
			ret.put("type", "error");
			ret.put("msg", "请填写客户名称");
			return ret;
		}
		if(StringUtils.isEmpty(account.getPassword())){
			ret.put("type", "error");
			ret.put("msg", "请填写客户登录密码!");
			return ret;
		}
		if(isExist(account.getName(), account.getId())){
			ret.put("type", "error");
			ret.put("msg", "该用户名已存在!");
			return ret;
		}
		if(accountService.edit(account) <= 0){
			ret.put("type", "error");
			ret.put("msg", "添加失败，请联系管理员!");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "编辑成功!");
		logger.info("----->>>>EditAccount End -------------");
		return ret;
	}
	
	/**
	 * 删除客户
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete(Long id){
		logger.info("----->>>>DeleteAccount Start -------------");
		Map<String, Object> ret = new HashMap<String, Object>();
		if(id == null){
			ret.put("type", "error");
			ret.put("msg", "请选择要删除的客户");
			return ret;
		}
		
		try {
			if(accountService.delete(id) <= 0){
				ret.put("type", "error");
				ret.put("msg", "删除失败，请联系管理员!");
				return ret;
			}
		} catch (Exception e) {
			ret.put("type", "error");
			ret.put("msg", "该客户下存在订单信息，不允许删除!");
			return ret;
		}
		
		ret.put("type", "success");
		ret.put("msg", "删除成功!");
		logger.info("----->>>>DeleteAccount End -------------");
		return ret;
	}
	
	/**
	 * 检查用户名是否存在
	 * @param name
	 * @param id
	 * @return
	 */
	private boolean isExist(String name,Long id){
		Account account = accountService.findByName(name);
		if(account == null)return false;
		if(account.getId().longValue() == id.longValue())return false;
		return true;
	}
	
	
	/**
	 * 客户商品发布页
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/productlist",method=RequestMethod.GET)
	public ModelAndView accuntProductList(ModelAndView model){
		logger.info("----->>>>toAccountProductListPage Start -------------");
		model.setViewName("account/productlist");
		logger.info("----->>>>toAccountProductListPage End -------------");
		return model;
	}
	
	@RequestMapping("/File")
	public String  upload(HttpServletRequest httpServletRequest, @RequestParam(value = "file", required = false) MultipartFile file) throws Exception {

		logger.info("----->>>>UploadController Start -------------");
		
		if (file.isEmpty()) {
			logger.info("----->>>>file.isEmpty -------------");
		}
		
		String path = httpServletRequest.getServletContext().getRealPath("/WEB-INF/file");
		// 获取原文件名
		String fileName = file.getOriginalFilename();
		// 创建文件实例
		File filePath = new File(path, fileName);
		// 如果文件目录不存在，创建目录
		if (!filePath.getParentFile().exists()) {
			filePath.getParentFile().mkdirs();
			System.out.println("创建目录" + filePath);
		}
		// 写入文件
		file.transferTo(filePath);
		
//		byte[] bytes = file.getBytes();
//		FtpUtils.sshSftp(bytes, "1111.jpg");
		
		
		logger.info("----->>>>UploadController Start -------------");
		
		return "account/account";
	}
	
	@RequestMapping("/Address")
	public String  address(HttpServletRequest httpServletRequest, @RequestParam(value = "file", required = false) MultipartFile file) throws Exception {

		logger.info("----->>>>AccountAddress Start -------------");

		
		
		logger.info("----->>>>AccountAddress End -------------");
		return "account/accountAddress";
	}
}
