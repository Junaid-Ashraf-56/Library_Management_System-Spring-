package Terminal_UI;

import model.Book;
import model.Person;

import java.util.List;

public class Format {
    public void printBoxedMessage(String message) {
        String border = "═".repeat(message.length() + 4);
        System.out.println("╔" + border + "╗");
        System.out.printf("║  %-" + message.length() + "s  ║%n", message);
        System.out.println("╚" + border + "╝");
    }
    public void printBookTable(List<Book> books) {
        String format = "| %-5s | %-20s | %-15s | %-6s | %-10s |%n";
        System.out.println("╔═════╦════════════════════╦═══════════════╦═══════╦════════════╗");
        System.out.printf(format, "ID", "Title", "Author", "Copies", "Available");
        System.out.println("╠═════╬════════════════════╬═══════════════╬═══════╬════════════╣");
        for (Book book : books) {
            System.out.printf(format,
                    book.getBookId(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getCopies(),
                    book.isAvailable() ? "Yes" : "No"
            );
        }
        System.out.println("╚═════╩════════════════════╩═══════════════╩═══════╩════════════╝");
    }

    public void printPersonTable(List<Person> persons) {
        String format = "| %-5s | %-15s | %-25s | %-6s | %-20s |%n";
        System.out.println("╔═════╦═════════════════╦══════════════════════════╦═══════╦══════════════════════╗");
        System.out.printf(format, "ID", "Name", "Email", "Books", "Book Names");
        System.out.println("╠═════╬═════════════════╬══════════════════════════╬═══════╬══════════════════════╣");
        for (Person person : persons) {
            System.out.printf(format,
                    person.getLibraryId(),
                    person.getName(),
                    person.getEmail(),
                    person.getBorrowBooks(),
                    person.getBorrowBooksName()
            );
        }
        System.out.println("╚═════╩═════════════════╩══════════════════════════╩═══════╩══════════════════════╝");
    }
    public void printTitleIsbnTable(Book book) {
        String format = "| %-30s | %-13s |%n";
        System.out.println("╔════════════════════════════════════════╦═════════════════╗");
        System.out.printf(format, "Title", "ISBN");
        System.out.println("╠════════════════════════════════════════╬═════════════════╣");
        System.out.printf(format, book.getTitle(), String.valueOf(book.getIsbn()));
        System.out.println("╚════════════════════════════════════════╩═════════════════╝");
    }

    public static void libTable(Person person) {
        String format = "| %-20s | %-12s | %-30s |%n";

        System.out.println("╔════════════════════╦══════════════╦════════════════════════════════════╗");
        System.out.printf(format, "Name", "Library ID", "Email");
        System.out.println("╠════════════════════╬══════════════╬════════════════════════════════════╣");
        System.out.printf(format,
                person.getName(),
                person.getLibraryId(),
                person.getEmail()
        );
        System.out.println("╚════════════════════╩══════════════╩════════════════════════════════════╝");
    }

}
