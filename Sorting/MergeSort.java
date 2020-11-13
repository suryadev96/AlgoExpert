import java.util.Arrays;
class MergeSort{

	public static int[] mergeSort(int[] array){
		if (array.length <= 1){
			return array;
		}
		int middleIndex = array.length / 2;
		int[] leftHalf = Arrays.copyOfRange(array, 0 , middleIndex);
		int[] rightHalf = Arrays.copyOfRange(array, middleIndex, array.length);
		return mergeSortedArrays(mergeSort(leftHalf), mergeSort(rightHalf));
	}

	public static int[] mergeSortedArrays(int[] leftHalf, int[] rightHalf){
		int[] sortedArray = new int[leftHalf.length + rightHalf.length];
		int i=0;
		int j=0;
		int k=0;

		while (i < leftHalf.length && j < rightHalf.length){
			if (leftHalf[i] <= rightHalf[i]){
				sortedArray[k++] = leftHalf[i++];
			} else{
				sortedArray[k++] = rightHalf[j++];
			}
		}
		while (i < leftHalf.length){
			sortedArray[k++] = leftHalf[i++];
		}
		while (i < rightHalf.length){
			sortedArray[k++] = rightHalf[j++];
		}
		return sortedArray;
	}

}

class MergeSort{

	public static int[] mergeSort(int[] array){
		if (array.length <= 1){
			return array;
		}
		int[] auxillaryArray = array.clone();
		mergeSort(array, 0, array.length - 1, auxillaryArray);
		return array;
	}

	public static void mergeSort(int[] mainArray, int startIdx, int endIdx, int[] auxillaryArray){
		if (startIdx == endIdx){
			return;
		}
		int middleIndex = (startIdx + endIdx) / 2;
		mergeSort(auxillaryArray, startIdx, middleIndex, mainArray);
		mergeSort(auxillaryArray, middleIndex + 1, endIdx, mainArray);
		doMerge(mainArray, startIdx, middleIndex, endIdx, auxillaryArray);
	}

	public static void doMerge(int[] mainArray, int startIdx, int middleIndex, int endIdx, int[] auxillaryArray){
		int k = startIdx;
		int i = startIdx;
		int j = middleIndex + 1;
		while (i <= middleIndex && j <= endIdx){
			if (auxillaryArray[i] <= auxillaryArray[j]){
				mainArray[k++] = auxillaryArray[i++];
			} else{
				mainArray[k++] = auxillaryArray[j++];
			}
		}
		while (i <= middleIndex){
			mainArray[k++] = auxillaryArray[i++];
		}
		while (j <= endIdx){
			mainArray[k++] = auxillaryArray[j++];
		}
	}
}

//my solution
import java.util.*;

class Program {
  public static int[] mergeSort(int[] array) {
    mergeSort(array,0,array.length-1);
  	return array;
  }
	
	public static void mergeSort(int[] array, int l, int r){
		if (l < r){
			int m = l + (r-l)/2;
			mergeSort(array,l,m);
			mergeSort(array,m+1,r);
			merge(array,l,m,r);
		}
	}
	
	public static void merge(int[] array, int l, int m, int r){
		int[] left = Arrays.copyOfRange(array,l,m+1);
		int[] right = Arrays.copyOfRange(array,m+1,r+1);
		
		int i=0;
		int j=0;
		int k=l;
		
		while (i < left.length && j < right.length){
			if (left[i] <= right[j]){
				array[k++] = left[i++];
			}else{
				array[k++] = right[j++];
			}
		}
		
		while (i < left.length){
			array[k++] = left[i++];
		}
		
		while (j < right.length){
			array[k++] = right[j++];
		}
	}
}
