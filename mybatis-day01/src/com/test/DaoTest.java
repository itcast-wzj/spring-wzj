package com.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.bean.Person;
import com.bean.User;
import com.dao.PersonMapper;

public class DaoTest {
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
		PersonMapper dao = sqlSession.getMapper(PersonMapper.class);
		int count = dao.addUser(new Person("wzj",20));
		System.out.println(count);
	}
}
