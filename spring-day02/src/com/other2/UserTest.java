package com.other2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *   测试复杂类型注入 
 * @author 王志坚
 *
 */
public class UserTest {
	public static void main(String[] args) {
		ApplicationContext ioc = new ClassPathXmlApplicationContext("com/other2/user.xml");
		User user = ioc.getBean("user",User.class);
		System.out.println(user);
		/*
		 * 	User [
		 * 		car=Car [name=宝马], 
		 * 		lists=[list01, list02, Car [name=宝马], Car [name=奔驰]]
		 * 		,maps={user=root, pwd=123}
		 * 		,prop={pwd=123, username=wzj}]
		 * 
		 * */
	}
}
