package com.ycm.mapping;

import java.util.List;

import com.ycm.domain.BookType;

/**
 * 图书类型数据访问接口
 *
 */

public interface BookTypeDAO {
	/*
     * 获得所有图书类型
     */
	public List<BookType> getAllBookType();
}
