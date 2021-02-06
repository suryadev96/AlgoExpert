/*
Word can contain special characters, punctuation and numbers .
"whitespaces 4" => "4 whitespaces"
You're not allowed to use any built in split or reverse methods . 
You can use join method
*/
class Question{

	public String reverseWordsInString(String s){
		char[] sChar = s.toCharArray();
		int n = sChar.length;
		reverse(sChar, 0, n-1);

		int startOfWord = 0;

		while (startOfWord < n){

			int endOfWord = startOfWord;
			while (endOfWord < n && sChar[endOfWord] != ' '){
				endOfWord++;
			}

			//endOfWord points to space
			reverse(sChar, startOfWord, endOfWord-1);
			startOfWord = endOfWord + 1;
		}
		return new String(sChar);
	}

	public void reverse(char[] sChar , int start, int end){
		while (start < end){
			char temp = sChar[start];
			sChar[start] = sChar[end];
			sChar[end] = temp;
			start++;
			end--;
		}
	}

}