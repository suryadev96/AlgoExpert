/*
u are given two strings. first pattern consists of only x and y. the other one is a normal string. function that checks if normal string
matches the pattern.
return the x , y
*/

import java.util.HashMap;
import java.util.Map;

class PatternMatcher {

    public static String[] patternMatcher(String pattern, String str) {
        if (pattern.length() > str.length()) {
            return new String[]{};
        }
        char[] newPattern = getNewPattern(pattern);
        boolean didSwitch = newPattern[0] != pattern.charAt(0);
        Map<Character, Integer> counts = new HashMap<Character, Integer>();
        counts.put('x', 0);
        counts.put('y', 0);
        int firstYPos = getCountsAndFirstYPos(newPattern, counts);
        if (counts.get('y') != 0) {

            for (int lenOfX = 1; lenOfX < str.length(); lenOfX++) {
                double lenOfY = ((double) str.length() - (double) lenOfX * (double) counts.get('x')) / (double) counts.get('y');
                if (lenOfY <= 0 || lenOfY % 1 != 0) {
                    continue;
                }
                int yIdx = firstYPos * lenOfX;
                String x = str.substring(0, lenOfX);
                String y = str.substring(yIdx, yIdx + (int) lenOfY);
                String potentialMatch = buildPotentialMatch(newPattern, x, y);
                if (str.equals(potentialMatch)) {
                    return didSwitch ? new String[]{y, x} : new String[]{x, y};
                }
            }

        } else {

            double lenOfX = str.length() / counts.get('x');
            if (lenOfX % 1 == 0) {
                String x = str.substring(0, (int) lenOfX);
                String potentialMatch = buildPotentialMatch(newPattern, x, "");
                if (str.equals(potentialMatch)) {
                    return didSwitch ? new String[]{"", x} : new String[]{x, ""};
                }
            }

        }
        return new String[]{};
    }

    //for each potential combination x and y; map the new pattern generated; this will be compared to the main string
    public static String buildPotentialMatch(char[] pattern, String x, String y) {
        StringBuilder potentialMatch = new StringBuilder();
        for (char c : pattern) {
            if (c == 'x') {
                potentialMatch.append(x);
            } else {
                potentialMatch.append(y);
            }
        }
        return potentialMatch.toString();
    }


    //if pattern doesn't starts with 'x' swap all x and y and generate a new pattern; this will simplify the algorithm
    public static char[] getNewPattern(String pattern) {
        char[] patternLetters = pattern.toCharArray();
        if (pattern.charAt(0) == 'x') {
            return patternLetters;
        }
        for (int i = 0; i < patternLetters.length; i++) {
            if (patternLetters[i] == 'x') {
                patternLetters[i] = 'y';
            } else {
                patternLetters[i] = 'x';
            }
        }
        return patternLetters;
    }

    //count the number of x and y; keep track of the position of first y . knowing how many x and y appear in the pattern,
    //paired with the length of main string will quickly allow you to test out various possible lengths for x and y
    public static int getCountsAndFirstYPos(char[] pattern, Map<Character, Integer> counts) {
        int firstYPos = -1;
        for (int i = 0; i < pattern.length; i++) {
            char c = pattern[i];
            counts.put(c, counts.get(c) + 1);
            if (c == 'y' && firstYPos == -1) {
                firstYPos = i;
            }
        }
        return firstYPos;
    }

    public static void main(String[] args) {
        String inputPattern = "xxyxxy";
        String inputString = "gogopowerrangergogopowerranger";
        String[] xy = patternMatcher(inputPattern, inputString);
        for (String s : xy) {
            System.out.print(s + " ");
        }
    }

}

//practice solution
import java.util.*;

class Program {
    public static String[] patternMatcher(String pattern, String str) {
        if (pattern.length() > str.length()) {
            return new String[]{};
        }
        //interchanges y with x if y starts first to simplify algo
        char[] newPattern = getNewPattern(pattern);

        boolean didSwitch = newPattern[0] != pattern.charAt(0);

        Map<Character, Integer> counts = new HashMap<>();
        int firstYPos = getCountsAndFirstYPos(newPattern, counts);

        if (counts.get('y') != 0) {

            for (int xLen = 1; xLen < str.length(); xLen++) {

                //xLen*Cx + yLen*Cy = str.length()
                double yLen = ((double) str.length() - (double) xLen * (double) counts.get('x')) / (double) counts.get('y');

                if (yLen <= 0 || yLen % 1 != 0) continue;

                int yIdx = firstYPos * xLen;
                String x = str.substring(0, xLen);
                String y = str.substring(yIdx, yIdx + (int) yLen);

                String potentialMatch = buildPotentialMatch(newPattern, x, y);

                if (str.equals(potentialMatch)) {
                    return didSwitch ? new String[]{y, x} : new String[]{x, y};
                }
            }
        } else {

            double xLen = str.length() / counts.get('x');

            if (xLen % 1 == 0) {
                String x = str.substring(0, (int) xLen);
                String potentialMatch = buildPotentialMatch(newPattern, x, "");
                if (str.equals(potentialMatch)) {
                    return didSwitch ? new String[]{"", x} : new String[]{x, ""};
                }
            }
        }
        return new String[]{};
    }

    public static String buildPotentialMatch(char[] pattern, String x, String y) {
        StringBuilder sb = new StringBuilder();
        for (char c : pattern) {
            if (c == 'x') {
                sb.append(x);
            } else {
                sb.append(y);
            }
        }
        return sb.toString();
    }

    public static int getCountsAndFirstYPos(char[] pattern, Map<Character, Integer> counts) {
        counts.put('x', 0);
        counts.put('y', 0);

        int firstYPos = -1;

        for (int i = 0; i < pattern.length; i++) {
            char ch = pattern[i];
            counts.put(ch, counts.get(ch) + 1);
            if (ch == 'y' && firstYPos == -1) {
                firstYPos = i;
            }
        }
        return firstYPos;
    }

    public static char[] getNewPattern(String pattern) {
        char[] patternLetters = pattern.toCharArray();
        if (pattern.charAt(0) == 'x') return patternLetters;
        for (int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) == 'x') {
                patternLetters[i] = 'y';
            } else {
                patternLetters[i] = 'x';
            }
        }
        return patternLetters;
    }
}
