package com;

import KioskClasses.Stock;
import KioskClasses.stockDataManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class AdminKiosk extends JFrame{

    private static  Object currentWindow;

    private JButton btnUpdate;
    private JButton btnExit;
    private JPanel AdminPanel;
    private JButton btnOrder;
    private JTable tblStock;
    private JButton btnSave;
    private JButton btnCheck;
    private JButton btnAdd;

    public static ArrayList<Stock> stockTable = new ArrayList<>();

    public void setArrayStock(ArrayList<Stock> stockTable)
    {this.stockTable = stockTable;}

    DefaultTableModel tempTable;


        public AdminKiosk(){
            stockDataManager tableData = new stockDataManager();
            tableData.stockLoad();

            setArrayStock(tableData.getStocks());


            setContentPane(AdminPanel);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setPreferredSize(new Dimension(500, 500));
            pack();

            String[] columnNames = {"Barcode", "Item", "(£)Price", "Stock"};

            tempTable = new DefaultTableModel(columnNames, 0);
            tblStock.setModel(tempTable);
            tempTable.getDataVector().removeAllElements();

            tempTable.addRow(columnNames);

            //for each object inside this object do this
            for(KioskClasses.Stock Stock:stockTable){
                Object[] data ={Stock.getItemID(),Stock.getItemName(), Stock.getItemPrice(), Stock.getQuantity()};
                tempTable.addRow(data);
            }


            btnCheck.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    //for each object inside this object do this
                    for(KioskClasses.Stock Stock:stockTable){
                        if(Stock.getQuantity() < 1){
                            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),
                                    "Need to order: " + Stock.getItemName(),
                                    "Order Stock",
                                    JOptionPane.WARNING_MESSAGE);
                            break;
                        }
                    }
                }
            });

            btnUpdate.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    tblStock.setEnabled(true);

                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),
                            "You can now edit the stock",
                            "Stock Edit",
                            JOptionPane.WARNING_MESSAGE);
                }
            });

            btnSave.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    stockTable.add(new Stock());

                    for(int i = 1; i < tempTable.getRowCount(); i++ ){
                        if(tempTable.getValueAt(i, 0) == null){
                            break;
                        }else{
                            stockTable.get(i-1).setItemID(String.valueOf(tempTable.getValueAt(i, 0)));
                            stockTable.get(i-1).setItemName(String.valueOf(tempTable.getValueAt(i, 1)));
                            stockTable.get(i-1).setItemPrice(Float.valueOf( String.valueOf(tempTable.getValueAt(i, 2))));
                            stockTable.get(i-1).setQuantity(Integer.valueOf( String.valueOf(tempTable.getValueAt(i, 3))));
                        }
                    }
                    tableData.saveStock();

                    dispose();
                    setVisible(true);
                    tblStock.setEnabled(false);

                }
            });

            btnAdd.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    tempTable.addRow(new Object[]{"","","",""});
                }
            });

            btnExit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    AdminLogin backLogin = new AdminLogin();
                    backLogin.setVisible(true);

                    dispose();
                }
            });

            btnOrder.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),
                            "Stock has been ordered",
                            "Order Stock",
                            JOptionPane.WARNING_MESSAGE);
                }
            });
        }
}




