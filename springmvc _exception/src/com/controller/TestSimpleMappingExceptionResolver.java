package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestSimpleMappingExceptionResolver {

	@RequestMapping("/TestSimpleMapping")
	public String testSimpleMapping() {
		//这里我故意空指针异常,看simpleMappingExceptionResolver能够捕捉到吗
		String str = null;
		str.length();
		return "success";
	}
	
	@RequestMapping("/TestSimpleMapping2")
	public String testSimpleMapping2() {
		//这里我故意数组下标越界异常,看simpleMappingExceptionResolver能够捕捉到吗
		int[] arr = new int[2];
		System.out.println(arr[2]);
		return "success";
	}
}
