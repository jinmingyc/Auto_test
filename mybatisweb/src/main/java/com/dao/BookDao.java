package com.dao;

import org.apache.ibatis.session.SqlSession;

import com.domain.Book;
import com.util.BatisUtil;

public class BookDao {
	
	public Book getBookById(int id) {
		SqlSession session = BatisUtil.createSqlSession();
		
		Book book = null;
		try {
			book=session.selectOne("com.dao.BookMapper.getBookById",id);
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			BatisUtil.closeSession(session);
		}
		return book;
	}

	public static void main(String [] args) {
		BookDao dao = new BookDao();
		System.out.println("------------------------------"+dao.getBookById(1));
	}
}
