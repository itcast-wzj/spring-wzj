package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ExceptionHandler_Controller {
	
//	@ExceptionHandler(value= {RuntimeException.class})
//	public  String runtimeException(Exception ex) {
//		System.out.println("���쳣�ˣ�: "+ex.getMessage());
//		return "error";
//	}
//	
//	@ExceptionHandler(value= {ArithmeticException.class})
//	public  ModelAndView handlerException(Exception ex) {
//		System.out.println("���쳣�ˣ�: "+ex.getMessage());
//		//���쳣��Ϣ��ʾ��ҳ����
//		ModelAndView mv = new ModelAndView("error");
//		mv.addObject("exception", ex);
//		return mv;
//	}

	@RequestMapping("/testExceptionHandler")
	public String testExceptionHandler(@RequestParam("i") Integer i) {
		System.out.println("result: "+ 10 / i);
		return "success";
	}
}
