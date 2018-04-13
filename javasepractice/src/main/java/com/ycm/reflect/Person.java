package com.ycm.reflect;

public class Person implements InterFace {

	private String id;
	private String name;
	public String age;

	public void read() {
		// TODO Auto-generated method stub

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public Person() {

	}

	public Person(String id) {
		this.id = id;
	}

	public Person(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public Person(String id, String name, String age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}
	
	public String toString() {
		return "id"+this.id+"name"+this.name+"age"+this.age;
		
	}

	public static void update() {
		
	}
}
