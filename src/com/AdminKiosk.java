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

    public void setArrayStock(ArrayList<Stock> stockTable) {
        this.stockTable = stockTable;
    }

    //Creates a another JTable to overlay the original JTable (tbl Stock)
    DefaultTableModel tempTable;

        public AdminKiosk(){

            stockDataManager tableData = new stockDataManager();
            tableData.stockLoad();
            setArrayStock(tableData.getStocks());


            setContentPane(AdminPanel);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setPreferredSize(new Dimension(500, 500));
            setTitle("Stock Manager");
            pack();


            String[] columnNames = {"Barcode", "Item", "(£)Price", "Stock"};

            //Set the columns in tempTable
            tempTable = new DefaultTableModel(columnNames, 0);

            //Sets the table model into the tbl Stock
            tblStock.setModel(tempTable);
            tempTable.getDataVector().removeAllElements();

            tempTable.addRow(columnNames);

            //for each object inside stockTable object add a new row
            for(KioskClasses.Stock Stock:stockTable){
                Object[] data ={Stock.getItemID(),Stock.getItemName(), Stock.getItemPrice(), Stock.getQuantity()};
                tempTable.addRow(data);
            }


            btnCheck.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    //for each object inside stockTable object check to see if the stock level is less than 5
                    for(KioskClasses.Stock Stock:stockTable){
                        if(Stock.getQuantity() < 5){
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
                    //Allows the user to edit the table directly
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

                    //The int must start at 1 to make it skip the first row which contain the column titles
                    for(int i = 1; i < tempTable.getRowCount(); i++ ){
                        if(tempTable.getValueAt(i, 0) == null){
                            break;
                        }else{
                            stockTable.get(i-1).setItemID(String.valueOf(tempTable.getValueAt(i, 0)));
                            stockTable.get(i-1).setItemName(String.valueOf(tempTable.getValueAt(i, 1)));
                            stockTable.get(i-1).setItemPrice(Float.parseFloat( String.valueOf(tempTable.getValueAt(i, 2))));
                            stockTable.get(i-1).setQuantity(Integer.parseInt( String.valueOf(tempTable.getValueAt(i, 3))));
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
                    //Creates a new object for the input Item to go in
                    stockTable.add(new Stock());
                    //Adds a blank row in the table for the user to edit directly
                    tempTable.addRow(new Object[]{"","","",""});
                    tblStock.setEnabled(true);
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




