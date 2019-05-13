package com.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlTest {
	public static void main(String[] args) {
//		ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
		/**
		 * 1.ClassPathXmlApplicationContext是在加载application.xml的时候就已经
		 *         将容器中的bean给实例化好了，前提要是scope=singleton,而不是propotype
		 *         
		 * 2.默认是在classpath下找，如果你把他放com.xml包下，就这样指定com/xml/applicationContext.xml
		 * 
		 * 3.ClassPathXmlApplicationContext可以加载多个xml文件
		 * 
		 * 4.为什么注入bean默认是单列模式? 注入的bean，通常是组件级别的对象(不用频繁创建)
		 */
		ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
		//为了证明是单例
		User bean = ioc.getBean(User.class);
		User bean2 = ioc.getBean(User.class);
		System.out.println(bean == bean2);
		//为了能够看到销毁的样子上面的applicationContext变成ClassPathXmlApplicationContext
		ioc.destroy();
	}
}
