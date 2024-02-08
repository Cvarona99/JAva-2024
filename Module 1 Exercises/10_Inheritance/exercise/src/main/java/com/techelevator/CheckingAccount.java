package com.techelevator;

public class CheckingAccount extends BankAccount {
    // withdraw method constants
    private static final int OVERDRAFT_LIMIT = -100;
    private static final int OVERDRAFT_FEE = 10;

    // Constructors

    public CheckingAccount(String accountHolderName, String accountNumber) {
        super(accountHolderName, accountNumber);
    }

    public CheckingAccount(String accountHolderName, String accountNumber, int balance) {
        super(accountHolderName, accountNumber, balance);
    }

    // overriden withdraw method
    public int withdraw(int amountToWithdraw) {

        int amountAfterWithdrawal = getBalance() - amountToWithdraw;

        if (amountAfterWithdrawal <= OVERDRAFT_LIMIT)
            return getBalance();

        if (amountAfterWithdrawal < 0)
            super.withdraw(amountToWithdraw + OVERDRAFT_FEE);
        else
            super.withdraw(amountToWithdraw);

        return getBalance();
    }




//        if (getBalance() < 0 && getBalance() > OVERDRAFT_LIMIT)   // It's a common mistake at this point but I continue to place my statements in the wrong order, the logic also did not account for all types of cases
//            super.withdraw(OVERDRAFT_FEE);
//        else if (amountAfterWithdrawal < OVERDRAFT_LIMIT)
//            return getBalance();
//        super.withdraw(amountToWithdraw);




}