package com.system.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.home.service.ProductService;

/**
 * @author jpf
 * @Title: IndexController
 * @ProjectName springmvc-demo
 * @Description: TODO
 * @date 2021/10/15 15:00
 */

@RequestMapping("/home")
@Controller
public class IndexController {
	
//	@Autowired(required=true)
//	private AccountService accountService;
//	@Autowired
//	private ProductCategoryService productCategoryService;
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "/Home",method = RequestMethod.GET)
	public ModelAndView homePage(ModelAndView model){
		
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("offset", 0);
		queryMap.put("pageSize", 5);
		queryMap.put("orderBy", "createTime");
		queryMap.put("sort", "desc");
		
		model.addObject("lastProductList", productService.findList(queryMap));
		queryMap.put("orderBy", "sellNum");
		model.addObject("sellProductList", productService.findList(queryMap));
		model.addObject("allCategoryClass","shop_hd_menu_hover");
		model.addObject("currentHome", "current_");
		model.addObject("currentHome", "current_");
		model.setViewName("home/home");
		return model;

    }
	
	@RequestMapping("/systemHome")
	public ModelAndView systemHomePage(){
        Date date=new Date();

        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("name","John Doe");
        modelAndView.setViewName("home/systemhome");
        return modelAndView;
    }
	
	@RequestMapping("/system")
	public ModelAndView systemLogin(){
        Date date=new Date();

        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("name","John Doe");
        modelAndView.setViewName("home/systemlogin");
        return modelAndView;
    }
	
	@RequestMapping("/uplodeFile")
	public ModelAndView upload(){
        Date date=new Date();

        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("date",date);
        modelAndView.setViewName("upload");
        return modelAndView;
    }
}
