package com.system.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.system.entity.Product;

@RequestMapping("/visa")
@Controller
public class VisaController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	/*
	 * 网站签证主页面
	 */
	@RequestMapping("visahome")
	public ModelAndView prodhome(){
		
		ModelAndView model = new ModelAndView();
		logger.info("----->>>>VisaHome Start -------------");
		
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("offset", 0);
		queryMap.put("pageSize", 8);
		queryMap.put("orderBy", "createTime");
		queryMap.put("sort", "desc");
		//List<Product> productList = productService.findList(queryMap);
		//model.addObject("lastProductList", productList);
		model.setViewName("visa/visahome");
        
        logger.info("----->>>>VisaHome End ---------------");
        return model;
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
