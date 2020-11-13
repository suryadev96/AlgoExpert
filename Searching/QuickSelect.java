/*
function that takes in array of distinct integers and integer k. return the k th smallest integer in the array
*/
import java.util.*;
class Question{

	public static int quickSelect(int[] array, int k){
		int position = k-1;
		return quickSelect(array,0,array.length-1,position);
	}

	//startIdx and endIdx represents the portion of the array that needs to be sorted
	public static int quickSelect(int[] array, int startIdx, int endIdx, int position){

		while (true){
			if (startIdx > endIdx){
				throw new RuntimeException("Your algorithm should never arrive here");
			}

			int pivotIdx = startIdx;
			//left pointer always looks for the elements greater than pivot
			int leftIdx = startIdx+1;
			//right pointer always looks for the elements lesser than pivot
			int rightIdx = endIdx;

			//when leftIdx > rightIdx => leftIdx always looks for elements greater than pivot but since rightIdx always looks for 
			//elements lesser than pivot . since leftIdx > rightIdx => all elements from leftIdx are greater than pivot
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
			if (rightIdx == position){
				return array[rightIdx];
			}else if (rightIdx < position){
				startIdx = rightIdx+1;
			}else{
				endIdx = rightIdx-1;
			}
		}
	}

	public static void swap(int i, int j, int[] array){
		int temp = array[j];
		array[j] = array[i];
		array[i] = temp;
	}

	//Time complexity is O(Nlogk)
	public static int findKthLargest(int[] nums, int k){

		//min heap smallest element first
		PriorityQueue<Integer> heap = new PriorityQueue<Integer>((n1,n2) -> n1 - n2);

		//keep k largest elements in the heap
		for (int n : nums){
			heap.add(n);
			if (heap.size() > k){
				heap.poll();
			}
		}
		return heap.poll();
	}

	public static void main(String[] args){
		int[] array = {8,5,2,9,7,6,3};
		int k = 3;
		int element = quickSelect(array,k);
		System.out.println(element);
	}

}

//my solution
import java.util.*;

class Program {
	
  public static int quickselect(int[] array, int k) {
    int position = k-1;
	return quickSelect(array,0,array.length-1,position);
  }
	
	public static int quickSelect(int[] array, int l, int r, int k){
		int pi = partition(array,l,r);
		if (pi == k){
			return array[pi];
		}else if (pi > k){
			return quickSelect(array,l,pi-1,k);
		}else{
			return quickSelect(array,pi+1,r,k);
		}
	}

	public static int quickSelectIter(int[] array, int l, int r, int k){
		while (l <= r){
			int pi = partition(array,l,r);
			if (pi == k) break;
			if (pi < k){
				l = pi+1;
			}else{
				r = pi-1;
			}
		}
		return array[pi];
	}

	
	public static int partition(int[] array, int startIdx, int endIdx){
		int pivotIdx = startIdx;
		int leftIdx = startIdx + 1;
		int rightIdx = endIdx;
		
		//before left, all elements are less than or equal to pivot
		//after right, all elements are greater than or equal to pivot
		
		while (leftIdx <= rightIdx){
			if (array[leftIdx] > array[pivotIdx] && array[rightIdx] < array[pivotIdx]){
				swap(array,leftIdx,rightIdx);
			}
			if (array[leftIdx] <= array[pivotIdx]){
				leftIdx++;
			}
			if (array[rightIdx] >= array[pivotIdx]){
				rightIdx--;
			}
		}
		swap(array,pivotIdx,rightIdx);
		return rightIdx;
	}
	
	public static void swap(int[] array, int i, int j){
		int temp = array[j];
		array[j] = array[i];
		array[i] = temp;
	}
}
