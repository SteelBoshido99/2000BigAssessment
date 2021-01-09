package com;

import KioskClasses.Admin;
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
    private JPanel CustPannel;
    private JTextField txtScanedItem;
    private JLabel lblTotal;


    ArrayList<Stock> subStock = new ArrayList<>();

    public void setTempArrayStock(ArrayList<Stock> tempStock){
        this.subStock = tempStock;
    }
    float finalTotal = 0f;


    public CustomerKiosk() {

        stockDataManager display = new stockDataManager();
        display.stockLoad();
        setTempArrayStock(display.getStocks());

        setContentPane(CustPannel);
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
                addedStock.setItemID(txtScanedItem.getText());


                try{
                    for (int a = 0; a < subStock.size(); a ++){
                        if (addedStock.getItemID().equals(subStock.get(a).getItemID())){
                            //if(subStock.get(a).getQuantity() < )

                            txtBasket.append(subStock.get(a).getItemName() + "\n");

                            float basketTotal = subStock.get(a).getItemPrice();
                            float runningTotal = Float.sum (basketTotal,finalTotal);
                            finalTotal = runningTotal;

                            lblTotal.setText(String.valueOf(finalTotal));
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
