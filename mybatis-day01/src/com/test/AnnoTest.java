package com.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.bean.Animal;
import com.dao.AnimalDao;

public class AnnoTest {
	public static void main(String[] args) {
		String resource = "config/mybatis/mybatis-config.xml";
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = factory.openSession();//可以设置自动提交
		AnimalDao animalDao = sqlSession.getMapper(AnimalDao.class);
		int count = animalDao.addAnimal(new Animal("旺财", 13));
		System.out.println(count);
		//没有设置自动提交,所以需要手动去提交!!!
		sqlSession.commit();
	}
}
