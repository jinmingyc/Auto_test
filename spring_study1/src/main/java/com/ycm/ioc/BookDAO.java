package com.ycm.ioc;

public class BookDAO implements IBookDAO {

	public String addBook(String bookname) {
		return "添加图书"+bookname+"成功！";
	}

}
