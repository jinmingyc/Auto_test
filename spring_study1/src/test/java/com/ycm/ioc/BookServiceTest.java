package com.ycm.ioc;

import org.testng.annotations.Test;

import com.ycm.ioc.constrbeansbyannotation.BookService;

public class BookServiceTest {

	@Test
	public void testStoreBook() {

		com.ycm.ioc.constrbeansbyannotation.BookService bookService = new BookService();
		bookService.storeBook("《第一本书》");

	}

}
