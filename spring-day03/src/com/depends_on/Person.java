package com.depends_on;


public class Person {
	private String name;
	private Car car;
	
	public Person() {
		super();
		System.out.println("Person()....");
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
		System.out.println("Person.setName()....");
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
		System.out.println("Person.setCar()...");
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", car=" + car + "]";
	}
}
