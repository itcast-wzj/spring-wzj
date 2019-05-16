package com.anno.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect //切面类
public class Advice {
	
	//定义切点
	@Pointcut("execution(* com.anno.aop.Student.getName(..))")
	public void getName() {}
	
	//将通知应用到目标对象的连接点上
	@Before("Advice.getName()")
	public void beforeAdvice() {
		System.out.println("前置通知");
	}
	
	@AfterReturning("Advice.getName()") //有异常不会执行
//	@After("Advice.getName()")   //有异常也会执行
	public void afterAdvice() {
		System.out.println("后置通知");
	}
	
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("这是环绕通知之前的部分");
		Object proceed = joinPoint.proceed();//调用目标方法
		System.out.println("这是环绕通知之后的部分");
		return proceed;
	}
	
	@AfterThrowing("Advice.getName()")
	public void afterException() {
		System.out.println("异常通知：出现异常了");
	
	}
}
