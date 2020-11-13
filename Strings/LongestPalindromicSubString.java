class LongestPalindromicSubstring {

    public static String longestPalindromSubstring(String str) {
        String longest = "";
        for (int i = 0; i < str.length(); i++) {
            for (int j = i; j < str.length(); j++) {
                String substring = str.substring(i, j + 1);
                if (substring.length() > longest.length() && isPalindrome(substring)) {
                    longest = substring;
                }
            }
        }
        return longest;
    }

    public static boolean isPalindrome(String str) {
        int leftIdx = 0;
        int rightIdx = str.length() - 1;
        while (leftIdx < rightIdx) {
            if (str.charAt(leftIdx) != str.charAt(rightIdx)) {
                return false;
            }
            leftIdx++;
            rightIdx--;
        }
        return true;
    }

    public static String longestPalindromSubstringEff(String str) {
        int[] longest = {0, 1};
        // i being the center of the palindrome
        for (int i = 1; i < str.length(); i++) {
            //abcba -> c is the center (i-1,i+1)
            int[] odd = getLongestPalindromFrom(str, i - 1, i + 1);
            //abccba -> if second c is the center (i-1,i)
            int[] even = getLongestPalindromFrom(str, i - 1, i);
            int[] currentLongest = odd[1] - odd[0] > even[1] - even[0] ? odd : even;
            longest = longest[1] - longest[0] > currentLongest[1] - currentLongest[0] ? longest : currentLongest;
        }
        return str.substring(longest[0], longest[1]);
    }

    public static int[] getLongestPalindromFrom(String str, int leftIdx, int rightIdx) {
        while (leftIdx >= 0 && rightIdx < str.length()) {
            if (str.charAt(leftIdx) != str.charAt(rightIdx)) {
                break;
            }
            leftIdx--;
            rightIdx++;
        }
        //leftIdx and rightIdx both are exclusive of the palindrome
        //but since we are using substring funtion , the leftIdx must be inclusive .hence adding +1
        return new int[]{leftIdx + 1, rightIdx};
    }

    public static void main(String[] args) {
        String s = "abaxyzzyxf";
        String longestPalindrome = longestPalindromSubstring(s);
        System.out.println(longestPalindrome);
        longestPalindrome = longestPalindromSubstringEff(s);
        System.out.println(longestPalindrome);
    }

}