package com.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	/**
	 	��spring�����е�bean��������springmvc�����е�bean,
	 	�����NoSuchBeanDefinitionException�쳣
	 */
//	@Autowired
//	private UserController controller;

	//��ܶ��ǻ��ڷ����,����ͨ�����캯����������
	public UserService() {
		System.out.println("UserService �޲�");
	}

	
}
