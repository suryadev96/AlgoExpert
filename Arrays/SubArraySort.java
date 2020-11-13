/*
return an array of the starting and ending indices of the smallest subarray in the input array that needs to be sorted in place
in order for the entire input array to be sorted in ascending order

array = [1,2,4,7,10,11,7,12,6,7,16,18,19];
[3,9]
*/
import java.util.*;

class SubArraySort{

	public static int[] subarraySort(int[] array){

		int minOutOfOrder = Integer.MAX_VALUE;
		int maxOutOfOrder = Integer.MIN_VALUE;

		for (int i=0; i<array.length; i++){
			int num = array[i];

			if (isOutOfOrder(i,num,array)){
				minOutOfOrder = Math.min(minOutOfOrder, num);
				maxOutOfOrder = Math.max(maxOutOfOrder, num);
			}
		}

		if (minOutOfOrder == Integer.MAX_VALUE){
			return new int[]{-1,-1};
		}

		int subArrayLeftIdx = 0;
		while (minOutOfOrder >= array[subArrayLeftIdx]){
			subArrayLeftIdx++;
		}

		int subArrayRightIdx = 0;
		while (maxOutOfOrder <= array[subArrayRightIdx]){
			subArrayRightIdx--;
		}
		return new int[]{subArrayLeftIdx, subArrayRightIdx};
	}

	public static boolean isOutOfOrder(int i, int num, int[] array){
		if (i == 0){
			return num > array[i+1];
		}
		if (i == array.length-1){
			return num < array[i-1];
		}
		return num > array[i+1] || num < array[i-1];
	}

	public static void main(String[] args) {
		int[] array = {1,2,4,7,10,11,7,12,6,7,16,18,19};
		int[] order = subarraySort(array);
		System.out.println(order[0] + "-" + order[1]);
	}

}