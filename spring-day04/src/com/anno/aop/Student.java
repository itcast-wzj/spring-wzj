package com.anno.aop;

//目标对象: Target Object
public class Student {
	private String name;
	private int  age;
	
	public String getName() {
		System.out.println("getName():"+name);
//		int a = 10/0;
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + "]";
	}
	
	
}
