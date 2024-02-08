package com.techelevator;

import java.util.Scanner;

/*
 Write a command line program which prompts the user for the total bill, and the amount tendered. It should then
 display the change required.

 Please enter the amount of the bill: 23.65
 Please enter the amount tendered: 100.00
 The change required is 76.35
 */
public class MakeChange {


    public static void main(String[] args) {

        // Prompt user for total bill value
        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.println("Please enter the amount of the bill:");
        // bill variable user input
        double bill = input.nextDouble();
        //String bill = String.valueOf(Double.parseDouble  (input.nextLine()));
        // Prompt the user for the amount tendered
        System.out.println();
        System.out.println("Please enter the amount tendered:");
        double tender = input.nextDouble();
		while (tender < bill || (!(bill >= 0))) {
			System.out.println("Error: Amount tendered is invalid");
			System.out.println("Please enter the amount tendered:");
			tender = input.nextDouble();

		}
        double change = tender - bill;
		System.out.println();
        System.out.println("The change required is " + change);


	}

}
