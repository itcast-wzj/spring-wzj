package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bean.User;
import com.mapper.UserMapper;

//如果项目中没有junit,记得右击添加junit的库
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MainTest {
	
	@Autowired
	private UserMapper mapper;
	
	//就是你没注入到容器中,所以这里获取不到
	//NoSuchBeanDefinitionException: No matching bean of type [com.bean.User] found for dependency: expected at least 1 bean which qualifies as autowire candidate for this dependency
	
	/**
	 * @Autowired 是找容器中有没有与之配对的类型
	 *      容器中有该类型,就自动匹配上了
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
