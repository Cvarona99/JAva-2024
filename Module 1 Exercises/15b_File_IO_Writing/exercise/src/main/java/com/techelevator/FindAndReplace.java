package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FindAndReplace {

    // Use this scanner for all user input. Don't create additional Scanners with System.in
    private final Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) {
        FindAndReplace findAndReplace = new FindAndReplace();
        findAndReplace.run();
    }

    public void run() {
        // Set up a try with that will close automatically at the end

        try (userInput) {
            // prompt user for search word
            System.out.println("What is the search word?");
            String searchWord = userInput.nextLine();
            // prompt user for the replacement word
            System.out.println("what is the replacement word?");
            String replacementWord = userInput.nextLine();
            // prompt user for the source file
            System.out.println("What is the source file?");
            String sourceFile = userInput.nextLine();
            // check if source file exists
            if (!new File(sourceFile).isFile()) {
                System.out.println("ERROR: Invalid source file");
                System.exit(1);
            }
            // prompt the user for the destination file
            System.out.println("What is the destination file?");
            String destination = userInput.nextLine();



            // execute the replaceWord method
            replaceWord(searchWord, replacementWord, sourceFile, destination);
        }
    }




    private void replaceWord(String searchWord, String replacementWord, String sourceFile, String destination) {
        try (Scanner fileReader = new Scanner(new File(sourceFile)); // read the source file
             PrintWriter writer = new PrintWriter(destination)) { // write to the destination
            while (fileReader.hasNextLine()) {
                // replace the searched word with the replacement word
                String line = fileReader.nextLine();
                writer.println(line.replace(searchWord,replacementWord));

            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: invalid destination");
            System.exit(1);
        }


    }
}