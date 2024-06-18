import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class WestminsterShoppingManager implements ShoppingManager{

private static ArrayList <Product> products = new ArrayList<Product>();

    public void loadFileToList() {
        try {
            File readFile = new File("storingFile.txt");
            Scanner file_reader = new Scanner(readFile);

            while (file_reader.hasNextLine()) {
                String category = file_reader.nextLine();
                if (category.equals("Electronics")) {
                    String productID = file_reader.nextLine();
                    String productName = file_reader.nextLine();
                    int productStock = (Integer.parseInt(file_reader.nextLine()));
                    double productPrice = (Double.parseDouble(file_reader.nextLine()));
                    String brand = file_reader.nextLine();
                    int monthsOfWarranty = (Integer.parseInt(file_reader.nextLine()));

                    Product electronics = new Electronics(productID, productName, productStock, productPrice, brand, monthsOfWarranty);
                    products.add(electronics);
                } else if (category.equals("Clothing")) {
                    String productID = file_reader.nextLine();
                    String productName = file_reader.nextLine();
                    int productStock = (Integer.parseInt(file_reader.nextLine()));
                    double productPrice = (Double.parseDouble(file_reader.nextLine()));
                    String size = file_reader.nextLine();
                    String colour = file_reader.nextLine();

                    Product clothing = new Clothing(productID, productName, productStock, productPrice, size, colour);
                    products.add(clothing);
                }

            }
            file_reader.close();


        } catch (
                IOException e) {
            System.out.println("The file was not found.");
        }
    }

    Scanner scan = new Scanner(System.in);
    /**
     * To print console options to the manager
     */
    public void managerConsole(){

        boolean loop = true;
        while (loop) {
            System.out.print("Select a task\n" +
                    "1. Add a new Product\n" +
                    "2. Delete a Product\n" +
                    "3. Print the list of the Products\n" +
                    "4. Save in a file\n" +
                    "5. Exit\n" +
                    "Enter your option: ");


            try {
                int taskOption = scan.nextInt();
                scan.nextLine();

                switch (taskOption) {
                    case (1):
                        addProduct();
                        break;

                    case (2):
                        deleteProduct();
                        break;

                    case (3):
                        printListOfProducts();
                        break;
                    case (4):
                        saveInFile();
                        break;
                    case (5):
                        System.out.println("exit");
                        saveInFile();
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


    @Override
    /**
     * Add products to arraylist by the manager
     */
    public void addProduct() {

        if (products.size() <= 50){

            boolean loop = true;
            while (loop) {
                try {
                    System.out.print("Select type of Product\n" +
                            "1. Electronics\n" +
                            "2. Clothing\n" +
                            "Enter your option: ");
                    int productOption = scan.nextInt();
                    scan.nextLine();

                    switch (productOption) {
                        case (1):


                            System.out.println("Enter Product ID: ");
                            String productID = scan.nextLine();

                            if(!productExists(productID)) {

                                System.out.println("Enter Product Name: ");
                                String productName = scan.nextLine();
                                System.out.println("Enter Product Stock: ");
                                int productStock = scan.nextInt();
                                scan.nextLine();
                                System.out.println("Enter Product Price: ");
                                double productPrice = scan.nextDouble();
                                scan.nextLine();
                                System.out.println("Enter Product Brand: ");
                                String brand = scan.nextLine();
                                System.out.println("Enter Product Warranty in months: ");
                                int monthsOfWarranty = scan.nextInt();
                                scan.nextLine();
                                Product electronics = new Electronics(productID, productName, productStock, productPrice, brand, monthsOfWarranty);
                                products.add(electronics);
                                System.out.println(productName + " has been added successfully");
                            }else{
                                System.out.println("This Product ID exists");
                            }
                            loop = false;
                            break;

                        case (2):



                            System.out.println("Enter Product ID: ");
                            productID = scan.nextLine();

                            if(!productExists(productID)) {

                                System.out.println("Enter Product Name: ");
                                String productName = scan.nextLine();
                                System.out.println("Enter Product Stock: ");
                                int productStock = scan.nextInt();
                                scan.nextLine();
                                System.out.println("Enter Product Price: ");
                                double productPrice = scan.nextDouble();
                                scan.nextLine();
                                System.out.println("Enter Product Size: ");
                                String size = scan.nextLine();
                                System.out.println("Enter Product colour: ");
                                String colour = scan.nextLine();
                                Product clothing = new Clothing(productID, productName, productStock, productPrice, size, colour);
                                products.add(clothing);
                                System.out.println(productName + " has been added successfully");
                            }else{
                                System.out.println("This Product ID exists");
                            }
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

        }else{
            System.out.println("Maximum 50 products can be stored");
        }
    }

    /**
     * Checks whether the entered product ID already exists
     * @param productID
     * @return
     */
    public boolean productExists(String productID){
        for(int i = 0; i < products.size();i++){
            if(productID.equals(products.get(i).getProductID())){
                return true;
            }
        }return false;
    }

    @Override
    public void deleteProduct() {
        System.out.print("Enter the Product ID: ");
        String productID = scan.nextLine();
        boolean productFound = false;
        for(int i = 0; i < products.size();i++){
            if(productID.equals(products.get(i).getProductID())){
                if(products.get(i) instanceof  Electronics) {
                    System.out.println(products.get(i).getProductName() + " from Electronics has been removed successfully");
                } else if (products.get(i) instanceof Clothing){
                    System.out.println(products.get(i).getProductName() + " from Clothing has been removed successfully");
                }
                System.out.println(products.size() + " number of products are available");
                productFound = true;
                products.remove(i);
            }
        }
        if (!productFound){
            System.out.println("This Product does not exists");
        }
    }
    @Override
    public void printListOfProducts() {
       if(products.size() == 0){
           System.out.println("No products Available");
       }
       Collections.sort(products); //REF: https://prepinsta.com/java/program-to-sort-arraylist-of-custom-objects-by-property/
        for (int i = 0; i < products.size();i++){
            if (products.get(i) instanceof Electronics){
                System.out.println(products.get(i).toString());
                System.out.println("______________________________________________________________");
            }else if(products.get(i) instanceof Clothing){
                System.out.println(products.get(i).toString());
                System.out.println("______________________________________________________________");
            }
        }
    }
    @Override
    public void saveInFile() {
        File file = new File("storingFile.txt");

        while(true) {
            if (file.exists() ) {
                try {
                    FileWriter myWriter = new FileWriter("storingFile.txt");
                    for(int i = 0; i < products.size();i++){
                        if (products.get(i) instanceof Electronics){
                            myWriter.write(products.get(i).fileData());
                        }else if(products.get(i) instanceof Clothing){
                            myWriter.write(products.get(i).fileData());
                        }
                    }
                    myWriter.close();
                    System.out.println("Data has been stored successfully");
                    break;
                }
                catch (IOException e){
                    System.out.println("An error occurred.");
                }

            } else {
                try {
                    if(file.createNewFile()) {
                        System.out.println();
                    }
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                }
            }
        }

    }

    public static ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        WestminsterShoppingManager.products = products;
    }
}
