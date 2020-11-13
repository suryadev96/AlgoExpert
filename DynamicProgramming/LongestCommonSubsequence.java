/*
Write a function that takes in 2 strings and return their longest common subsequence
S1 = "ZXVVYZW"
S2 = "XKYKZPW"

["X","Y","Z","W"]
*/
import java.util.*;
class LongestCommonSubsequence{

	public static List<Character> longestCommonSubsequence(String str1, String str2){
		int[][] lcs = new int[str2.length()+1][str1.length()+1];

		for (int i=1; i<str2.length()+1 ;i++){
			for (int j=1; j<str1.length()+1;j++){
				if (str2.charAt(i-1) == str1.charAt(j-1)){
					lcs[i][j] = lcs[i-1][j-1] + 1;
				} else{
					lcs[i][j] = Math.max(lcs[i-1][j], lcs[i][j-1]);
				}
			}
		}
		return buildSequence(lcs,str1);
	}

	public static List<Character> buildSequence(int[][] lcs, String str){
		List<Character> sequence = new ArrayList<Character>();
		int i = lcs.length - 1;
		int j = lcs[0].length - 1;

		//until both str1 and str2 is not empty. if one becomes empty then there is no common letter between them
		while (i != 0 && j!=0){
			if (lcs[i][j] == lcs[i-1][j]){
				i--;
			} else if (lcs[i][j] == lcs[i][j-1]){
				j--;
			} else{ //current character from both strings are equal and included in longestcommonsubsequence
				sequence.add(0,str.charAt(j-1));
				i--;
				j--;
			}
		}
		return sequence;
	}

	public static void main(String[] args){
		String str1 = "zxvvyzw";
		String str2 = "xkykzpw";
		List<Character> lcs = longestCommonSubsequence(str1,str2);
		for (Character c : lcs){
			System.out.print(c);
		}
	}

}

import java.util.*;

class Program {
  public static List<Character> longestCommonSubsequence(String str1, String str2) {
		int m = str1.length();
		int n = str2.length();
    int[][] dp = new int[m+1][n+1];
		
		for (int i=1;i<=m;i++){
			for (int j=1;j<=n;j++){
				if (str1.charAt(i-1) == str2.charAt(j-1)){
					dp[i][j] = dp[i-1][j-1] + 1;
				}else{
					dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
				}
			}
		}
		return buildCommonSubsequence(str1,str2,dp);
  }
	
	public static List<Character> buildCommonSubsequence(String s1, String s2, int[][] dp){
		int i=dp.length-1;
		int j = dp[0].length-1;
		
		List<Character> result = new ArrayList<>();
		
		while (i != 0 && j != 0){
			if (dp[i][j] == dp[i-1][j]){
				i--;
			}else if (dp[i][j] == dp[i][j-1]){
				j--;
			}else{
				result.add(0,s1.charAt(i-1));
				i--;
				j--;
			}
		}
		return result;
	}
}
