package com.ycm.ioc.constrbeansbyclass;

public class UseFuctionService {

	public FunctionService functionService;
	
	public void setFunctionService(FunctionService functionService) {
		this.functionService = functionService;
	}
	
	public String sayHello(String word) {
		return functionService.sayHello(word);
	}
}
