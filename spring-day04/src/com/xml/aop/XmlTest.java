package com.xml.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlTest {
	public static void main(String[] args) {
		ApplicationContext ioc = new ClassPathXmlApplicationContext("com/xml/aop/beans.xml");
		Student student = ioc.getBean("student",Student.class);
//		System.out.println(student.getName()); 注意！！！
		student.getName();
	}
}
