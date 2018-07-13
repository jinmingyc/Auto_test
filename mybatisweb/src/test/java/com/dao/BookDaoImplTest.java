package com.dao;

import static org.testng.Assert.assertEquals;

import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.dao2.BookDaoImpl;
import com.domain.Book;

public class BookDaoImplTest {
	BookDaoImpl dao = null;

	@BeforeMethod
	public void before() {
		dao = new BookDaoImpl();
	}

	@Test
	public void testGetBookById() {

		Book book = dao.getBookById(2);
		System.out.println(book);
		Assert.assertNotNull(book);
	}

	@Test
	public void testGetAllBooks() {
		for (Book book : dao.getAllBooks()) {
			System.out.println(book);
			Assert.assertNotNull(book);
		}
	}

	@Test
	public void testAdd() {
		Book book = new Book();
		// book.setId(6);
		book.setTitle("手机2");
		book.setTypeName("计算机");
		book.setPrice(125);
		book.setState("已解除");
		Assert.assertEquals(1, dao.add(book));
		// assertEquals(dao.getBookById(4),book);
	}

	@Test
	public void testUpdate() {
		Book book = dao.getBookById(1);
		book.setTitle("新名称");
		Assert.assertEquals(1, dao.update(book));
	}

	@Test
	public void testDelete() {
		Assert.assertEquals(1, dao.deleteById(2));
	}
}
