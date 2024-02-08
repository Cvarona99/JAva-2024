package com.techelevator;

public class BankAccount {
    // Instance variables
    private String accountHolderName;
    private String accountNumber;
    private int balance;

    // Constructors
    public BankAccount(String accountHolderName, String accountNumber) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
    }

    public BankAccount(String accountHolderName, String accountNumber, int balance){
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    // getters
    public String getAccountHolderName() {
        return accountHolderName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public int getBalance() {
        return balance;
    }

    // methods
    public int deposit(int amountToDeposit){

        this.balance = amountToDeposit + balance;
        return this.balance;

    }

    public int withdraw(int amountToWithdraw) {
        this.balance = balance - amountToWithdraw;
        return this.balance;
    }


}
