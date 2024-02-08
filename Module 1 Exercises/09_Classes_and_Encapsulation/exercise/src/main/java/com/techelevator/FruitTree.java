package com.techelevator;

public class FruitTree {

    // Instance variables
    private String typeOfFruit;
    private int piecesOfFruitLeft;

    // Constructors
    public FruitTree(String typeOfFruit, int startingPiecesOfFruit) {
        this.typeOfFruit = typeOfFruit;
        this.piecesOfFruitLeft = startingPiecesOfFruit;
    }

    // getters
    public String getTypeOfFruit() {
        return typeOfFruit;
    }

    public int getPiecesOfFruitLeft() {
        return piecesOfFruitLeft;
    }

    // Pick fruit method
    public boolean pickFruit(int numberOfPiecesToRemove) {

//        if (piecesOfFruitLeft < numberOfPiecesToRemove) {  // logic was flipped, needed to switch the order of the cases
////            return false;
////        } else {
////            piecesOfFruitLeft -= piecesOfFruitLeft - numberOfPiecesToRemove;
////            return true;
////        }
        if (piecesOfFruitLeft >= numberOfPiecesToRemove) {
            piecesOfFruitLeft -= numberOfPiecesToRemove;
            return true;
        } else {
            return false;
        }
    }
}