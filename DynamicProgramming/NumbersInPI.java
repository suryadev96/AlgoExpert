/*
Given a string representation of first n digits of pi and a list of postive integers (all in string format),
write a function that returns the smallest number of spaces that can be added to n digits of pi such that all resulting numbers are found 
in the list of integers.
*/
import java.util.*;

class Question{

	//time complexity is O(N^3)
	//u can divide the string to n prefixes
	//for each prefix , u again divide suffix into n prefixes
	//each prefix slicing again takes n time because of immutable strings=> O(N^3)
	public static int numbersInPI(String pi, String[] numbers){
		Set<String> numbersTable = new HashSet<String>();

		for (String number : numbers){
			numbersTable.add(number);
		}

		//cache stores the minimum no of spaces needed for the substring starting at index
		Map<Integer,Integer> cache = new HashMap<Integer,Integer>();

		//int minSpaces = getMinSpaces(pi,numbersTable,cache,0);

		for (int i=pi.length()-1; i>=0;i--){
			getMinSpaces(pi,numbersTable,cache,i);
		}

		return cache.get(0) == Integer.MAX_VALUE ? -1 : cache.get(0);
		//return minSpaces == Integer.MAX_VALUE ? -1 : minSpaces;
	}

	//build the cache from bottom to top 
	//31452 => 2 52 452 1452 31452
	public static int getMinSpaces(String pi, Set<String> numbersTable, Map<Integer,Integer> cache , int idx){

		//that means we had put a space at the end of the string 
		//314|2| => remove the lastIdx
		if (idx == pi.length())return -1;

		if (cache.containsKey(idx)) return cache.get(idx);

		int minSpaces = Integer.MAX_VALUE;

		//Try all the prefixes
		for (int i=idx; i < pi.length();i++){
			String prefix = pi.substring(idx,i+1);

			if (numbersTable.contains(prefix)){
				int minSpacesInSuffix = getMinSpaces(pi,numbersTable,cache,i+1);
				//handle int overflow
				if (minSpacesInSuffix == Integer.MAX_VALUE){
					minSpaces = Math.min(minSpaces, minSpacesInSuffix);
				}else{
					minSpaces = Math.min(minSpaces , minSpacesInSuffix + 1); //minSpaces for other prefixes
				}
			}
		}
		cache.put(idx,minSpaces);
		return cache.get(idx);
	}

	//bottom up solution
	public static int numbersInPiMySolution(String pi, String[] numbers) {
		int n = pi.length();
		int[] cuts = new int[n+1];
		cuts[0] = -1;
		Set<String> numbersSet = new HashSet<>(Arrays.asList(numbers));
		
		for (int i=1;i<=n;i++){
			cuts[i] = Integer.MAX_VALUE;
			for (int j=0;j<i;j++){
				String number = pi.substring(j,i);
				if (numbersSet.contains(number) && cuts[j] != Integer.MAX_VALUE && cuts[j] + 1 < cuts[i]){
					cuts[i] = cuts[j] + 1;
				}
			}
		}
		return cuts[n] == Integer.MAX_VALUE ? -1 : cuts[n];
  }

	public static void main(String[] args){
		String pi = "314592653589793238462643383279";
		String[] numbers = {"31459265358979323846", "26433", "8" , "3279", "31459265", "35897932384626433832", "79"};

		int minSpaces = numbersInPI(pi,numbers);
		System.out.println(minSpaces);
	}
}
/*
2
*/