package Terminal_UI;


import controller.AdminController;
import model.Book;
import model.Person;
import service.BookService;
import service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.PersonService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import static Terminal_UI.Format.*;

@Component
public class ConsoleUI {
    private final AdminController adminController;
    private final BorrowService borrowService;
    private final PersonService personService;
    private final BookService bookService;


    @Autowired
    public ConsoleUI(AdminController adminController, BorrowService borrowService, PersonService personService, BookService bookService) {
        this.adminController = adminController;
        this.borrowService = borrowService;
        this.personService = personService;
        this.bookService = bookService;
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            printAscii("banner.txt");
            printAscii("main_menu.txt");
            System.out.print("Enter your choice: ");
            String input = sc.nextLine();

            switch (input) {
                case "1" -> handleAdmin(sc);
                case "2" -> handleUser(sc);
                case "3" -> {
                    System.out.println("\nThank you for using the Library System!");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void handleAdmin(Scanner sc) {
        printAscii("banner.txt");
        boolean back = false;
        while (!back) {
            printAscii("admin_menu.txt");
            System.out.print("Enter your choice: ");
            String input = sc.nextLine();

            switch (input) {
                case "1" -> {
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter email: ");
                    String email = sc.nextLine();
                    Person person = new Person();
                    person.setName(name);
                    person.setEmail(email);
                    person.setBorrowBooks(0);
                    person.setBorrowBooksName("");
                    adminController.addPerson(person);
                    printBoxedMessage("Person added successfully.");
                }
                case "2" ->{
                    System.out.println("Enter book title");
                    String title = sc.nextLine();
                    System.out.println("Enter your book author");
                    String author = sc.nextLine();
                    System.out.println("Enter ISBN for the book");
                    int isbn = Integer.parseInt(sc.nextLine());
                    System.out.println("Enter copies of book");
                    int copies = Integer.parseInt(sc.nextLine());
                    boolean isAvailable;
                    isAvailable = copies >= 1;

                    Book book = new Book();
                    book.setTitle(title);
                    book.setAuthor(author);
                    book.setIsbn(isbn);
                    book.setCopies(copies);
                    book.setAvailable(isAvailable);
                    adminController.addBook(book);
                    printBoxedMessage("Book Added successfully");
                }
                case "3" -> {
                    System.out.println("Enter ISBN of the book");
                    int isbn = Integer.parseInt(sc.nextLine());
                    Book existing = bookService.getBookByIsbn(isbn);
                    if (existing == null){
                        System.out.println("Book not found");
                    }
                    System.out.println("Enter new book title");
                    String title = sc.nextLine();
                    System.out.println("Enter new your book author");
                    String author = sc.nextLine();
                    System.out.println("Enter new ISBN for the book");
                    isbn = Integer.parseInt(sc.nextLine());
                    System.out.println("Enter new copies of book");
                    int copies = Integer.parseInt(sc.nextLine());
                    boolean isAvailable;
                    isAvailable = copies >= 1;

                    Book book = new Book();
                    book.setTitle(title);
                    book.setAuthor(author);
                    book.setIsbn(isbn);
                    book.setCopies(copies);
                    book.setAvailable(isAvailable);
                    adminController.manageBooks(book);
                    printBoxedMessage("Book Updated successfully");
                }
                case "4" -> {
                    List<Person> personList = adminController.listAllPersons();
                    printBoxedMessage(" Registered Persons:");
                    printPersonTable(personList);
                }
                case "5" -> {
                    List<Book> books = adminController.listAllBook();
                    printBoxedMessage("Added Books");
                    printBookTable(books);
                }
                case "6" ->{
                    System.out.println("Enter Library Id for deleting it");
                    int libId = Integer.parseInt(sc.nextLine());
                    adminController.deletePerson(libId);
                    printBoxedMessage("Person delete success fully");
                }
                case "7" -> {
                    System.out.println("Enter ISBN for deleting it");
                    int isbn = Integer.parseInt(sc.nextLine());
                    adminController.deleteBook(isbn);
                    printBoxedMessage("Book delete success fully");
                }
                case "8" -> back = true;
                default -> printBoxedMessage("Invalid choice.");
            }
        }
    }

    private void handleUser(Scanner sc) {
        printAscii("banner.txt");
        boolean back = false;
        while (!back) {
            printAscii("user_menu.txt");
            System.out.print("Enter your choice: ");
            String input = sc.nextLine();

            switch (input) {
                case "1" -> {
                    System.out.print("Enter your Library ID: ");
                    int personId = Integer.parseInt(sc.nextLine());

                    System.out.print("Enter ISBN to borrow: ");
                    int bookId = Integer.parseInt(sc.nextLine());

                    try {
                        borrowService.borrowBook(personId, bookId);
                        printBoxedMessage("Book borrowed successfully.");
                    } catch (Exception e) {
                        printBoxedMessage("Failed to borrow book: " + e.getMessage());
                    }
                }
                case "2" -> {
                    System.out.print("Enter your Library ID: ");
                    int personId = Integer.parseInt(sc.nextLine());

                    System.out.print("Enter ISBN to return: ");
                    int bookId = Integer.parseInt(sc.nextLine());

                    try {
                        borrowService.returnBook(personId, bookId);
                        printBoxedMessage("Book returned successfully.");
                    } catch (Exception e) {
                        printBoxedMessage("Failed to return book: " + e.getMessage());
                    }
                }
                case "3" -> {
                    System.out.println("Enter your library Id");
                    int libId = Integer.parseInt(sc.nextLine());
                    Person person = personService.getPersonByLibId(libId);
                    if (person==null){
                        printBoxedMessage("Person not found");
                        return;
                    }
                    List<String> borrowedBooks = borrowService.borrowBooks(libId);
                    printBookList("Borrowed Book Names",borrowedBooks);
                }
                case "4" ->{
                    System.out.println("Enter your library Id");
                    int libId = Integer.parseInt(sc.nextLine());
                    Person person = personService.getPersonByLibId(libId);
                    if (person==null){
                        printBoxedMessage("Person not found");
                        return;
                    }
                    List<String> returnBooks = borrowService.returnBooks(libId);
                    printBookList("Returned Book Names",returnBooks);
                }
                case "5" -> {
                    printBoxedMessage("Search Book ISBN BY title: ");
                    System.out.println("Enter Book Title ");
                    String bookTitle = sc.nextLine();
                    printTitleIsbnTable(bookService.getBookByTitle(bookTitle));
                }
                case "6" -> {
                    printBoxedMessage("Get library Id of the user");
                    System.out.println("Enter you Email");
                    String email = sc.nextLine();
                    libTable(personService.getLibId(email));
                }
                case "7" -> back = true;
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void printAscii(String fileName) {
        try {
            Files.lines(Paths.get("src/main/resources/ascii/" + fileName))
                    .forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("Could not load " + fileName);
        }
    }
}
