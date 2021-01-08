package KioskClasses;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class adminDataManager {

    public String adminPath = "resources\\Admin.txt";
    public String separator = "\\|";

    public  ArrayList<Admin> getAdmins(){
        return admins;
    }

    private ArrayList<Admin> admins = new ArrayList<>();

    public void adminLoad(){

        try{
            File adminFile = new File(adminPath);

            Scanner adminScanner = new Scanner(adminFile);
            while(adminScanner.hasNextLine()){

                String aData = adminScanner.nextLine();

                String[] adminData = aData.split(separator);

                Admin newAdmin = new Admin();

                newAdmin.setUsername(adminData[0]);

                newAdmin.setPassword(adminData[1]);

                admins.add(newAdmin);
            }

            adminScanner.close();


            System.out.println("Admins loaded successfully");

        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
