package dao;

import model.BorrowRecord;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class BorrowDAOImpl implements BorrowDAO{
    public JdbcTemplate jdbcTemplate;

    public BorrowDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(BorrowRecord record) {
        String sql = "INSERT INTO borrow(borrowId,personId,bookId,borrowDate,returnDate) Values(?,?,?,?,?)";
        jdbcTemplate.update(sql,
                record.getBorrowId(),
                record.getPersonId(),
                record.getBookId(),
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
                        rs.getInt("bookId"),
                        rs.getDate("borrowDate").toLocalDate(),
                        rs.getDate("returnDate").toLocalDate()
                ));
    }

    @Override
    public BorrowRecord findByPersonAndBook(int personId, int bookId) {
        String sql = "SELECT * FROM borrow WHERE personId = ? AND bookId = ?";
        return jdbcTemplate.queryForObject(sql,
                new Object[]{personId,bookId},(rs, rowNum) -> new BorrowRecord(
                        rs.getInt("borrowId"),
                        rs.getInt("personId"),
                        rs.getInt("bookId"),
                        rs.getDate("borrowDate").toLocalDate(),
                        rs.getDate("returnDate").toLocalDate()
                ));
    }

    @Override
    public List<BorrowRecord> findByPerson(int personId) {
        String sql = "SELECT * FROM borrow WHERE personId = ?";
        return jdbcTemplate.query(sql,
                new Object[]{personId},(rs, rowNum) -> new BorrowRecord(
                        rs.getInt("borrowId"),
                        rs.getInt("personId"),
                        rs.getInt("bookId"),
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
                        rs.getInt("bookId"),
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
        String sql = "UPDATE borrow SET returnDate = ?,borrowDate = ? WHERE personId = ? AND bookId = ?";
        jdbcTemplate.update(sql,
                record.getReturnDate(),
                record.getBorrowDate(),
                record.getPersonId(),
                record.getBookId()
        );
    }
}
