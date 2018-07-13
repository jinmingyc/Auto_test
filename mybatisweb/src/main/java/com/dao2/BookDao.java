package com.dao2;

import java.util.List;

import com.domain.Book;

public interface BookDao {
	public Book getBookById(int id);
	
	public List<Book> getAllBooks();
	
	public int add(Book book);
	
	public int update(Book book);
	
	public int deleteById(int id);
	
}
