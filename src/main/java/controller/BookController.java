package controller;

import model.Book;
import service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.BorrowService;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final BorrowService borrowService;
    @Autowired
    public BookController(BookService bookService, BorrowService borrowService) {
        this.bookService = bookService;
        this.borrowService = borrowService;
    }
    @PostMapping
    public String addBook(@RequestBody Book book) {
        bookService.addBook(book);
        return "Book added successfully!";
    }
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBook();
    }
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable int id) {
        return bookService.getBookById(id);
    }
    @PutMapping("/{id}")
    public String updateBook(@PathVariable int id, @RequestBody Book book) {
        book.setBookId(id);
        bookService.updateBook(book);
        return "Book updated successfully!";
    }
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
        return "Book deleted successfully!";
    }

    public void borrowBook(int personId,int bookId){
        borrowService.borrowBook(personId,bookId);
    }

    public void returnBook(int personId,int bookId){
        borrowService.returnBook(personId,bookId);
    }

    public String bookIsbn(String title){
        return bookService.getBookByTitle(title);
    }
}
