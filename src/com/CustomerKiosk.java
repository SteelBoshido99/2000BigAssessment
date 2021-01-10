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


    ArrayList<Stock> subStock = new ArrayList<>();

    public void setTempArrayStock(ArrayList<Stock> tempStock){
        this.subStock = tempStock;
    }


    public static float finalTotal = 0f;


    public CustomerKiosk() {

        stockDataManager display = new stockDataManager();
        display.stockLoad();
        setTempArrayStock(display.getStocks());

        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 500));

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminLogin loginPage = new AdminLogin();
                loginPage.setVisible(true);

                CustomerKiosk main = new CustomerKiosk();
                main.setVisible(false);
            }
        });



        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Stock addedStock = new Stock();
                addedStock.setItemID(txtScannedItem.getText());

                try{
                    for (int a = 0; a < subStock.size(); a ++){
                        if (addedStock.getItemID().equals(subStock.get(a).getItemID())){
                            /* This will check to see if the scanned item already exists in the basket
                            and then add a multiplier to the item*/

                           /*  if(addedStock.getItemID().equals(txtBasket.getText())){

                                System.out.println("This can work");

                             }*/


                            //Prints out the scanned item on a new line, by list form
                            txtBasket.append(subStock.get(a).getItemName() + "\n");



                            float basketTotal = subStock.get(a).getItemPrice();
                            //Provides a middle ground so that the accumulated total is displayed
                            float runningTotal = Float.sum (basketTotal,finalTotal);
                            finalTotal = runningTotal;


                            String priceToString = String.format("%.02f",finalTotal);
                            lblTotal.setText(" £ " + priceToString);
                            break;

                        }
                    }
                }catch (Exception i){
                    i.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        CustomerKiosk mainKiosk = new CustomerKiosk();
            mainKiosk.setVisible(true);
    }
}
