package com.dao2;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.domain.Book;
import com.util.BatisUtil;

public class BookDaoImpl implements BookDao {

	public Book getBookById(int id) {
		SqlSession session = null;
		try {
			session = BatisUtil.createSqlSession();
			BookDao dao = session.getMapper(BookDao.class);
			Book book=dao.getBookById(id);
			session.commit();
			return book;
		} finally {
			//BatisUtil.closeSession(session);
		}
	}

	public List<Book> getAllBooks() {
		SqlSession session =null;
		try {
			session = BatisUtil.createSqlSession();
			BookDao dao = session.getMapper(BookDao.class);			
			return dao.getAllBooks();
			
		} finally {
			//BatisUtil.closeSession(session);
		}
		
		
	}

	public int add(Book book) {
		SqlSession session = null;
		try {
			session = BatisUtil.createSqlSession(true);
			BookDao dao = session.getMapper(BookDao.class);
			return dao.add(book);
		} finally {
			BatisUtil.closeSession(session);
		}
	}

	public int update(Book book) {
		SqlSession session = null;
		try {
			session = BatisUtil.createSqlSession(true);
			BookDao dao = session.getMapper(BookDao.class);
			return dao.update(book);
		} finally {
			BatisUtil.closeSession(session);
		}
	}

	public int deleteById(int id) {
		// TODO Auto-generated method stub
		SqlSession session =null;
		try {
			session = BatisUtil.createSqlSession();
			BookDao dao = session.getMapper(BookDao.class);
			return dao.deleteById(id);
		} finally {
			BatisUtil.closeSession(session);
		}
		
	}

}
