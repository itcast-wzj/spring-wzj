package com.anno;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component //不指定名称,默认在容器中是类名首字母小写 car
public class Car {
	@Value("宝马")
	private String name;
	private Map<String, String> map;
	private List<String> list;
	public Car() {
		super();
		 map = new HashMap<String, String>();
		 map.put("MapA", "This is A");
		 map.put("MapB", "This is B");
		 map.put("MapC", "This is C");
	
		 list = new ArrayList<String>();
		 list.add("List0");
		 list.add("List1");
		 list.add("List2");
	}

	public String carRun(String str) {
		return str+"run……";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	@Override
	public String toString() {
		return "Car [name=" + name + ", list=" + list + ", map=" + map + "]";
	}

}
