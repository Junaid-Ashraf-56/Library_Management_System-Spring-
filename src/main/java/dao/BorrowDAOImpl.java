package dao;

import model.BorrowRecord;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BorrowDAOImpl implements BorrowDAO{
    public JdbcTemplate jdbcTemplate;

    public BorrowDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(BorrowRecord record) {
        String sql = "INSERT INTO borrow(libraryId,isbn,borrowDate,returnDate) Values(?,?,?,?)";
        jdbcTemplate.update(sql,
                record.getLibraryId(),
                record.getIsbn(),
                record.getBorrowDate(),
                record.getReturnDate());
    }

    @Override
    public BorrowRecord findById(int id) {
        String sql = "SELECT * FROM borrow WHERE borrowId = ?";
        return jdbcTemplate.queryForObject(sql,
                new Object[]{id},(rs, rowNum) -> new BorrowRecord(
                        rs.getInt("borrowId"),
                        rs.getInt("personId"),
                        rs.getInt("isbn"),
                        rs.getDate("borrowDate").toLocalDate(),
                        rs.getDate("returnDate").toLocalDate()
                ));
    }

    @Override
    public BorrowRecord findByPersonAndBook(int personId, int isbn) {
        String sql = "SELECT * FROM borrow WHERE libraryId = ? AND isbn = ?";
        return jdbcTemplate.queryForObject(sql,
                new Object[]{personId,isbn},(rs, rowNum) -> new BorrowRecord(
                        rs.getInt("borrowId"),
                        rs.getInt("libraryId"),
                        rs.getInt("isbn"),
                        rs.getDate("borrowDate").toLocalDate(),
                        rs.getDate("returnDate") != null ? rs.getDate("returnDate").toLocalDate() : null
                ));
    }

    @Override
    public List<BorrowRecord> findByPerson(int personId) {
        String sql = "SELECT * FROM borrow WHERE personId = ?";
        return jdbcTemplate.query(sql,
                new Object[]{personId},(rs, rowNum) -> new BorrowRecord(
                        rs.getInt("borrowId"),
                        rs.getInt("personId"),
                        rs.getInt("isbn"),
                        rs.getDate("borrowDate").toLocalDate(),
                        rs.getDate("returnDate").toLocalDate()
                ));
    }

    @Override
    public List<BorrowRecord> findAll() {
        String sql = "SELECT * FROM borrow";
        return jdbcTemplate.query(sql,
                new Object[]{},(rs, rowNum) -> new BorrowRecord(
                        rs.getInt("borrowId"),
                        rs.getInt("personId"),
                        rs.getInt("isbn"),
                        rs.getDate("borrowDate").toLocalDate(),
                        rs.getDate("returnDate").toLocalDate()
                ));
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM borrow WHERE borrowId = ?";
        jdbcTemplate.update(sql,id);
    }

    @Override
    public void update(BorrowRecord record) {
        String sql = "UPDATE borrow SET returnDate = ?,borrowDate = ? WHERE libraryId = ? AND isbn = ?";
        jdbcTemplate.update(sql,
                record.getReturnDate(),
                record.getBorrowDate(),
                record.getLibraryId(),
                record.getIsbn()
        );
    }
    @Override
    public List<String> getBorrowedBookNames(int libraryId) {
        String sql = """
        SELECT b.title
        FROM borrow br
        JOIN books b ON br.isbn = b.isbn
        WHERE br.libraryId = ? AND br.returnDate IS NULL
    """;
        return jdbcTemplate.query(sql, new Object[]{libraryId}, (rs, rowNum) -> rs.getString("title"));
    }
    @Override
    public List<String> getReturnedBookNames(int libraryId) {
        String sql = """
        SELECT b.title
        FROM borrow br
        JOIN books b ON br.isbn = b.isbn
        WHERE br.libraryId = ? AND br.returnDate IS NOT NULL
    """;
        return jdbcTemplate.query(sql, new Object[]{libraryId}, (rs, rowNum) -> rs.getString("title"));
    }

}
