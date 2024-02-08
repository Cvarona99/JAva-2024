package com.techelevator;
public class Exercises {

    /*
     Given a string name, e.g. "Bob", return a greeting of the form "Hello Bob!".
     helloName("Bob") → "Hello Bob!"
     helloName("Alice") → "Hello Alice!"
     helloName("X") → "Hello X!"
     */
    public String helloName(String name) {
        name = ("Hello " + name + "!");
        return name;
    }

    /*
     Given two strings, a and b, return the result of putting them together in the order abba,
     e.g. "Hi" and "Bye" returns "HiByeByeHi".
     makeAbba("Hi", "Bye") → "HiByeByeHi"
     makeAbba("Yo", "Alice") → "YoAliceAliceYo"
     makeAbba("What", "Up") → "WhatUpUpWhat"
     */
    public String makeAbba(String a, String b) {
        return (a + b + b + a);
    }

    /*
     The web is built with HTML strings like "<i>Yay</i>" which draws Yay as italic text. In this example,
     the "i" tag makes <i> and </i> which surround the word "Yay". Given tag and word strings, create the
     HTML string with tags around the word, e.g. "<i>Yay</i>".
     makeTags("i", "Yay") → "<i>Yay</i>"
     makeTags("i", "Hello") → "<i>Hello</i>"
     makeTags("cite", "Yay") → "<cite>Yay</cite>"
     */
    public String makeTags(String tag, String word) {
        return ("<" + tag + ">" + word + "</" + tag + ">");
    }

    /*
     Given an "out" string length 4, such as "<<>>", and a word, return a new string where the word is in the
     middle of the out string, e.g. "<<word>>". Note: use str.substring(i, j) to extract the String starting
     at index i and going up to but not including index j.
     makeOutWord("<<>>", "Yay") → "<<Yay>>"
     makeOutWord("<<>>", "WooHoo") → "<<WooHoo>>"
     makeOutWord("[[]]", "word") → "[[word]]"
     */
    public String makeOutWord(String out, String word) {
        String firstOut = (out.substring(0, 2));
        String lastOut = (out.substring(2));
        return (firstOut + word + lastOut);
    }

    /*
     Given a string, return a new string made of 3 copies of the last 2 chars of the original string. The string
     length will be at least 2.
     extraEnd("Hello") → "lololo"
     extraEnd("ab") → "ababab"
     extraEnd("Hi") → "HiHiHi"
     */
    public String extraEnd(String str) {
        String lastTwo = (str.substring(str.length() - 2));
        return (lastTwo + lastTwo + lastTwo);
    }

    /*
     Given a string, return the string made of its first two chars, so the String "Hello" yields "He". If the
     string is shorter than length 2, return whatever there is, so "X" yields "X", and the empty string ""
     yields the empty string "". Note that str.length() returns the length of a string.
     firstTwo("Hello") → "He"
     firstTwo("abcdefg") → "ab"
     firstTwo("ab") → "ab"
     */
    public String firstTwo(String str) {
        String firstTwo;
        if (str.length() < 2)
            firstTwo = str;
        else
            firstTwo = (str.substring(0, 2));

        return firstTwo;
    }

    /*
     Given a string of even length, return the first half. So the string "WooHoo" yields "Woo".
     firstHalf("WooHoo") → "Woo"
     firstHalf("HelloThere") → "Hello"
     firstHalf("abcdef") → "abc"
     */
    public String firstHalf(String str) {
        String firstHalf = null;
        if (str.length() % 2 == 0)
            firstHalf = (str.substring(0, str.length() / 2));
        return firstHalf;
    }

    /*
     Given a string, return a version without the first and last char, so "Hello" yields "ell".
     The string length will be at least 2.
     withoutEnd("Hello") → "ell"
     withoutEnd("java") → "av"
     withoutEnd("coding") → "odin"
     */
    public String withoutEnd(String str) {
        return (str.substring(1, str.length() - 1));
    }

