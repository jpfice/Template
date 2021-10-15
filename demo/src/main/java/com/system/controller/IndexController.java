package com.system.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author jpf
 * @Title: IndexController
 * @ProjectName springmvc-demo
 * @Description: TODO
 * @date 2021/10/15 15:00
 */

@Controller
public class IndexController {
	
	
	@RequestMapping("/systemHome")
	public ModelAndView systemHomePage(){
        Date date=new Date();

        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("date",date);
        modelAndView.setViewName("systemhome");
        return modelAndView;
    }
	
	@RequestMapping("/demo")
	public ModelAndView handle01(){
        Date date=new Date();

        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("date",date);
        modelAndView.setViewName("index");
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
