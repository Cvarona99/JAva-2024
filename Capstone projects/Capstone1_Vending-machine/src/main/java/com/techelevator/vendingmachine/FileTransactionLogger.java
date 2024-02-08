package com.techelevator.vendingmachine;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileTransactionLogger {

    private static final String LOG_FILE_NAME = "Log.txt";

    public void logTransaction(String transaction) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE_NAME, true))) {
            String timeStamp = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a").format(new Date());
            writer.println(timeStamp + " " + transaction);
        } catch (IOException e) {
            System.out.println("Error writing to the log file: " + e.getMessage());
        }
    }
}
