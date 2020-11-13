/*
if you were to take input set X and add an element to it, how would the resulting powerset change
[] [1] [2] [12]
for all the subsets present in the powerset add 3
[3]
[13]
[23]
[123]
*/
import java.util.*;

class Question{

	public static List<List<Integer>> powerset(List<Integer> arr){
		List<List<Integer>> subsets = new ArrayList<List<Integer>>();

		//empty set
		subsets.add(new ArrayList<Integer>());

		for (Integer ele : arr){
			int length = subsets.size();
			for (int i=0;i<length;i++){
				List<Integer> currentSubset = new ArrayList<Integer>(subsets.get(i));
				currentSubset.add(ele);
				subsets.add(currentSubset);
			}
		}
		return subsets;
	}

	public static void main(String[] args){
		List<Integer> arr = Arrays.asList(1,2,3);
		List<List<Integer>> powerset = powerset(arr);
		for (List<Integer> subset : powerset){
			for (Integer i : subset){
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}
}
/*

1 
2 
1 2 
3 
1 3 
2 3 
1 2 3 
*/