package com.system.controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import com.system.entity.Company;


public class ValidController {
	
	@RequestMapping("/valid")
	public String valid(@Validated Company company, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				System.out.println(fieldError);
			}
			return "fail";
		}
		return "success";
	}

}
