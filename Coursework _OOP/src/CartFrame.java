import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CartFrame extends JFrame {
    private JTable cartTable;

    private JButton deleteButton;
    private JTextArea priceInfo;
    private JButton placeOrder;
    private ArrayList<Product> cartArrayList;
    private ArrayList<Integer> quantityArrayList;
    private ArrayList<Double> totalPriceArrayList;




    CartFrame(){

        ShoppingCart viewCart = new ShoppingCart();
        cartArrayList = ShoppingCart.getCart();
        quantityArrayList = ShoppingCart.getOrderedQuantity();
        totalPriceArrayList = ShoppingCart.getTotalPricePerproduct();

        setLayout(new BorderLayout(5,5));


        JPanel panel = new JPanel(new BorderLayout());
        CartTableModel cartTableModel = new CartTableModel(cartArrayList,quantityArrayList,totalPriceArrayList);
        cartTable = new JTable(cartTableModel);
        cartTable.setPreferredScrollableViewportSize(new Dimension(300, 100));
        panel.add(new JScrollPane(cartTable), BorderLayout.CENTER);
        deleteButton = new JButton("Delete");

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = cartTable.getSelectedRow();
                if (selectedRow != -1) {

                    ShoppingCart.getCart().remove(selectedRow);
                    ShoppingCart.getOrderedQuantity().remove(selectedRow);
                    ShoppingCart.getTotalPricePerproduct().remove(selectedRow);


                    cartTableModel.fireTableDataChanged();


                    priceInfo.setText(viewCart.finalCost());
                }
            }
        });

        panel.add(deleteButton,BorderLayout.EAST);
        add(panel,BorderLayout.NORTH);


        priceInfo = new JTextArea();
        priceInfo.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        priceInfo.setEditable(false);
        priceInfo.setRows(5);
        priceInfo.setText(viewCart.finalCost());
        add(priceInfo,BorderLayout.CENTER);


        placeOrder = new JButton("Place Order");

        placeOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Reduce the quantity of products in the cart
                for (int i = 0; i < cartArrayList.size(); i++) {
                    Product product = cartArrayList.get(i);
                    int orderedQuantity = quantityArrayList.get(i);

                    int newStock = product.getProductStock() - orderedQuantity;
                    product.setProductStock(newStock);
                  priceInfo.setText("Order Placed Thank you");
                }

            }

        });
        add(placeOrder, BorderLayout.SOUTH);
    }
}




