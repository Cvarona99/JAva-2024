package com.techelevator.tenmo.services;


import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.UserCredentials;

import javax.sound.midi.Soundbank;
import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ConsoleService {

    private final Scanner scanner = new Scanner(System.in);

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public int promptForMenuSelection(String prompt) {
        int menuSelection;
        System.out.print(prompt);
        try {
            menuSelection = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            menuSelection = -1;
        }
        return menuSelection;
    }

    public void printGreeting() {
        System.out.println("*********************");
        System.out.println("* Welcome to TEnmo! *");
        System.out.println("*********************");
    }

    public void printTransferDetailsHeader() {

            System.out.println("\n*** Transfer Details ***");
        }

    public void printLoginMenu() {
        System.out.println();
        System.out.println("1: Register");
        System.out.println("2: Login");
        System.out.println("0: Exit");
        System.out.println();
    }

    public void printMainMenu() {
        System.out.println();
        System.out.println("1: View your current balance");
        System.out.println("2: View your past transfers");
        System.out.println("3: View your pending requests");
        System.out.println("4: Send TE bucks");
        System.out.println("5: Request TE bucks");
        System.out.println("0: Exit");
        System.out.println();
    }


    public UserCredentials promptForCredentials() {
        String username = promptForString("Username: ");
        String password = promptForString("Password: ");
        return new UserCredentials(username, password);
    }

    public String promptForString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public int promptForInt(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("\nPlease enter a number or 0 to cancel: ");
            }
        }
    }

    public BigDecimal promptForBigDecimal(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                return new BigDecimal(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("\nPlease enter a decimal number or 0 to cancel: ");
            }
        }
    }


    public void printRegistrationHeader() {
        System.out.println("\n*** Register User ***");
    }

    public void printLoginHeader() {
        System.out.println("\n*** Login ***");
    }

    public void pause() {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    public void printBalanceHeader() {
        System.out.println("\n*** View Current Balance ***");
    }

    public void printCurrentBalance(BigDecimal balance) {
        System.out.println("Your current balance is: $" + balance);
    }

    public void printTransferHistoryHeader() {
        System.out.println("\n*** View Transfer History ***");
    }

    public void printTransfers(List<Transfer> transferHistory) {
        System.out.printf("%-15s %-15s %-10s%n", "ID", "From/To", "Amount");
        for (Transfer transfer : transferHistory) {
            if (transfer.getTypeDescription().equalsIgnoreCase("send")) {
                System.out.printf("%-15d %-3s %-10s $%-10.2f%n",
                        transfer.getTransferId(),
                        "To:",
                        transfer.getToUsername(),
                        transfer.getAmount());

            } else if (transfer.getTypeDescription().equalsIgnoreCase("request")) {


                System.out.printf("%-15d %-3s %-10s $%-10.2f%n",
                        transfer.getTransferId(),
                        "From:",
                        transfer.getFromUsername(),
                        transfer.getAmount());
            }
        }
        System.out.println(" ");

    }

    public void printTransferDetails(Transfer transfer) {
        System.out.println("Id: " + transfer.getTransferId());
        System.out.println("From: " + transfer.getFromUsername());
        System.out.println("To: " + transfer.getToUsername());
        System.out.println("Status: " + transfer.getStatusDescription());
        System.out.println("Amount: " + transfer.getAmount());
    }


    public void printPendingTransfers(List<Transfer> pendingTransfers) {
        System.out.printf("%-15s %-15s %-10s%n", "ID", "To", "Amount");
        for (Transfer transfer : pendingTransfers) {
            System.out.printf("%-15d %-15s $%-10.2f%n",
                    transfer.getTransferId(),
                    transfer.getToUsername(),
                    transfer.getAmount());

        }
        System.out.println(" ");
    }

    public void printPendingRequestsHeader() {
        System.out.println("\n*** View Pending Requests ***");
    }

    public void printSendBucksHeader() {
        System.out.println("\n*** Send TE Bucks ***");
    }

    public void printUsers(List<Account> accounts) {
        System.out.println("\nUsers:");
        System.out.printf("%-15s %-15s%n", "User ID", "Username");
        for (Account account : accounts) {
            System.out.printf("%-15d %-15s%n", account.getUserId(), account.getUsername());
        }
    }

    public void printRequestBucksHeader() {
        System.out.println("\n*** Request TE Bucks ***");
    }

    public void printSuccessMessage(String message) {
        System.out.println("\n" + message);
    }


    public void printErrorMessage() {
        System.out.println("An error occurred. Check the log for details.");
    }


}
