class Palindrome{

	public static boolean isPalindrome(String str){
		String reversedString = "";
		for (int i=str.length()-1;i>=0;i--){
			reversedString += str.charAt(i);
		}
		return str.equals(reversedString);
	}

	public static boolean isPalindrome(String str){
		StringBuilder reversedString = new StringBuilder();
		for (int i=str.length()-1;i>=0;i--){
			reversedString.append(str.charAt(i));
		}
		return str.equals(reversedString.toString());
	}

	public static boolean isPalindrome(String str){
		return isPalindrome(str,0);
	}

	public static boolean isPalindrome(String str, int i){
		int j = str.length() - 1 - i;
		return i>=j ? true: str.charAt(i) == str.charAt(j) && isPalindrome(str, i+1);
	}

	public static boolean isPalindrome(String str){
		int leftIdx = 0;
		int rightIdx = str.length()-1;
		while (leftIdx < rightIdx){
			if (str.charAt(leftIdx) != str.charAt(rightIdx)){
				return false;
			}
			leftIdx++;
			rightIdx--;
		}
		return true;
	}
}