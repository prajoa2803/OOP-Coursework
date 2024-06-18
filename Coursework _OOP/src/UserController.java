import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserController {

    private static ArrayList<User> users = new ArrayList<User>();

    static String signedUser = " ";


    public void userConsoleMenu() {
        loadFileToList();
        Scanner scan = new Scanner(System.in);

        boolean loop = true;
        while (loop) {
            System.out.print(
                    "1. New User - Sign Up\n" +
                            "2. Exisitng User - Sign In\n" +
                            "3. Exit \n"+
                            "Enter your option: ");
            try {
                int userOption = scan.nextInt();
                scan.nextLine();

                switch (userOption) {
                    case (1):
                        signUp();
                        break;

                    case (2):
                        signIn();
                        break;
                    case (3):
                        System.out.println("exit");

                        loop = false;
                        break;

                    default:
                        System.out.println("!!!Enter Valid Option!!!");

                }
            } catch (InputMismatchException exception) {
                scan.next();
                System.out.println("!!!Enter Valid Option!!!");
            }
        }
    }

    public void signUp() {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter UserName: ");
        String username = scan.nextLine();

        for (int i = 0; i < users.size(); i++) {
            if (username.equals(users.get(i).getUsername())) {
                System.out.println("This username already exists, Sign in try another username");
                return;  // Exit the method if username already exists
            }
        }

        System.out.print("Enter Password: ");
        String password = scan.nextLine();
        users.add(new User(username, password, 0));

        try {
            FileWriter myWriter = new FileWriter("UserInfo.txt");
            for(int i = 0; i < users.size();i++){

                    myWriter.write(users.get(i).toFile());

            }
            myWriter.close();
            System.out.println("Data has been stored successfully");

        }
        catch (IOException e){
            System.out.println("An error occurred.");
        }
    }

    public void signIn() {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter Username: ");
        String username = scan.nextLine();

        for (int i = 0; i < users.size(); i++) {
            if (username.equals(users.get(i).getUsername())) {
                System.out.print("Enter Password: ");
                String password = scan.nextLine();

                if (password.equals(users.get(i).getPassword())) {
                    int num = users.get(i).getNoOfPurchase() + 1;
                    users.get(i).setNoOfPurchase(num);
                    signedUser = users.get(i).getUsername();
                    try {
                        FileWriter myWriter = new FileWriter("UserInfo.txt");
                        for(i = 0; i < users.size();i++){

                            myWriter.write(users.get(i).toFile());

                        }
                        myWriter.close();
                        System.out.println("Data has been stored successfully");

                    }
                    catch (IOException e){
                        System.out.println("An error occurred.");
                    }

                    ShoppingFrame frame = new ShoppingFrame();
                    frame.setTitle("Westminster Shopping Centre");
                    frame.setSize(600, 600);
                    frame.setVisible(true);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    return;  // Exit the method after successful login
                } else {
                    System.out.println("Incorrect Password.");
                    return;  // Exit the method if password is incorrect
                }
            }
        }

        System.out.println("This Username does not exist.");
    }


    public void loadFileToList () {
            try {
                File readFile = new File("UserInfo.txt");
                Scanner file_reader = new Scanner(readFile);

                while (file_reader.hasNextLine()) {
                    String username = file_reader.nextLine();
                    String password = file_reader.nextLine();
                    int noOfPurchase = Integer.parseInt(file_reader.nextLine());
                    users.add(new User(username, password, noOfPurchase));
                }
                file_reader.close();


            } catch (
                    IOException e) {
                System.out.println("The file was not found.");
            }
        }

    public static String getSignedUser() {
        return signedUser;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }
}
