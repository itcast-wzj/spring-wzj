package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestSimpleMappingExceptionResolver {

	@RequestMapping("/TestSimpleMapping")
	public String testSimpleMapping() {
		//�����ҹ����ָ���쳣,��simpleMappingExceptionResolver�ܹ���׽����
		String str = null;
		str.length();
		return "success";
	}
	
	@RequestMapping("/TestSimpleMapping2")
	public String testSimpleMapping2() {
		//�����ҹ��������±�Խ���쳣,��simpleMappingExceptionResolver�ܹ���׽����
		int[] arr = new int[2];
		System.out.println(arr[2]);
		return "success";
	}
}
