public abstract class Product implements Comparable <Product>{
    private String productID;
    private String productName;
    private int productStock;
    private double productPrice;

    public Product(String productID, String productName, int productStock, double productPrice) {
        this.productID = productID;
        this.productName = productName;
        this.productStock = productStock;
        this.productPrice = productPrice;
    }

    public String getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductStock() {
        return productStock;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductStock(int productStock) {
        this.productStock = productStock;
    }



    @Override
    public String toString() {
        return
                "productID='" + productID + '\'' +
                ", productName='" + productName + '\'' +
                ", productStock=" + productStock +
                ", productPrice=" + productPrice;
    }

    @Override
    public int compareTo(Product product) {
        return this.productID.compareTo(product.productID);
    }

    public String fileData(){
        return productID + "\n"+
                productName + "\n"+
                productStock + "\n"+
                productPrice + "\n";
    }
}
