/*
function that takes in a unsorted array of integers, return the sorted array of three largest integers in the input array
*/
class Question{

	//time complexity = O(n) because of finite computations
	public static int[] findThreeLargestNumbers(int[] array){
		int[] threeLargest = {Integer.MIN_VALUE,Integer.MIN_VALUE,Integer.MIN_VALUE};

		for (int num : array){
			updateLargest(threeLargest,num);
		}
		return threeLargest;
	}

	public static void updateLargest(int[] threeLargest, int num){
		if (num > threeLargest[2]){
			shiftAndUpdate(threeLargest,num,2);
		}else if (num > threeLargest[1]){
			shiftAndUpdate(threeLargest,num,1);
		}else if (num > threeLargest[0]){
			shiftAndUpdate(threeLargest,num,0);
		}
	}

	//here we are shifting left the numbers before idx to make space for the current num which occupies idx
	public static void shiftAndUpdate(int[] array, int num , int idx){
		for(int i=0;i<=idx;i++){
			if (i == idx){
				array[i] = num;
			}else{
				array[i] = array[i+1];
			}
		}
	}

	public static void main(String[] args){
		int[] array = {141,1,17,-7,-17,-27,18,541,8,7,7};
		int[] largestNumbers = findThreeLargestNumbers(array);
		for (Integer i : largestNumbers){
			System.out.print(i + " ");
		}
	}

}