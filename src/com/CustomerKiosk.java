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

    public  JTextArea txtReceipt;
    public JTextArea txtBasket;
    public static String currentReceipt;
    public JTextField txtPayment;
    public static Object currentKiosk;
    int cashOrCard;



    public static ArrayList <Stock> subStock = new ArrayList<>();

    public void setTempArrayStock(ArrayList<Stock> tempStock){
        this.subStock = tempStock;
    }


    public static float finalTotal = 0f;
    public static String cashChange;

    public JLabel getLblTotal() {
        return lblTotal;
    }

    public CustomerKiosk() {
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


        stockDataManager display = new stockDataManager();
        display.stockLoad();
        setTempArrayStock(display.getStocks());

        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 500));
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

                Stock addedStock = new Stock();
                addedStock.setItemID(txtScannedItem.getText());

                try{
                    for (int a = 0; a < subStock.size(); a ++) {
                        if (addedStock.getItemID().equals(subStock.get(a).getItemID())) {

                            txtBasket.setText("");

                            int activeShop = subStock.get(a).getActiveStock();

                            activeShop += 1;

                            subStock.get(a).setActiveStock(activeShop);


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

                            String priceToString = String.format("%.02f", finalTotal);
                            lblTotal.setText("Total: " + " £ " + priceToString);
                            break;
                        }
                    }

                    //------
                    currentReceipt = txtBasket.getText();

                    }catch (Exception i){
                        i.printStackTrace();
                    }
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
                cashOrCard = 1;
            }
        });

        btnVerify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int inPin = Integer.parseInt(txtPin.getText());

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

                btnSubTotal.setVisible(true);
                txtPayment.setVisible(true);
                btnCard.setVisible(false);
                cashOrCard = 2;

            }
        });


        btnSubTotal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float payment = Float.parseFloat(txtPayment.getText());
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
                pay.currentPayment = (CustomerKiosk) currentKiosk;


                if(cashOrCard == 1){

                    pay.swingLoaderCard();

                } else {

                    pay.cashSwingLoader();

                }
            }
        });

    }

    public static void main(String[] args) {
        CustomerKiosk mainKiosk = new CustomerKiosk();
            mainKiosk.setVisible(true);
    }





}
//Will reset the Active Stock
/*for (com.products products: adminProducts ) {
        products.setProductQuantity(0);
        }*/


 /*if (txtBasket.getText().equals("")) {

                            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),
                                    "You did not enter a product.",
                                    "No Item Found",
                                    JOptionPane.WARNING_MESSAGE);
                            break;
                        } else {
                        }*/
