package com.instance_beans;

/**
 * 静态工厂方法
 * @author 王志坚
 *
 */
public class Person {
	private static Person person = new Person();
	
	public static Person createPerson() {
		return person;
	}
}
