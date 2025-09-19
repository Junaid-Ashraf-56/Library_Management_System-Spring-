package dao;

import model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDAOImpl implements BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAOImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addBook(Book book) {
        String sql = "INSERT INTO books(title,author,isbn,copies,available) VALUES(?,?,?,?,?)";
        jdbcTemplate.update(sql,book.getTitle(),book.getAuthor(),book.getIsbn(),book.getCopies(),book.isAvailable());
    }

    @Override
    public Book getBookById(int id) {
        String sql = "SELECT * FROM books WHERE id = ?";
        return jdbcTemplate.queryForObject(sql,new Object[]{id},(rs,rowNum) -> new Book(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("author"),
                rs.getInt("isbn"),
                rs.getInt("copies"),
                rs.getBoolean("available")
        ));
    }

    @Override
    public List<Book> getAllBook() {
        String sql = "SELECT * FROM books";
        return jdbcTemplate.query(sql,((rs, rowNum) -> new Book(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("author"),
                rs.getInt("isbn"),
                rs.getInt("copies"),
                rs.getBoolean("available")
        )));
    }

    @Override
    public void updateBook(Book book) {
        String sql = "UPDATE books set title = ?,set author = ?,set isbn = ?,set copies = ?,set available = ? WHERE bookId = ?";
        jdbcTemplate.update(sql,book.getTitle(),book.getAuthor(),book.getIsbn(),book.isAvailable());
    }

    @Override
    public void deleteBook(int id) {
        String sql = "DELETE FROM books WHERE id = ?";
        jdbcTemplate.update(sql,id);
    }

    @Override
    public Book getBookByIsbn(int isbn) {
        String sql = "SELECT * FROM books WHERE isbn = ?";
        return jdbcTemplate.queryForObject(sql,new Object[]{isbn},(rs, rowNum) -> new Book(
            rs.getInt("id"),
            rs.getString("title"),
            rs.getString("author"),
            rs.getInt("isbn"),
            rs.getInt("copies"),
            rs.getBoolean("available")
        ));
    }

    @Override
    public Book getBookByTitle(String title) {
        String sql = "SELECT isbn FROM books WHERE title = %?%";
        return jdbcTemplate.queryForObject(sql, new Object[]{title}, (rs, rowNum) -> new Book(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("author"),
                rs.getInt("isbn"),
                rs.getInt("copies"),
                rs.getBoolean("available")
        ));
    }
}
