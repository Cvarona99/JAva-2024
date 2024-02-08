package com.techelevator;

public class Elevator {

    // Instance variables
    private int currentFloor;
    private int numberOfFloors;
    private boolean doorOpen;

    // constructor TODO
    public Elevator(int numberOfLevels) {
        this.numberOfFloors = numberOfLevels;
        this.currentFloor = 1;
        this.doorOpen = false;
    }

    // Getters
    public int getCurrentFloor() {
        return currentFloor;
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public boolean isDoorOpen() {
        return doorOpen;
    }

    public void openDoor() {
        this.doorOpen = true;
    }

    public void closeDoor() {
        this.doorOpen = false;
    }

    public void goUp(int desiredFloor) {
        if (!doorOpen)
            if (desiredFloor <= numberOfFloors && desiredFloor > currentFloor)
                this.currentFloor = desiredFloor;
    }

    public void goDown(int desiredFloor) {
        if (!doorOpen)
            if (desiredFloor >= 1 && desiredFloor < currentFloor)
                this.currentFloor = desiredFloor;
    }
}

