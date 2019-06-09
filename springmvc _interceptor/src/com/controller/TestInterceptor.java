package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestInterceptor {
	
	private static final String SUCCESS = "success";
	
	@RequestMapping("/testInterceptor")
	public String testInterceptor() {
		System.out.println("testInterceptor 目标方法");
		return SUCCESS;
	}
	
	@RequestMapping("/hello")
	public String hello() {
		System.out.println("hello 目标方法");
		return SUCCESS;
	}
}
