package com.ycm.ioc.constrbeansbyclass;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {

	public static void main(String[] args) {
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
	
	UseFuctionService useFuctionService = context.getBean(UseFuctionService.class);
	
	System.out.println(useFuctionService.sayHello("java config"));
	
	context.close();

	}

}
