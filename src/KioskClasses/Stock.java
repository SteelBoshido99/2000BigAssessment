package KioskClasses;

public class Stock {

    private String ItemName;
    private String ItemID;
    private float ItemPrice;
    private int Quantity;


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
}
