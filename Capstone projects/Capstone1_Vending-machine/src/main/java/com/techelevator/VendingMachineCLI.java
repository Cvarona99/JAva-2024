package com.techelevator;

import com.techelevator.utilities.TransactionLogger;
import com.techelevator.view.Menu;
import com.techelevator.vendingmachine.*;

import java.text.NumberFormat;
import java.util.Map;

public class VendingMachineCLI {

    private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
    private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
    private static final String MAIN_MENU_OPTION_EXIT = "Exit";
    private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT};
    private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
    private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
    private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
    private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION};

    private Menu menu;
    private VendingMachine vendingMachine;

    public VendingMachineCLI(Menu menu, Map<String, VendingMachineItem> inventory) {
        this.menu = menu;
        this.vendingMachine = new VendingMachine(inventory);
    }

    public void run() {
        while (true) {
            String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

            if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
                displayVendingMachineItems();
            } else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
                purchaseMenu();
            } else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
                System.exit(0);
            }
        }
    }

    private void purchaseMenu() {
        while (true) {
            String purchaseChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

            if (purchaseChoice.equals("Feed Money")) {
                feedMoney();
            } else if (purchaseChoice.equals("Select Product")) {
                // Display the list of available products
                displayVendingMachineItems();
                selectProduct();
            } else if (purchaseChoice.equals("Finish Transaction")) {
                finishTransaction();
                break; // Exit the purchase menu
            }
            displayCurrentMoneyProvided();
        }
    }

    private void displayVendingMachineItems() {
        System.out.println("Current Vending Machine Inventory:");
        Map<String, VendingMachineItem> inventory = vendingMachine.getInventory();

        for (Map.Entry<String, VendingMachineItem> entry : inventory.entrySet()) {
            VendingMachineItem item = entry.getValue();
            System.out.println(item.getSlotLocation() + " | " + item.getName() + " | $" + item.getPrice() + " | " + item.getType() + " | Quantity: " + item.getQuantity());
        }
        System.out.println();
    }

    private void feedMoney() {
        // Prompt the user to enter an amount and update the balance
        System.out.print("Enter whole dollar amount to feed: ");
        double dollarsToAdd = menu.getUserInputInteger();
        vendingMachine.feedMoney(dollarsToAdd); // Update the balance in the VendingMachine
        TransactionLogger.logTransaction("FEED MONEY", dollarsToAdd, vendingMachine.getBalance());
    }

    private void selectProduct() {
        // Prompt the user to select a product by entering its code
        System.out.print("Enter the code of the product you want to purchase: ");
        String productCode = menu.getUserInput();

        // Attempt to select the product, handle errors if necessary
        try {
            vendingMachine.purchaseItem(productCode);
            dispenseItem(productCode);
            logTransaction(productCode);
        }
        catch (InvalidPurchaseException e){
            System.out.println(e.getMessage());
        }
    }

    private void logTransaction(String productCode) {
        TransactionLogger.logTransaction(
                vendingMachine.getProductNameByCode(productCode),
                vendingMachine.getProductPriceByCode(productCode),
                vendingMachine.getBalance());
    }

    private void dispenseItem(String productCode) {
        System.out.println("Dispensing: " + vendingMachine.getProductNameByCode(productCode) +
                " Cost: " + vendingMachine.getProductPriceByCode(productCode));
        System.out.println(vendingMachine.getProductMessageByCode(productCode));
    }

    private void finishTransaction() {
        // Finish the transaction and give change
        double remainingBalance = vendingMachine.getBalance();
        System.out.println(vendingMachine.giveChange());
        TransactionLogger.logTransaction("GIVE CHANGE", remainingBalance, 0);
        System.out.println("Transaction complete. Change given: $" + remainingBalance);
    }

    private void displayCurrentMoneyProvided() {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
        System.out.println();
        System.out.println("Current Money Provided: " + numberFormat.format(vendingMachine.getBalance()));
    }
}
