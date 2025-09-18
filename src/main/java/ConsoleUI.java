import controller.AdminController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.BorrowService;

import java.util.Scanner;

@Component
public class ConsoleUI {
    private final AdminController adminController;
    private final BorrowService borrowService;

    @Autowired
    public ConsoleUI(AdminController adminController, BorrowService borrowService) {
        this.adminController = adminController;
        this.borrowService = borrowService;
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            printMainMenu();
            int choice = sc.nextInt();
            if (choice == 1) {
                adminMenu(sc);
            } else if (choice == 2) {
                userMenu(sc);
            } else if (choice == 3) {
                System.out.println("\n👋 Thank you for using the Library System!");
                break;
            } else {
                System.out.println("❌ Invalid choice. Try again.");
            }
        }
    }

    private void printMainMenu() {
        System.out.println("\n");
        System.out.println("╔════════════════════════════════════╗");
        System.out.println("║          📚 LIBRARY SYSTEM         ║");
        System.out.println("╠════════════════════════════════════╣");
        System.out.println("║    1  Admin                        ║");
        System.out.println("║    2  User                         ║");
        System.out.println("║    3  Exit                         ║");
        System.out.println("╚════════════════════════════════════╝");
        System.out.print("👉 Enter your choice: ");
    }

    private void adminMenu(Scanner sc) {
        System.out.println("\n");
        System.out.println("╔════════════════════════════════════╗");
        System.out.println("║             👨‍💼 ADMIN MENU          ║");
        System.out.println("╠════════════════════════════════════╣");
        System.out.println("║    1  Add Person                   ║");
        System.out.println("║    2  Add Book                     ║");
        System.out.println("║    3  Manage Books                 ║");
        System.out.println("║    4  List Persons                 ║");
        System.out.println("║    5  List Books                   ║");
        System.out.println("║    6  Delete Person                ║");
        System.out.println("║    7  Delete Book                  ║");
        System.out.println("║    8  Back to Main Menu            ║");
        System.out.println("╚════════════════════════════════════╝");
        System.out.print("👉 Enter your choice: ");
    }

    private void userMenu(Scanner sc) {
        System.out.println("\n");
        System.out.println("╔════════════════════════════════════╗");
        System.out.println("║              🙋 USER MENU          ║");
        System.out.println("╠════════════════════════════════════╣");
        System.out.println("║    1  Borrow Book                  ║");
        System.out.println("║    2  Return Book                  ║");
        System.out.println("║    3  Name of Borrow Books         ║");
        System.out.println("║    4  Name of return Books         ║");
        System.out.println("║    5  Book ISBN                    ║");
        System.out.println("║    6  Library ID                   ║");
        System.out.println("║    7  Back to Main Menu            ║");
        System.out.println("╚════════════════════════════════════╝");
        System.out.print("👉 Enter your choice: ");
    }
}
