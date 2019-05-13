package com.java;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaTest {
	public static void main(String[] args) {
		ApplicationContext ioc = new AnnotationConfigApplicationContext(AopConfig.class);
		User user = (User)ioc.getBean("getUser");
		System.out.println(user);
	}
}
