package com.domain;

public class TStudent {
	private int id;
	private String name;
	private int count;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "id:"+this.getId()+",name:"+this.getName()+",count:"+this.getCount();
	}

}
