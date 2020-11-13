/*
A String chain is defined as follows; let String A be a string in the initial array; if removing any single character from A yields 
a new string B thats contained in the initial array of strings , then string A and B form a string chain of length 2

sort the strings based on length in increasing order 

if u remove a character and it yieds a another string for which u have already computed the longest string chain.

ae abc abd ade abde abcde labde abcdef
|   |   |   |   |   |      |     |

1   1   1   2   3   4      4     5

Time complexity is O(N*M^2) M^2 because for each string of length M, u generate M strings . generating each string takes O(M) time
space complexity is O(NM)
*/
import java.util.*;

class StringChain{

	String nextString;
	Integer maxChainLength;

	public StringChain(String nextString, Integer maxChainLength){
		this.nextString = nextString;
		this.maxChainLength = maxChainLength;
	}
}

class Question{

	public static List<String> longestStringChain(List<String> strings){

		//insert all strings in hashmap for quick search
		Map<String,StringChain> stringChains = new HashMap<String,StringChain>();

		for (String string : strings){
			stringChains.put(string, new StringChain("",1));
		}

		List<String> sortedStrings = new ArrayList<String>(strings);

		sortedStrings.sort((a,b) -> a.length() - b.length());

		//find longest string chain for each string with this string as the head of the chain
		for (String string : sortedStrings){
			findLongestStringChain(string,stringChains);
		}

		return buildLongestStringChain(strings,stringChains);
	}

	public static void findLongestStringChain(String string, Map<String,StringChain> stringChains){
		//try removing every letter of the current string to see if the remaining strings form a string chain
		for (int i=0;i<string.length();i++){
			String smallerString = getSmallerString(string,i);
			if (!stringChains.containsKey(smallerString))continue;
			tryUpdateLongestStringChain(string,smallerString,stringChains);
		}
	}

	public static void tryUpdateLongestStringChain(String currentString, String smallerString, Map<String,StringChain> stringChains){
		int smallerStringChainLength = stringChains.get(smallerString).maxChainLength;
		int currentStringChainLength = stringChains.get(currentString).maxChainLength;

		if (smallerStringChainLength + 1 > currentStringChainLength){
			stringChains.get(currentString).maxChainLength = smallerStringChainLength + 1;
			stringChains.get(currentString).nextString = smallerString;
		}
	}

	public static List<String> buildLongestStringChain(List<String> strings, Map<String,StringChain> stringChains){
		//find the string that starts the longest string chain
		int maxChainLength = 0;
		String chainStartingString = "";
		for (String string : strings){
			if (stringChains.get(string).maxChainLength > maxChainLength){
				maxChainLength = stringChains.get(string).maxChainLength;
				chainStartingString = string;
			}
		}

		//starting at the string found above , build the longest string chain
		List<String> ourLongestStringChain = new ArrayList<String>();
		String currentString = chainStartingString;

		while (currentString != ""){
			ourLongestStringChain.add(currentString);
			currentString = stringChains.get(currentString).nextString;
		}

		return ourLongestStringChain;
	}

	public static String getSmallerString(String string, int index){
		return string.substring(0,index) + string.substring(index+1);
	}

	public static void main(String[] args){
		List<String> strings = new ArrayList<String>(Arrays.asList("abde","abc","abd","abcde","ade","ae","labde","abcdef"));
		List<String> longestStringChain = longestStringChain(strings);
		System.out.println(longestStringChain);
	}
}
/*
[abcdef, abcde, abde, ade, ae]
*/
//my solution
import java.util.*;
class StringChain{
	String nextString;
	int maxChainLength;
	public StringChain(String nextString, int maxChainLength){
		this.nextString = nextString;
		this.maxChainLength = maxChainLength;
	}
}
class Program {
  public static List<String> longestStringChain(List<String> strings) {
		//sort by length
        strings.sort((a,b)->a.length()-b.length());
		
		Map<String,StringChain> chainMap = new HashMap<>();
		for (String s : strings){
			chainMap.put(s, new StringChain("",1));
		}
		
		String maxChainString = strings.get(0);
		
		for (int i=1;i<strings.size();i++){
			String currentString = strings.get(i);
			findLongestStringChain(currentString,chainMap);
			if (chainMap.get(currentString).maxChainLength > chainMap.get(maxChainString).maxChainLength){
				maxChainString = currentString;
			}
		}
    return getLongestStringChain(chainMap,maxChainString);
  }
	
	public static List<String> getLongestStringChain(Map<String,StringChain> chainMap, String maxChainString){
		List<String> result = new ArrayList<>();
		String i = maxChainString;
		System.out.println(i);
		while (i != ""){
			result.add(i);
			i = chainMap.get(i).nextString;
			System.out.println(i);
		}
		if (result.size() == 1) return new ArrayList<>();
		return result;
	}
	
	public static void findLongestStringChain(String s,Map<String,StringChain> chainMap){
		for (int i=0;i<s.length();i++){
			String smallerString = s.substring(0,i) + s.substring(i+1);
			if (chainMap.containsKey(smallerString)){
				tryUpdateLongestStringChain(s,smallerString,chainMap);
			}
		}
	}
	
	public static void tryUpdateLongestStringChain(String s, String p, Map<String,StringChain> chainMap){
		int smallerChainLength = chainMap.get(p).maxChainLength;
		int currentChainLength = chainMap.get(s).maxChainLength;
		if (smallerChainLength + 1 > currentChainLength){
			chainMap.get(s).maxChainLength = smallerChainLength + 1;
			chainMap.get(s).nextString = p;
		}
	}
}

