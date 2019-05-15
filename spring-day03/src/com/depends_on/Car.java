package com.depends_on;

public class Car {
	private String name;
	
	

	public Car() {
		super();
		System.out.println("Car()……");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		System.out.println("Car.setName()……");
	}

	@Override
	public String toString() {
		return "Car [name=" + name + "]";
	}
	
}
