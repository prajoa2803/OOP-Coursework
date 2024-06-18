import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collections;


public class ShoppingFrame extends JFrame {
    private JButton shoppingCart;
    private JComboBox productCategory;
    private JTable productTable;
    private JTextArea productDetails;
    private JTextField quantity;
    private JButton addToCart;
    private ArrayList<Product> productArrayList;
    private ShoppingCart cart;
    private CartFrame cartFrame;

    ShoppingFrame() {

        cart = new ShoppingCart();
        setLayout(new BorderLayout());

        JPanel shoppingCartPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 25, 1));
        shoppingCart = new JButton("Shopping Cart");
        shoppingCartPanel.add(shoppingCart);
        ViewCartHandler viewCartHandler = new ViewCartHandler();
        shoppingCart.addActionListener(viewCartHandler);

        JPanel comboBoxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 25, 1));

        comboBoxPanel.add(new JLabel("Select Product Category"));

        String[] category = {"All", "Electronics", "Clothing"};
        productCategory = new JComboBox(category);
        CategoryListener categoryListener = new CategoryListener();
        productCategory.addItemListener(categoryListener);

        comboBoxPanel.add(productCategory);

        JPanel northPanel = new JPanel(new GridLayout(2, 1, 25, 1));
        northPanel.add(shoppingCartPanel);
        northPanel.add(comboBoxPanel);

        WestminsterShoppingManager westminsterShoppingManager = new WestminsterShoppingManager();
        productArrayList = westminsterShoppingManager.getProducts();
        Collections.sort(productArrayList);
        ProductTableModel productTableModel = new ProductTableModel(productArrayList);

        productTable = new JTable(productTableModel);
        JScrollPane centralPane = new JScrollPane(productTable);
        ListSelectionModel selectionModel = productTable.getSelectionModel();
        TableListener tableListener = new TableListener();
        selectionModel.addListSelectionListener(tableListener);


        productDetails = new JTextArea();
        productDetails.setRows(6);
        productDetails.setEditable(false);

        quantity = new JTextField("1", 3);
        JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 25, 1));
        quantityPanel.add(new JLabel("Quantity: "));
        quantityPanel.add(quantity);

        JPanel addToCartPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 1));
        addToCart = new JButton("Add to Shopping Cart");
        addToCartPanel.add(addToCart);
        AddToCartListener addToCartListener = new AddToCartListener();
        addToCart.addActionListener(addToCartListener);

        JPanel southPanel = new JPanel(new GridLayout(4, 1, 25, 1));
        southPanel.add(new JLabel("Selected Product - Details"));
        southPanel.add(productDetails);
        southPanel.add(quantityPanel);
        southPanel.add(addToCartPanel);

        add(northPanel, BorderLayout.NORTH);
        add(centralPane, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);


    }

    private class TableListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                int row = productTable.getSelectedRow();
                String productID = (String) productTable.getValueAt(row, 0);

                for (int i = 0; i < productArrayList.size(); i++) {
                    if (productID.equals(productArrayList.get(i).getProductID())) {
                        if (productArrayList.get(i) instanceof Electronics) {
                            productDetails.setText("Product id: " + productArrayList.get(i).getProductID() + "\n" +
                                    "Category: Electronics" + "\n" +
                                    "Name: " + productArrayList.get(i).getProductName() + "\n" +
                                    "Brand: " + ((Electronics) productArrayList.get(i)).getBrand() + "\n" +
                                    "Warranty (months): " + ((Electronics) productArrayList.get(i)).getMonthsOfWarranty() + "\n" +
                                    "Items Available: " + productArrayList.get(i).getProductStock());
                        } else if (productArrayList.get(i) instanceof Clothing) {
                            productDetails.setText("Product id: " + productArrayList.get(i).getProductID() + "\n" +
                                    "Category: Clothing" + "\n" +
                                    "Name: " + productArrayList.get(i).getProductName() + "\n" +
                                    "Size: " + ((Clothing) productArrayList.get(i)).getSize() + "\n" +
                                    "Colour: " + ((Clothing) productArrayList.get(i)).getColour() + "\n" +
                                    "Items Available: " + productArrayList.get(i).getProductStock());
                        }
                    }
                }
            }
        }
    }

    private class CategoryListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {

                if (e.getItem().equals("Electronics")) {
                    ArrayList <Product> electronics = new ArrayList<Product>();

                    for(int i = 0; i < productArrayList.size();i++){
                        if (productArrayList.get(i) instanceof Electronics){
                            electronics.add(productArrayList.get(i));
                        }
                    }
                    ProductTableModel productTableModel = new ProductTableModel(electronics);
                    productTable.setModel(productTableModel);
                } else if (e.getItem().equals("Clothing")) {
                    ArrayList <Product> clothing = new ArrayList<Product>();

                    for(int i = 0; i < productArrayList.size();i++){
                        if (productArrayList.get(i) instanceof Clothing){
                            clothing.add(productArrayList.get(i));
                        }
                    }
                    ProductTableModel productTableModel = new ProductTableModel(clothing);
                    productTable.setModel(productTableModel);
                } else if (e.getItem().equals("All")) {
                    ProductTableModel productTableModel = new ProductTableModel(productArrayList);
                    productTable.setModel(productTableModel);
                }
            }
        }
    }

    private class AddToCartListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int row = productTable.getSelectedRow();

            if (row != -1){
                String productID = (String) productTable.getValueAt(row, 0);
                String orderedQuantity = quantity.getText();
                try{
                    int orderedAmount = Integer.parseInt(orderedQuantity);

                    for (int i = 0; i < productArrayList.size(); i++) {
                        if (productID.equals(productArrayList.get(i).getProductID())) {
                            if (productArrayList.get(i) instanceof Electronics) {

                                String productName = productArrayList.get(i).getProductName();
                                int productStock = productArrayList.get(i).getProductStock();
                                double productPrice = productArrayList.get(i).getProductPrice();
                                String brand = ((Electronics) productArrayList.get(i)).getBrand();
                                int monthsOfWarranty = ((Electronics) productArrayList.get(i)).getMonthsOfWarranty();
                                double totalPricePerProduct = productArrayList.get(i).getProductPrice()*orderedAmount;
                                Product product = new Electronics(productID, productName, productStock, productPrice, brand, monthsOfWarranty);
                                cart.addToCart(product, orderedAmount, totalPricePerProduct);
//
                            } else if (productArrayList.get(i) instanceof Clothing) {

                                String productName = productArrayList.get(i).getProductName();
                                int productStock = productArrayList.get(i).getProductStock();
                                double productPrice = productArrayList.get(i).getProductPrice();
                                String size = ((Clothing) productArrayList.get(i)).getSize();
                                String colour  = ((Clothing) productArrayList.get(i)).getColour();
                                double totalPricePerProduct = productArrayList.get(i).getProductPrice()*orderedAmount;
                                Product product = new Clothing(productID, productName, productStock, productPrice, size, colour);
                                cart.addToCart(product, orderedAmount,totalPricePerProduct);
                            }
                                productDetails.setText(null);

                        }
                    }


                }catch(NumberFormatException exception){
                    productDetails.append("\nEnter a Number");
                }

            }else{
                productDetails.setText("Select a Product");
            }
        }
    }

    private class ViewCartHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (cartFrame != null) {
                cartFrame.dispose();
            }

           cartFrame = new CartFrame();
           cartFrame.setTitle("Shopping Cart");
           cartFrame.setVisible(true);
           cartFrame.setSize(400,350);
           cartFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        }
    }
}

