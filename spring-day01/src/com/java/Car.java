package com.java;

public class Car {
	private String name;

	//通过带参构造为属性赋值
	public Car(String name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		return "Car [name=" + name + "]";
	}
	
}
