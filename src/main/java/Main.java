
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import config.AppConfig;
import Terminal_UI.ConsoleUI;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        ConsoleUI consoleUI = context.getBean(ConsoleUI.class);
        consoleUI.start();
    }
}
