/*
array = [5,1,4,2]
[8,40,10,20]
*/
import java.util.*;

class Question{

	//3 passes solution
	public int[] arrayOfProducts(int[] array){
		int n = array.length;
		int[] product = new int[n];

		int[] L = new int[n];
		int[] R = new int[n];

		int leftProduct = 1;
		for (int i=0;i<n;i++){
			L[i] = leftProduct;
			leftProduct *= array[i];
		}

		int rightProduct = 1;
		for (int i=n-1;i>=0;i--){
			R[i] = rightProduct;
			rightProduct *= array[i];
		}

		for (int i=0;i<n;i++){
			product[i] = L[i] * R[i];
		}
		return product;
	}

	//2 passes solution
	public int[] arrayOfProducts(int[] array) {
    	int n = array.length;
		int[] product = new int[n];

		int leftProduct = 1;
		for (int i=0;i<n;i++){
			product[i] = leftProduct;
			leftProduct *= array[i];
		}

		int rightProduct = 1;
		for (int i=n-1;i>=0;i--){
			product[i] *= rightProduct;
			rightProduct *= array[i];
		}

		return product;
  	}

}