package com.techelevator.utilities;

import com.techelevator.vendingmachine.VendingMachine;
import com.techelevator.vendingmachine.VendingMachineItem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class SalesReportGenerator {

    private static final String REPORTS_DIRECTORY = "sales_reports";
    private static final String FILE_EXTENSION = ".txt";

    public void generateSalesReport(VendingMachine vendingMachine) {
        createReportsDirectoryIfNotExists();

        Date currentTime = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = dateFormat.format(currentTime);

        String fileName = REPORTS_DIRECTORY + File.separator + "SalesReport_" + timestamp + FILE_EXTENSION;

        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println("Sales Report - " + new Date());
            writer.println();
            writer.println("Item Name | Quantity Sold");
            writer.println("--------------------------------");

            Map<String, VendingMachineItem> inventory = vendingMachine.getInventory();

            for (Map.Entry<String, VendingMachineItem> entry : inventory.entrySet()) {
                VendingMachineItem item = entry.getValue();
                int quantitySold = item.getMaxQuanity() - item.getQuantity();

                writer.println(item.getName() + " | " + quantitySold);
            }

            writer.println();
            writer.println("Total Sales: $" + calculateTotalSales(vendingMachine));
        } catch (IOException e) {
            System.out.println("Error generating sales report: " + e.getMessage());
        }
    }

    private void createReportsDirectoryIfNotExists() {
        File directory = new File(REPORTS_DIRECTORY);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    private double calculateTotalSales(VendingMachine vendingMachine) {
        double totalSales = 0.0;

        Map<String, VendingMachineItem> inventory = vendingMachine.getInventory();

        for (Map.Entry<String, VendingMachineItem> entry : inventory.entrySet()) {
            VendingMachineItem item = entry.getValue();
            int quantitySold = item.getMaxQuanity() - item.getQuantity();
            totalSales += (quantitySold * item.getPrice());
        }

        return totalSales;
    }
}
