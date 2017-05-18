/**
 * Created by huxiongfeng on 10/05/17.
 */

//package scrabbler;



import java.io.*;
import java.text.*;
import java.nio.file.*;
import java.nio.charset.*;
import java.util.*;

class MatchSpell {
    private static String filename = "words.txt";

    public static List<String> matchSpell(String input) throws IOException {
        List<String> results = new ArrayList<>();
        List<String> dict = getWords(filename); // helper funtion #1
        matchSpell(input, dict, results); // helper funtion #2
        return results;
    }

    // Tested
    private static List<String> getWords(String filename) throws IOException {
        // get all lines in dictionary file line by line
        Path p = Paths.get(filename);
        return Files.readAllLines(p, StandardCharsets.UTF_8);
    }
    // Tested
    private static void matchSpell(String input, List<String> dict, List<String> results) {
        // corner case
        if(input == null || input.length() == 0 || dict == null) {
            return;
        }
        // put all letters in HashMap, set original values as 0
        Map<String, Integer> freqMap = new HashMap<>();
        for(int i = 0; i < input.length(); i++) {
            String letter = Character.toString(input.charAt(i));
            freqMap.put(letter, 0);
        }
        for(String word : dict) {
            // traverse all words in dict, add the satisfying ones into results
            if(isMatchSpell(freqMap, word)) { // helper funtion #3
                results.add(word);   
            }
        }
    }
    // Tested
    private static boolean isMatchSpell(Map<String, Integer> freqMap, String word) {
        Map<String, Integer> temp = new HashMap<>();
        temp.putAll(freqMap);
        for (int i = 0; i < word.length(); i++) {
            String letter = Character.toString(word.charAt(i));
            // if current letter in this word is not contained in given set or has already been used once, return false
            // if current letter in this word is contained in given set and is used for the first time, set its value to 1
            if (!temp.containsKey(letter) || temp.get(letter) != 0) {
                return false;
            } else {
                temp.put(letter, 1);
            }
        }
        return true;
    }
}

class MatchPrefix {
    private static String filename = "words.txt";

    // Tested
    public static List<String> matchPrefix(String input) throws IOException {
        List<String> results = new ArrayList<>();
        List<String> dict = getWords(filename); // helper funtion #1
        matchPrefix(input, dict, results); // helper funtion #2
        return results;
    }

    // Tested
    private static List<String> getWords(String filename) throws IOException {
        // get all lines in dictionary file line by line
        Path p = Paths.get(filename);
        return Files.readAllLines(p, StandardCharsets.UTF_8);
    }
    // Tested
    private static void matchPrefix(String input, List<String> dict, List<String> results) {
        // corner case
        if(input == null || input.length() == 0 || dict == null) {
            return;
        }

        for(String word : dict) {
            // traverse all words in dict, add the satisfying ones into results
            if(isMatchPrefix(input, word)) { // helper funtion #3
                results.add(word);   
            }
        }
    }
    // Tested
    private static boolean isMatchPrefix(String input, String word) {
        // corner case - word is shorter than prefix 
        if(word.length() < input.length()) {
            return false;
        }
        for (int i = 0; i < input.length(); i++) {
            String prefixLetter = Character.toString(input.charAt(i));
            String wordLetter = Character.toString(word.charAt(i));
            if (!wordLetter.equals(prefixLetter)) {
                return false;
            } 
        }
        return true;
    }
}

class MatchPrefixOpt {
    private static String filename = "words.txt";

    // Tested
    public static List<String> matchPrefix(String input) throws IOException {
        List<String> results = new ArrayList<>();
        List<String> dict = getWords(filename); // helper funtion #1
        matchPrefix(input, dict, results); // helper funtion #2
        return results;
    }

