/*
minimum number of cuts needed to perform on the string such that each substring is a palindrome

noonabbad

C[i] -> min no of cuts needed for the string s[0..i] 
noonabba

oonabba is palindrome ? n | oonabaa  n - C[0]
onabba is palindrome ? no | onabba   no - C[1]
nabba is palindrome ?  noo | nabba   noo - C[2]
abba is palindrome ? noon | abba     noon - C[3]
bba is palindrome ? noona | bba 

Time complexity is for each index in the cut array, u are iterating over all the previous indexes. O(n^2)
Time complexity for builing palindrome array is O(n^3) => this can be reduced to O(n^2) by using DP
*/
import java.util.*;

class Question{

	public static int palindromePartitioningMinCuts(String s){

		boolean[][] palindromes = new boolean[s.length()][s.length()];

		//palindrome[i][j] determines if str[i..j] is a palindrome or not
		for (int i=0;i<s.length();i++){
			for (int j=i;j<s.length();j++){
				palindromes[i][j] = isPalindrome(s.substring(i,j+1));
			}
		}

		//C[i] -> min no of cuts needed for the string s[0..i] such that each substring is a palindrome
		int[] cuts = new int[s.length()];

		Arrays.fill(cuts,Integer.MAX_VALUE);

		for (int i=0;i<s.length();i++){
			if (palindromes[0][i]){
				cuts[i] = 0;
			}else{
				cuts[i] = cuts[i-1] + 1;
				//check for all the substrings if u can make a palindrome
				for (int j=1;j<i;j++){
					if (palindromes[j][i] && cuts[j-1] + 1 < cuts[i]){
						cuts[i] = cuts[j-1]+1;
					}
				}
			}
		}
		return cuts[s.length()-1];
	}

	public static int palindromePartitioningMinCutsEff(String s){

		boolean[][] palindromes = new boolean[s.length()][s.length()];

		for (int i=0;i<s.length();i++){
			for (int j=0;j<s.length();j++){
				if (i == j){
					palindromes[i][j] = true;
				}else{
					palindromes[i][j] = false;
				}
			}
		}

		//process all the substrings of length 2 .. n
		for (int l=2 ; l <= s.length();l++){
			// i,j represents a str[i.j] start index i, end index j
			for (int i=0;i<s.length() - l + 1 ; i++){
				int j = i + l - 1;
				if (l == 2){
					palindromes[i][j] = (s.charAt(i) == s.charAt(j));
				}else{
					palindromes[i][j] = (s.charAt(i) == s.charAt(j) && palindromes[i+1][j-1]); 
				}
			}
		}

		//C[i] -> min no of cuts needed for the string s[0..i] such that each substring is a palindrome
		int[] cuts = new int[s.length()];

		Arrays.fill(cuts,Integer.MAX_VALUE);

		for (int i=0;i<s.length();i++){
			if (palindromes[0][i]){
				cuts[i] = 0;
			}else{
				cuts[i] = cuts[i-1] + 1;
				//check for all the substrings if u can make a palindrome
				for (int j=1;j<=i;j++){
					if (palindromes[j][i] && cuts[j-1] + 1 < cuts[i]){
						cuts[i] = cuts[j-1]+1;
					}
				}
			}
		}
		return cuts[s.length()-1];

		/*
		int[] cuts = new int[s.length()+1];

		Arrays.fill(cuts, Integer.MAX_VALUE);

		cuts[0] = 0;

		for (int i=1;i<=s.length();i++){
			if (palindromes[0][i-1]) cuts[i] = 0;
			else{
				cuts[i] = cuts[i-1] + 1;

				for (int j=0;j<i;j++){
					if (palindromes[j][i-1] && cuts[j] + 1 < cuts[i]){
						cuts[i] = cuts[j] + 1;
					}
				}

			}
		}
		return cuts[s.length()]
		*/
	}

	public static boolean isPalindrome(String s){
		int leftIdx = 0;
		int rightIdx = s.length()-1;

		//aba //abba
		while (leftIdx < rightIdx){
			if (s.charAt(leftIdx) != s.charAt(rightIdx)){
				return false;
			}
			leftIdx++;
			rightIdx--;
		}
		return true;
	}

	public static void main(String[] args){
		String s = "noonabbad";
		int minCuts = palindromePartitioningMinCutsEff(s);
		System.out.println(minCuts);
	}
}
import java.util.*;

class Program {
  public static int palindromePartitioningMinCuts(String str) {
    int n = str.length();
		if (n == 1) return 0;
		boolean[][] palindrome = new boolean[n][n];
		for (int i=0;i<n;i++){
			palindrome[i][i] = true;
		}
		for (int l=2;l<=n;l++){
			for (int i=0;i<=n-l;i++){
				int j = i+l-1;
				if (l == 2){
					palindrome[i][j] = str.charAt(i) == str.charAt(j);
				}else{
					palindrome[i][j] = (str.charAt(i) == str.charAt(j) && palindrome[i+1][j-1]);
				}
			}
		}
		
		int[] cuts = new int[n+1];
		cuts[0] = -1;
		for (int i=1;i<=n;i++){
			cuts[i] = cuts[i-1] + 1;
			for (int j=0;j<i;j++){
				if (palindrome[j][i-1]){
					cuts[i] = Math.min(cuts[i], cuts[j] + 1);
				}
			}
		}
    return cuts[n];
  }
}
