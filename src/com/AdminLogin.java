package com;

import KioskClasses.Admin;
import KioskClasses.adminDataManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AdminLogin extends JFrame{

    private JTextField txtUsername;
    private JTextField txtPassword;
    private JButton btnAuthorise;
    private JPanel pnlLogin;
    private JButton btnCancel;



    //Creates a local array that will temporarily store the input account details
    private ArrayList<Admin> subAdmins = new ArrayList<>();

    public void setTempArrayAdmin(ArrayList<Admin> tempAdmin){
        this.subAdmins = tempAdmin;
    }



    public AdminLogin() {
        setContentPane(pnlLogin);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setPreferredSize(new Dimension(500, 500));

        btnAuthorise.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                adminDataManager staffLogin = new adminDataManager();
                staffLogin.adminLoad();
                setTempArrayAdmin(staffLogin.getAdmins());



                //Instantiates an the input Admin account and stores it in the temporary Array
                Admin tempAdmin = new Admin();
                tempAdmin.setUsername(txtUsername.getText());
                tempAdmin.setPassword(txtPassword.getText());

                try{
                    //Will go through the size of the temp array list
                    for (int x = 0; x < subAdmins.size(); x ++){
                        //Compares the details in the temporary array with the array in AdminLoad
                        if(tempAdmin.getUsername().equals(subAdmins.get(x).getUsername())
                        && tempAdmin.getPassword().equals(subAdmins.get(x).getPassword())){

                            setVisible(false);
                            AdminKiosk Page = new AdminKiosk();
                            Page.setVisible(true);
                            break;

                        }else{
                            System.out.println("Incorrect username or password");
                        }
                    }
                }catch(Exception r){
                    r.printStackTrace();
                }
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminLogin main = new AdminLogin();
                main.setVisible(false);
                main.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

                CustomerKiosk customerGUI = new CustomerKiosk();
                customerGUI.setVisible(true);
            }
        });
    }


    public static void main(String[] args) {
        AdminLogin Page = new AdminLogin();
        Page.setVisible(true);
    }
}
