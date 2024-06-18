import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class CartTableModel extends AbstractTableModel {

    private String[] columnNames = {"Product", "Quantity", "Price(£)"};
    private ArrayList<Product> productsList;
    private ArrayList <Integer>  quantityList;

    private ArrayList <Double> pricePerProductList;
    public CartTableModel(ArrayList <Product> productsList, ArrayList <Integer> quantityList, ArrayList <Double> pricePerProductList){
        this.productsList = productsList;
        this.quantityList = quantityList;
        this.pricePerProductList = pricePerProductList;
    }
    @Override
    public int getRowCount() {
        return productsList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object temp = null;
        if(columnIndex == 0){
            if(productsList.get(rowIndex) instanceof Electronics){
                temp = (productsList.get(rowIndex).getProductID() + ", " + productsList.get(rowIndex).getProductName() + ", " +
                        ((Electronics)productsList.get(rowIndex)).getBrand() + ", " + ((Electronics) productsList.get(rowIndex)).getMonthsOfWarranty());
            }
            else if(productsList.get(rowIndex) instanceof Clothing){
                temp = (productsList.get(rowIndex).getProductID() + ", " + productsList.get(rowIndex).getProductName() + ", " +
                        ((Clothing) productsList.get(rowIndex)).getSize() + ", " + ((Clothing) productsList.get(rowIndex)).getColour());
            }
        }
        else if(columnIndex == 1){
            temp = quantityList.get(rowIndex);
        }
        else if(columnIndex == 2){
           temp = pricePerProductList.get(rowIndex);
        }return temp;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

}
