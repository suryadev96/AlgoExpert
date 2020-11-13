class SelectionSort{

	public static int[] selectionSort(int[] array){
		if (array.length == 0){
			return new int[]{};
		}

		int startIdx = 0;
		while (startIdx < array.length - 1){
			int smallestIdx = startIdx;
			for(int i = startIdx+1; i < array.length;i++){
				if (array[smallestIdx] > array[i]){
					smallestIdx = i;
				}
			}
			swap(startIdx, smallestIdx, array);
			startIdx++;
		}
		return array;
	}
}

import java.util.*;

class Program {
  public static int[] selectionSort(int[] array) {
    int n = array.length;
		
		int i=0;
		while (i < n-1){
			int smallestIdx = i;
			for (int j=i+1;j<n;j++){
				if (array[smallestIdx] > array[j]){
					smallestIdx = j;
				}
			}
			swap(i,smallestIdx,array);
			i++;
		}
    return array;
  }
	
	public static void swap(int i, int j, int[] array){
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}
