package com.system.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.system.entity.Product;
import com.system.service.ProductService;

/**
 * @author jpf
 * @Title: IndexController
 * @ProjectName springmvc-demo
 * @Description: TODO
 * @date 2021/10/15 15:00
 */
@Controller
public class IndexController {
	
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class); 
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/home")
	public ModelAndView home(){
		
		ModelAndView model = new ModelAndView();
		logger.info("----->>>>HomePage Start -------------");
		
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("offset", 0);
		queryMap.put("pageSize", 5);
		queryMap.put("orderBy", "createTime");
		queryMap.put("sort", "desc");
		List<Product> productList = productService.findList(queryMap);
		model.addObject("lastProductList", productList);
		model.setViewName("home/home");
        
        logger.info("----->>>>HomePage End ---------------");
        return model;
    }
	
	@RequestMapping("/systemHome")
	public ModelAndView systemHome(){
		
		ModelAndView model = new ModelAndView();
		logger.info("----->>>>SystemHomePage Start -------------");
		
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("offset", 0);
		queryMap.put("pageSize", 10);
		queryMap.put("sort", "desc");
		queryMap.put("orderBy", "sellNum");
		//queryMap.put("productId", id);
		
		List<Product> productList = productService.findList(queryMap);
		model.addObject("sellProductList", productList);
		model.setViewName("home/systemhome");
        
        logger.info("----->>>>SystemHomePage End ---------------");
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
