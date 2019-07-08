package com.atguigu.crud.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.atguigu.crud.bean.Employee;
import com.github.pagehelper.PageInfo;

/**
 * 使用Mock,测试请求
 * @author 1
 *
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
//记得用Mock测试，还要导入springmvc的配置文件
@ContextConfiguration(locations= {"classpath:applicationContext.xml","file:src/main/webapp/WEB-INF/dispatcherServlet-servlet.xml"})
public class PageInfoMockTest {
	
	//传入springmvc的ioc
	@Autowired //@AutoWired其实只能注入容器中的bean, 而注入springmvc容器本身需要用@WebApplicationConfigura
	WebApplicationContext context;
	
	//虚拟mvc请求，获取处理结果
	MockMvc mockMvc;
	
	@Before
	public void init() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void testPage() throws Exception {
		//模拟请求拿到返回值
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("pageNum", "1")).andReturn();
		
		//请求成功后，请求域中会有pageInfo,我们可以取出pageInfo进行验证 
		MockHttpServletRequest request = result.getRequest();
		PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
		System.out.println("当前页码："+pageInfo.getPageNum());
		System.out.println("总页码："+pageInfo.getPages());
		System.out.println("总记录数："+pageInfo.getTotal());
		System.out.println("在页面需要连续显示的页码");
		int[] nums = pageInfo.getNavigatepageNums();
		for (int i : nums) {
			System.out.print(" "+i);
		}
		System.out.println();
		
		//获取员工数据
		List<Employee> list = pageInfo.getList();
		for (Employee emp : list) {
			System.out.println("员工id: "+emp.getEmpId()+"\t员工姓名："+emp.getEmpName());
		}
		
		/*
			测试的时候出现一个问题
			javax.servlet.SessionCookieConfig
			Spring4测试的时候， 需要Servlet3.0的支持，导入3.0以上的servlet就行
			<dependency>
			    <groupId>javax.servlet</groupId>
			    <artifactId>javax.servlet-api</artifactId>
			    <!--因为容器中也有  -->
			    <version>3.0.1</version>
			    <scope>provided</scope>
			</dependency>
		 */
	}
}
