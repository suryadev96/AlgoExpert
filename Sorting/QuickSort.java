class QuickSort{

	public static int[] quickSort(int[] array){
		quickSort(array, 0, array.length - 1);
	}

	public static void quickSort(int[] array, int startIdx, int endIdx){
		if (startIdx >= endIdx){
			return;
		}

		int pivotIdx = startIdx;
		//left always looks for the elements greater than pivot element
		int leftIdx = startIdx + 1;
		//right always looks for the elements smaller than pivot element
		int rightIdx = endIdx;

		while (rightIdx >= leftIdx){
			if (array[leftIdx] > array[pivotIdx] && array[rightIdx] < array[pivotIdx]){
				swap(leftIdx, rightIdx, array);
			}
			if (array[leftIdx] <= array[pivotIdx]){
				leftIdx++;
			}
			if (array[rightIdx] >= array[pivotIdx]){
				rightIdx--;
			}
		}
		swap(pivotIdx,rightIdx,array);

		boolean leftSubArrayIsSmaller = rightIdx - 1 - startIdx < endIdx - (rightIdx + 1);
		if (leftSubArrayIsSmaller){
			quickSort(array, startIdx, rightIdx - 1);
			quickSort(array, rightIdx + 1, endIdx);
		} else{
			quickSort(array, rightIdx + 1, endIdx);
			quickSort(array, startIdx, rightIdx - 1);
		}
	}

	public static void swap(int i, int j, int[] array){
		int temp = array[j];
		array[j] = array[i];
		array[i] = temp;
	}
}

//my solution
import java.util.*;

class Program {
  public static int[] quickSort(int[] array) {
    quickSort(array,0,array.length-1);
	return array;
  }
	
	public static void quickSort(int[] array, int low, int high){
		if (low < high){
			int pi = partition(array,low,high);
			quickSort(array,low,pi-1);
			quickSort(array,pi+1,high);
		}
	}
	
	public static int partition(int[] array, int startIdx, int endIdx){
		int pivotIdx = startIdx;
		int leftIdx = startIdx+1;
		int rightIdx = endIdx;
		
		//before left all elements are less than or equal to  pivot
		//after right all elements are greater than or equal to  pivot
		
		while (leftIdx <= rightIdx){
			if (array[leftIdx] > array[pivotIdx] && array[rightIdx] < array[pivotIdx]){
				swap(leftIdx,rightIdx,array);
			}
			if (array[leftIdx] <= array[pivotIdx]){
				leftIdx++;
			}
			if (array[rightIdx] >= array[pivotIdx]){
				rightIdx--;
			}
		}
		swap(pivotIdx,rightIdx,array);
		return rightIdx;
	}
	
	public static void swap(int i, int j, int[] array){
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}
