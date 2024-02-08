package com.techelevator;

public class SavingsAccount extends BankAccount {

    // Constants
    private static final int MINIMUM_BALANCE = 150;
    private static final int SERVICE_CHARGE = 2;



    // Constructors
    public SavingsAccount(String accountHolderName, String accountNumber) {
        super(accountHolderName, accountNumber);
    }
    public SavingsAccount(String accountHolderName, String accountNumber, int balance){
        super(accountHolderName, accountNumber, balance);
    }

    // Overridden withdraw class
    public int withdraw(int amountToWithdraw) {

        int totalWithdrawn = getBalance() - (amountToWithdraw + SERVICE_CHARGE);
        int postWithdrawal = getBalance() - amountToWithdraw;

        if (totalWithdrawn < 0)
            return getBalance();

        if (postWithdrawal < MINIMUM_BALANCE)
            super.withdraw(SERVICE_CHARGE);

        super.withdraw(amountToWithdraw);






        return getBalance();
    }



}
