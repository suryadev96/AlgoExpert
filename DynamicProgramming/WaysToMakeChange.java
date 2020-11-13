/*
Given an array of distinct positive integers representing coin denominations and a n representing a target amount of money
write a function that returns the number of ways to make change for that target amount using the given coin denominations
*/
import java.util.*;

class WaysToMakeChange{

	public static int numberOfWaysToMakeChange(int n, int[] denoms){
		int[] ways = new int[n + 1];
		ways[0] = 1;
		for (int denom : denoms){
			for (int amount = 1; amount <= n; amount++){
				if (denom <= amount){
					ways[amount] += ways[amount - denom];
				}
			}
		}
		return ways[n];
	}

	public static int numberOfWaysToMakeChange(int n, int[] denoms) {
	    int[] ways = new int[n+1];
		ways[0] = 1;
		for (int i=0;i<denoms.length;i++){
			for (int j=denoms[i];j<=n;j++){
				ways[j] += ways[j-denoms[i]];
			}
		}
	    return ways[n];
  	}


	public static void main(String[] args) {
		int n = 6;
		int[] denom = {1,5};
		System.out.println(numberOfWaysToMakeChange(n,denom));
	}

}