package com.dao;

import java.util.List;

import com.bean.Person;

public interface PersonMapper {
	
	int addUser(Person person);
	
	Person selectById(int id);
	
	List<Person> selectAll();
}