    /*
     Given 2 strings, a and b, return a string of the form short+long+short, with the shorter string
     on the outside and the longer string on the inside. The strings will not be the same length, but
     they may be empty (length 0).
     comboString("Hello", "hi") → "hiHellohi"
     comboString("hi", "Hello") → "hiHellohi"
     comboString("aaa", "b") → "baaab"
     */
    public String comboString(String a, String b) {
        String shortest;
        String longest;
        if (a.length() < b.length()) {
            shortest = a;
            longest = b;
        } else {
            shortest = b;
            longest = a;
        }
        return (shortest + longest + shortest);

    }

    /*
     Given 2 strings, return their concatenation, except omit the first char of each. The strings will
     be at least length 1.
     nonStart("Hello", "There") → "ellohere"
     nonStart("java", "code") → "avaode"
     nonStart("shotl", "java") → "hotlava"
     */
    public String nonStart(String a, String b) {
        return (a.substring(1) + b.substring(1));
    }

    /*
     Given a string, return a "rotated left 2" version where the first 2 chars are moved to the end.
     The string length will be at least 2.
     left2("Hello") → "lloHe"
     left2("java") → "vaja"
     left2("Hi") → "Hi"
     */
    public String left2(String str) {
        String first = str.substring(0, 2);
        String last = str.substring(2);

        return (last + first);
    }

    /*
     Given a string, return a "rotated right 2" version where the last 2 chars are moved to the start.
     The string length will be at least 2.
     right2("Hello") → "loHel"
     right2("java") → "vaja"
     right2("Hi") → "Hi"
     */
    public String right2(String str) {
        String first;
        String lastTwo;
        lastTwo = str.substring(str.length() - 2);
        first = str.substring(0, str.length() - 2);


        //lastTwo = str.substring(str.length() / 2 );

        return lastTwo + first;
    }

    /*
     Given a string, return a string length 1 from its front, unless front is false, in which case
     return a string length 1 from its back. The string will be non-empty.
     theEnd("Hello", true) → "H"
     theEnd("Hello", false) → "o"
     theEnd("oh", true) → "o"
     */
    public String theEnd(String str, boolean front) {
        if (front) {
            str = str.substring(0, 1);
        } else {
            str = str.substring(str.length() - 1);
        }
        return str;
    }

    /*
     Given a string, return a version without both the first and last char of the string. The string
     may be any length, including 0.
     withoutEnd2("Hello") → "ell"
     withoutEnd2("abc") → "b"
     withoutEnd2("ab") → ""
     */
    public String withoutEnd2(String str) {
        if (str.length() <= 1) {
            return ("");
        }
        return (str.substring(1, str.length() - 1));
    }

    /*
     Given a string of even length, return a string made of the middle two chars, so the string "string"
     yields "ri". The string length will be at least 2.
     middleTwo("string") → "ri"
     middleTwo("code") → "od"
     middleTwo("Practice") → "ct"
     */
    public String middleTwo(String str) {
        String middleTwo;
        if (str.length() == 2) {
            middleTwo = str;
        } else {
            middleTwo = str.substring((str.length() / 2) - 1, (str.length() / 2) + 1);
        }
        return middleTwo;
    }

    /*
     Given a string, return true if it ends in "ly".
     endsLy("oddly") → true
     endsLy("y") → false
     endsLy("oddy") → false
     */
    public boolean endsLy(String str) {
        if (str.length() < 2) {
            return false;
        }
        String lastTwo = str.substring((str.length() - 2));
        return (lastTwo.contains("ly"));
    }

    /*
     Given a string and an int n, return a string made of the first and last n chars from the string. The
     string length will be at least n.
     nTwice("Hello", 2) → "Helo"
     nTwice("Chocolate", 3) → "Choate"
     nTwice("Chocolate", 1) → "Ce"
     */
    public String nTwice(String str, int n) {
        return str.substring(0, n) + str.substring(str.length() - n);
    }

