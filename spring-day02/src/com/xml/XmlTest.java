package com.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 	使用xml版演示了spring之spel的用法
 * 	@author 王志坚
 *
 */
public class XmlTest {
	public static void main(String[] args) {
		ApplicationContext ioc = new ClassPathXmlApplicationContext("com/xml/test.xml");
		//这样就不需要强转
		Person person = ioc.getBean("person",Person.class);
		System.out.println(person);
		/**
		 * 	Person [
		 * 		//1.spel基本操作
		 * 		name=宝马,
		 * 	    car=Car [name=宝马, list=[List0, List1, List2], map={MapA=This is A, MapB=This is B, MapC=This is C}],
		 *		//2.spel方法调用
		 * 	    method1=3, method2=Startrun……,
		 * 		//3.spel操作符，三目运算符
		 * 	    operator1=true, operator2=4, operator3=false, 
		 * 		//4.spel在map,list中取值
		 * 		map_str=This is A, list_str=List0]
		 * 
		 */
	}
}
