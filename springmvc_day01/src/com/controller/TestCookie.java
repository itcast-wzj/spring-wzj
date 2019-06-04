package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/springmvc")
public class TestCookie {
	
	private static final String SUCCESS = "success";
	
	@RequestMapping("/testCookie")
	public String testCookie(@CookieValue("JSESSIONID") String id) {
		System.out.println("testCookie: "+id);
		return SUCCESS;
	}
}	
