/*
An array a's balance index is an index i such that a[0] + a[1] + ... + a[i-1] = a[i+1] + a[i+2] + ..+a[lastIdx]
*/
//O(2n) solution + 3 passes
class Program {
  public static int balanceIndex(int[] array) {
    int n = array.length;
		
		int[] left = new int[n];
		
		for (int i=1;i<n;i++){
			left[i] = left[i-1] + array[i-1];
		}
		
		int[] right = new int[n];
		
		for (int i=n-2;i>=0;i--){
			right[i] = right[i+1] + array[i+1];
		}
		
		for (int i=0;i<n;i++){
			if (left[i] == right[i]){
				return i;
			}
		}
    return -1;
  }
}

//O(n) solution + 2passes
class Program {
  public static int balanceIndex(int[] array) {
    int n = array.length;
		
		int[] left = new int[n];
		
		for (int i=1;i<n;i++){
			left[i] = left[i-1] + array[i-1];
		}
		
		int right = 0;
		
		for (int i=n-1;i>=0;i--){
			if (left[i] == right) return i;
			right += array[i];
		}
		
    return -1;
  }
}

//O(1) solution + 2 passes
class Program{
 public static int balanceIndex(int[] array) {
    int n = array.length;
	
	int left = 0;
	int right = Arrays.stream(array).sum();

	for (int i=0;i<n;i++){
		right -= array[i];
		if (left == right) return i;
		left += array[i];
	}	

    return -1;
  }
