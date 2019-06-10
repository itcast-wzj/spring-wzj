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
			//如果这里抛出了异常就不会往下执行
			throw new UserNameNotMatchPasswordException();
		}
		System.out.println("testResponseStatus");
		return "error";
	}
	
	/**
		@ResponseStatus在方法上面修饰
		无论该方法中是否有异常，都会返回一个你指定的异常页面是：内容为你指定的HttpStatus.FORBIDDEN,reason="测试@ResponseStatus在方法上
		如果没有异常的话，那条输出语句是会执行的 ！！！
	 */
	@ResponseStatus(value=HttpStatus.FORBIDDEN,reason="测试@ResponseStatus在方法上")
	@RequestMapping("/testResponseStatus2")
	public String testResponseStatus2(@RequestParam("i") Integer i) {
		if(i == 10) {
			//如果这里抛出了异常就不会往下执行
			throw new UserNameNotMatchPasswordException();
		}
		System.out.println("testResponseStatus2");
		return "error";
	}
}
