package com.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.bean.User;

public class XmlTest {
	public static void main(String[] args) {
		String resource = "config/mybatis/mybatis-config.xml";
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = factory.openSession(true);//设置自动提交
		/**
		 * xml方式:不需要定义dao接口,也可以执行方法,只需定位到该xml中的方法
		 * config.mapper.UserMapper.insertUser
		 * config.mapper.UserMapper是命名空间,xml方式可以随便定义,就像包名一样,
		 * insertUser就像下面的类一样
		 */
		int count = sqlSession.insert("config.mapper.UserMapper.insertUser", new User("dyt",20));
		System.out.println(count);
	}
}
