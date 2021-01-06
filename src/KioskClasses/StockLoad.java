package KioskClasses;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class StockLoad {

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

            }


        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }




}
