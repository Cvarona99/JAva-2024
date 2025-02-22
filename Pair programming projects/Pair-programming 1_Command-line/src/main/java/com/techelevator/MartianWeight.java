package com.techelevator;
import java.util.Scanner;
/*
In case you've ever pondered how much you weigh on Mars, here's the calculation:
 	Wm = We * 0.378
 	where 'Wm' is the weight on Mars, and 'We' is the weight on Earth
 
Write a command line program which accepts a series of Earth weights from the user  
and displays each Earth weight as itself, and its Martian equivalent.

Enter a series of Earth weights (space-separated): 98 235 185
 
 98 lbs. on Earth is 37 lbs. on Mars.
 235 lbs. on Earth is 88 lbs. on Mars.
 185 lbs. on Earth is 69 lbs. on Mars. 
 */
public class MartianWeight {
	static final double WEIGHTRATIO = 0.378;

	public static void main(String[] args) {
		// Take input from user
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a series of Earth weights (space-separated): ");
		String[] weightInput = input.nextLine().split(" ");
		for (String weight : weightInput) {
			System.out.println(weight + " lbs. on Earth is " + marsWeightAsInt(weight) + " on Mars.");
		}
	}

	static int marsWeightAsInt(String earthWeight) {
		return (int)((Double.parseDouble(earthWeight)) * WEIGHTRATIO);
	}

}
