/*
String = "1921680"

[
"1.9.216.80",
"1.92.16.80"
]
*/
import java.util.*;

import java.util.*;

class Question {

 	public List<String> validIPAddresses(String s){

		List<String> result = new ArrayList<>();

		int n = s.length();

		for (int i=1; i< Math.min(n,4); i++) {

			String[] current = new String[]{"","","",""};

			current[0] = s.substring(0,i);

			if (!isValidPart(current[0])){
				continue;
			}

			for (int j=i+1;j<i+Math.min(n-i,4);j++){
				current[1] = s.substring(i,j);
				if (!isValidPart(current[1])){
					continue;
				}

				for (int k=j+1;k<j+Math.min(n-j,4);k++){
					current[2] = s.substring(j,k);
					current[3] = s.substring(k);

					if (isValidPart(current[2]) && isValidPart(current[3])){
						result.add(join(current));
					}
				}
			}
		}
		return result;
	}

	public boolean isValidPart(String s){
		int num = Integer.parseInt(s);
		if (num > 255) return false; 
		return s.length() == Integer.toString(num).length(); //check for leading 0
	}

	public String join(String[] s){
		StringBuilder sb = new StringBuilder();
		for (int i=0;i<s.length-1;i++){
			sb.append(s[i]).append(".");
		}
		sb.append(s[s.length-1]);
		return sb.toString();
	}
}
