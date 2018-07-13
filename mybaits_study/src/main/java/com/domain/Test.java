package com.domain;

public class Test {
	private int buyer;
	private int pro;
	private int count;
	public int getBuyer() {
		return buyer;
	}
	public void setBuyer(int buyer) {
		this.buyer = buyer;
	}
	public int getPro() {
		return pro;
	}
	public void setPro(int pro) {
		this.pro = pro;
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
		//return super.toString();
		return this.getBuyer()+"****"+this.getPro()+"*****"+this.getCount();
	}
	
	
}
