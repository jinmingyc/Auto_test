package com.ycm.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BookService {

	IBookDAO bookDAO;

	public BookService() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("IOCBeans.xml");
		bookDAO = (IBookDAO) ctx.getBean("bookdao");
	}

	public void storeBook(String bookname) {
		System.out.println("图书上架");
		String result = bookDAO.addBook(bookname);
		System.out.println(result);
	}
}
