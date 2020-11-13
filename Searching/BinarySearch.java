class Question{

	public static int binarySearch(int[] array, int target){
		return binarySearchIter(array,target,0,array.length-1);
	}

	//value always lies between left and right inclusive => max l=r
	public static int binarySearch(int[] array, int target, int left, int right){
		if (left > right)return -1;
		int middle = (left + right)/2;
		int potentialMatch = array[middle];

		if (target == potentialMatch){
			return middle;
		}else if (target < potentialMatch){
			return binarySearch(array,target,left,middle-1);
		}else{
			return binarySearch(array,target,middle+1,right);
		}
	}

	public static int binarySearchIter(int[] array, int target, int left, int right){
		while (left <= right){
			int middle = (left + right)/2;
			int potentialMatch = array[middle];
			if (target == potentialMatch){
				return middle;
			}else if (target < potentialMatch){
				right = middle -1;
			}else{
				left = middle+1;	
			}
		}
		return -1;
	}

	public static void main(String[] args){
		int[] array = {0,1,21,33,45,45,61,71,72,73};
		int target = 33;
		int num = binarySearch(array,target);
		System.out.println(num);
	}

}