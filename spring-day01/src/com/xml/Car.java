package com.xml;

public class Car {
	private String name;
	
	//NoSuchMethodException: com.xml.Car.<init>()
	//是通过空参构造创建对象
//	public Car() {
//		super();
//	}

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
