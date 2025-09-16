package main.dao;

import main.model.Book;

import java.util.List;

public interface BookDAO {
    void addBook(Book book);
    List<Book> getAllBook();
    Book getBookById(int id);
    void updateBook(Book book);
    void deleteBook(int id);
}
