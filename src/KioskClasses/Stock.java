package KioskClasses;

public class Stock {

    private String ItemName;
    private int ItemID;
    private float ItemPrice;
    private int Quantity;


    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public int getItemID() {
        return ItemID;
    }

    public void setItemID(int itemID) {
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
}
