class BubbleSort{

	//all the lighter elements bubble up the array
	public static int[] bubbleSort(int[] array){
		if (array.length == 0){
			return new int[]{};
		}
		boolean isSorted = false;
		int counter = 0;
		while (!isSorted){
			isSorted = true;
			//after first iteration, it makes sure that largest element is at the last index
			for (int i=0; i < array.length -1 -counter ; i++){
				if (array[i] > array[i+1]){
					swap(i,i+1,array);
					isSorted = false;
				}
			}
			counter++;
		}
		return array;
	}

	public static void swap(int i, int j, int[] array){
		int temp = array[j];
		array[j] = array[i];
		array[i] = temp;
	}

	public static void main(String[] args){
		int[] array = {5,4,3,2,1};
		int[] sorted = bubbleSort(array);
		for (int i=0;i<sorted.length;i++){
		    System.out.print(sorted[i] + " ");
		}
	}
}