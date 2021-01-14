package KioskClasses;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class stockDataManager {

    public String stockPath = "resources\\Stock.txt";
    public String separator = "\\|";

    public ArrayList<Stock> getStocks(){return stocks;}


    private ArrayList<Stock> stocks = new ArrayList<>();


    //This will load in the stock data from the specified file path
    public void stockLoad(){

        try{

            File stockFile = new File(stockPath);

            //Scanning
            Scanner stockScanner = new Scanner(stockFile);
            while(stockScanner.hasNextLine()){

                String sData = stockScanner.nextLine();

                String[] stockData = sData.split(separator);

                Stock newStock = new Stock();

                newStock.setItemID(stockData[0]);

                newStock.setItemName(stockData[1]);

                float inItemCost = Float.parseFloat(stockData[2]);
                newStock.setItemPrice(inItemCost);

                int inItemNum = Integer.parseInt(stockData[3]);
                newStock.setQuantity(inItemNum);

                int inActiveNum = Integer.parseInt(stockData[4]);
                newStock.setActiveStock(inActiveNum);

                stocks.add(newStock);
            }
             stockScanner.close();

            System.out.println("Stock file successfully loaded");

        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void saveStock (){
        try{
            FileWriter stockWriter  = new FileWriter (stockPath);

            for(int i = 0; i < stocks.size(); i ++){
                String data = "";

                if(i > 0){
                    data += "\n";
                }

                data += stocks.get(i).getItemID();

                data += "|" + stocks.get(i).getItemName();

                String inItemCost = Float.toString(stocks.get(i).getItemPrice());
                data += "|" + inItemCost;

                String inItemNum = Integer.toString(stocks.get(i).getQuantity());
                data += "|" + inItemNum;

                String inActiveNum = Integer.toString(stocks.get(i).getActiveStock());
                data += "|" + inActiveNum;

                stockWriter.write(data);


            }

            stockWriter.close();

            System.out.println("Stock file saved");


        }catch(IOException e){
            e.printStackTrace();

        }

    }




}
