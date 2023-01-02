import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Scanner;

public class MainApp {

    public void startApp() {
        Scanner scanner = new Scanner(System.in);
        boolean passwordType;
        String userChoice;

        while (true) {

            System.out.print("If you want to quit app, enter (x) otherwise press enter: ");
            if (scanner.nextLine().equals("x")) {
                break;
            } else {
                System.out.print("\nDo you want a password with symbols?(yes/no): ");
                userChoice = scanner.next();

                if (userChoice.equals("yes") || userChoice.equals("y")) {
                    passwordType = true;
                } else if (userChoice.equals("x")) {
                    break;
                } else {
                    passwordType = false;
                }

                PasswordGenerator pg = new PasswordGenerator(passwordType);

                boolean isOn = true;
                while (isOn) {
                    String password = regeneratePassword(pg);
                    System.out.println(password);
                    System.out.print("\nDo you want to copy on clipboard (yes/no): ");
                    userChoice = scanner.next();
                    if (userChoice.equals("yes") || userChoice.equals("y")) {
                        Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
                        StringSelection data = new StringSelection(password);
                        cb.setContents(data, data);
                        break;
                    } else {

                        System.out.print("\nDo you want to regenerate it?: ");
                        userChoice = scanner.next();

                        if (userChoice.equals("no") || userChoice.equals("n"))
                            isOn = false;
                    }
                }
            }
        }

    }

    private String regeneratePassword(PasswordGenerator pg) {
        pg.generate();
        return pg.getPassword();
    }

}
