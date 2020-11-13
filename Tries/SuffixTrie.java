/*
Write a SuffixTrie class for a suffix-Trie like data structure
searches for strings in the trie
Note that every string added to the trie should end with the special endSymbol character "*"
*/
import java.util.*;

class TrieNode{
	Map<Character,TrieNode> children = new HashMap<Character,TrieNode>();
}

class SuffixTrie{

	TrieNode root = new TrieNode();
	char endSymbol = '*';

	public SuffixTrie(String str){
		populateSuffixTrieFrom(str);
	}

	//O(n^2) time and O(n^2) space
	public void populateSuffixTrieFrom(String str){
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
		node.children.put(endSymbol,null);
	}

	//O(m) time searches if str is a suffix 
	public boolean contains(String str){
		TrieNode node = root;
		for (int i=0;i<str.length();i++){
			char letter = str.charAt(i);
			if (!node.children.containsKey(letter)){
				return false;
			}
			node = node.children.get(letter);
		}
		return node.children.containsKey(endSymbol) ? true : false;
	}

}

class Question{

	public static void main(String[] args){
		String str = "babc";
		SuffixTrie suffixTrie = new SuffixTrie(str);
		boolean isSuffix = suffixTrie.contains("abc");
		System.out.println(isSuffix);
	}
}

//my solution
import java.util.*;

class Program {
  // Do not edit the class below except for the
  // populateSuffixTrieFrom and contains methods.
  // Feel free to add new properties and methods
  // to the class.
  static class TrieNode {
    Map<Character, TrieNode> children = new HashMap<Character, TrieNode>();
  }

  static class SuffixTrie {
    TrieNode root = new TrieNode();
    char endSymbol = '*';

    public SuffixTrie(String str) {
      populateSuffixTrieFrom(str);
    }

    public void populateSuffixTrieFrom(String str) {
      	for (int i=0;i<str.length();i++){
			insertSubstringStartingAt(i,str);
		}
    }
		
	public void insertSubstringStartingAt(int idx, String s){
		TrieNode node = root;
		for (int i=idx;i<s.length();i++){
			char ch = s.charAt(i);
			node = node.children.computeIfAbsent(ch, k -> new TrieNode());
		}
		node.children.put(endSymbol,null);
	}

    public boolean contains(String str) {
      TrieNode node = root;
		for (int i=0;i<str.length();i++){
			char letter = str.charAt(i);
			if (!node.children.containsKey(letter)){
				return false;
			}
			node = node.children.get(letter);
		}
      return node.children.containsKey(endSymbol) ? true : false;
    }
  }
}
