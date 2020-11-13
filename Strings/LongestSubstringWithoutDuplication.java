import java.util.HashMap;
import java.util.Map;

class LongestSubstringWithoutDuplication {

    public static String longestSubstringWithoutDuplication(String str) {
        //the recent index of each character seen in the string
        Map<Character, Integer> lastSeen = new HashMap<Character, Integer>();

        int[] longest = {0, 1};
        //startIndex is most recent index from which you could start a substring with no duplicate characters, ending at your current index
        int startIdx = 0;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (lastSeen.containsKey(c)) {
                startIdx = Math.max(startIdx, lastSeen.get(c) + 1); //this condition is important
            }
            if (longest[1] - longest[0] < i + 1 - startIdx) {
                longest = new int[]{startIdx, i + 1};
            }
            lastSeen.put(c, i);
        }
        String result = str.substring(longest[0], longest[1]);
        return result;
    }

    public static void main(String[] args) {
        String s = "clementisacap";
        String longestWithoutDuplicates = longestSubstringWithoutDuplication(s);
        System.out.println(longestWithoutDuplicates);
    }
}
/*
mentisac

startIdx: if u remove Math.max() condition
c : 0
l : 0
e : 0
m : 0
e : 3
n : 3
t : 3
i : 3
s : 3
a : 3
c : 1
a : 10
p : 10
lementisac

*/
