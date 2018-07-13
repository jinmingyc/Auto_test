package com.dao;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.domain.Book;

public class BookDaoTest {
	
	@Test
	public void testGetBookById() {
		BookDao dao = new BookDao();
		
		Book book=dao.getBookById(1);
		System.out.println(book);
		
		Assert.assertNotNull(book);
	}

	
}
