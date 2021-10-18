package com.restful.demo;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
/*
 * 
 * 特征:

　　　　1.通过url地址来标识资源,系统中的每个对象或资源都可以通过其url地址来获取

　　　　2.统一接口,显式地使用HTTP方法,来进行crud(create,update,insert,delete)映射

　　　　　　创建资源使用POST

　　　　　　更新资源使用PUT

　　　　　　检索资源使用GET

　　　　　　删除资源使用DELETE

　　　　3.资源多重反映.通过url地址访问的每个资源都可以根据客户端的规定进行返回,例:JSON,XML
	 
	 * @RequestMapping:请求映射地址到对应的方法,该注解又可以分为一下几种类型:
	　　　　@GetMapping
	　　　　@PostMpping
	　　　　@PutMapping
	　　　　@DeleteMapping
	　　　　@PatchMapping
	　　@ResponsrBody:响应内容转换为JSON格式
	　　@RequestBody:请求内容转换为JSON格式
	　　@RestContrller:等同@Controller+@ResponsrBody
 * 
 */
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;

import com.system.entity.User;
import com.system.service.UserService;

@Controller
public class UserDemoController {
	
	//整体API设计(基于用户管理模块):
	//Get/user:获取所有用户
	//Get/user/{id}:根据ID获取该用户信息
	//Post/insert:保存用户信息
	//Put/update:更新用户信息
	//Delete/delete/{id}:根据用户ID删除用户
	//Get/user/from:获取创建用户表单
	//Get/update/{id}:根据ID获取用户信息
	
	@Autowired
	public UserService userService;
	
	@GetMapping
	public ModelAndView list(Model model) {
		ModelAndView models = new ModelAndView();
		models.addObject("", model);
		models.addObject("", model);
		models.setViewName("user/list");
		return models;
		//return new ModelAndView(String viewName, @Nullable Map<String, ?> model)
	}
	
	@GetMapping("{id}")
	public ModelAndView byId(@PathVariable("id") int id, Model model) {
		//userService.findById(id);
		ModelAndView models = new ModelAndView();
		models.addObject("", model);	
		models.addObject("", model);
		models.setViewName("user/list");
		return models;
		//return new ModelAndView(String viewName, @Nullable Map<String, ?> model)
	}
	
	//https://www.cnblogs.com/mmmmyblog/p/9598693.html
	@GetMapping("{id}")
	public ModelAndView creatfrom(@PathVariable("id") int id, Model model) {
		//userService.findById(id);
		ModelAndView models = new ModelAndView();
		models.addObject("", model);	
		models.addObject("", model);
		models.setViewName("user/list");
		return models;
		//return new ModelAndView(String viewName, @Nullable Map<String, ?> model)
	}
	
	@PostMapping("insert")
	public ModelAndView insert(User user, Model model) {
		ModelAndView models = new ModelAndView();
		models.addObject("", model);	
		models.addObject("", model);
		models.setViewName("user/list");
		return models;
	}
	
	@PutMapping("update")
	public ModelAndView update(User user, Model model) {
		ModelAndView models = new ModelAndView();
		models.addObject("", model);	
		models.addObject("", model);
		models.setViewName("user/list");
		return models;
	}
	
	@DeleteMapping(value = "delete/{id}")
	public ModelAndView delete(@PathVariable("id") int id, Model model) {
		//boolean bo = userService.delete(id);
		boolean bo = true;
		if (bo) {
			return new ModelAndView("redirect:/user");
		}
		else {
			return null;
		}
	}
	

}
