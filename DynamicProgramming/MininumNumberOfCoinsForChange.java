/*
write a function that returns the smallest number of coins needed to make the change that target amount using the 
given coin denominations
*/
import java.util.*;

class MinimumNumberOfCoinsForChange{

	public static int minimumNumberOfCoinsForChange(int n, int[] denoms){
		int[] numOfCoins = new int[n + 1];
		Arrays.fill(numOfCoins, Integer.MAX_VALUE);
		numOfCoins[0] = 0;
		int toCompare = 0;
		for (int denom: denoms){
			for (int amount = 0; amount < numOfCoins.length ; amount++){
				if (denom <= amount){
					if (numOfCoins[amount - denom] == Integer.MAX_VALUE){
						toCompare = numOfCoins[amount - denom];
					}else{
						toCompare = numOfCoins[amount - denom] + 1;
					}	
					numOfCoins[amount] = Math.min(numOfCoins[amount], toCompare);
				}
			}
		}
		return numOfCoins[n] != Integer.MAX_VALUE ? numOfCoins[n] : -1;
	}

	public static int minimumNumberOfCoinsForChangeEff(int[] denoms, int n){

		int[] dp = new int[n+1];

		int max = n+1; //Assuming each item weights 1 in the worst case

        Arrays.fill(dp,max);
		//if knapsack capacity is 0, u don't need any items
		dp[0] = 0;

		//dp[i][j] is min weights required to achieve weight j with first i weights
		//dont consider current weight or consider current weight
		//dp[i][j] = dp[i-1][j] || dp[i][j-weights[i-1]]

		for(int i=0;i<denoms.length;i++){
			for (int j=denoms[i];j<=n;j++){
				dp[j] = Math.min(dp[j],dp[j-denoms[i]] + 1);
			}
		}
		return dp[n] > n ? -1 : dp[n];
	}

	public static int minNumberOfCoinsForChangeMySolution(int n, int[] denoms) {
    	int[] dp = new int[n+1];
		Arrays.fill(dp,n+1);
		dp[0] = 0;
		for (int i=0;i<denoms.length;i++){
			for (int j=denoms[i];j<=n;j++){
				dp[j] = Math.min(dp[j],dp[j-denoms[i]] + 1);
			}
		}
		return dp[n] == n+1 ? -1 : dp[n];
  }

	public static void main(String[] args) {
		int n = 7;
		int[] denoms = {1,5,10};
		System.out.println(minimumNumberOfCoinsForChangeEff(denoms,n));
	}

}