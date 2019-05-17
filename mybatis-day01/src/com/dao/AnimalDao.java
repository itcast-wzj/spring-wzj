package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;

import com.bean.Animal;

public interface AnimalDao {
	
	@Insert("insert into animal(name,age) values(#{name},#{age})")
	int addAnimal(Animal animal);
	
	Animal selectById(int id);
	
	List<Animal> selectAll();
}
