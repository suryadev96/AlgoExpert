class Question {

    public static boolean interleavingStrings(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length()) return false;

        return areInterleaved(s1, s2, s3, 0, 0);
    }

    public static boolean areInterleaved(String s1, String s2, String s3, int i, int j) {
        int k = i + j;

        if (k == s3.length()) return true;

        if (i < s1.length() && s1.charAt(i) == s3.charAt(k)) {
            if (areInterleaved(s1, s2, s3, i + 1, j)) return true;
        }

        if (j < s2.length() && s2.charAt(j) == s3.charAt(k)) {
            return areInterleaved(s1, s2, s3, i, j + 1);
        }
        return false;
    }

    public static boolean interleavingStringsMemo(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length()) return false;

        Boolean[][] cache = new Boolean[s1.length()][s2.length()];

        return areInterleavedMemo(s1, s2, s3, 0, 0, cache);
    }

    public static boolean areInterleavedMemo(String s1, String s2, String s3, int i, int j, Boolean[][] cache) {

        if (cache[i][j] != null) return cache[i][j];
        int k = i + j;

        if (k == s3.length()) return true;

        if (i < s1.length() && s1.charAt(i) == s3.charAt(k)) {
            boolean result = areInterleavedMemo(s1, s2, s3, i + 1, j, cache);
            cache[i][j] = result;
            if (cache[i][j]) return true;
        }

        if (j < s2.length() && s2.charAt(j) == s3.charAt(k)) {
            boolean result = areInterleavedMemo(s1, s2, s3, i, j + 1, cache);
            cache[i][j] = result;
            return result;
        }
        return false;
    }

    public static void main(String[] args) {
        String s1 = "algoexpert";
        String s2 = "your-dream-job";
        String s3 = "your-algodream-expertjob";
        boolean isInterleaved = interleavingStrings(s1, s2, s3);
        System.out.println(isInterleaved);
    }
}

//my solution
import java.util.*;

class Program {
    public static boolean interweavingStrings(String one, String two, String three) {
        if (three.length() != one.length() + two.length()) return false;

        boolean[][] dp = new boolean[one.length() + 1][two.length() + 1];

        for (int i = 0; i <= one.length(); i++) {
            for (int j = 0; j <= two.length(); j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] && two.charAt(j - 1) == three.charAt(j - 1);
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] && one.charAt(i - 1) == three.charAt(i - 1);
                } else {
                    dp[i][j] = (dp[i - 1][j] && one.charAt(i - 1) == three.charAt(i + j - 1)
                            || dp[i][j - 1] && two.charAt(j - 1) == three.charAt(i + j - 1));
                }
            }
        }
        return dp[one.length()][two.length()];
    }
}
