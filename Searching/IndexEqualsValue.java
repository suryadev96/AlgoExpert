/*
Write a function that takes in a sorted array of distinct integers and return the first index in the array that is equal to 
the value at the index. if there is no such index , return -1


approach:
if the value at an index is smaller than index (idx=50) (val=30) => that means there are 51 distinct numbers but there are only 
30 distinct positive numbers => that means values to the left will be smaller than corresponding indices; this is true
since left indices naturally decrement by 1 each and left values will decrement atleast by 1 each due to the array being sorted 
and distinct. Same logic applies to the right side of the array
*/
class Question{

	public static int indexEqualsValue(int[] array){

		//before l, all values are less than the indices
		int l = 0;
		//after r, all values are greater than or equal to the indices
		int r = array.length -1;

		while (l <= r){
			int mid = l + (r-l)/2;

			if (array[mid] < mid){
				l = mid+1;
			} else{
				r = mid-1;
			}
		}	
		if (l < array.length && array[l] == l){
			return l;
		}
		return -1;
	}

	public static void main(String[] args) {
		int[] array = {-5,-3,0,3,4,5,9};
		System.out.println(indexEqualsValue(array));
	}

}