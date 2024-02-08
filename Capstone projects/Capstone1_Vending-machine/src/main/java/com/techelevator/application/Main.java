package com.techelevator.application;

import com.techelevator.VendingMachineCLI;
import com.techelevator.view.Menu;
import com.techelevator.utilities.InventoryLoader;
import com.techelevator.vendingmachine.VendingMachineItem;

import java.util.Map;

public class Main {

    public static void main(String[] args) {
        String inventoryFileName = "src/main/java/inventory.csv";
        Map<String, VendingMachineItem> inventory = InventoryLoader.loadInventory(inventoryFileName);

        Menu menu = new Menu(System.in, System.out);
        VendingMachineCLI vendingMachineCLI = new VendingMachineCLI(menu, inventory);
        vendingMachineCLI.run();
    }
}
