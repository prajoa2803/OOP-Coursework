public class Electronics extends Product{
    private String brand;
    private int monthsOfWarranty;

    public Electronics(String productID, String productName, int productStock, double productPrice, String brand, int monthsOfWarranty) {
        super(productID, productName, productStock, productPrice);
        this.brand = brand;
        this.monthsOfWarranty = monthsOfWarranty;
    }

    public String getBrand() {
        return brand;
    }

    public int getMonthsOfWarranty() {
        return monthsOfWarranty;
    }



    @Override
    public String toString() {
        return "Electronics :- " +
                super.toString()+
                ", brand='" + brand + '\'' +
                ", monthsOfWarranty=" + monthsOfWarranty;
    }
    public String fileData(){
        return "Electronics" + "\n" +
                super.fileData() +
                brand + "\n" +
                monthsOfWarranty + "\n";

    }
}
