package com.xml;

public class User {
	private String name;
	
	private Car car;
	
	public void init() {
		System.out.println("User init() 初始化");
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
	
	public void destory() {
		System.out.println("User destory() 销毁");
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", car=" + car + "]";
	}
	
	
}
