/*
return the greatest sum that can be generated from a strictly increasing subsequence in the array as well as array of the 
numbers in the subsequence
*/
import java.util.*;
class MaxSumIncreasingSubsequence{

	public static List<List<Integer>> maxSumIncreasingSubsequence(int[] array){

		//This sequence array stores the previous sequence element index of the max sum increasing subsequence ending with the current element index.
		//This basically works like left pointer to the previous element
		int[] sequences = new int[array.length];

		//Initially, all the individual elements forms the sequence. so there is no previous sequence element index
		Arrays.fill(sequences, Integer.MIN_VALUE);

		//Initially, all the individual elements forms the sequence. so sum will be the element itself
		int[] sums = array.clone();

		//The max sum increasing subsequence in the array ending with this index
		int maxSumIdx = 0;

		for (int i=0;i<array.length;i++){
			int currentNum = array[i];

			for (int j=0;j<i;j++){
				int otherNum = array[j];
				if (otherNum < currentNum && sums[j] + currentNum >= sums[i]){
					sums[i] = sums[j] + currentNum;
					sequences[i] = j;
				}
			}

			if (sums[i] >= sums[maxSumIdx]){
				maxSumIdx = i;
			}
		}
		return buildSequence(array, sequences, maxSumIdx, sums[maxSumIdx]);
	}

	public static List<List<Integer>>buildSequence(int[] array, int[] sequences, int currentIdx, int maxSum){
		List<List<Integer>> sequence = new ArrayList<List<Integer>>();
		sequence.add(new ArrayList<Integer>());
		sequence.add(new ArrayList<Integer>());
		sequence.get(0).add(maxSum);
		while (currentIdx != Integer.MIN_VALUE){
			sequence.get(1).add(0, array[currentIdx]);
			currentIdx = sequences[currentIdx];
		}
		return sequence;
	}

	public static void main(String[] args) {
		int[] array = {10,70,20,30,50,11,30};
		System.out.println(maxSumIncreasingSubsequence(array));
	}

}
//[[110], [10, 20, 30, 50]]

import java.util.*;

class Program {
  public static List<List<Integer>> maxSumIncreasingSubsequence(int[] array) {
    int n = array.length;
		int[] sequence = new int[n];
		Arrays.fill(sequence,Integer.MIN_VALUE);
		
		int[] sums = new int[n];
		
		for (int i=0;i<n;i++){
			sums[i] = array[i];
		}
		
		int maxSumIdx = 0;
		
		for (int i=1;i<n;i++){
			for (int j=0;j<i;j++){
				if (array[i] > array[j] && sums[j] + array[i] > sums[i]){
					sums[i] = sums[j] + array[i];
					sequence[i] = j;
				}
			}
			if (sums[i] > sums[maxSumIdx]){
				maxSumIdx = i;
			}
		}
		
		return constructSequence(array,sequence,maxSumIdx,sums[maxSumIdx]);
  }
	
	public static List<List<Integer>> constructSequence(int[] array, int[] sequence, int maxSumIdx, int sum){
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		result.add(Arrays.asList(sum));
		result.add(new ArrayList<Integer>());
		int i = maxSumIdx;
		while (i != Integer.MIN_VALUE){
			result.get(1).add(0,array[i]);
			i = sequence[i];
		}
		return result;
	}
}
