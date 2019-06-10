package com.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	
	//springmvc容器中的bean可以引用spring容器中的bean
	@Autowired
	private UserService service;

	public UserController() {
		System.out.println("UserController 无参");
	}

	@RequestMapping("/hello")
	public String hello() {
		System.out.println(service);
		return "success";
	}
}
