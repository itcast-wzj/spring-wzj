package com.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class HandlerException {
	
	/**
	 	1. 在@ExceptionHandler 修饰的方法的入参可以加入Exception类型的参数，该参数对应发生的异常对象
	 	2.@ExceptionHandler 方法的入参不能传Map, 若希望把异常信息传到页面上，需要使用ModelAndView作为返回值
	 	3.@ExceptionHandler 方法标记的异常有优先级的问题
	 	4.@ControllerAdvice： 如果在当前Handler 中找不到@ExceptionHandler注解处理当前方法出现的异常
	 	  则将去@ControllerAdvice标记的类中去查找@ExceptionHandler
	 */
	@ExceptionHandler(value= {ArithmeticException.class})
	public ModelAndView testExceptionHandler(Exception ex) {
		ModelAndView mv = new ModelAndView("error");
		//将Exception存到model中 在页面显示
		mv.addObject("exception", ex);
		return mv;
	}
}
