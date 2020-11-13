/*
return minimum number of edit operations that need to be performed on the first string to obtain the second string
There are 3 edit operations : insertion of a character, deletion of a character, substitution of a character
*/

class LevenshteinDistance {

    public static int leveshteinDistance(String str1, String str2) {
        int[][] edits = new int[str2.length() + 1][str1.length() + 1];
        for (int i = 0; i < str2.length(); i++) {
            //if u don't have any characters present in the str1(j), then you have to insert i characters to match str2
            edits[i][0] = i;
        }
        for (int j = 0; j < str1.length(); j++) {
            //if u don't have any characters present in the str2(i), then you have to delete j characters present in str1(j)
            edits[0][j] = j;
        }

        for (int i = 1; i < str2.length() + 1; i++) {
            for (int j = 1; j < str1.length() + 1; j++) {
                if (str2.charAt(i - 1) == str1.charAt(j - 1)) {
                    edits[i][j] = edits[i - 1][j - 1];
                } else {
                    //Add additional character to str1(j) to match the last character of str2(i), then edits becomes i,j-1
                    //Delete last character from str1(j) , then edits becomes i-1,j
                    //Replace last character from str1 to match the last character of str2, then edits becomes i-1,j-1
                    edits[i][j] = Math.min(edits[i - 1][j - 1], Math.min(edits[i - 1][j], edits[i][j - 1]));
                }
            }
        }
        return edits[str2.length()][str1.length()];
    }

    public static int levenshteinDistance(String str1, String str2) {
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];

        //if we have to transform to empty string => delete all characters from str1
        for (int i = 0; i <= str1.length(); i++) {
            dp[i][0] = i;
        }

        //if we have to transform empty string to str2 => add all characters from str2
        for (int i = 0; i <= str2.length(); i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1]));
                }
            }
        }
        return dp[str1.length()][str2.length()];
    }

    //if u observe at any given point in 2D array, we are using only last two rows edits[i-1,j-1], edits[i-1,j], edits[i,j-1]
    //so u can save the time complexity by storing only last two rows
    //store the small string on the horizontal (meaning j index) and big string on the vertically (meaning i index)
    //space complexity O(min(m,n))
    public static int levenshteinDistanceEff(String str1, String str2) {
        String small = str1.length() < str2.length() ? str1 : str2;
        String big = str1.length() >= str2.length() ? str1 : str2;

        //this row is to represent even indexes starts from row 0
        int[] evenEdits = new int[small.length() + 1];
        //this row is to represent odd indexes starts from row 1
        int[] oddEdits = new int[small.length() + 1];

        //when big string is empty
        for (int j = 0; j < small.length() + 1; j++) {
            evenEdits[j] = j;
        }

        for (int i = 1; i < big.length() + 1; i++) {
            int[] currentEdits = new int[small.length() + 1];
            int[] previousEdits = new int[small.length() + 1];

            if (i % 2 == 1) {
                currentEdits = oddEdits;
                previousEdits = evenEdits;
            } else {
                currentEdits = evenEdits;
                previousEdits = oddEdits;
            }
            //when small string is empty
            currentEdits[0] = i;

            for (int j = 1; j < small.length() + 1; j++) {
                if (big.charAt(i - 1) == small.charAt(j - 1)) {
                    currentEdits[j] = previousEdits[j - 1];
                } else {
                    currentEdits[j] = 1 + Math.min(previousEdits[j - 1], Math.min(previousEdits[j], currentEdits[j - 1]));
                }
            }
        }
        return big.length() % 2 == 0 ? evenEdits[small.length()] : oddEdits[small.length()];
    }

    public static void main(String[] args) {
        String str1 = "abc";
        String str2 = "yabd";
        int edits = levenshteinDistanceEff(str1, str2);
        System.out.println(edits);
    }
}