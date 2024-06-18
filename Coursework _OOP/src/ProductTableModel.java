import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ProductTableModel extends AbstractTableModel {

    private String[] columnNames = {"Product ID", "Name", "Category", "Price(£)", "Info"};
    private ArrayList <Product> productsList;

    public ProductTableModel(ArrayList <Product> productsList){
        this.productsList = productsList;
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
            temp = productsList.get(rowIndex).getProductID();
        }
        else if(columnIndex == 1){
            temp = productsList.get(rowIndex).getProductName();
        }
        else if(columnIndex == 2){
            if(productsList.get(rowIndex) instanceof Electronics){
                temp = "Electronics";
            }
            else if(productsList.get(rowIndex) instanceof Clothing){
                temp = "Clothing";
            }
        }
        else if(columnIndex == 3){
            temp = productsList.get(rowIndex).getProductPrice();
        }
        else if(columnIndex == 4){
            if(productsList.get(rowIndex) instanceof Electronics){
                temp = ((Electronics) productsList.get(rowIndex)).getBrand() + ", " + ((Electronics) productsList.get(rowIndex)).getMonthsOfWarranty();
            }
            else if(productsList.get(rowIndex) instanceof Clothing){
                temp = ((Clothing) productsList.get(rowIndex)).getSize() + ", " + ((Clothing) productsList.get(rowIndex)).getColour();
            }
        }return temp;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

}
