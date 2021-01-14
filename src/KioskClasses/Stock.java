package KioskClasses;

public class Stock {

    //Must be private so that other classes can't alter the values wrongly
    private String ItemName;
    private String ItemID;
    private float ItemPrice;
    private int Quantity;
    private int ActiveStock;


    //Gets and Sets ensure that the correct type of variables are being used
    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getItemID() {
        return ItemID;
    }

    public void setItemID(String itemID) {
        ItemID = itemID;
    }

    public float getItemPrice() {
        return ItemPrice;
    }

    public void setItemPrice(float itemPrice) {
        ItemPrice = itemPrice;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public int getActiveStock() {
        return ActiveStock;
    }

    public void setActiveStock(int activeStock) {
        ActiveStock = activeStock;
    }
}
