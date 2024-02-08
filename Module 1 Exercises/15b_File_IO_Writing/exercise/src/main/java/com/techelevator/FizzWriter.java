package com.techelevator;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FizzWriter {

    // Use this scanner for all user input. Don't create additional Scanners with System.in
    private final Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) {
        FizzWriter fizzWriter = new FizzWriter();
        fizzWriter.run();
    }

    public void run() {
        // try with that will close itself at the end

        // prompt the user for the destination file
        System.out.println("What is the destination file");
        String destination = userInput.nextLine();
        // If the file doesn't yet exist, create it and write to it
        if (!new File(destination).exists()) {
            File newFile = new File(destination);
            fizzBuzz(destination);
        } else
            fizzBuzz(destination);


    }

    private void fizzBuzz(String destination) {
        try (PrintWriter writer = new PrintWriter(destination)) { // Write to the destination file
            for (int i = 1; i <= 300; i++) {
                if (i % 3 == 0 && i % 5 == 0)
                    writer.write("FizzBuzz");
                else if (i % 3 == 0)
                    writer.write("Fizz");
                else if (i % 5 == 0)
                    writer.write("Buzz");
                else
                    writer.write(String.valueOf(i));
                // print a new line after each addition of "fizz", "buzz" or "fizzBuzz"
                writer.println();
            }
        } catch (IOException e) {
            System.out.println("ERROR: Unable to write to destination");
            System.exit(1);
        }

    }
}


