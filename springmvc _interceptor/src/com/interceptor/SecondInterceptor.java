package com.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class SecondInterceptor implements HandlerInterceptor{
	
	/**
	 	目标方法执行之前执行
	 	如果返回值为false, 则不会调用后续的拦截器和目标方法
	 	如果返回值为true, 则继续调用后续的拦截器和目标方法
	 	可以用于权限，日志，事务等
	 */
	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		System.out.println("【SecondInterceptor】 preHandle" );
		return false;
	}
	
	/**
	 	目标方法执行之后，视图渲染之前执行
	 	可以对请求域中的属性或试图做出修改【因为参数有个ModelAndView】
	 */
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		System.out.println("【SecondInterceptor】 postHandle" );
		
	}

	/**
	  	渲染试图之后被调用
	 	用于释放资源
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("【SecondInterceptor】 afterCompletion" );
	}

}
