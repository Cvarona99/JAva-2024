package com.techelevator;


public class SquareWall extends RectangleWall{
    //Instance variable
    public SquareWall(String name, String color, int sideLength) {
        super(name,color,sideLength,sideLength);
    }
    @Override
    public String toString() {
        return getName() + " (" + getLength() + "x" + getHeight() + ") square";
    }
}
