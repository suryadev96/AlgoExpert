/*
array = [1,0,0,-1,-1,0,1,1]
order = [0,1,-1]

[0,0,0,1,1,1,-1,-1]
*/
import java.util.*;

class Question{

	//2 passes
	public int[] threeNumberSort(int[] array, int[] order){
		int firstValue = order[0];
		int thirdValue = order[2];

		//before firstIdx, all values are order[0]
		int firstIdx = 0;
		for (int i=0;i<array.length;i++){
			if (array[i] == firstValue) {
				swap(firstIdx,i,array);
				firstIdx ++;
			}
		}

		//after thirdIdx, all values are order[2]
		int thirdIdx = array.length - 1;
		for (int i= array.length-1;i>=0;i--){
			if (array[i] == thirdValue) {
				swap(thirdIdx,i,array);
				thirdIdx--;
			}
		}
		return array;
	}


	//1 pass solution
	public int[] threeNumberSort(int[] array, int[] order){
		int firstValue = order[0];
		int secondValue = order[1];

		int firstIdx = 0;
		int secondIdx = 0;
		int thirdIdx = array.length-1;

		while (secondIdx <= thirdIdx) {
			int value = array[secondIdx];

			if (value == firstValue){
				swap(firstIdx, secondIdx, array);
				firstIdx++;
				secondIdx++; //this is because all values before secondIdx are either firstValue or secondValue 
							//because if thirdValue encountered , it must have been swapped to the last
			} else if (value == secondValue) {
				secondIdx++;
			} else {
				swap(secondIdx, thirdIdx, array);
				thirdIdx--;
			}
		}
		return array;
	}

	public void swap(int i, int j, int[] array){
		int temp = array[j];
		array[j] = array[i];
		array[i] = temp;
	}

}