package com.techelevator.vendingmachine;

import java.math.BigDecimal;
import java.util.Map;

public class VendingMachine {

    private Map<String, VendingMachineItem> inventory;
    private double balance = 0.0;

    public VendingMachine(Map<String, VendingMachineItem> inventory) {
        this.inventory = inventory;
    }

    public double getBalance() {
        return balance;
    }

    public Map<String, VendingMachineItem> getInventory() {
        return inventory;
    }

    public void purchaseItem(String slotLocation) throws InvalidPurchaseException{
        VendingMachineItem item = inventory.get(slotLocation);
        //Check if purchase is possible.
        if (item != null && item.getQuantity() > 0 && balance >= item.getPrice()) {

            item.decrementQuantity();
            //Convert balance to BigDecimal to account for floating point imprecision.
            BigDecimal balance = BigDecimal.valueOf(this.balance);
            BigDecimal price = BigDecimal.valueOf(item.getPrice());

            this.balance = balance.subtract(price).doubleValue();

        } else if (item != null && item.isSoldOut()){
            //If item is sold out, throw error message.
            throw new InvalidPurchaseException("Product sold out. Please make another selection.");
        }else if (item !=null&& balance<item.getPrice()){
            //If balance is lower than item price, throw error message.
            throw new InvalidPurchaseException("Not enough funds, please try again.");
        } else {
            //If no item was found, throw error message
            throw new InvalidPurchaseException("Invalid item, please try again.");
        }
    }

    public void feedMoney(double dollarsToAdd) {
        balance += dollarsToAdd;
    }

    public String getProductNameByCode(String productCode) {
        VendingMachineItem item = inventory.get(productCode);
        if (item != null) {
            return item.getName();
        }
        return null;
    }

    public double getProductPriceByCode(String productCode) {
        VendingMachineItem item = inventory.get(productCode);
        if (item != null) {
            return item.getPrice();
        }
        return 0.0;
    }

    public String getProductMessageByCode(String productCode){
        VendingMachineItem item = inventory.get(productCode);
        if (item != null){
            return getProductMessage(item.getType());
        }
        return "";
    }

    public String giveChange() {
        //Convert current balance to cents.
        int remainingBalanceInCents = (int) (balance*100);
        //calculate number of quarters and remove them from the remaining balance.
        int numberOfQuarters = remainingBalanceInCents / 25;
        remainingBalanceInCents %= 25;
        //calculate number of dimes and remove them from the remaining balance.
        int numberOfDimes = remainingBalanceInCents / 10;
        remainingBalanceInCents = remainingBalanceInCents % 10;
        //calculate number of nickels, set balance to 0 as all coins have now been accounted for.
        int numberOfNickels = remainingBalanceInCents / 5;
        balance = 0.0;

        return "Change given: " + numberOfQuarters + " quarters, " + numberOfDimes + " dimes, " + numberOfNickels + " nickels.";
    }

    private String getProductMessage(String type) {
        switch (type) {
            case "Chip":
                return "Crunch Crunch, Yum!";
            case "Candy":
                return "Munch Munch, Yum!";
            case "Drink":
                return "Glug Glug, Yum!";
            case "Gum":
                return "Chew Chew, Yum!";
            default:
                return "Yum!";
        }
    }
}
