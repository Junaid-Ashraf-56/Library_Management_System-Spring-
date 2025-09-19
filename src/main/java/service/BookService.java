package service;

import dao.BookDAO;
import model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookDAO bookDAO;

    @Autowired
    public BookService(BookDAO bookDAO){
        this.bookDAO = bookDAO;
    }

    public void addBook(Book book) {
        Book existing = bookDAO.getBookByIsbn(book.getIsbn());
        if (existing != null) {
            throw new IllegalArgumentException("A book with ISBN " + book.getIsbn() + " already exists.");
        }
        bookDAO.addBook(book);
    }


    public Book getBookById(int id){
        return bookDAO.getBookById(id);
    }

    public List<Book> getAllBook(){
        return bookDAO.getAllBook();
    }

    public void deleteBook(int id){
        bookDAO.deleteBook(id);
    }

    public void updateBook(Book book){
        bookDAO.updateBook(book);
    }
    public Book getBookByTitle(String title){
        return bookDAO.getBookByTitle(title);
    }
    public Book getBookByIsbn(int isbn){
        return bookDAO.getBookByIsbn(isbn);
    }
}
