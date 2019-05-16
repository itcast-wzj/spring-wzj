# 一、spring中的Aop

## 1.1、Aop中的一些概念

- **通知(advice)**就是你想要的功能，也就是上面说的 安全，事物，日志等。你给先定义好把，然后在想用的地方用一下。
- **连接点(joinPoint)** 就是spring允许你使用通知的地方，那可真就多了，基本每个方法的前，后
- **切入点(pointCut)**: **就是连接点的表达式**,因为目标对象中可能有几个方法,那可能就有十几个连接点
  但是我不想让所有连接点都应用通知,这时就使用切点表达式来**筛选**目标对象的连接点
- **切面（Aspect）**切面是通知和切入点的结合。现在发现了吧，没连接点什么事情，连接点就是为了让你好理解切点，搞出来的，明白这个概念就行了。通知说明了干什么和什么时候干（什么时候通过方法名中的before,after，around等就能知道）
  而切入点说明了在哪干（指定到底是哪个方法），这就是一个完整的切面定义

- 其他的**引入,目标,代理,织入**等概念参考一下博客:

- **https://blog.csdn.net/changudeng1992/article/details/80625134**

- **https://baike.baidu.com/item/AOP/1332219?fr=aladdin** aop百度百科其实也讲的蛮好
## 1.2、实现AOP的两种方式

### 1.2.1、前提

**jar包:**

 - spring-aop.jar 
 - spring-aspects.jar
 - **第三方jar**
 - aopalliance.jar
 - aspectj.weaver.jar

### 1.2.2、基于xml

#### 第一步：

**创建一个目标对象student**

```java
package com.xml.aop;

//目标对象: Target Object
public class Student {
	private String name;
	private int  age;
	
	public String getName() {
		System.out.println("getName:"+name);
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + "]";
	}
}

```



#### 第二步

**创建一个切面类**

```java
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

```

#### 第三步

**在xml中进行配置**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
    xmlns:aop="http://www.springframework.org/schema/aop"
    xmlns:p="http://www.springframework.org/schema/p"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans.xsd 
    http://www.springframework.org/schema/aop 
    http://www.springframework.org/schema/aop/spring-aop.xsd ">
    
	<!--1.将目标对象Student注入到容器中  -->
	<bean id="student" class="com.xml.aop.Student" p:name="wzj" p:age="20" />
	
	<!-- 将切面类注入容器 -->
	<bean id="myAspect" class="com.xml.aop.Advice"/>
    <!--进行配置 -->
   	<aop:config>
		<aop:aspect ref="myAspect">
			<!--
				切入点其实就是筛选目标对象(Student)的连接点
				那什么又是连接点?
				其实就是可以用通知的地方，可以简单理解为目标对象中的方法就是一个连接点
			  -->
			<aop:pointcut expression="execution(* com.xml.aop.Student.getName(..))" id="cut"/>
			<!--指定切面类中什么方法作为前置通知方法,并应用在指定的切点上 -->
			<aop:before method="beforeAdvice" pointcut-ref="cut"/>
			<aop:after-returning method="afterAdvice" pointcut-ref="cut"/>
		</aop:aspect>   	
   	</aop:config>
</beans>
```

#### 第四步

**进行测试**

```java
package com.xml.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlTest {
	public static void main(String[] args) {
		ApplicationContext ioc = new ClassPathXmlApplicationContext("com/xml/aop/beans.xml");
		Student student = ioc.getBean("student",Student.class);
//		System.out.println(student.getName()); 注意！！！
		student.getName();
	}
}

```

### 1.2.3、基于@Aspect注解

**其实懂了xml的配置,这个也就是依葫芦画瓢**

#### 第一步

**目标对象还是student**

#### 第二步 

**切面类**

```java

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

```

####  第三步

- **在xml中**

- 开启：自动代理 	<aop:aspectj-autoproxy/>

- **将目标对象 和 切面类 注入到容器中**

```xml
<!--开启自动代理-->
	<aop:aspectj-autoproxy/>
	
   <!--目标对象  -->
   <bean id="student" class="com.anno.aop.Student" p:name="wzj" p:age="20"/>
   <!--切面类 -->
   <bean class="com.anno.aop.Advice"></bean>
```

#### 第四步

**测试**

```java
package com.anno.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlTest {
	public static void main(String[] args) {
		ApplicationContext ioc = new ClassPathXmlApplicationContext("com/anno/aop/beans.xml");
		Student student = (Student) ioc.getBean("student");
		/*
		     后置通知是在方法运行完后执行!!!!
		     System.out.println(student.getName());
		     其实在这条输出语句执行之前，getName()方法已经执行完了,你这个只是打印方法的返回值
		      所以你看到的结果是
		         	前置通知
		            后置通知
		           	打印结果
		     你还傻乎乎的感觉,后置通知不是在方法执行完之后吗，怎么这么奇怪 哈哈哈哈
		  
		  正确的方法是不用输出语句,在调用方法的内部打印一下，结果就很清晰了
		 			前置通知
		 			打印结果
		            后置通知
		 */
		//System.out.println(student.getName());  //注意 ！！！！
		student.getName(); 
		           
	}
}

```




