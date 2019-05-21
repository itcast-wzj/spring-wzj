package com.bean;

import org.springframework.stereotype.Component;

/**
 *       为什么这个bean没有@Component,在测试的时候也可以使用
 *       因为你是自己new了一个，没有交给spring去管理，
 *       不要迷失了本质，以为什么都要加@compoent
 * @author 王志坚
 *
 */

@Component
public class User {
	private int id;
	private String username;
	private String password;
	
	public User() {
		super();
	}
	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + "]";
	}
}	
