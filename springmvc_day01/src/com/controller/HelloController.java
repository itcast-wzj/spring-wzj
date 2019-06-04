package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
	
	/**
	 * 1. 使用@RequestMapping 注解来映射请求的URL
	 * 2. 返回值会通过视图解析器解析为实际的物理视图, 对于InternalResourceViewResolver视图解析器,会做如下解析
	 * 	     通过prefix+返回值+后缀   这样的方式得到实际的物理视图，然后做转发操作(只有服务器内部转发才能访问WEB-INF下面的内容,通过浏览器也可以得知是一个转发操作【地址栏没变】)
	 * 	  /WEB-INF/view/success.jsp
	 * @return
	 */
	@RequestMapping("/hello")
	public String hello() {
		System.out.println("hello world");
		return "success";
	}
}
