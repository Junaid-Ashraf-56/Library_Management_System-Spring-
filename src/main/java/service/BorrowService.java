package service;

import dao.BookDAO;
import dao.BorrowDAO;
import model.Book;
import model.BorrowRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class BorrowService {
    private final BookDAO bookDAO;
    private final BorrowDAO borrowDAO;

    @Autowired
    public BorrowService(BookDAO bookDAO, BorrowDAO borrowDAO) {
        this.bookDAO = bookDAO;
        this.borrowDAO = borrowDAO;
    }

    @Transactional
    public void borrowBook(int personId,int bookId) throws IllegalStateException {
        Book book = bookDAO.getBookById(bookId);
        if (book == null || !book.isAvailable()){
            throw new IllegalStateException("Book is not available");
        }
        int copies = book.getCopies();
        book.setCopies(copies - 1);
        if (book.getCopies() == 0) {
            book.setAvailable(false);
        }
        bookDAO.updateBook(book);


        BorrowRecord record = new BorrowRecord(personId,bookId);
        borrowDAO.save(record);
    }

    @Transactional
    public void returnBook(int personId,int bookId) throws IllegalStateException {
        BorrowRecord borrowRecord = borrowDAO.findByPersonAndBook(personId,bookId);
        if (borrowRecord == null){
            throw new IllegalStateException("Record not found");
        }
        Book book = bookDAO.getBookById(bookId);
        if (book==null){
            throw new IllegalStateException("Book not found");
        }
        borrowRecord.setReturnDate(LocalDate.now());
        book.setCopies(book.getCopies()+1);
        if (!book.isAvailable()){
            book.setAvailable(true);
        }
        bookDAO.updateBook(book);
        borrowDAO.update(borrowRecord);
    }

    public List<String> borrowBooks(int libId){
        return borrowDAO.getBorrowedBookNames(libId);
    }
    public List<String> returnBooks(int libId){
        return borrowDAO.getReturnedBookNames(libId);
    }
}
