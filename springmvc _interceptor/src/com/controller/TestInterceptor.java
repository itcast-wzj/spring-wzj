package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestInterceptor {
	
	private static final String SUCCESS = "success";
	
	@RequestMapping("/testInterceptor")
	public String testInterceptor() {
		System.out.println("testInterceptor Ŀ�귽��");
		return SUCCESS;
	}
	
	@RequestMapping("/hello")
	public String hello() {
		System.out.println("hello Ŀ�귽��");
		return SUCCESS;
	}
}
