package com.ycm.ioc.constrbeansbyannotation;

import org.springframework.stereotype.Component;
/**
 * 
 * @author yuanchengming
 *通过注解的方式配置IOC
 */
@Component("bookdaoObj")
public class BookDAO implements IBookDAO {

	public String addBook(String bookname) {
		return "添加图书"+bookname+"成功！";
	}

}
