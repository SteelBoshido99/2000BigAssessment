package com;


import KioskClasses.Stock;
import KioskClasses.stockDataManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class CustomerKiosk extends JFrame{
    private JTextArea txtBasket;
    private JButton btnPay;
    private JButton btnLogin;
    private JButton btnAdd;
    private JPanel mainPanel;
    private JTextField txtScannedItem;
    private JLabel lblTotal;
    private JButton btnCash;
    private JButton btnCard;
    private JTextArea txtReceipt;


    ArrayList<Stock> subStock = new ArrayList<>();

    public void setTempArrayStock(ArrayList<Stock> tempStock){
        this.subStock = tempStock;
    }


    public static float finalTotal = 0f;


    public CustomerKiosk() {


        btnCard.setVisible(false);
        btnCash.setVisible(false);
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
                               //Ensures that no scanned items are printed onto the basket
                               if(stock.getActiveStock() > 0){
                                   txtBasket.append(stock.getActiveStock() + "" + stock.getItemName() + ".....£"
                                           + String.format("%.02f", stock.getItemPrice()) + " each" + "\n");
                               }
                           }

                            float basketTotal = subStock.get(a).getItemPrice();
                            //Provides a middle ground so that the accumulated total is displayed
                            float runningTotal = Float.sum(basketTotal, finalTotal);
                            finalTotal = runningTotal;

                            String priceToString = String.format("%.02f", finalTotal);
                            lblTotal.setText(" £ " + priceToString);
                            break;



                        }
                    }

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
    }

    public static void main(String[] args) {
        CustomerKiosk mainKiosk = new CustomerKiosk();
            mainKiosk.setVisible(true);
    }
}

 /*if (txtBasket.getText().equals("")) {

                            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),
                                    "You did not enter a product.",
                                    "No Item Found",
                                    JOptionPane.WARNING_MESSAGE);
                            break;
                        } else {
                        }*/