    /*
     Given a string and an int n, return a string that starts at n and has a length of 2. Note that n may or may not be a valid
     location in the string. If n is too low or too high to define a string of length 2, return the string's first 2 characters.
     The string length will be at least 2.
     twoChar("java", 0) → "ja"
     twoChar("java", 2) → "va"
     twoChar("java", 3) → "ja"
     */
    public String twoChar(String str, int n) {
        // Check if n is a valid starting location for a substring of length 2
        if (n >= 0 && n <= str.length() - 2) {
            return str.substring(n, n + 2);
        } else {
            return str.substring(0, 2);
        }
    }

    /*
     Given a string of odd length, return the string length 3 from its middle, so "Candy" yields "and".
     The string length will be at least 3.
     middleThree("Candy") → "and"
     middleThree("and") → "and"
     middleThree("solving") → "lvi"
     */
    public String middleThree(String str) {
        int middleIndex = str.length() / 2;
        return str.substring(middleIndex - 1, middleIndex + 2);
    }

    /*
     Given a string, return true if "bad" appears starting at index 0 or 1 in the string, such as with
     "badxxx" or "xbadxx" but not "xxbadxx". The string may be any length, including 0. Note: use .equals()
     to compare 2 strings.
     hasBad("badxx") → true
     hasBad("xbadxx") → true
     hasBad("xxbadxx") → false
     */
    public static boolean hasBad(String str) {
        if (str.length() < 3) {
            return false;
        } else if (str.startsWith("bad")) {
            return true;
        } else return str.length() > 3 && str.startsWith("bad", 1);
    }

    /*
     Given a string and a non-negative int n, return a larger string that is n copies of the original string.
     stringTimes("Hi", 2) → "HiHi"
     stringTimes("Hi", 3) → "HiHiHi"
     stringTimes("Hi", 1) → "Hi"
     */

    public String stringTimes(String str, int n) {
        if (str.isEmpty()) {
            return "";
        }
        int index = 0;
        StringBuilder result = new StringBuilder();
        while (index < n) {
            result.append(str);
            index++;
        }
        return result.toString();
    }

    /*
     Given a string and a non-negative int n, we'll say that the front of the string is the first 3 chars, or
     whatever is there if the string is less than length 3. Return n copies of the front;
     frontTimes("Chocolate", 2) → "ChoCho"
     frontTimes("Chocolate", 3) → "ChoChoCho"
     frontTimes("Abc", 3) → "AbcAbcAbc"
     */

    public String frontTimes(String str, int n) {
        if (str.isEmpty()) {
            return "";
        }
        int length = str.length();
        if (str.length() > 3) {
            length = 3;
        }
        int i = 0;
        StringBuilder result = new StringBuilder();
        while (i < n) {
            result.append(str, 0, length);
            i++;
        }
        return result.toString();
    }

    /*
     Count the number of "xx" in the given string. We'll say that overlapping is allowed, so "xxx" contains 2 "xx".
     countXX("abcxx") → 1
     countXX("xxx") → 2
     countXX("xxxx") →
     */

    public int countXX(String str) {
        if (str.length() < 2) {
            return 0;
        }
        int index = 0;
        int count = 0;
        while (index < str.length()) {
            if (str.startsWith("xx", index)) {
                count++;
            }
            index++;
        }
        return count;
    }

    /*
     Given a string, return true if the first instance of "x" in the string is immediately followed by another "x".
     doubleX("axxbb") → true
     doubleX("axaxax") → false
     doubleX("xxxxx") → true
     */
    public boolean doubleX(String str) {
        int xOne = str.indexOf("x");
        if (xOne == -1 || xOne == str.length() - 1) {
            return false;
        }
        return (str.charAt(xOne + 1) == 'x'); // checks whether the character after x one is also "x"
    }

    /*
     Given a string, return a new string made of every other char starting with the first, so "Hello" yields "Hlo".
     stringBits("Hello") → "Hlo"
     stringBits("Hi") → "H"
     stringBits("Heeololeo") → "Hello"
     */
    public String stringBits(String str) {
        if (str.length() < 2) {
            return str;
        }
        char[] result = new char[(str.length() + 1) / 2];
        int resultIndex = 0;
        for (int i = 0; i < str.length(); i += 2) {
            result[resultIndex] = str.charAt(i);
            resultIndex++;
            }
        String resultString = new String(result);
        System.out.println(resultString);
        return resultString;

    }

