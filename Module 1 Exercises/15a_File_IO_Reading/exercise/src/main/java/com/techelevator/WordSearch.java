package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordSearch {

    // Use this scanner for all user input. Don't create additional Scanners with System.in
    private final Scanner userInput = new Scanner(System.in);


    public static void main(String[] args) {


        WordSearch wordSearch = new WordSearch();
        wordSearch.run();
    }

    public void run() {

        /* Your code goes here */
        // Prompt the user for the file name
        try (userInput) {
            System.out.println("What is the fully qualified name of the file that should be searched?");
            String filePath = userInput.nextLine();
            // Prompt user for word being searched
            System.out.println("What is the word you are looking for?");
            String searchWord = userInput.nextLine();
            // Ask if the search should be case-sensitive
            System.out.println("Should the search be case sensitive? (Y/N)");
            String yesOrNo = userInput.nextLine();
            boolean isCaseSensitive = false;


            if (yesOrNo.equalsIgnoreCase("y") || yesOrNo.equalsIgnoreCase("yes")) {
//				// execute CaseSensitive method
                isCaseSensitive = true;
            } else if (yesOrNo.equalsIgnoreCase("n") || yesOrNo.equalsIgnoreCase(("no"))) {
                isCaseSensitive = false;
            } else {
                System.out.println("wrong input ");
                System.exit(1);
            }
            // execute lookForWord method
            lookForWord(filePath, searchWord, isCaseSensitive);
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: Missing or unreadable file path.");
        }
    }

    private void lookForWord(String filePath, String searchWord, boolean isCaseSensitive) throws FileNotFoundException {
        File file = new File(filePath);
// I had trouble using only one scanner object throughout the class file, so I had to create one to read the file
        if (!isCaseSensitive)
            searchWord = searchWord.toLowerCase();
        // try and catch
        try (Scanner fileScanner = new Scanner(file)) {
            int lineCount = 1;
            // while loop that reads the next line and converts to lower case if caseInsensitive, then prints out the line containing the word being searched
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String lowerCase = line;

                if (!isCaseSensitive)
                    lowerCase = line.toLowerCase();


                if (lowerCase.contains(searchWord))
                    System.out.println(lineCount + ") " + line);
                lineCount++;
            }
        }
    }
}


//		if(!isCaseSensitive){
//			searchWord = searchWord.toLowerCase();
//
//			try (Scanner fileScanner = new Scanner(file)) {
//				int lineCount = 1;
//				while (fileScanner.hasNextLine()) {
//					String line = fileScanner.nextLine();
//
//					if (line.contains(searchWord))
//						System.out.println(lineCount + ") " + line);
//					lineCount++;
//				}
//			}
//		}
