package com.autowire.xml;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@runWith是junit库里面的
@RunWith(SpringJUnit4ClassRunner.class)//会自动帮我们创建ioc容器 
//@ContextConfiguration(locations = {"classpath:com/autowire/autowired.xml"}) 绝对路径,指明在classpath下，就是有时候是/有时候是.分不清
@ContextConfiguration(locations = {"autowired.xml"}) //相对路径,相对于当前有@ContextConfiguration的这个类 
public class AutoWiredTest {
	@Autowired
	private  ApplicationContext ioc;
	
	@Test
	public void fun1() {
		Customer bean = ioc.getBean("customer",Customer.class);
		System.out.println(bean);
	}
}
