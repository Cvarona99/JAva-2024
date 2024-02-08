package com.techelevator.vendingmachine;

public class InvalidPurchaseException extends Exception{
    public InvalidPurchaseException(String message){
        super(message);
    }
}
