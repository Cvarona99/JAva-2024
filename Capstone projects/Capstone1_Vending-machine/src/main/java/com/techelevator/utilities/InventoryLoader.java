package com.techelevator.utilities;

import com.techelevator.vendingmachine.VendingMachineItem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class InventoryLoader {

    public static Map<String, VendingMachineItem> loadInventory(String inventoryFileName) {
        Map<String, VendingMachineItem> inventory = new HashMap<>();

        try (Scanner fileScanner = new Scanner(new File(inventoryFileName))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                VendingMachineItem item = splitStringToItem(line);
                inventory.put(item.getSlotLocation(),item);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: Inventory file not found.");
        }
        catch (IndexOutOfBoundsException ex) {
            System.err.println("Error: inventory file invalid format.");
        }
        return inventory;
    }

    public static VendingMachineItem  splitStringToItem(String line) {
        String[] parts = line.split("\\|");

            String slotLocation = parts[0];
            String productName = parts[1];
            double price = Double.parseDouble(parts[2]);
            String type = parts[3];

            return new VendingMachineItem(slotLocation, productName, price, type);
    }
}
