package com.domain;

public class Book {
	private int id;
	private String title;
	private String typename;
	private double price;
	private String state;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTypeName() {
		return typename;
	}
	public void setTypeName(String typeName) {
		this.typename = typeName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		
		return "Book{" +
                "id=" + this.id +
                ", title='" + title + '\'' +
                ", typename='" + typename + '\'' +
                ", price=" + price +
                ", state='" + state + '\'' +
                '}';
	}
	
	
	
	
	

}
