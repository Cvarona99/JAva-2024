package com.techelevator;

public class Airplane {

    // Instance variables
    private String planeNumber;
    private int totalFirstClassSeats;
    private int bookedFirstClassSeats;

    private int totalCoachSeats;
    private int bookedCoachSeats;

    // Constructors
    public Airplane(String planeNumber, int totalFirstClassSeats, int totalCoachSeats) {
        this.planeNumber = planeNumber;
        this.totalFirstClassSeats = totalFirstClassSeats;
        this.totalCoachSeats = totalCoachSeats;
    }


    // Getters


    public String getPlaneNumber() {
        return planeNumber;
    }

    public int getBookedCoachSeats() {
        return bookedCoachSeats;
    }

    public int getTotalFirstClassSeats() {
        return totalFirstClassSeats;
    }

    public int getBookedFirstClassSeats() {
        return bookedFirstClassSeats;
    }

    public int getTotalCoachSeats() {
        return totalCoachSeats;
    }

    public int getAvailableFirstClassSeats() {
        return totalFirstClassSeats - bookedFirstClassSeats;
    }

    public int getAvailableCoachSeats() {
        return totalCoachSeats - bookedCoachSeats;
    }

    // Reserve seats method
    public boolean reserveSeats(boolean forFirstClass, int totalNumberOfSeats) {
        if (forFirstClass) {
            if (getAvailableFirstClassSeats() >= totalNumberOfSeats) {
                bookedFirstClassSeats += totalNumberOfSeats;
                return true;
            }
        } else if (getAvailableCoachSeats() >= totalNumberOfSeats) {
            bookedCoachSeats += totalNumberOfSeats;
            return true;
        }
        return false;

//        if ((bookedCoachSeats + bookedFirstClassSeats) <= totalNumberOfSeats) {  // Logic was causing errors
//            if (forFirstClass) {
//                bookedFirstClassSeats += totalNumberOfSeats;
//            } else if (!forFirstClass) {
//                bookedCoachSeats += totalNumberOfSeats;
//            }
//            reserved = true;
//        } else {
//            reserved = false;
//        }
//        return reserved;
    }

}
