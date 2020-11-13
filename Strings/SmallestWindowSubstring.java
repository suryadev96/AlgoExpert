import java.util.*;

class SmallestWindowSubstring {

    public static String smallestSubstringContaining(String bigString, String smallString) {
        Map<Character, Integer> targetCharCounts = getCharCounts(smallString);
        List<Integer> substringBounds = getSubstringBounds(bigString, targetCharCounts);
        return getStringFromBounds(bigString, substringBounds);
    }

    public static List<Integer> getSubstringBounds(String string, Map<Character, Integer> targetCharCounts) {
        List<Integer> substringBounds = new ArrayList<Integer>(Arrays.asList(0, Integer.MAX_VALUE));
        Map<Character, Integer> substringCharCounts = new HashMap<Character, Integer>();
        int numUniqueChars = targetCharCounts.size();
        int numUniqueCharsDone = 0;
        int leftIdx = 0;
        int rightIdx = 0;

        //Move the rightIdx to the right in the string until you have counted all the target characters enough times
        while (rightIdx < string.length()) {
            char rightChar = string.charAt(rightIdx);
            if (!targetCharCounts.containsKey(rightChar)) {
                rightIdx++;
                continue;
            }

            increaseCharCount(rightChar, substringCharCounts);
            if (substringCharCounts.get(rightChar) == targetCharCounts.get(rightChar)) {
                numUniqueCharsDone++;
            }

            //Move the leftIdx to the right in the string until you have no longer have enough of the target characters
            //in between the leftIdx and the rightIdx . update the stringbounds accordingly
            while (numUniqueCharsDone == numUniqueChars && leftIdx <= rightIdx) {
                substringBounds = getCloserBounds(leftIdx, rightIdx, substringBounds.get(0), substringBounds.get(1));
                char leftChar = string.charAt(leftIdx);
                if (!targetCharCounts.containsKey(leftChar)) {
                    leftIdx++;
                    continue;
                }
                if (substringCharCounts.get(leftChar) == targetCharCounts.get(leftChar)) {
                    numUniqueCharsDone--;
                }
                decreaseCharCount(leftChar, substringCharCounts);
                leftIdx++;
            }
            rightIdx++;
        }
        return substringBounds;
    }

    public static String getStringFromBounds(String string, List<Integer> bounds) {
        int start = bounds.get(0);
        int end = bounds.get(1);
        if (end == Integer.MAX_VALUE) return "";
        return string.substring(start, end + 1);
    }

    public static List<Integer> getCloserBounds(int idx1, int idx2, int idx3, int idx4) {
        return idx2 - idx1 < idx4 - idx3 ? new ArrayList<Integer>(Arrays.asList(idx1, idx2)) : new ArrayList<Integer>(Arrays.asList(idx3, idx4));
    }

    public static Map<Character, Integer> getCharCounts(String string) {
        Map<Character, Integer> charCounts = new HashMap<Character, Integer>();
        for (int i = 0; i < string.length(); i++) {
            increaseCharCount(string.charAt(i), charCounts);
        }
        return charCounts;
    }

    public static void increaseCharCount(char c, Map<Character, Integer> charCounts) {
        if (!charCounts.containsKey(c)) {
            charCounts.put(c, 1);
        } else {
            charCounts.put(c, charCounts.get(c) + 1);
        }
    }

    public static void decreaseCharCount(char c, Map<Character, Integer> charCounts) {
        charCounts.put(c, charCounts.get(c) - 1);
    }

    public static void main(String[] args) {
        String bigString = "abcd$ef$axb$c$";
        String smallString = "$$abf";
        String smallWindow = smallestSubstringContaining(bigString, smallString);
        System.out.println(smallWindow);
    }
}
/*
f$axb$
*/

//my solution
import java.util.*;

class Program {
    public static String smallestSubstringContaining(String bigString, String smallString) {
        Map<Character, Integer> targetCharCounts = getTargetCharCounts(smallString);
        int[] windowBounds = getWindowBounds(targetCharCounts, bigString);
        return getStringFromBounds(bigString, windowBounds);
    }

    public static int[] getWindowBounds(Map<Character, Integer> tMap, String s) {
        int[] windowBounds = {0, Integer.MAX_VALUE};

        Map<Character, Integer> wMap = new HashMap<>();

        int required = tMap.size();
        int formed = 0;

        int l = 0;
        int r = 0;

        while (r < s.length()) {

            char rc = s.charAt(r);
            if (!tMap.containsKey(rc)) {
                r++;
                continue;
            }

            wMap.put(rc, wMap.getOrDefault(rc, 0) + 1);

            if (wMap.get(rc) == tMap.get(rc)) {
                formed++;
            }

            //minimize the window as much as possible
            while (formed == required && l <= r) {
                int[] currentBounds = {l, r};
                if (currentBounds[1] - currentBounds[0] < windowBounds[1] - windowBounds[0]) {
                    windowBounds = currentBounds;
                }

                char lc = s.charAt(l);
                if (!tMap.containsKey(lc)) {
                    l++;
                    continue;
                }
                wMap.put(lc, wMap.get(lc) - 1);
                if (wMap.get(lc) < tMap.get(lc)) {
                    formed--;
                }
                l++;
            }
            r++;
        }
        return windowBounds;
    }

    public static String getStringFromBounds(String bigString, int[] bounds) {
        if (bounds[1] == Integer.MAX_VALUE) return "";
        return bigString.substring(bounds[0], bounds[1] + 1);
    }

    public static Map<Character, Integer> getTargetCharCounts(String T) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < T.length(); i++) {
            char c = T.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        return map;
    }
}
