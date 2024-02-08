package com.techelevator;

import java.lang.reflect.Member;

public class Exercise03_Shirts {

    private final static char SMALL_TSHIRT = 'S';
    private final static char MEDIUM_TSHIRT = 'M';
    private final static char LARGE_TSHIRT = 'L';

    /*
    A t-shirt company rep from Top Threads just finished taking an order
    from a client and needs to key it into the system. The client ordered,
    3 Small shirts ('S'), 2 Medium shirts ('M'), and 1 Large shirt ('L').

    Implement the logic to reflect an order of 6 t-shirts.

    Examples:
    buildOrder() → ['S', 'S', 'S', 'M', 'M', 'L']
     */
    public char[] buildOrder() {
        // A whole slew of variables for the sake of easy access within the method
        int totalOrders = 6;
        int smallOrders = 3;
        int medOrders = 2;
        int largeOrders = 1;
        int index = 0;
        char[] shirtOrders = new char[totalOrders];
        int smallShirtsLeft = 3;
        int medShirtsLeft = 2;
        int largeShirtsLeft = 1;
        if (smallOrders > 0)
        {
            shirtOrders[0] = SMALL_TSHIRT;
        }

        // while index is less than the total number of shirt orders, keep running the loop. Within the loop there is a counter for the number of shirts available in each size,
        // as well as a counter for the index moving up the array. the if statement checks that there are shirts to add and then adds them in order of small shirts to large. when no other small shirts remain,
        // the next if statement continues adding shirts based on how many are in that size, then so on and so forth until there are no more of each shirt size remaining to add and the index has reached
        // the end of the array; The loop then breaks and the array now with all the orders is returned.
        while (index < totalOrders)
        {
            if (smallShirtsLeft <= smallOrders && !(smallShirtsLeft == 0))
            {
                shirtOrders[index] = SMALL_TSHIRT;
                smallShirtsLeft--;
                index++;
            }
            else if (smallShirtsLeft == 0 && medShirtsLeft <= medOrders && !(medShirtsLeft == 0))
            {
                shirtOrders[index] = MEDIUM_TSHIRT;
                medShirtsLeft--;
                index++;
            }
            else if (medShirtsLeft == 0 && !(largeShirtsLeft == 0))
            {
                shirtOrders[index] = LARGE_TSHIRT;
                largeShirtsLeft--;
                index++;
            }
            else
                break;



// Old code that was used in a previous commit but may still be useful for editing
      /*      if (smallShirtsLeft == 0)
            {
                shirtOrders[3] = MEDIUM_TSHIRT;
                medShirtsLeft--;
                index++;

            }
            if (med)

            shirtOrders[4] = MEDIUM_TSHIRT;
            index++;

            shirtOrders[5] = LARGE_TSHIRT;
            index++;
            if (index == totalOrders - 1)
            {
                break;
            }
     */}
        return shirtOrders;
    }

    /*
    Another customer called in and is hosting a large networking event and
    needs a bulk order. Rather than indicate how many of each shirt they
    wanted, they've asked for as even distribution as possible.

    Implement the logic to generate an order representing as even a distribution
    as possible, for example: ('S', 'M', 'L', 'S', 'M', 'L', 'S', ...)

    Note: The number of shirts ordered is guaranteed to be non-negative.

    Examples:
    buildBulkOrder(6) → ['S', 'M', 'L', 'S', 'M', 'L']    
    buildBulkOrder(3) → ['S', 'M', 'L']
    buildBulkOrder(4) → ['S', 'M', 'L', 'S']
    buildBulkOrder(0) → []
     */
    public char[] buildBulkOrder(int numberOfShirts) {

        int index = 0;
        int shirtsUntilDefault = numberOfShirts ;
        char[] shirtChart = new char[numberOfShirts];
        if (shirtChart.length == 0)
        {
            return new char[0];

        }

        while (index < numberOfShirts) {
            if (!(shirtsUntilDefault == 0)) {
                shirtChart[index] = SMALL_TSHIRT;
                shirtsUntilDefault--;
                if (!(index == shirtChart.length-1)) {
                    index++;
                } else {
                    break;
                }
                if (shirtsUntilDefault >= 1){
                    shirtChart[index] = MEDIUM_TSHIRT;
                    shirtsUntilDefault--;
                    index++;

                    if (shirtsUntilDefault >= 1){
                        shirtChart[index] = LARGE_TSHIRT;
                        shirtsUntilDefault--;

                        if (!(index == shirtChart.length-1)){
                            index++;
                        } else {
                            break;
                        }

                    }

                }

            }

            // A large amount of bloated code that was repetitive and helped me realized the same thing could be done within one loop.


            /* else if (numberOfShirts % 2 == 0 && !(shirtsUntilDefault == 0))
            {
                shirtsUntilDefault = numberOfShirts;
                shirtChart[index] = SMALL_TSHIRT;
                shirtsUntilDefault--;
                if (!(index == shirtChart.length-1))
                {
                    index++;
                }
                else
                {
                    break;
                }


                if (shirtsUntilDefault >= 1 && !(shirtsUntilDefault == 0))
                {
                    shirtChart[index] = MEDIUM_TSHIRT;
                    shirtsUntilDefault--;
                    if (!(index == shirtChart.length-1))
                    {
                        index++;
                    }
                    else
                    {
                        break;
                    }

                    if (shirtsUntilDefault >= 1)
                    {
                        shirtChart[index] = LARGE_TSHIRT;
                        shirtsUntilDefault--;

                        if (!(index == shirtChart.length-1))
                        {
                            index++;
                        }

                        else
                        {
                            break;
                        }

                    }

                }

            }
            else if (numberOfShirts % 1 == 0 && !(shirtsUntilDefault == 0))
            {
                shirtsUntilDefault = numberOfShirts;
                shirtChart[index] = SMALL_TSHIRT;
                shirtsUntilDefault--;
                if (!(index == shirtChart.length-1))
                {
                    index++;
                }
                else
                {
                    break;
                }

                if (shirtsUntilDefault >= 1)
                {
                    shirtChart[index] = MEDIUM_TSHIRT;
                    shirtsUntilDefault--;
                    index++;

                    if (shirtsUntilDefault >= 1)
                    {
                        shirtChart[index] = LARGE_TSHIRT;
                        shirtsUntilDefault--;

                        if (!(index == shirtChart.length-1))
                        {
                            index++;
                        }

                        else
                        {
                            break;
                        }


                    }

                }
            }
*/
           else
               break;
        }
        return shirtChart;
    }

    /*
    The warehouse is out of small shirts and will only request more when the
    next order comes in that includes an 'S' shirt.

    Implement the logic to look through the next incoming order `char[] order`
    and return true if we need to place an order for Small shirts.

    Examples:
    placeRequest(['M', 'L', 'S']) → true
    placeRequest(['M', 'S', 'L']) → true
    placeRequest(['M', 'M', 'L']) → false
    placeRequest([]) → false
     */
    public boolean placeRequest(char[] order){
    int index = 0;
    boolean orderSmall = false;
    if (order.length == 0)
    {
        orderSmall = false;
        return orderSmall;

    }
        while (index < order.length)
        {
            if (order[index] == SMALL_TSHIRT)
            {
                orderSmall = true;
                break;
            }
            else if (order[index] == MEDIUM_TSHIRT || order[index] == LARGE_TSHIRT)
            {
                orderSmall = false;
                index++;

            }

        }



        return orderSmall;
    }
}
