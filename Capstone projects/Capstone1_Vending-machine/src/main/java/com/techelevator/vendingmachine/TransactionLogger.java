package com.techelevator.utilities;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransactionLogger {

    private static final String LOG_FILE_NAME = "Log.txt";

    public static void logTransaction(String action, double amount, double balance) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE_NAME, true))) {
            String timestamp = getCurrentTimestamp();

            writer.println(timestamp + " " + action + ": $" + String.format("%.2f", amount) + " $" + String.format("%.2f", balance));
        } catch (IOException e) {
            System.out.println("Error writing to the log file.");
        }
    }

    private static String getCurrentTimestamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
        return dateFormat.format(new Date());
    }
}
