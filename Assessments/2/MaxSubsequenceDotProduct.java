/*
In mathematics, the dot product of two vectors a = [a1, a2, .... an] and b = [b1, b2 .... bn] is equal to 

Maximum dot product that can be obtained from any two subsequences of the resepective input array
*/

class Program {
    public static int maxSubsequenceDotProduct(int[] arrayOne, int[] arrayTwo) {

        int m = arrayOne.length;
        int n = arrayTwo.length;

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            int one = arrayOne[i - 1];
            for (int j = 1; j <= n; j++) {
                int two = arrayTwo[j - 1];
                int currProduct = one * two;
                dp[i][j] = Math.max(dp[i][j - 1], Math.max(dp[i - 1][j], dp[i - 1][j - 1] + currProduct));
            }
        }

        return dp[m][n];
    }

    public static int handleOverflow(int number, int toAdd) {
        if (toAdd > 0) return number + toAdd;

        boolean willOverflow = toAdd < 0 && number + toAdd > number;

        if (willOverflow) return number;

        return number + toAdd;
    }
}
