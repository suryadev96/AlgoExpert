/*
* match any number of characters including zero characters
? matches single character

"*.js" matches any file name ending in the javascript extension

filename = "abcdefg";
pattern = "a*e?g";
*/
import java.util.*;

class Program {
  public static boolean globMatching(String fileName, String pattern) {
    int m = fileName.length();
		int n = pattern.length();
		
		boolean[][] dp = new boolean[m+1][n+1];
		
		dp[0][0] = true;
		
		//if filename is empty
		for (int i=1;i<=n;i++){
			if (pattern.charAt(i-1) == '*'){
				dp[0][i] = true;
			}else{
				break; //if we encouter any character other than * 
			}
		}
		
		//if pattern is empty and file is not empty => all false;
		for (int i=1;i<=m;i++){
			for (int j=1;j<=n;j++){
				char fileCh = fileName.charAt(i-1);
				char patternCh = pattern.charAt(j-1);
				
				if (patternCh != '*'){
					dp[i][j] = (fileCh == patternCh) ? dp[i-1][j-1] : (patternCh == '?') ? dp[i-1][j-1] : false; 
				}else{
					//* can match 0 characters ; match 1 character ; match multiple characters
					dp[i][j] = dp[i][j-1] || dp[i-1][j-1] || dp[i-1][j];
				}
			}
		}
    return dp[m][n];
  }
}
