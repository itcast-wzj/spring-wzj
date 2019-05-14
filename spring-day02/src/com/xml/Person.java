package com.xml;

public class Person {
	//1.spel的基本使用
	private String name;
	private Car car;
	//2.spel的方法调用
	private String method1;
	private String method2;
	//3.spel的运算符和三目运算符 
	private boolean operator1;
	private int operator2;
	private boolean operator3;
	//4.springEl 操作List,Map集合取值
	private String map_str;
	private String list_str;
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
	public String getMethod1() {
		return method1;
	}
	public void setMethod1(String method1) {
		this.method1 = method1;
	}
	public String getMethod2() {
		return method2;
	}
	public void setMethod2(String method2) {
		this.method2 = method2;
	}
	public boolean isOperator1() {
		return operator1;
	}
	public void setOperator1(boolean operator1) {
		this.operator1 = operator1;
	}
	
	public int getOperator2() {
		return operator2;
	}
	public void setOperator2(int operator2) {
		this.operator2 = operator2;
	}
	public boolean isOperator3() {
		return operator3;
	}
	public void setOperator3(boolean operator3) {
		this.operator3 = operator3;
	}
	
	public String getMap_str() {
		return map_str;
	}
	public void setMap_str(String map_str) {
		this.map_str = map_str;
	}
	public String getList_str() {
		return list_str;
	}
	public void setList_str(String list_str) {
		this.list_str = list_str;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", car=" + car + ", method1=" + method1 + ", method2=" + method2
				+ ", operator1=" + operator1 + ", operator2=" + operator2 + ", operator3=" + operator3 + ", map_str="
				+ map_str + ", list_str=" + list_str + "]";
	}
	
}
