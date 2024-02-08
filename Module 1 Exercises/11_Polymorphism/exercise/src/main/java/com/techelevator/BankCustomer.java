package com.techelevator;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
public class BankCustomer {

    private String name;
    private String address;
    private String phoneNumber;
    private List<Accountable> accounts;
    // Default Constructor - No args
    public BankCustomer(){
        this.name = "";
        this.address = "";
        this.phoneNumber = "";
        this.accounts = new ArrayList<>();
    }
    // Constructor
    public BankCustomer(String name, String address, String phoneNumber){
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.accounts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Accountable[] getAccounts() {
        return accounts.toArray(new Accountable[0]);
    }

    public void addAccount(Accountable newAccount) {
        this.accounts.addAll(Arrays.asList(newAccount));
    }
    public boolean isVip() {
        int balance = 0;
        for (Accountable account : accounts) {
            balance += account.getBalance();

        }
        return balance >= 25000;
    }

}
