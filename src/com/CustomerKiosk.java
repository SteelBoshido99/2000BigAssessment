package com;


import KioskClasses.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class CustomerKiosk extends JFrame{
    private JButton btnPay;
    private JButton btnLogin;
    private JButton btnAdd;
    private JPanel mainPanel;
    private JTextField txtScannedItem;
    private JButton btnSubTotal;
    private JLabel lblTotal;
    private JButton btnCash;
    private JButton btnCard;
    private JButton btnPrint;
    private JLabel lblChange;
    private JLabel lblPin;
    private JTextField txtPin;
    private JButton btnVerify;
    private JLabel lblCashPayment;
    private JButton btnEnd;

    //These need to be public so that the Threading class can access them
    public  JTextArea txtReceipt;
    public JTextArea txtBasket;
    public static String currentReceipt;
    public JTextField txtPayment;
    public static Object currentKiosk;
    public static int cashOrCard;
    public float payment;
    public static float finalTotal = 0f;
    public static String cashChange;

    //Creates an array in the Customer Kiosk to allow for manipulation of data
    public static ArrayList <Stock> subStock = new ArrayList<>();

    public void setTempArrayStock(ArrayList<Stock> tempStock){
        this.subStock = tempStock;
    }

    public JLabel getLblTotal() {
        return lblTotal;
    }

    public void setTxtReceipt(String txtInReceipt) {
        txtReceipt.append(txtInReceipt);
    }

    public CustomerKiosk() {
        //Creates an instance of CustomerKiosk and places it the Object
        currentKiosk = this;

        btnCard.setVisible(false);
        btnCash.setVisible(false);
        txtPayment.setVisible(false);
        btnPrint.setVisible(false);
        btnSubTotal.setVisible(false);
        lblChange.setVisible(false);
        lblPin.setVisible(false);
        txtPin.setVisible(false);
        btnVerify.setVisible(false);
        txtReceipt.setVisible(false);
        lblCashPayment.setVisible(false);
        btnEnd.setVisible(false);

        stockDataManager display = new stockDataManager();


        display.stockLoad();
        setTempArrayStock(display.getStocks());


        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 500));
        setTitle("Customer Kiosk");
        pack();

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminLogin loginPage = new AdminLogin();
                loginPage.setVisible(true);


                setVisible(false);
            }
        });


        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Checks to see if the Scanned text field is empty
                if (String.valueOf(txtScannedItem.getText()).equals("")) {

                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),
                            "You did not enter a product.",
                            "No Item Found",
                            JOptionPane.WARNING_MESSAGE);
                }

                Stock addedStock = new Stock();
                addedStock.setItemID(txtScannedItem.getText());

                try{
                    for (int a = 0; a < subStock.size(); a ++) {
                        if (addedStock.getItemID().equals(subStock.get(a).getItemID())) {

                            txtBasket.setText("");
                            //Gets the value of the stock levels
                            int CustStock = subStock.get(a).getQuantity();

                            //Gets the value of the active stock levels
                            int activeShop = subStock.get(a).getActiveStock();

                            //A variable for the total of Stock - Active stock
                            int newStockNum = 0;


                            //Checks to see if stock is at 0, if not for the stock calculation
                            if(subStock.get(a).getQuantity() == 0){

                                txtScannedItem.setText("");

                                //Shoots a message to notify the customer that their scanned item has now more stock
                                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),
                                        "No more stock of: " + subStock.get(a).getItemName(),
                                        "No more Stock",
                                        JOptionPane.WARNING_MESSAGE);

                            }else{
                                activeShop += 1;

                                newStockNum = CustStock - activeShop;
                            }

                            subStock.get(a).setActiveStock(activeShop);
                            subStock.get(a).setQuantity(newStockNum);

                            display.saveStock();

                            //for each object inside this object do this
                           for(Stock stock : subStock){
                               //Ensures items that have not been scanned are left out the basket
                               if(stock.getActiveStock() > 0){
                                   txtBasket.append(stock.getActiveStock() + " x " + ""
                                           + stock.getItemName() + ".....£"
                                           + String.format("%.02f", stock.getItemPrice())
                                           + " each" + "\n");
                               }
                           }
                            float basketTotal = subStock.get(a).getItemPrice();
                            //Provides a middle ground so that the accumulated total is displayed
                            float runningTotal = Float.sum(basketTotal, finalTotal);
                            finalTotal = runningTotal;

                            //Shows the final total of the compiled basket
                            String priceToString = String.format("%.02f", finalTotal);
                            lblTotal.setText("Total: " + " £ " + priceToString);
                            break;
                        }
                    }

                    //Places the items in the txt in an Object (For threading)
                    currentReceipt = txtBasket.getText();

                    }catch (Exception i){
                        i.printStackTrace();
                    }

                //Clears the scanned item txt field
                txtScannedItem.setText("");
            }
        });

        btnPay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnCard.setVisible(true);
                btnCash.setVisible(true);
            }
        });


//This will only involve the card payment
        btnCard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnCash.setVisible(false);
                txtPin.setVisible(true);
                lblPin.setVisible(true);
                btnVerify.setVisible(true);
                //Determines whether the Threader class should use the card related outcome
                cashOrCard = 1;
            }
        });

        btnVerify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    //Gets the string from the Pin text field and places it in an int
                int inPin = Integer.parseInt(txtPin.getText());

                //Compares the input value with the set pin
                if(inPin == 6942){
                    txtPin.setText("Pin Accepted");
                    txtPin.enable(false);
                    btnPrint.setVisible(true);
                } else {
                    txtPin.setText("");
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),
                            "Wrong pin number",
                            "Wrong pin",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });



//This will only involve the cash payments
        btnCash.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                lblCashPayment.setVisible(true);
                btnSubTotal.setVisible(true);
                txtPayment.setVisible(true);
                btnCard.setVisible(false);
                //Determines whether the Threader class should use the cash related outcome
                cashOrCard = 2;

            }
        });


        btnSubTotal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Calculates the change for the customer if cash was used
                payment = Float.parseFloat(txtPayment.getText());
                cashChange = String.valueOf(String.format("%.02f", payment - finalTotal) );
                lblChange.setVisible(true);
                lblChange.setText("Change " + "£ " + cashChange);
                btnPrint.setVisible(true);

            }
        });



        btnPrint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblTotal.setVisible(false);
                txtBasket.setVisible(false);
                txtReceipt.setVisible(true);
                txtReceipt.setEditable(false);

                receiptThreader pay = new receiptThreader();

                //Places the instance of CustomerKiosk into activeReceipt in the Threading Class
                pay.activeReceipt = (CustomerKiosk) currentKiosk;

                pay.SwingLoader();

                //Will reset the Active Stock to 0
                for (KioskClasses.Stock Stock: subStock ) {
                    Stock.setActiveStock(0);
                    display.saveStock();
                }

                btnCard.setVisible(false);
                btnCash.setVisible(false);
                txtPayment.setVisible(false);
                btnPrint.setVisible(false);
                btnSubTotal.setVisible(false);
                lblChange.setVisible(false);
                lblPin.setVisible(false);
                txtPin.setVisible(false);
                btnVerify.setVisible(false);
                lblCashPayment.setVisible(false);
                btnEnd.setVisible(true);

            }
        });

        btnEnd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                //Creates a new Kiosk for another transaction
                CustomerKiosk newKiosk = new CustomerKiosk();
                newKiosk.setVisible(true);

                finalTotal = 0;
            }
        });
    }

    public static void main(String[] args) {
        CustomerKiosk mainKiosk = new CustomerKiosk();
            mainKiosk.setVisible(true);
    }

}
