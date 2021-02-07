/*
Given array of integers between [1-n] . n is length of array.
Write a function that returns the first integer that appears more than once.
return the first duplicate value that has minimum index

array = [2,1,5,2,3,3,4]

2 
//2 is the first integer that appers more than once
//3 also appears more than once, but the second 3 appears after the second 2
*/
import java.util.*;
class Question{

	public int firstDuplicateValue(int[] array){
		Set<Integer> seen = new HashSet<>();
		for (int num : array) {
			if (seen.contains(num)) return num;
			seen.add(num);
		}
		return -1;
	}

	public int firstDuplicateValueNoSpace(int[] array){
		for (int num : array){
			int absValue = Math.abs(num); //mark the index whose value is current num; this marks the num presence without using space
			if (array[absValue - 1] < 0) return absValue; //negative num indicates absValue is seen before bcoz its index is marked 
			array[absValue - 1] *= -1;
		}
		return -1;
	}

}