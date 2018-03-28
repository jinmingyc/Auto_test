package com.test.fastjsontest;

import java.awt.List;
import java.util.ArrayList;

public class Group {
	
	private Long Id;
	private String Name;
	private ArrayList<User> users = new ArrayList<User>();
	
	
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	
	public ArrayList<User> getUsers() {
		// TODO Auto-generated method stub
		return users;
	}
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
	public void addUser(User user) {
		// TODO Auto-generated method stub
		this.users.add(user);
		
	}
	
	

}
