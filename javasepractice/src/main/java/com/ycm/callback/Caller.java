package com.ycm.callback;

public class Caller {
	private MyCallInterface callInterface;
	
	public Caller() {};
	
	public void setCallFuc(MyCallInterface callInterFace) {
		this.callInterface=callInterFace;		
	}
	
	public void call() {
		callInterface.printName();
	}

}
