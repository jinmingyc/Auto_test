package com.ycm.ioc.constrbeansbyxml;

public class Student extends Person{

	public int height;
	
	public Student(String name,int height) {
		this.name = name;
		this.height = height;
	}
	
	public Student(String name,int height,Address address) {
		this.name=name;
		this.height=height;
		this.address=address;
	}
	
	public void init() {
		System.out.println("this is init");
	}
	
	public void destroy() {
		System.out.println("this is destroy");
	}
	
	@Override
	public String toString() {
		 return "Student{" + "height=" + height+",name="+name +"address="+address+'}';
	}
}
