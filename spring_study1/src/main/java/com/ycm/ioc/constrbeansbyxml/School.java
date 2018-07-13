package com.ycm.ioc.constrbeansbyxml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class School {
	
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("IOCBeans.xml");
		
	//y有参构造方法-参数名
		 Person jack=ctx.getBean("stuJack",Student.class);
	//y有参构造方法-参数索引
		 Person tom=ctx.getBean("stuTom",Student.class);
		System.out.println(tom);
		System.out.println(jack);
		//通过属性构造
		Address zhuhai=ctx.getBean(Address.class);
		System.out.println(zhuhai);
		
		//对象引用
		Person rose=ctx.getBean("rose",Student.class);
		System.out.println(rose);
	}

}
