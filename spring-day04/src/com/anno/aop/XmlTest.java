package com.anno.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlTest {
	public static void main(String[] args) {
		ApplicationContext ioc = new ClassPathXmlApplicationContext("com/anno/aop/beans.xml");
		Student student = (Student) ioc.getBean("student");
		/*
		     后置通知是在方法运行完后执行!!!!
		  System.out.println(student.getName());
		     其实在这条输出语句执行之前，getName()方法已经执行完了,你这个只是打印方法的返回值
		      所以你看到的结果是
		         	前置通知
		                              后置通知
		           	打印结果
		     你还傻乎乎的感觉,后置通知不是在方法执行完之后吗，怎么这么奇怪 哈哈哈哈
		  
		  正确的方法是不用输出语句,在调用方法的内部打印一下，结果就很清晰了
		 			前置通知
		 			打印结果
		                              后置通知
		 */
		//System.out.println(student.getName());  //注意 ！！！！
		student.getName(); 
		           
	}
}
