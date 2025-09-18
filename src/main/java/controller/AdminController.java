package controller;

import model.Book;
import model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.BookService;
import service.PersonService;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final PersonService personService;
    private final BookService bookService;

    @Autowired
    public AdminController(PersonService personService, BookService bookService) {
        this.personService = personService;
        this.bookService = bookService;
    }

    @PostMapping("/person")
    public void addPerson(@RequestBody Person person) {
        personService.addPerson(person);
    }

    @PostMapping("/book")
    public void addBook(@RequestBody Book book) {
        bookService.addBook(book);
    }

    @PutMapping("/book")
    public void manageBooks(@RequestBody Book book) {
        bookService.updateBook(book);
    }

    @GetMapping("/persons")
    public List<Person> listAllPersons() {
        return personService.getAllPerson();
    }

    @GetMapping("/books")
    public List<Book> listAllBook(){
        return bookService.getAllBook();
    }


    @DeleteMapping("/person/{id}")
    public void deletePerson(@PathVariable("id") int personId) {
        personService.deletePerson(personId);
    }

    @DeleteMapping("/book/{id}")
    public void deleteBook(@PathVariable("id") int bookId) {
        bookService.deleteBook(bookId);
    }
}

