package com.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	
	//springmvc�����е�bean��������spring�����е�bean
	@Autowired
	private UserService service;

	public UserController() {
		System.out.println("UserController �޲�");
	}

	@RequestMapping("/hello")
	public String hello() {
		System.out.println(service);
		return "success";
	}
}
