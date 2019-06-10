package com.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	/**
	 	而spring容器中的bean不能引用springmvc容器中的bean,
	 	会出现NoSuchBeanDefinitionException异常
	 */
//	@Autowired
//	private UserController controller;

	//框架都是基于反射的,反射通过构造函数创建对象
	public UserService() {
		System.out.println("UserService 无参");
	}

	
}
