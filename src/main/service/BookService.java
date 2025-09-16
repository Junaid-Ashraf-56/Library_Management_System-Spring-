package main.service;

import main.dao.BookDAO;
import main.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookDAO bookDAO;

    public BookService(BookDAO bookDAO){
        this.bookDAO = bookDAO;
    }

    public void addBook(Book book){
        if (book.getTitle() == null || book.getTitle().isEmpty()){
            throw new IllegalArgumentException("Book title not found");
        }
        Book existing = bookDAO.getBookByIsbn(book.getIsbn());
        if (existing!=null){
            int copies = existing.getCopies()+book.getCopies();
            existing.setCopies(copies);
        }else {
            bookDAO.addBook(book);
        }
    }

    public Book getBookById(int id){
        return bookDAO.getBookById(id);
    }

    public List<Book> getAllBook(Book book){
        return bookDAO.getAllBook();
    }

    public void deleteBook(int id){
        bookDAO.deleteBook(id);
    }

    public void updateBook(Book book){
        bookDAO.updateBook(book);
    }
}
