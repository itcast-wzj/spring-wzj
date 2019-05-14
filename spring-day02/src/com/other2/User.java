package com.other2;

import java.util.List;
import java.util.Map;
import java.util.Properties;

public class User {
	/*
	 复杂类型注入：
	 	list
	 	map
	 	properties
	 */
	private Car car;
	private List<Object> lists;
	private Map<String, Object> maps;
	private Properties prop;
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	public List<Object> getLists() {
		return lists;
	}
	public void setLists(List<Object> lists) {
		this.lists = lists;
	}
	public Map<String, Object> getMaps() {
		return maps;
	}
	public void setMaps(Map<String, Object> maps) {
		this.maps = maps;
	}
	public Properties getProp() {
		return prop;
	}
	public void setProp(Properties prop) {
		this.prop = prop;
	}
	
	@Override
	public String toString() {
		return "User [car=" + car + ", lists=" + lists + ", maps=" + maps + ", prop=" + prop + "]";
	}
		
}
