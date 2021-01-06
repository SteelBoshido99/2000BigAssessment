package com;

import KioskClasses.stockDataManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AdminKiosk extends JFrame{

    private static  Object currentWindow;

    private JTextArea txtAdminStock;
    private JButton btnOrder;
    private JButton btnAdd;
    private JButton btnRemove;
    private JButton btnExit;
    private JPanel AdminPanel;
    private JButton btnLoadStock;

        public AdminKiosk(){

            setContentPane(AdminPanel);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setPreferredSize(new Dimension(500, 500));

            stockDataManager stkDisplay = new stockDataManager();

            stkDisplay.load();

            btnLoadStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtAdminStock.setText(String.valueOf(stkDisplay));
             }
            });

        }

    public static void main(String[] args) {
            AdminKiosk adminView = new AdminKiosk();
            adminView.setVisible(true);
    }








}




