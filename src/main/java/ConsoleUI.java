import controller.AdminController;
import service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.PersonService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

@Component
public class ConsoleUI {
    private final AdminController adminController;
    private final BorrowService borrowService;
    private final PersonService personService;

    @Autowired
    public ConsoleUI(AdminController adminController, BorrowService borrowService, PersonService personService) {
        this.adminController = adminController;
        this.borrowService = borrowService;
        this.personService = personService;
    }

    public static void start() {
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

    private static void handleAdmin(Scanner sc) {
        printAscii("banner.txt");
        boolean back = false;
        while (!back) {
            printAscii("admin_menu.txt");
            System.out.print("Enter your choice: ");
            String input = sc.nextLine();

            switch (input) {
                case "1" -> System.out.println("🛠 Add Person logic here");
                case "2" -> System.out.println("📘 Add Book logic here");
                case "3" -> System.out.println("📝 Manage Books logic here");
                case "4" -> System.out.println("👥 List Persons logic here");
                case "5" -> System.out.println("📚 List Books logic here");
                case "6" -> System.out.println("❌ Delete Person logic here");
                case "7" -> System.out.println("❌ Delete Book logic here");
                case "8" -> back = true;
                default -> System.out.println("❌ Invalid choice.");
            }
        }
    }

    private static void handleUser(Scanner sc) {
        printAscii("banner.txt");
        boolean back = false;
        while (!back) {
            printAscii("user_menu.txt");
            System.out.print("Enter your choice: ");
            String input = sc.nextLine();

            switch (input) {
                case "1" -> System.out.println("📖 Borrow Book logic here");
                case "2" -> System.out.println("📤 Return Book logic here");
                case "3" -> System.out.println("📚 Borrowed Book Names logic here");
                case "4" -> System.out.println("📚 Returned Book Names logic here");
                case "5" -> System.out.println("🔍 Book ISBN logic here");
                case "6" -> System.out.println("🆔 Library ID logic here");
                case "7" -> back = true;
                default -> System.out.println("❌ Invalid choice.");
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

    public static void main(String[] args) {
        Scanner abc = new Scanner(System.in);
        start();
        handleAdmin(abc);
        handleUser(abc);
    }
}
