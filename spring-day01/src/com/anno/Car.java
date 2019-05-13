package com.anno;

import java.beans.ConstructorProperties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class Car {
	@Value("奔驰")
	private String name;
	
	//这里使用@ConstructorProperties如果不用空参构造就报错
	public Car() {
		super();
		// TODO Auto-generated constructor stub
	}

	//通过带参构造为属性赋值
	@ConstructorProperties("name")
	public Car(String name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		return "Car [name=" + name + "]";
	}
	
}
