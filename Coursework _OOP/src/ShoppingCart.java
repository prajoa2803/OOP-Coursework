import java.util.ArrayList;

public class ShoppingCart {

    private static ArrayList<Product> cart = new ArrayList<Product>();

    private static ArrayList<Integer> orderedQuantity = new ArrayList<Integer>();

    private static ArrayList<Double> totalPricePerproduct = new ArrayList<Double>();



    public void addToCart(Product product, int quantity, double totalPricePerProduct) {
        cart.add(product);
        orderedQuantity.add(quantity);
        totalPricePerproduct.add(totalPricePerProduct);
    }


    public static ArrayList<Product> getCart() {
        return cart;
    }

    public static ArrayList<Integer> getOrderedQuantity() {
        return orderedQuantity;
    }

    public static ArrayList<Double> getTotalPricePerproduct() {
        return totalPricePerproduct;
    }

    public boolean firstPurchaseDiscount() {
        UserController userController = new UserController();
        String username = UserController.getSignedUser();
        ArrayList<User> userList = UserController.getUsers();
        boolean eligible = false;
        for (int i = 0; i < userList.size(); i++) {
            if (username.equals(userList.get(i).getUsername())) {
                if (userList.get(i).getNoOfPurchase() == 1) {
                    eligible = true;
                }
            }
        }
        return eligible;
    }

    public boolean threeItemsDiscount() {
        int elecCount = 0;
        int clothCount = 0;
        boolean eligible = false;
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i) instanceof Electronics) {
                elecCount = elecCount + orderedQuantity.get(i);
            } else if (cart.get(i) instanceof Clothing) {
                clothCount = clothCount + orderedQuantity.get(i);
            }
        }
        if (elecCount > 2 || clothCount > 2) {
            eligible = true;
        }
        return eligible;
    }

    public String finalCost() {
        double total = 0.00;
        double firstPurDis = 0.00;
        double threePurDis = 0.00;
        double netTotal = 0.00;
        for (int i = 0; i < totalPricePerproduct.size(); i++) {
            total = total + totalPricePerproduct.get(i);
        }
        if (firstPurchaseDiscount()) {
            firstPurDis = (total * 0.1);
        }
        if (threeItemsDiscount()) {
            threePurDis = (total * 0.2);
        }
        netTotal = total - (firstPurDis + threePurDis);
        return ("Total       " + String.format("%.2f", total) + "\n" +
                "First Purchase Discount (10%)       " + String.format("%.2f", firstPurDis) + "\n" +
                "Three Items in same Category Discount (20%)       " + String.format("%.2f", threePurDis) + "\n" + "\n" +
                "Final Total       "  + String.format("%.2f", netTotal) );
    }
}
