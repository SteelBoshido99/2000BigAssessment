package KioskClasses;

import java.util.ArrayList;

public class shoppingList{

    public int listStock;

    public ArrayList<Stock> getStockList(){
        return stockList;
    }

    private ArrayList<Stock> stockList = new ArrayList<>();

    public void setListStock(int listStock) {
        this.listStock = listStock;
    }

    public int getListStock() {
        return listStock;
    }

}
