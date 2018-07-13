package com.ycm.aop.exm1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("IOCBeans.xml");
		HelloWorld hw1=(HelloWorld)ctx.getBean("helloWorldImpl1");
		HelloWorld hw2=(HelloWorld)ctx.getBean("helloWorldImpl2");
		
		
		hw1.printHelloWorld();
		hw1.doPrint();
		
		hw2.printHelloWorld();
		hw2.doPrint();
		

	}

}
