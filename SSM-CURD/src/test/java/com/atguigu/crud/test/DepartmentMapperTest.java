package com.atguigu.crud.test;

import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.atguigu.crud.bean.Department;
import com.atguigu.crud.bean.Employee;
import com.atguigu.crud.dao.DepartmentMapper;
import com.atguigu.crud.dao.EmployeeMapper;

/*
 	@ContextConfigration 是在spring-test.jar中 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:applicationContext.xml"})
public class DepartmentMapperTest {
	
	@Autowired
	DepartmentMapper departmentMapper;
	
	@Autowired 
	EmployeeMapper employeeMapper;
	
	/*
	    这里拿到的其实是一个批量sqlSession
	    因为SqlSessionTemplate是SqlSession接口的实现类
	    而@Autowired默认是按类型注入
	    所以是SqlSession sqlSession = new SqlSessionTemplate();
	 */
	@Autowired
	SqlSession sqlSession;
	
	@Test
	public void testDeptCRUD() {
		/*
		 	之前没有日志输出，我想看到sql的执行, 就加了log4j.jar和log4j.properties(去下载的源码下面搜索)
		 	执行的时候输出大片日志，我把输出级别改成ERROR就没了，要看的时候改成DEBUG
		 */
		//有选择性（Selective）的插入,就是如果某个值为空,就不插入
		//我们这里为什么将id为空呢？ 因为数据库中我设置了主键
		departmentMapper.insertSelective(new Department(null, "开发部"));
		departmentMapper.insertSelective(new Department(null, "测试部"));
	}
	
	@Test
	public void testEmpCRUD() {
//		for(int i=0; i<100; i++) {
//			employeeMapper.insertSelective(new Employee(empId, empName, gender, email, deptId));
//		}
		//用这种批量，完成的效率比上面高很多,要使用批量, 注意用批量sqlSession获取Mapper,不要直接用Mapper
		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		for(int i=0; i<100; i++) {
			String uid = UUID.randomUUID().toString().substring(0, 5)+i;
			mapper.insertSelective(new Employee(null, uid, "M", uid+"@163.com", 1));
		}
		System.out.println("批量完成");
	}
}
