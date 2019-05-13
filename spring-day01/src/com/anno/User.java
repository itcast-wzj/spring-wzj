package com.anno;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
public class User {
	/**
	 *    基本数据类型注入用 @value  (特例String也是用这个) 
	 *    引用数据类型注入用@autoWired
	 *    注解好像都是以set注入为属性赋值
	 */
	@Value("wzj")
	private String name;
	
	@Autowired
	private Car car;
	
	@PostConstruct //出生之后
	public void init() {
		System.out.println("User init()……");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	@PreDestroy //死亡之前
	public void destory() {
		System.out.println("User destory()……");
	}
	
	@Override
	public String toString() {
		return "User [name=" + name + ", car=" + car + "]";
	}
	
}
