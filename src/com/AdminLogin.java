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


                Admin tempAdmin = new Admin();
                tempAdmin.setUsername(txtUsername.getText());
                tempAdmin.setPassword(txtPassword.getText());

                try{
                    for (int x = 0; x < subAdmins.size(); x ++){
                        if(tempAdmin.getUsername().equals(subAdmins.get(x).getUsername())
                        && tempAdmin.getPassword().equals(subAdmins.get(x).getPassword())){

                            setVisible(false);
                            AdminKiosk Page = new AdminKiosk();
                            Page.setVisible(true);
                            break;

                        }else{
                            System.out.println("Incorrect username or password...lol");
                        }
                    }
                }catch(Exception r){
                    r.printStackTrace();
                }
            }
        });
    }


    public static void main(String[] args) {
        AdminLogin Page = new AdminLogin();
        Page.setVisible(true);
    }
}
