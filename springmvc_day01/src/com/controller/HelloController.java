package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
	
	/**
	 * 1. ʹ��@RequestMapping ע����ӳ�������URL
	 * 2. ����ֵ��ͨ����ͼ����������Ϊʵ�ʵ�������ͼ, ����InternalResourceViewResolver��ͼ������,�������½���
	 * 	     ͨ��prefix+����ֵ+��׺   �����ķ�ʽ�õ�ʵ�ʵ�������ͼ��Ȼ����ת������(ֻ�з������ڲ�ת�����ܷ���WEB-INF���������,ͨ�������Ҳ���Ե�֪��һ��ת����������ַ��û�䡿)
	 * 	  /WEB-INF/view/success.jsp
	 * @return
	 */
	@RequestMapping("/hello")
	public String hello() {
		System.out.println("hello world");
		return "success";
	}
}
