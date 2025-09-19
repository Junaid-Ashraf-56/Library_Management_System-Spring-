package dao;

import model.BorrowRecord;

import java.util.List;

public interface BorrowDAO {
    void save(BorrowRecord record);
    BorrowRecord findById(int id);
    BorrowRecord findByPersonAndBook(int personId, int bookId);
    List<BorrowRecord> findByPerson(int personId);
    List<BorrowRecord> findAll();
    void delete(int id);
    void update(BorrowRecord record);
    List<String> getBorrowedBookNames(int libraryId);
    List<String> getReturnedBookNames(int libraryId);
}
