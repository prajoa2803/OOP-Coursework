public class Clothing extends Product{
    private String size;
    private String colour;

    public Clothing(String productID, String productName, int productStock, double productPrice, String size, String colour) {
        super(productID, productName, productStock, productPrice);
        this.size = size;
        this.colour = colour;
    }

    public String getSize() {
        return size;
    }

    public String getColour() {
        return colour;
    }

    @Override
    public String toString() {
        return "Clothing :- " +
                super.toString() +
                ", size='" + size + '\'' +
                ", colour='" + colour;
    }

    public String fileData(){
        return "Clothing" + "\n" +
                super.fileData() +
                size + "\n" +
                colour + "\n";

    }
}
