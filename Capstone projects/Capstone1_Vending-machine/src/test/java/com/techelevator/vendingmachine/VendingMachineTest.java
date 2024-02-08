package com.techelevator.vendingmachine;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VendingMachineTest {
    VendingMachine testMachine;


    @BeforeEach
    public void setUp() {
        Map<String, VendingMachineItem> testInventory = new HashMap<>();
        testInventory.put("A1",new VendingMachineItem("A1","testItem",1.0,"Chip"));
        testMachine = new VendingMachine(testInventory);
    }

    @Test
    public void purchaseItemWithCorrectValues() {
        double testBalance = 3.75;
        testMachine.feedMoney(testBalance);
        try {
            testMachine.purchaseItem("A1");
            assertEquals(2.75, testMachine.getBalance(), 0.0);
        } catch (InvalidPurchaseException invalidPurchaseException) {
            fail();
        }
    }

    @Test
    public void purchaseItemButNotEnoughMoney() {
        try {
            testMachine.purchaseItem("A1");
            fail();
        } catch (InvalidPurchaseException invalidPurchaseException) {
            assertEquals("Not enough funds, please try again.",invalidPurchaseException.getMessage());
        }
    }

    @Test
    public void purchaseItemButInvalidItem() {
        try {
            testMachine.purchaseItem("B1");
            fail();
        } catch (InvalidPurchaseException invalidPurchaseException) {
            assertEquals("Invalid item, please try again.",invalidPurchaseException.getMessage());
        }
    }

    @Test
    public void purchaseItemButItemSoldOut() {
        for (int i = 0; i < 5; i++)
            testMachine.getInventory().get("A1").decrementQuantity();
        try  {
            testMachine.purchaseItem("A1");
            fail();
        } catch (InvalidPurchaseException invalidPurchaseException) {
            assertEquals("Product sold out. Please make another selection.",invalidPurchaseException.getMessage());
        }
    }


    @Test
    public void feedMoney() {
        double amountToAdd = 5.0;
        testMachine.feedMoney(amountToAdd);
        assertEquals(amountToAdd, testMachine.getBalance(), 0.0);

    }

    @Test
    public void giveChangeIgnorePennies() {
        double change = 3.84;
        String changeToGive = "Change given: " + 15 + " quarters, " + 0 + " dimes, " + 1 + " nickels.";
        testMachine.feedMoney(change);
        assertEquals(changeToGive, testMachine.giveChange());
    }

    @Test
    public void giveChangeAllCoinTypes() {
        double change = .40;
        String changeToGive = "Change given: " + 1 + " quarters, " + 1 + " dimes, " + 1 + " nickels.";
        testMachine.feedMoney(change);
        assertEquals(changeToGive, testMachine.giveChange());
    }


}