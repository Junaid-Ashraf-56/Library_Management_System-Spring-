package service;

import dao.BookDAO;
import dao.BorrowDAO;
import model.Book;
import model.BorrowRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BorrowServiceTest {

    @Mock private BorrowDAO borrowDAO;
    @Mock
    private BookDAO bookDAO;

    @InjectMocks
    private BorrowService borrowService;

    @Test
    void testBorrowBook_decreasesCopies() {
        int libraryId = 1001;
        int isbn = 1234;
        Book book = new Book(libraryId,"Atomic Habits", "Author", isbn, 2, true);

        when(bookDAO.getBookByIsbn(isbn)).thenReturn(book);

        borrowService.borrowBook(libraryId, isbn);

        assertEquals(1, book.getCopies());
        verify(bookDAO).updateBook(book);
        verify(borrowDAO).save(any(BorrowRecord.class));
    }

    @Test
    void testReturnBook_increasesCopies() {
        int libraryId = 1001;
        int isbn = 1234;
        Book book = new Book(libraryId,"Atomic Habits", "Author", isbn, 1, true);
        BorrowRecord record = new BorrowRecord(libraryId, isbn);

        when(borrowDAO.findByPersonAndBook(libraryId, isbn)).thenReturn(record);
        when(bookDAO.getBookByIsbn(isbn)).thenReturn(book);

        borrowService.returnBook(libraryId, isbn);

        assertEquals(2, book.getCopies());
        assertTrue(book.isAvailable());
        verify(bookDAO).updateBook(book);
        verify(borrowDAO).update(record);
    }
}
