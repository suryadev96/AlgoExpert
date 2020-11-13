class CaesarCypher{

	public static String caesarCypherEncryptor(String str, int key){
		char[] newLetters = new char[str.length()];
		int newKey = key % 26;
		for (int i=0 ; i< str.length(); i++){
			newLetters[i] = getNewLetter(str.charAt(i), newKey);
		}
		return new String(newLetters);
	}

	public static char getNewLetter(char letter, int key){
		int newLetterCode = letter + key;  //97 + 26 = 123
		return newLetterCode <= 122 ? (char) newLetterCode : (char) (96 + newLetterCode % 122);
	}

	public static String caesarCypherEncryptorEasy(String str, int key){
		char[] newLetters = new char[str.length()];
		int newKey = key % 26;
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		for (int i=0; i<str.length(); i++){
			newLetters[i] = getNewLetterEasy(str.charAt(i), newKey, alphabet);
		}
		return new String(newLetters);
	}


	public static char getNewLetterEasy(char letter, int key, String alphabet){
		int newLetterCode = alphabet.indexOf(letter) + key;
		return newLetterCode <= 25 ? alphabet.charAt(newLetterCode) : alphabet.charAt(newLetterCode % 26);
	}

	public static void main(String[] args){
		String s = "xyz";
		int key = 2;
		System.out.println(caesarCypherEncryptor(s,key));
		System.out.println(caesarCypherEncryptorEasy(s,key));
			
	}
}

