package com.instance_beans;

import java.util.ServiceLoader;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试类
 * @author 王志坚
 *
 */
//这里创建了applicationContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"person.xml"}) //相对路径
public class BeanTest {
//	@Resource
//	private Person person;
	
	@Autowired
	private ServiceLocator  serviceLocator;
	
	@Autowired
	private ClientService clientService;
	
	@Test
	public void fun1() {
		System.out.println(serviceLocator);
		System.out.println(clientService);
	}
	
	
}
