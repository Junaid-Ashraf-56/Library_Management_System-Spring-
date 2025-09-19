package dao;

import model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
        String sql = "SELECT * FROM books WHERE bookId = ?";
        return jdbcTemplate.queryForObject(sql,new Object[]{id},(rs,rowNum) -> new Book(
                rs.getInt("bookId"),
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
                rs.getInt("bookId"),
                rs.getString("title"),
                rs.getString("author"),
                rs.getInt("isbn"),
                rs.getInt("copies"),
                rs.getBoolean("available")
        )));
    }

    @Override
    public void updateBook(Book book) {
        String sql = "UPDATE books SET title = ?, author = ?, isbn = ?, copies = ?, available = ? WHERE bookId = ?";
        jdbcTemplate.update(sql,
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn(),
                book.getCopies(),
                book.isAvailable(),
                book.getBookId()
        );
    }


    @Override
    public void deleteBook(int id) {
        String sql = "DELETE FROM books WHERE isbn = ?";
        jdbcTemplate.update(sql,id);
    }

    public Book getBookByIsbn(int isbn) {
        String sql = "SELECT * FROM books WHERE isbn = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{isbn}, (rs, rowNum) -> {
                Book book = new Book();
                book.setBookId(rs.getInt("bookId"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setIsbn(rs.getInt("isbn"));
                book.setCopies(rs.getInt("copies"));
                book.setAvailable(rs.getBoolean("available"));
                return book;
            });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    @Override
    public Book getBookByTitle(String title) {
        String sql = "SELECT * FROM books WHERE title LIKE ?";
        String searchPattern = "%" + title + "%";

        return jdbcTemplate.queryForObject(sql, new Object[]{searchPattern}, (rs, rowNum) -> new Book(
                rs.getInt("bookId"),
                rs.getString("title"),
                rs.getString("author"),
                rs.getInt("isbn"),
                rs.getInt("copies"),
                rs.getBoolean("available")
        ));
    }

}
