package com.techelevator;

public class Exercise03_ShippingTotal {
    boolean isOver;
    boolean lessorEqual;
    double upperRate;
    double lowerRate;
    /*
     * Scamper Shipping Company specializes in small, local deliveries.
     * The problems below ask you to implement the logic to calculate a shipping
     * amount for a package.
     */

    // You can use these constants in your solutions.
    private final int MAX_WEIGHT_POUNDS = 40;
    private final double UP_TO_40_LB_RATE = 0.50;
    private final double OVER_40_LB_RATE = 0.75;

    /*
     * Scamper Shipping Company charges $0.50 per pound for items up to and
     * including 40 pounds. It charges $0.75 per pound for items over 40 pounds.
     * Return the shipping rate when provided a weight in pounds.
     *
     * Examples:
     * calculateShippingRate(10) ➔ 0.50
     * calculateShippingRate(25) ➔ 0.50
     * calculateShippingRate(40) ➔ 0.50
     * calculateShippingRate(45) ➔ 0.75
     */

    public double calculateShippingRate(int weightPounds) {
        isOver = weightPounds > MAX_WEIGHT_POUNDS;
        lessorEqual = weightPounds <= MAX_WEIGHT_POUNDS;
        upperRate = OVER_40_LB_RATE;
        lowerRate = UP_TO_40_LB_RATE;

        if (isOver)
        {
            return upperRate;
        }
        else
        {
            return lowerRate;
        }

    }

    /*
     * Scamper Shipping Company charges $0.50 per pound for items up to and
     * including 40 pounds. It charges $0.75 per pound for items over 40 pounds.
     * Implement the logic needed to calculate the shipping cost when provided a
     * weight in pounds.
     *
     * You may use calculateShippingRate() in your solution.
     *
     * Examples:
     * calculateShippingTotal(10) ➔ 5.0
     * calculateShippingTotal(25) ➔ 12.5
     * calculateShippingTotal(40) ➔ 20.0
     * calculateShippingTotal(45) ➔ 33.75
     */
    public double calculateShippingTotal(int weightPounds) {
        isOver = weightPounds > MAX_WEIGHT_POUNDS;
        lessorEqual = weightPounds <= MAX_WEIGHT_POUNDS;
        double upperTotal = weightPounds * OVER_40_LB_RATE;
        double lowerTotal = weightPounds * UP_TO_40_LB_RATE;

        if (isOver)
        {
            return upperTotal;
        }
        else
        {
            return lowerTotal;
        }

    }

    /*
     * Scamper Shipping Company now allows customers to provide a discount code to
     * give them 10% off of their order.
     * Implement the logic to calculate the correct shipping rate when provided a
     * weight in pounds and a boolean value for hasDiscount.
     *
     * You may use any previous methods in your solution.
     *
     * Examples:
     * calculateShippingTotal(10, false) ➔ 5.0
     * calculateShippingTotal(10, true) ➔ 4.5
     * calculateShippingTotal(25, false) ➔ 12.5
     * calculateShippingTotal(25, true) ➔ 11.25
     * calculateShippingTotal(40, false) ➔ 20.0
     * calculateShippingTotal(40, true) ➔ 18.0
     * calculateShippingTotal(45, false) ➔ 33.75
     * calculateShippingTotal(45, true) ➔ 30.375
     */

    // This is the last question I worked on in the exercises.
    public double calculateShippingTotal(int weightPounds, boolean hasDiscount) {
        isOver = weightPounds > MAX_WEIGHT_POUNDS;
        lessorEqual = weightPounds <= MAX_WEIGHT_POUNDS;
        double upperNoDiscount = weightPounds * OVER_40_LB_RATE;
        double lowerNoDiscount = weightPounds * UP_TO_40_LB_RATE;
        double underDiscount = lowerNoDiscount * .10;
        double higherDiscount = upperNoDiscount * .10;

        if (isOver)
        {
            if (hasDiscount )
            {
                return upperNoDiscount - higherDiscount;
            }
            else
            {
                return upperNoDiscount;
            }
        }


        else
        {
            if (hasDiscount)
            {
                return lowerNoDiscount - underDiscount;
            }
            else
            {
                return lowerNoDiscount;
            }

        }
    }
}



