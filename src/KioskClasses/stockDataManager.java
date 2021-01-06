package KioskClasses;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class stockDataManager {

    public String stockPath = "resources\\Stock.txt";
    public String separator = "\\|";



    public final ArrayList<Stock> stocks = new ArrayList<>();

    public void load(){

        try{

            File stockFile = new File(stockPath);

            Scanner stockScanner = new Scanner(stockFile);
            while(stockScanner.hasNextLine()){

                String data = stockScanner.nextLine();

                String[] stockData = data.split(separator);

                Stock newStock = new Stock();

                int inItemID = Integer.parseInt(stockData[0]);
                newStock.setItemID(inItemID);

                newStock.setItemName(stockData[1]);

                float inItemCost = Float.parseFloat(stockData[2]);
                newStock.setItemPrice(inItemCost);

                int inItemNum = Integer.parseInt(stockData[3]);
                newStock.setQuantity(inItemNum);

                stocks.add(newStock);
            }
             stockScanner.close();

            System.out.println("Stock file successfully loaded");



        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }




}
