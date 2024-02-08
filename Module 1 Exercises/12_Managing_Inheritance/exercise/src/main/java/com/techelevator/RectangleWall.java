package com.techelevator;

public class RectangleWall extends Wall {
    //Instance variables
    private final int length;
    private final int height;


    //Constructor
    public RectangleWall(String name, String color, int length, int height) {
        super(name, color);
        this.length = length;
        this.height = height;
    }

    public int getLength() {
        return length;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public int getArea() {
        return length * height;
    }

    public String toString() {
        return getName() + " (" + length + "x" + height + ") rectangle";
    }

}