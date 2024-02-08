package com.techelevator.vendingmachine;

public class VendingMachineItem {
    private String slotLocation;
    private String name;
    private double price;
    private String type;
    private int quantity;
    private final int MAX_QUANTITY = 5;

    public VendingMachineItem(String slotLocation, String name, double price, String type) {
        this.slotLocation = slotLocation;
        this.name = name;
        this.price = price;
        this.type = type;
        this.quantity = MAX_QUANTITY;
    }

    public int getMaxQuanity(){return MAX_QUANTITY; }

    public String getSlotLocation() {
        return slotLocation;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void decrementQuantity() {
        if (quantity > 0) {
            quantity--;
        }
    }

    public boolean isSoldOut() {
        return quantity == 0;
    }

    @Override
    public String toString() {
        return name + " - $" + price;
    }
}

