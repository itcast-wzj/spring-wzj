package com.autowire.xml;

public class Customer {
	private Person person;

	public Customer() {
		super();
	}
	
	public Customer(Person person) {
		super();
		this.person = person;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
		System.out.println("setPeson()....");
	}

	@Override
	public String toString() {
		return "Customer [person=" + person + "]";
	}
	
}
