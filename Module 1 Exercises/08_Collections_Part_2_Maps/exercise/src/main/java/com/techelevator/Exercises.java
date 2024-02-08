package com.techelevator;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Exercises {

    /*
     * Given the name of an animal, return the name of a group of that animal
     * (e.g. "Elephant" -> "Herd", "Rhino" - "Crash").
     *
     * The animal name should be case insensitive so "elephant", "Elephant", and
     * "ELEPHANT" should all return "Herd".
     *
     * If the name of the animal is not found, null, or empty, return "unknown".
     *
     * Rhino -> Crash
     * Giraffe -> Tower
     * Elephant -> Herd
     * Lion -> Pride
     * Crow -> Murder
     * Pigeon -> Kit
     * Flamingo -> Pat
     * Deer -> Herd
     * Dog -> Pack
     * Crocodile -> Float
     *
     * animalGroupName("giraffe") → "Tower"
     * animalGroupName("") -> "unknown"
     * animalGroupName("walrus") -> "unknown"
     * animalGroupName("Rhino") -> "Crash"
     * animalGroupName("rhino") -> "Crash"
     * animalGroupName("elephants") -> "unknown"
     *
     */
    public String animalGroupName(String animalName) {
        // create a map that stores the animal group name
        Map<String, String> group = new HashMap<>();

        // Add items to the map
        group.put("Rhino", "Crash");
        group.put("Giraffe", "Tower");
        group.put("Elephant", "Herd");
        group.put("Lion", "Pride");
        group.put("Crow", "Murder");
        group.put("Flamingo", "Pat");
        group.put("Deer", "Herd");
        group.put("Dog", "Pack");
        group.put("Crocodile", "Float");

        // check if animalname is empty
        if (animalName == null || animalName.isEmpty()) {
            return "unknown";
        }
        // Make the name case insensitive
        String notSensitive = animalName.substring(0, 1).toUpperCase() + animalName.substring(1).toLowerCase();
        String groupVal = group.get(notSensitive);

        if (groupVal == null) {
            return "unknown";
        }
        return groupVal;
    }


    /*
     * Given a String item number (a.k.a. SKU), return the discount percentage if the item is on sale.
     * If the item is not on sale, return 0.00.
     *
     * If the item number is empty or null, return 0.00.
     *
     * "KITCHEN4001" -> 0.20
     * "GARAGE1070" -> 0.15
     * "LIVINGROOM"	-> 0.10
     * "KITCHEN6073" -> 0.40
     * "BEDROOM3434" -> 0.60
     * "BATH0073" -> 0.15
     *
     * The item number should be case insensitive so "kitchen4001", "Kitchen4001", and "KITCHEN4001"
     * should all return 0.20.
     *
     * isItOnSale("kitchen4001") → 0.20
     * isItOnSale("") → 0.00
     * isItOnSale("GARAGE1070") → 0.15
     * isItOnSale("dungeon9999") → 0.00
     *
     */
    public double isItOnSale(String itemNumber) {
        // create a map that stores the SKU's
        Map<String, Double> sku = new HashMap<>();

        // populate with keys
        sku.put("KITCHEN4001", 0.20);
        sku.put("GARAGE1070", 0.15);
        sku.put("LIVINGROOM", 0.10);
        sku.put("KITCHEN6073", 0.40);
        sku.put("BEDROOM3434", 0.60);
        sku.put("BATH0073", 0.15);

        // check if discount exists
        if (itemNumber == null || itemNumber.isEmpty()) {
            return 0.0;
        }
        String notSensitive = itemNumber.toUpperCase();
        Double discount = sku.get(notSensitive);
        if (discount == null) {
            return 0.0;
        }
        return discount;
    }

    /*
     * Modify and return the given Map as follows: if "Peter" has more than 0 money, transfer half of it to "Paul",
     * but only if Paul has less than $10s.
     *
     * Note, monetary amounts are specified in cents: penny=1, nickel=5, ... $1=100, ... $10=1000, ...
     *
     * robPeterToPayPaul({"Peter": 2000, "Paul": 99}) → {"Peter": 1000, "Paul": 1099}
     * robPeterToPayPaul({"Peter": 2000, "Paul": 30000}) → {"Peter": 2000, "Paul": 30000}
     * robPeterToPayPaul({"Peter": 101, "Paul": 500}) → {"Peter": 51, "Paul": 550}
     * robPeterToPayPaul({"Peter": 0, "Paul": 500}) → {"Peter": 0, "Paul": 500}
     *
     */
    public Map<String, Integer> robPeterToPayPaul(Map<String, Integer> peterPaul) {
        // create the map
        Map<String, Integer> sorryPeter = new HashMap<>();
        // check if the values do not meet the requirements
        if (peterPaul.get("Paul") >= 1000 || peterPaul.get("Peter") == 0) {
            return peterPaul;
        }
        // take half of Peters money and give it to paul, accounting for bad rounding
        else if (peterPaul.get("Paul") < 1000 && peterPaul.get("Peter") > 0) {
            int halfOfPeters = peterPaul.get("Peter") / 2;
            int remainder = peterPaul.get("Peter") - halfOfPeters;
            sorryPeter.put("Peter", remainder);
            sorryPeter.put("Paul", peterPaul.get("Paul") + halfOfPeters);
        }
        return sorryPeter;
    }

    /*
     * Modify and return the given Map as follows: if "Peter" has $50 or more, AND "Paul" has $100 or more,
     * then create a new "PeterPaulPartnership" worth a combined contribution of a quarter of each partner's
     * current worth.
     *
     * peterPaulPartnership({"Peter": 50000, "Paul": 100000}) → {"Peter": 37500, "Paul": 75000, "PeterPaulPartnership": 37500}
     * peterPaulPartnership({"Peter": 3333, "Paul": 1234567890}) → {"Peter": 3333, "Paul": 1234567890}
     *
     */
    public Map<String, Integer> peterPaulPartnership(Map<String, Integer> peterPaul) {
        // check if Peter doesn't have 5000 and Paul has less than 10000
        if (peterPaul.get("Peter") < 5000 || peterPaul.get("Paul") < 10000) {
            return peterPaul;
        }
        // check if Peter has at least 5000 and Paul has at least 10000
        else if (peterPaul.get("Peter") >= 5000 && peterPaul.get("Paul") >= 10000) {
            int petersFourth = peterPaul.get("Peter") / 4;
            int paulsFourth = peterPaul.get("Paul") / 4;

            peterPaul.put("Peter", peterPaul.get("Peter") - petersFourth);
            peterPaul.put("Paul", peterPaul.get("Paul") - paulsFourth);

            peterPaul.put("PeterPaulPartnership", petersFourth + paulsFourth);
        }


        return peterPaul;
    }

    /*
     * Given an array of non-empty strings, return a Map<String, String> where, for every String in the array,
     * there is an entry whose key is the first character of the string.
     *
     * The value of the entry is the last character of the String. If multiple Strings start with the same letter, then the
     * value for that key should be the later String's last character.
     *
     * beginningAndEnding(["code", "bug"]) → {"b": "g", "c": "e"}
     * beginningAndEnding(["code", "bug", "cat"]) → {"b": "g", "c": "t"}
     * beginningAndEnding(["muddy", "good", "moat", "good", "night"]) → {"g": "d", "m": "t", "n": "t"}
     */
    public Map<String, String> beginningAndEnding(String[] words) {
        // Create the map
        Map<String, String> beginEnd = new HashMap<>();
        // make the key the first character of the word and the value the last character
        for (String word : words) {
            String key = word.substring(0,1);
            String value = word.substring(word.length() -1);
            beginEnd.put(key, value);
        }
        return beginEnd;
    }

    /*
     * Given an array of Strings, return a Map<String, Integer> with a key for each different String, with the value the
     * number of times that String appears in the array.
     *
     * ** A CLASSIC **
     *
     * wordCount(["ba", "ba", "black", "sheep"]) → {"ba" : 2, "black": 1, "sheep": 1 }
     * wordCount(["a", "b", "a", "c", "b"]) → {"b": 2, "c": 1, "a": 2}
     * wordCount([]) → {}
     * wordCount(["c", "b", "a"]) → {"b": 1, "c": 1, "a": 1}
     *
     */
    public Map<String, Integer> wordCount(String[] words) {
        // Create the map
        Map<String, Integer> count = new HashMap<>();

        for (String word : words) {
            if (count.containsKey(word)) {
                int counter = count.get(word);
                count.put(word, counter + 1);
            } else {
                count.put(word, 1);
            }
        }
        return count;
    }

    /*
     * Given an array of int values, return a Map<Integer, Integer> with a key for each int, with the value the
     * number of times that int appears in the array.
     *
     * ** The lesser known cousin of the the classic wordCount **
     *
     * intCount([1, 99, 63, 1, 55, 77, 63, 99, 63, 44]) → {1: 2, 44: 1, 55: 1, 63: 3, 77: 1, 99:2}
     * intCount([107, 33, 107, 33, 33, 33, 106, 107]) → {33: 4, 106: 1, 107: 3}
     * intCount([]) → {}
     *
     */
    public Map<Integer, Integer> integerCount(int[] ints) {
        // Create the map
        Map<Integer, Integer> intMap = new HashMap<>();

        for (Integer integer : ints) {
             if (intMap.containsKey(integer)) {
                 int counter = intMap.get(integer);
                 intMap.put(integer, counter + 1);
             } else {
                 intMap.put(integer, 1);
             }
        }
        return intMap;
    }

    /*
     * Given an array of Strings, return a Map<String, Boolean> where each different String is a key and value
     * is true only if that String appears 2 or more times in the array.
     *
     * wordMultiple(["a", "b", "a", "c", "b"]) → {"b": true, "c": false, "a": true}
     * wordMultiple(["c", "b", "a"]) → {"b": false, "c": false, "a": false}
     * wordMultiple(["c", "c", "c", "c"]) → {"c": true}
     *
     */
    public Map<String, Boolean> wordMultiple(String[] words) {
        Map<String, Boolean> multiples = new HashMap<>();

        for (String word : words) {
            if (!(multiples.containsKey(word))) {
                multiples.put(word, false);
            }
            else if (!(multiples.get(word))) {
                multiples.put(word, true);
            }
        }
        return multiples;









    }

    /*
     * Given two Maps, Map<String, Integer>, merge the two into a new Map, Map<String, Integer> where keys in Map2,
     * and their int values, are added to the int values of matching keys in Map1. Return the new Map.
     *
     * Unmatched keys and their int values in Map2 are simply added to Map1.
     *
     * consolidateInventory({"SKU1": 100, "SKU2": 53, "SKU3": 44} {"SKU2":11, "SKU4": 5})
     * 	 → {"SKU1": 100, "SKU2": 64, "SKU3": 44, "SKU4": 5}
     *
     */
    public Map<String, Integer> consolidateInventory(Map<String, Integer> mainWarehouse, Map<String, Integer> remoteWarehouse) {
        // Create the map and fill it with the values of mainWarehouse
        Map<String, Integer> inventory = new HashMap<>(mainWarehouse);

        for (String sku : remoteWarehouse.keySet()) {
            if (inventory.containsKey(sku)) {
                int consolidated = inventory.get(sku) + remoteWarehouse.get(sku);
                inventory.put(sku, consolidated);
            } else {
                inventory.put(sku, remoteWarehouse.get(sku));
            }
        }
        return inventory;
    }
    /*
        // create the map and merge the two maps into it (?)
        Map<String, Integer> inventory = new HashMap<>();
        inventory.putAll(mainWarehouse);
        inventory.putAll(remoteWarehouse);
        return null;
    }
    */

    /*
     * Just when you thought it was safe to get back in the water --- last2Revisited!!!!
     *
     * Given an array of Strings, for each String, the count of the number of times that a subString length 2 appears
     * in the String and also as the last 2 chars of the String, so "hixxxhi" yields 1.
     *
     * We don't count the end subString, but the subString may overlap a prior position by one.  For instance, "xxxx"
     * has a count of 2, one pair at position 0, and the second at position 1. The third pair at position 2 is the
     * end subString, which we don't count.
     *
     * Return Map<String, Integer>, where the key is String from the array, and its last2 count.
     *
     * last2Revisited(["hixxhi", "xaxxaxaxx", "axxxaaxx"]) → {"hixxhi": 1, "xaxxaxaxx": 1, "axxxaaxx": 2}
     *
     */
    public Map<String, Integer> last2Revisited(String[] words) {
        // Create the map
        Map<String, Integer> last2 = new HashMap<>();
        // check if the string length is less than two
        for (String word : words) {
            if (word.length() <= 2) {
                last2.put(word, 0);
                continue;
            }
            // repeated code from original last2
            String lastTwo = word.substring(word.length() - 2);
            int sameTwoSubstrings = 0;

            for (int i = 0; i < word.length() - 2; i++) {
                String substring = word.substring(i, i + 2);
                if (substring.equals(lastTwo)) {
                    sameTwoSubstrings++;
                }
            }

            last2.put(word, sameTwoSubstrings);
        }

        return last2;
    }
}


