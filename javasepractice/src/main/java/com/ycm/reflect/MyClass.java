package com.ycm.reflect;

import java.io.Serializable;
import java.lang.reflect.Modifier;
import java.lang.reflect.TypeVariable;

class MyClass<T> implements Cloneable, Serializable {
	private int id = -1;
	private String name = "Unknown";

	public MyClass(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public String toString() {
		return "MyClass: id=" + this.id + ", name=" + this.name;
	}
}

