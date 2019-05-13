package com.anno;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *	这下面两个注解相当于
 *       在applicationContext.xml中写
 *  <context:component-scan base-package=""/>
 *  @author 王志坚
 *
 */
@Configuration
@ComponentScan(value = "com.anno")
public class AnnoTest {
	public static void main(String[] args) {
		/*
			NoSuchBeanDefinitionException: No qualifying bean of type [com.anno.User] is defined
			如果运行代码报这个错；意思就是在容器中没有找到这个bean；就相当于没扫描进去
			 常见NoSuchBeanDefinitionException:
			 参考： https://blog.csdn.net/jiangchao858/article/details/51586515
		 */
		ApplicationContext ioc =  new AnnotationConfigApplicationContext(AnnoTest.class);
		User user = (User)ioc.getBean("user");
		System.out.println(user);
	}
}
