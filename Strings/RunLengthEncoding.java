/*
String : "AAAAAAAAAAAAABBCCCCDD"
Output : "9A4A2B4C2D"

Long runs of 10 or more characters should be encoded in split fashion; like 9A3A
*/
import java.util.*;

class Program {

  public String runLengthEncoding(String string) {
    StringBuilder sb = new StringBuilder();
		
		char[] sChar = string.toCharArray();
		int n = sChar.length;
		int prev = -1;
		
		for (int i=0;i<n;i++){
			if (i == n-1 || sChar[i] != sChar[i+1] || (i - prev) == 9){
				sb.append(i - prev);
				sb.append(sChar[i]);
				prev = i;
			}
		}
    return sb.toString();
  }

}
