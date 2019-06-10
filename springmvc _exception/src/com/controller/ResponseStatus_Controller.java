package com.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.exception.UserNameNotMatchPasswordException;

@Controller
public class ResponseStatus_Controller {
	
	@RequestMapping("/testResponseStatus")
	public String testResponseStatus(@RequestParam("i") Integer i) {
		if(i == 10) {
			//��������׳����쳣�Ͳ�������ִ��
			throw new UserNameNotMatchPasswordException();
		}
		System.out.println("testResponseStatus");
		return "error";
	}
	
	/**
		@ResponseStatus�ڷ�����������
		���۸÷������Ƿ����쳣�����᷵��һ����ָ�����쳣ҳ���ǣ�����Ϊ��ָ����HttpStatus.FORBIDDEN,reason="����@ResponseStatus�ڷ�����
		���û���쳣�Ļ��������������ǻ�ִ�е� ������
	 */
	@ResponseStatus(value=HttpStatus.FORBIDDEN,reason="����@ResponseStatus�ڷ�����")
	@RequestMapping("/testResponseStatus2")
	public String testResponseStatus2(@RequestParam("i") Integer i) {
		if(i == 10) {
			//��������׳����쳣�Ͳ�������ִ��
			throw new UserNameNotMatchPasswordException();
		}
		System.out.println("testResponseStatus2");
		return "error";
	}
}