    // Tested
    private static List<String> getWords(String filename) throws IOException {
        Path p = Paths.get(filename);
        return Files.readAllLines(p, StandardCharsets.UTF_8);
    }
    // Tested
    private static void matchPrefix(String input, List<String> dict, List<String> results) {
        // corner case
        if(input == null || input.length() == 0 || dict == null) {
            return;
        }
        // leftBound - the index of first occurence of specified prefix
        // rightBound - the index of last occurence of specified prefix
        int leftBound = firstOccurence(input, dict);
        int rightBound = lastOccurence(input, dict);
        for(int i = leftBound; i <= rightBound; i++) {
            results.add(dict.get(i));
        }
    }
    // Tested
    private static int firstOccurence(String input, List<String> dict) {
        int left = 0;
        int right = dict.size() - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if(compare(dict.get(mid), input) == 1 || compare(dict.get(mid), input) == 0) {
                // The largest index of prefix words is smaller than or equal to mid
                right = mid;
            } else {
                // The smallest index of prefix words is larger than or equal to mid
                left = mid;
            }
        }
        if(compare(dict.get(left), input) ==  0) {
            return left;
        } else {
            return right;
        }
    }

    // Tested
    private static int lastOccurence(String input, List<String> dict) {
        int left = 0;
        int right = dict.size() - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if(compare(dict.get(mid), input) == -1 || compare(dict.get(mid), input) == 0) {
                // The index of prefix words should be larger than mid
                left = mid;
            } else {
                right = mid;
            }
        }
        if(compare(dict.get(right), input) ==  0) {
            return right;
        } else {
            return left;
        }
    }

    // Tested
    private static int compare(String word, String prefix) {
        int i = 0;
        while(i < word.length() && i < prefix.length() && word.charAt(i) == prefix.charAt(i) ) {
            i++;
        }
        if(i >= prefix.length()) {
            return 0;
        } else if(i >= word.length() || word.charAt(i) < prefix.charAt(i)) {
            return -1;
        } else {
            return 1;
        }
    }
}

class MatchSuffix {
    private static String filename = "words.txt";

    // Tested
    public static List<String> matchSuffix(String input) throws IOException {
        List<String> results = new ArrayList<>();
        List<String> dict = getWords(filename); // helper funtion #1
        matchSuffix(input, dict, results); // helper funtion #2
        return results;
    }

    // Tested
    private static List<String> getWords(String filename) throws IOException {
        Path p = Paths.get(filename);
        return Files.readAllLines(p, StandardCharsets.UTF_8);
    }
    // Tested
    private static void matchSuffix(String input, List<String> dict, List<String> results) {
        // corner case
        if(input == null || input.length() == 0 || dict == null) {
            return;
        }

        for(String word : dict) {
            if(isMatchSuffix(input, word)) { // helper funtion #3
                results.add(word);   
            }
        }
    }
    // Tested
    private static boolean isMatchSuffix(String input, String word) {
        // corner case - word is shorter than Suffix 
        if(word.length() < input.length()) {
            return false;
        }
        for (int i = 0; i < input.length(); i++) {
            String SuffixLetter = Character.toString(input.charAt(input.length()-1-i));
            String wordLetter = Character.toString(word.charAt(word.length()-1-i));
            if (!wordLetter.equals(SuffixLetter)) {
                return false;
            } 
        }
        return true;
    }
}

public class scrabbler {
    public static void main(String[] arg) {
       try {
            List<String> results = new ArrayList<>();
            if(arg == null || arg.length == 0) {
                return;
            }
            String s = arg[0];
            if(s.charAt(0) == '-' && s.charAt(2) == 'p') {
                // To find all words that begin with a specific prefix
                //results = MatchPrefix.matchPrefix(arg[1]);
                // Optimized method by binary search
                results = MatchPrefixOpt.matchPrefix(arg[1]);
            } else if(s.charAt(0) == '-' && s.charAt(2) == 's') {
                // To find all words with a specific suffix
                results = MatchSuffix.matchSuffix(arg[1]);
            }else {                
                results = MatchSpell.matchSpell(arg[0]);
            }
            for(String result : results) {
                System.out.println(result);
            }
        } catch(IOException exception) {
            exception.printStackTrace();
        }
    }
}

