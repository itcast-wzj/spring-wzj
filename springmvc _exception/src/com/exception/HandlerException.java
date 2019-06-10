package com.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class HandlerException {
	
	/**
	 	1. ��@ExceptionHandler ���εķ�������ο��Լ���Exception���͵Ĳ������ò�����Ӧ�������쳣����
	 	2.@ExceptionHandler ��������β��ܴ�Map, ��ϣ�����쳣��Ϣ����ҳ���ϣ���Ҫʹ��ModelAndView��Ϊ����ֵ
	 	3.@ExceptionHandler ������ǵ��쳣�����ȼ�������
	 	4.@ControllerAdvice�� ����ڵ�ǰHandler ���Ҳ���@ExceptionHandlerע�⴦��ǰ�������ֵ��쳣
	 	  ��ȥ@ControllerAdvice��ǵ�����ȥ����@ExceptionHandler
	 */
	@ExceptionHandler(value= {ArithmeticException.class})
	public ModelAndView testExceptionHandler(Exception ex) {
		ModelAndView mv = new ModelAndView("error");
		//��Exception�浽model�� ��ҳ����ʾ
		mv.addObject("exception", ex);
		return mv;
	}
}
