package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/springmvc")
public class TestRequestAttribute {
	
	@ModelAttribute
	public void BeforeHandlerMethod(HttpServletRequest req) {
		req.setAttribute("name", "wzj");
	}
}
