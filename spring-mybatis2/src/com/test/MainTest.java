package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bean.User;
import com.mapper.UserMapper;

//�����Ŀ��û��junit,�ǵ��һ����junit�Ŀ�
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MainTest {
	
	@Autowired
	private UserMapper mapper;
	
	//������ûע�뵽������,���������ȡ����
	//NoSuchBeanDefinitionException: No matching bean of type [com.bean.User] found for dependency: expected at least 1 bean which qualifies as autowire candidate for this dependency
	
	/**
	 * @Autowired ������������û����֮��Ե�����
	 *      �������и�����,���Զ�ƥ������
	 */
	@Autowired
	private User user;
	
	@Test
	public void selectByupTest() throws Exception {
//		System.out.println(user);
		user.setUsername("wzj");
		user.setPassword("123");
		int  count = mapper.selectByup(user);
		System.out.println(count);
	}
}
