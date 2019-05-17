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
		SqlSession sqlSession = factory.openSession(true);//�����Զ��ύ
		/**
		 * xml��ʽ:����Ҫ����dao�ӿ�,Ҳ����ִ�з���,ֻ�趨λ����xml�еķ���
		 * config.mapper.UserMapper.insertUser
		 * config.mapper.UserMapper�������ռ�,xml��ʽ������㶨��,�������һ��,
		 * insertUser�����������һ��
		 */
		int count = sqlSession.insert("config.mapper.UserMapper.insertUser", new User("dyt",20));
		System.out.println(count);
	}
}
