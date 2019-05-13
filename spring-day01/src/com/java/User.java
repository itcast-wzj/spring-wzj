package com.java;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class User {
	private String name;
	
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
	
	@PreDestroy //死亡之后
	public void destroy() {
		System.out.println("User destroy()……");
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", car=" + car + "]";
	}
	
	
}
