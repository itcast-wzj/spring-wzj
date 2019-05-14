package com.anno;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 	使用注解版演示了spring之spel的用法
 * 	@author 王志坚
 *
 */
//类似于在xml中<context:compoent-scan basePackages="xxx">
@Configuration
@ComponentScan(basePackages = {"com.anno"})//扫描compoent注解他们注入到ioc容器中 
public class AnnoTest {
	public static void main(String[] args) {
		ApplicationContext  ioc = new AnnotationConfigApplicationContext(AnnoTest.class);
		Person person = ioc.getBean("person",Person.class);
		System.out.println(person);
		/*
		 * Person [
		 * 	name=宝马, 
		 * 	car=Car [name=宝马, list=[List0, List1, List2], map={MapA=This is A, MapB=This is B, MapC=This is C}],
		 *  method1=5, method2=Startrun……, 
		 *  operator1=true, operator2=4, operator3=true, 
		 *  map_str=This is A, list_str=List0]
		 */
	}
}
