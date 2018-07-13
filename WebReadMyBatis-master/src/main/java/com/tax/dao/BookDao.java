package com.tax.dao;

import com.tax.model.Book;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
public interface BookDao {
    @Select("select id, title, typename, price, state from book where id=#{id}")
    public Book getBookById(int id);

    @Select("select * from book")
    public List<Book> getAllBooks();

    @Insert("insert into book (id, title, typename, price, state) values(seq_book_id.nextval, #{title}, #{typename}, #{price}, #{state})")
    public int add(Book book);

    @Update("update book set title=#{title},typename=#{typename},price=#{price},state=#{state} where id=#{id}")
    public int update(Book book);

    @Delete("delete from book where id=#{id}")
    public int deleteById(int id);
}