    /*
     Given a non-empty string like "Code" return a string like "CCoCodCode".
     stringSplosion("Code") → "CCoCodCode"
     stringSplosion("abc") → "aababc"
     stringSplosion("ab") → "aab"
     */
    public String stringSplosion(String str) {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            output.append(str, 0, i + 1);
        }
        return output.toString();
    }

    /*
     Given a string, return the count of the number of times that a substring length 2 appears in the string and
     also as the last 2 chars of the string, so "hixxxhi" yields 1 (we won't count the end substring).
     last2("hixxhi") → 1
     last2("xaxxaxaxx") → 1
     last2("axxxaaxx") → 2
     */
    public int last2(String str) {
        if (str.length() <= 2) {
            return 0;
        }
        String lastTwo = str.substring(str.length() - 2);
        int sameTwoSubstrings = 0;

        for (int i = 0; i < str.length() - 2; i++) {
            String substring = str.substring(i, i + 2);
            if (substring.equals(lastTwo)) {
                sameTwoSubstrings++;
            }
        }
        return sameTwoSubstrings;
    }

    /*
     Given a string, return a version where all the "x" have been removed. Except an "x" at the very start or end
     should not be removed.
     stringX("xxHxix") → "xHix"
     stringX("abxxxcd") → "abcd"
     stringX("xabxxxcdx") → "xabcdx"
     */
    public String stringX(String str) {
        String xRemoved = "";
        if (str.length() <= 2) {
            return str;
        }
        if (!(str.contains("x"))) {
            return str;
        }
        String firstX = "";
        String lastX = "";
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(0) == 'x') {
                firstX = str.substring(0, 1);
                if (str.charAt(i) == 'x') {
                    xRemoved = str.replace("x", "");
                    if (str.charAt(str.length() - 1) == 'x') {
                        lastX = str.substring(str.length() - 1);
                    }
                }
            } else if (str.charAt(i) == 'x') {
                xRemoved = str.replace("x", "");
            }
        }

        /* else if (!(str.indexOf("x") == 0) && ((str.indexOf("x") == Integer.parseInt((str.substring(1
                , str.length() - 1)))))) {
            xRemoved = str.replace("x", "");

        */
        return firstX + xRemoved + lastX;
    }


    /*
     Given a string, return a string made of the chars at indexes 0,1, 4,5, 8,9 ... so "kittens" yields "kien".
     altPairs("kitten") → "kien"
     altPairs("Chocolate") → "Chole"
     altPairs("CodingHorror") → "Congrr"
     */
    public String altPairs(String str) {
        if (str.length() < 2) {
            return str;
        }
        int remainder = str.length() % 4;
        int noNull = (str.length() / 4) * 2;
        if (remainder > 2) {
            noNull += 2;
        } else {
            noNull += remainder;
        }
        char[] result = new char[noNull];
        int resultIndex = 0;
        for (int i = 0; i < str.length(); i += 4) {
            result[resultIndex] = str.charAt(i);
            resultIndex++;
            if (i + 1 < str.length()) {
                result[resultIndex] = str.charAt(i + 1);
                resultIndex++;
            }
        }
        String resultString = new String(result);
        System.out.println(resultString);
        return resultString;

        //   String outputString = "";

        //   for (int i = 0; i < str.length(); i += 2) {
        //      outputString += str.charAt(i);

        //  System.out.println("The output string is: " + outputString);
        // return outputString;

    }

    /*
     Suppose the string "yak" is unlucky. Given a string, return a version where all the "yak" are removed.
     The "yak" strings will not overlap.
     stringYak("yakpak") → "pak"
     stringYak("pakyak") → "pak"
     stringYak("yak123ya") → "123ya"
     */
    public String stringYak(String str) {
        if (str.length() < 3) {
            return str;
        } else if (str.contains("yak")) {
            return str.replace("yak", "");
        }
        return str;
    }
}

