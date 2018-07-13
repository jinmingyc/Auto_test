package dao;

import com.tax.dao.BookDao;
import com.tax.dao.BookDaoImpl;
import com.tax.model.Book;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * BookDaoImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>03/15/2018</pre>
 */
public class BookDaoImplTest {

    BookDao dao;

    @Before
    public void before() throws Exception {
        dao = new BookDaoImpl();
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getBookById(int id)
     */
    @Test
    public void testGetBookById() throws Exception {
        System.out.println(dao.getBookById(3));
    }

    /**
     * Method: getAllBooks()
     */
    @Test
    public void testGetAllBooks() throws Exception {
        for (Book book:dao.getAllBooks()) {
            System.out.println(book);
        }
    }

    /**
     * Method: add(Book book)
     */
    @Test
    public void testAdd() throws Exception {
        Book book=new Book();
        book.setTypename("计算机");
        book.setState("未借出");
        book.setTitle("Spring 入门到放弃");
        book.setPrice(23.5);
        Assert.assertEquals(1,dao.add(book));
    }

    /**
     * Method: update(Book book)
     */
    @Test
    public void testUpdate() throws Exception {
        Book book=dao.getBookById(50);
        book.setTitle("从入门到精通");
        Assert.assertEquals(1,dao.update(book));
    }

    /**
     * Method: deleteById(int id)
     */
    @Test
    public void testDeleteById() throws Exception {
        Assert.assertEquals(1,dao.deleteById(50));
    }


} 
