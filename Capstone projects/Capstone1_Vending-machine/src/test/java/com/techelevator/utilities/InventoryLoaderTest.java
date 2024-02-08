package com.techelevator.utilities;

import com.techelevator.vendingmachine.VendingMachine;
import com.techelevator.vendingmachine.VendingMachineItem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class InventoryLoaderTest {

    @Test
    public void splitStringtoItem() {
        String testLine  = "A1|Potato Crisps|3.05|Chip";
        VendingMachineItem expectedItem = new VendingMachineItem("A1","Potato Crisps",3.05,"Chip");

        VendingMachineItem actualItem = InventoryLoader.splitStringToItem(testLine);

        assertEquals(expectedItem.getSlotLocation(),actualItem.getSlotLocation());
        assertEquals(expectedItem.getName(),actualItem.getName());
        assertEquals(expectedItem.getType(),actualItem.getType());
        assertEquals(expectedItem.getPrice(),actualItem.getPrice(), 0.0);
    }
}