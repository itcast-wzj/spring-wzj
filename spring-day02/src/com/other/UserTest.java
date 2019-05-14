package com.other;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 	因为java基础就是通过set/构造为属性赋值
	四种注入方式
	1.set注入
	2.构造注入
	3.p名称空间注入
	4.spel注入
 * @author 王志坚
 *
 */
public class UserTest {
	public static void main(String[] args) {
		ApplicationContext ioc = new ClassPathXmlApplicationContext("com/other/user.xml");
		User user = ioc.getBean("user",User.class);
		System.out.println(user);
	}
}
