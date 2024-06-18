import java.util.ArrayList;

public class User {
    private String username;
    private String password;

    private int noOfPurchase;

    public User(String username, String password, int noOfPurchase) {
        this.username = username;
        this.password = password;
        this.noOfPurchase = noOfPurchase;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword(){
        return password;
    }
    
    public int getNoOfPurchase() {
        return noOfPurchase;
    }

    public void setNoOfPurchase(int noOfPurchase) {
        this.noOfPurchase = noOfPurchase;
    }

    public String toFile() {
        return (username + '\n' +
                password + '\n' +
                noOfPurchase + "\n");
    }
}
