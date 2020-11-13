/*
array of positive integers representing the prices of single stock on various days(each index in the array represents a different day)
one transaction consists of buying the stock on given day and selling it on another later day
-> maximum profit u can make by buying and selling the stock given k transactions
*/

/*
prices -> 	[5,11,3,50,60,90] with k = 2

profit -> 0  0 0 0 0 0 0
		  1  0 6 6 47 57 87
		  2  0 6 6 53 63 93 

		  profit[t][d] => represents maximum profit made by t transactions till d days
	
				//not selling the stock on the d th day , selling the stock on the d th day
		  = max {profit[t][d-1] , price[d] + max ( -price[x] + profit[t-1][x]) }
											 0<=x<d

	time complexity = O(n^2k) => O(nk) if u realize that second max operation can be reduced by keeping track of max variable
										// d = 4 => -price[3] + profit[t-1][3]
												 => -price[2] + profit[t-1][2]
												 => -price[1] + profit[t-1][1]
												 => -price[0] + profit[t-1][0]
										//d = 5 => -price[4] + profit[t-1][4]
													same operations as 4					
	space complexity = O(nk) => O(n*2) if u realize that at any point in the 2d array u are using only last 2 rows
*/
class Question {

    public static int maxProfitWithKTransactions(int[] prices, int k) {
        if (prices.length == 0) return 0;
        int[][] profits = new int[k + 1][prices.length];

        for (int t = 1; t <= k; t++) {
            for (int d = 1; d < prices.length; d++) {
                int maxIncluding = 0;
                for (int p = 0; p < d; p++) {
                    maxIncluding = Math.max(maxIncluding, prices[d] - prices[p] + profits[t - 1][p]);
                }
                profits[t][d] = Math.max(profits[t][d - 1], maxIncluding);
            }
        }
        return profits[k][prices.length - 1];
    }

    public static int maxProfitWithKTransactionsCompEff(int[] prices, int k) {
        if (prices.length == 0) {
            return 0;
        }
        int[][] profits = new int[k + 1][prices.length];

        //base case is when t = 0 , u cant make any profit = 0
        //when d = 0, on the first day u cant make any profit = 0

        for (int t = 1; t <= k; t++) {
            int maxThusFar = Integer.MIN_VALUE;
            for (int d = 1; d < prices.length; d++) {
                maxThusFar = Math.max(maxThusFar, profits[t - 1][d - 1] - prices[d - 1]);
                profits[t][d] = Math.max(profits[t][d - 1], maxThusFar + prices[d]);
            }
        }
        return profits[k][prices.length - 1];
    }

    public static int maxProfitWithKTransactionsEff(int[] prices, int k) {
        if (prices.length == 0) return 0;

        //this is to represent even index starting from 0
        int[] evenProfits = new int[prices.length];
        int[] oddProfits = new int[prices.length];

        for (int t = 1; t <= k; t++) {
            int maxThusFar = Integer.MIN_VALUE;

            int[] currentProfits = new int[prices.length];
            int[] previousProfits = new int[prices.length];

            if (t % 2 == 1) {
                currentProfits = oddProfits;
                previousProfits = evenProfits;
            } else {
                currentProfits = evenProfits;
                previousProfits = oddProfits;
            }

            for (int d = 1; d < prices.length; d++) {
                maxThusFar = Math.max(maxThusFar, previousProfits[d - 1] - prices[d - 1]);
                currentProfits[d] = Math.max(currentProfits[d - 1], maxThusFar + prices[d]);
            }
        }
        return k % 2 == 0 ? evenProfits[prices.length - 1] : oddProfits[prices.length - 1];
    }

    public static void main(String[] args) {
        int[] prices = {5, 11, 3, 50, 60, 90};
        int k = 2;
        int maxProfit = maxProfitWithKTransactionsEff(prices, k);
        System.out.println(maxProfit);
    }
}