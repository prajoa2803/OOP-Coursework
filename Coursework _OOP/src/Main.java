import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        WestminsterShoppingManager westminsterShoppingManager = new WestminsterShoppingManager();
        westminsterShoppingManager.loadFileToList();

        Scanner scan = new Scanner(System.in);
        System.out.print("Select your role \n" +
                "1. Manager\n" +
                "2. User\n" +
                "Enter your option: ");

        boolean loop = true;
        while (loop) {
            try {
                int roleOption = scan.nextInt();

                switch (roleOption) {
                    case (1):
                        loop = false;
                        westminsterShoppingManager.managerConsole();
                        break;

                    case (2):
                        loop = false;
                        UserController userController = new UserController();
                        userController.userConsoleMenu();
                        break;

                    default:
                        System.out.print("Enter Valid Option: ");
                }
            } catch (InputMismatchException exception) {
                scan.next();
                System.out.print("Enter Valid Option: ");
            }
        }
    }


}
