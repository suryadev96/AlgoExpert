/*
Construct a Suffix Trie from big string and search all the small strings in this suffix trie
*/
import java.util.*;

class TrieNode{
	Map<Character,TrieNode> children = new HashMap<Character,TrieNode>();
}
class ModifiedSuffixTrie{

	TrieNode root = new TrieNode();

	public ModifiedSuffixTrie(String str){
		populateModifiedSuffixTrieFrom(str);
	}

	public void populateModifiedSuffixTrieFrom(String str){
		for (int i=0;i<str.length();i++){
			insertSubstringStartingAt(i,str);
		}
	}

	public void insertSubstringStartingAt(int i, String str){
		TrieNode node = root;
		for (int j=i;j<str.length();j++){
			char letter = str.charAt(j);
			if (!node.children.containsKey(letter)){
				TrieNode newNode = new TrieNode();
				node.children.put(letter,newNode);
			}
			node = node.children.get(letter);
		}
	}

	//str need not be a suffix but definitely it is a substring of a suffix
	public boolean contains(String str){
		TrieNode node = root;
		for (int i=0;i<str.length();i++){
			char letter = str.charAt(i);
			if (!node.children.containsKey(letter)){
				return false;
			}
			node = node.children.get(letter);
		}
		return true;
	}
}

class Question{


	public static List<Boolean> multiStringSearch(String bigString, String[] smallStrings)	{
		ModifiedSuffixTrie modifiedSuffixTrie = new ModifiedSuffixTrie(bigString);
		List<Boolean> solution = new ArrayList<Boolean>();
		for (String smallString : smallStrings){
			solution.add(modifiedSuffixTrie.contains(smallString));
		}
		return solution;
	}

	public static void main(String[] args){
		String bigString = "this is a big string";
		String[] smallStrings = {"this","yo","is","a","bigger","string","kappa","th"};
		List<Boolean> ans = multiStringSearch(bigString,smallStrings);
		printList(ans,smallStrings);
	}

	public static void printList(List<Boolean> ans, String[] smallStrings){
		int index=0;
		for (String s : smallStrings){
			System.out.println(s + "-> " + ans.get(index));
			index++;
		}
	}

}
/*
this-> true
yo-> false
is-> true
a-> true
bigger-> false
string-> true
kappa-> false
th->true
*/
//----------------------
/*
Construct a trie from all the small strings and search in the parts of big string if anything matches the trie from root to leaf
*/
import java.util.*;
class TrieNode{

	Map<Character,TrieNode> children = new HashMap<Character,TrieNode>();
	String word;
}
class Trie{

	TrieNode root = new TrieNode();
	char endSymbol = '*';

	public void insert(String str){
		TrieNode node = root;
		for (int i=0;i<str.length();i++){
			char letter = str.charAt(i);

			if (!node.children.containsKey(letter)){
				TrieNode newNode = new TrieNode();
				node.children.put(letter,newNode);
			}
			node = node.children.get(letter);
		}
		node.children.put(endSymbol,null);
		node.word = str;
	}
}
class Question{	


	public static List<Boolean> multiStringSearch(String bigString, String[] smallStrings){
		Trie trie = new Trie();
		for (String smallString : smallStrings){
			trie.insert(smallString);
		}

		//we have to check if small string is a substring of bigstring

		Set<String> containedStrings = new HashSet<String>();

		//search out in all the suffixes because if small string is a substring of bigstring. it definitely starts with any of the suffix
		for (int i=0;i<bigString.length();i++){
			findSmallStringsIn(bigString,i,trie,containedStrings);
		}

		List<Boolean> solution = new ArrayList<Boolean>();
		for (String str: smallStrings){
			solution.add(containedStrings.contains(str));
		}
		return solution;
	}

	//checks if small strings are present in the substring of str starting at startIdx
	public static void findSmallStringsIn(String str, int startIdx, Trie trie, Set<String> containedStrings){

		TrieNode currentNode = trie.root;

		for (int i=startIdx; i < str.length();i++){
			char currentChar = str.charAt(i);
			if (!currentNode.children.containsKey(currentChar)){
				break;
			}
			currentNode = currentNode.children.get(currentChar);
			if (currentNode.children.containsKey(trie.endSymbol)){
				containedStrings.add(currentNode.word);
			}
		}

	}

	public static void main(String[] args){
		String bigString = "this is a big string";
		String[] smallStrings = {"this","yo","is","a","bigger","string","kappa"};
		List<Boolean> ans = multiStringSearch(bigString,smallStrings);
		printList(ans,smallStrings);
	}

	public static void printList(List<Boolean> ans, String[] smallStrings){
		int index=0;
		for (String s : smallStrings){
			System.out.println(s + "-> " + ans.get(index));
			index++;
		}
	}

}

//------------
//my solution
import java.util.*;
class TrieNode{
	Map<Character,TrieNode> children = new HashMap<>();
}
class SuffixTrie{
	TrieNode root = new TrieNode();
	
	public SuffixTrie(String s){
		populateSuffixTrie(s);
	}
	
	public void populateSuffixTrie(String s){
		for (int i=0;i<s.length();i++){
			insertSubstringStartingAt(i,s);
		}
	}
	
	public void insertSubstringStartingAt(int idx, String s){
		TrieNode node = root;
		for (int i=idx;i<s.length();i++){
			char ch = s.charAt(i);
			node = node.children.computeIfAbsent(ch, k -> new TrieNode());
		}
	}
	
	public boolean contains(String s){
		TrieNode node = root;
		for (int i=0;i<s.length();i++){
			char ch = s.charAt(i);
			if (!node.children.containsKey(ch)) return false;
			node = node.children.get(ch);
		}
		return true;
	}
	
}
class Program {
  public static List<Boolean> multiStringSearch(String bigString, String[] smallStrings) {
    SuffixTrie suffixTrie = new SuffixTrie(bigString);
		List<Boolean> result = new ArrayList<>();
		for (String s : smallStrings){
			result.add(suffixTrie.contains(s));
		}
    return result;
  }
}

//------
import java.util.*;
class TrieNode{
	Map<Character,TrieNode> children = new HashMap<>();
	String word;
}
class Trie{
	
	TrieNode root = new TrieNode();
	
	public Trie(String[] smallStrings){
		for (String s : smallStrings){
			insert(s);
		}
	}
	
	public void insert(String s){
		TrieNode node = root;
		for (int i=0;i<s.length();i++){
			char ch = s.charAt(i);
			node = node.children.computeIfAbsent(ch, k -> new TrieNode());
		}
		node.word = s;
	}
	
	public void findSmallString(String s, int idx, Set<String> set){
		TrieNode node = root;
		for (int i=idx;i<s.length();i++){
			char ch = s.charAt(i);
			if (!node.children.containsKey(ch)) break;
			node = node.children.get(ch);
			if (node.word != null){
				set.add(node.word);
			}
		}
	}
}
class Program {
  public static List<Boolean> multiStringSearch(String bigString, String[] smallStrings) {
    Trie trie = new Trie(smallStrings);
		
		Set<String> containedStrings = new HashSet<>();
		
		for (int i=0;i<bigString.length();i++){
			trie.findSmallString(bigString,i,containedStrings);
		}
		
		List<Boolean> result = new ArrayList<>();
		for (String s : smallStrings){
			result.add(containedStrings.contains(s));
		}
    return result;
  }
}

