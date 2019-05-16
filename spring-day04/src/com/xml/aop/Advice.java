package com.xml.aop;

import org.aspectj.lang.ProceedingJoinPoint;

//这是通知类,包含很多通知
public class Advice {
	
	public void beforeAdvice() {
		System.out.println("前置通知");
	}
	
	
	public void afterAdvice() {
		System.out.println("后置通知");
	}
	
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("这是环绕通知之前的部分");
		Object proceed = joinPoint.proceed();//调用目标方法
		System.out.println("这是环绕通知之后的部分");
		return proceed;
	}
	
	public void afterException() {
		System.out.println("异常通知：出现异常了");
	}
}
