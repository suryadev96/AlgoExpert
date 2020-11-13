class Question{

	public static int shiftedBinarySearch(int[] array, int target){
		return shiftedBinarySearchIter(array,target,0,array.length-1);
	}

	public static int shiftedBinarySearchIter(int[] array, int target, int left, int right){
		while (left <= right){
			int middle = (left + right)/2;
			int potentialMatch = array[middle];
			int leftNum = array[left];
			int rightNum = array[right];

			if (target == potentialMatch){
				return middle;
			}

			//if leftNum and middleNum are in the ascending portions of the array 
			if (potentialMatch >= leftNum){

				//if target is in between leftNum and middleNum
				if (target < potentialMatch && target >= leftNum){
					right = middle -1;
				}else{ //if it is not between leftNum and middleNum
					left = middle+1;
				}
			}else{
				//456123
				//456 -> 1 ascending portion
				//123 -> 2 ascending portion
				//if leftNum and middleNum are in different ascending portions of the array
				//It implies middleNum and RightNum are in the same ascending portion of the array
				if (potentialMatch < target && target <= rightNum){
					left = middle +1;
				}else{
					right = middle-1;
				}
			}
		}
		return -1;
	}

	//number can be found between left and right inclusive
	public static int shiftedBinarySearch(int[] array, int target, int left, int right){
		if (left > right)return -1;

		int middle = (left + right)/2;

		int potentialMatch = array[middle]; //mid
		int leftNum = array[left];
		int rightNum = array[right];

		if (target == potentialMatch){
			return middle;
		}else if (potentialMatch >= leftNum){
			//if target is in between [leftNum,potentialMatch]
			if (potentialMatch > target && target >= leftNum){
				return shiftedBinarySearch(array,target,left,middle-1);
			}else{
				return shiftedBinarySearch(array,target,middle+1,right);
			}
		}else{
			//if target is in between [potentialMatch,rightNum]
			if (target > potentialMatch && target <= rightNum){
				return shiftedBinarySearch(array,target,middle+1,right);
			}else{
				return shiftedBinarySearch(array,target,left,middle-1);
			}
		}
	}

	public static void main(String[] args){
		int[] array = {45,61,71,72,73,0,1,21,33,45};
		int target = 33;
		int index = shiftedBinarySearch(array,target);
		System.out.print(index);
	}
}

//my solution:
import java.util.*;

class Program {
  public static int shiftedBinarySearch(int[] array, int target) {
    int left = 0;
		int right = array.length-1;
		
		while (left <= right){
			
			int mid = left + (right - left)/2;
			
			if (array[mid] == target) return mid;
			
			if (array[mid] >= array[left]){
				
				if (target >= array[left] && target < array[mid]){
					right = mid-1;
				}else{
					left = mid+1;
				}
			}else{
				if (target > array[mid] && target < array[right]){
					left = mid+1;
				}else{
					right = mid-1;
				}
			}
		}
    return -1;
  }
}
