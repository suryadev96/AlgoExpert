/*
insert each integer in the array from left to right in BST
take two array of integers and determine if these 2 arrays represent the same BST
you are not allowed to construct any BST in your code
*/

import java.util.*;

class Question{

	public static boolean isSameBSTs(List<Integer> arrayOne, List<Integer> arrayTwo){
		if (arrayOne.size() != arrayTwo.size()) return false;

		if (arrayOne.size() == 0 && arrayTwo.size() == 0)return true;

		//if root is not same
		if (arrayOne.get(0).intValue() != arrayTwo.get(0).intValue()) return false;

		List<Integer> leftOne = getSmaller(arrayOne);
		List<Integer> leftTwo = getSmaller(arrayTwo);

		List<Integer> rightOne = getBiggerOrEqual(arrayOne);
		List<Integer> rightTwo = getBiggerOrEqual(arrayTwo);

		return isSameBSTs(leftOne,leftTwo) && isSameBSTs(rightOne,rightTwo);
	}

	public static List<Integer> getSmaller(List<Integer> array){
		List<Integer> smaller = new ArrayList<Integer>();

		int root = array.get(0).intValue();

		for (int i=1;i<array.size();i++){
			int current = array.get(i).intValue();
			if (current < root)smaller.add(array.get(i));
		}
		return smaller;
	}

	public static List<Integer> getBiggerOrEqual(List<Integer> array){
		List<Integer> biggerOrEqual = new ArrayList<Integer>();

		int root = array.get(0).intValue();

		for (int i=1;i<array.size();i++){
			int current = array.get(i).intValue();
			if (current >= root)biggerOrEqual.add(array.get(i));
		}
		return biggerOrEqual;
	}

	public static boolean sameBSTsEff(List<Integer> arrayOne, List<Integer> arrayTwo){
		return areSameBSTs(arrayOne, arrayTwo, 0 , 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	//this says that if two bsts are same that are rooted at given indexes whose values lies in the range of minval,maxVal
	public static boolean areSameBSTs(List<Integer> arrayOne, List<Integer> arrayTwo, int rootIdxOne, int rootIdxTwo, int minVal, int maxVal){

		if (rootIdxOne == -1 || rootIdxTwo == -1) return rootIdxOne == rootIdxTwo;

		if (arrayOne.get(rootIdxOne).intValue() != arrayTwo.get(rootIdxTwo).intValue())return false;

		int leftRootIdxOne = getIdxOfFirstSmaller(arrayOne,rootIdxOne,minVal);
		int leftRootIdxTwo = getIdxOfFirstSmaller(arrayTwo,rootIdxTwo,minVal);

		int rightRootIdxOne = getIdxOfFirstBiggerOrEqual(arrayOne,rootIdxOne,maxVal);
		int rightRootIdxTwo = getIdxOfFirstBiggerOrEqual(arrayTwo,rootIdxTwo,maxVal);
		
		int currentValue = arrayOne.get(rootIdxOne);

		boolean leftAreSame = areSameBSTs(arrayOne,arrayTwo,leftRootIdxOne,leftRootIdxTwo,minVal,currentValue);
		boolean rightAreSame = areSameBSTs(arrayOne,arrayTwo,rightRootIdxOne,rightRootIdxTwo,currentValue,maxVal);

		return leftAreSame && rightAreSame;
	}	

	//6 8 2 4 10
	//first smaller Index for 6 is 2
	//next 
	//first smaller Index for 8 is 10 and not (2,4) because the range for the BST rooted at 8 is changed [8,maxVal]
	public static int getIdxOfFirstSmaller(List<Integer> array, int startingIdx, int minVal){
		int root = array.get(startingIdx).intValue();
		for (int i=startingIdx+1; i < array.size();i++){
			int current = array.get(i).intValue();
			if (current < root && current >= minVal){
				return i;
			}
		}
		return -1;
	}

	public static int getIdxOfFirstBiggerOrEqual(List<Integer> array, int startingIdx, int maxVal){
		int root = array.get(startingIdx).intValue();
		for (int i=startingIdx+1; i < array.size();i++){
			int current = array.get(i).intValue();
			if (current >= root && current < maxVal){
				return i;
			}
		}
		return -1;
	}


	public static void main(String[] args){
		List<Integer> arrayOne = new ArrayList<Integer>(Arrays.asList(10,15,8,12,94,81,5,2,11));
		List<Integer> arrayTwo = new ArrayList<Integer>(Arrays.asList(10,8,5,15,2,12,11,94,81));

		boolean isSameBST = isSameBSTs(arrayOne,arrayTwo);
		System.out.println(isSameBST);
	}

}

//mysolution
import java.util.*;

class Program {
  public static boolean sameBsts(List<Integer> arrayOne, List<Integer> arrayTwo) {
    int m = arrayOne.size();
		int n = arrayTwo.size();
		
		if (m != n) return false;
		
		if (m == 0 && n == 0) return true;
		
		if (arrayOne.get(0) != arrayTwo.get(0)) return false;
		
		List<Integer> leftOne = getSmaller(arrayOne);
		List<Integer> leftTwo = getSmaller(arrayTwo);
		
		List<Integer> rightOne = getLarger(arrayOne);
		List<Integer> rightTwo = getLarger(arrayTwo);
		
		return sameBsts(leftOne,leftTwo) && sameBsts(rightOne,rightTwo);
  }
	
	public static List<Integer> getSmaller(List<Integer> array){
		int root = array.get(0);
		List<Integer> left = new ArrayList<>();
		for (int i=1;i<array.size();i++){
			if (array.get(i) < root){
				left.add(array.get(i));
			}
		}
		return left;
	}
	
	public static List<Integer> getLarger(List<Integer> array){
		int root = array.get(0);
		List<Integer> right = new ArrayList<>();
		for (int i=1;i<array.size();i++){
			if (array.get(i) >= root){
				right.add(array.get(i));
			}
		}
		return right;
	}
	
}
//efficient solution
import java.util.*;

class Program {
  public static boolean sameBsts(List<Integer> arrayOne, List<Integer> arrayTwo) {
    return areSameBstsEff(arrayOne,arrayTwo,0,0,Integer.MIN_VALUE,Integer.MAX_VALUE);
  }
	
	public static boolean areSameBstsEff(List<Integer> arrayOne, List<Integer> arrayTwo, 
																			 int rootIdxOne, int rootIdxTwo, int minValue, int maxValue){
		if (rootIdxOne == -1 || rootIdxTwo == -1) return rootIdxOne == rootIdxTwo;
		
		if (arrayOne.get(rootIdxOne) != arrayTwo.get(rootIdxTwo)) return false;
		
		int currentValue = arrayOne.get(rootIdxOne);
		
		int leftIdxOne = getFirstIdxSmaller(arrayOne,rootIdxOne,minValue);
		int rightIdxOne = getFirstIdxGreaterEqual(arrayOne,rootIdxOne,maxValue);
		
		int leftIdxTwo = getFirstIdxSmaller(arrayTwo,rootIdxTwo,minValue);
		int rightIdxTwo = getFirstIdxGreaterEqual(arrayTwo,rootIdxTwo,maxValue);
		
		return areSameBstsEff(arrayOne,arrayTwo,leftIdxOne,leftIdxTwo,minValue,currentValue)
			&& areSameBstsEff(arrayOne,arrayTwo,rightIdxOne,rightIdxTwo,currentValue,maxValue);
	}
	
	public static int getFirstIdxSmaller(List<Integer> array, int rootIdx, int min){
		int rootValue = array.get(rootIdx);
		for (int i = rootIdx+1;i<array.size();i++){
			if (array.get(i) < rootValue && array.get(i) >= min){
				return i;
			}
		}
		return -1;
	}
	
	public static int getFirstIdxGreaterEqual(List<Integer> array, int rootIdx, int max){
		int rootValue = array.get(rootIdx);
		for (int i = rootIdx+1;i<array.size();i++){
			if (array.get(i) >= rootValue && array.get(i) <= max){
				return i;
			}
		}
		return -1;
	}
}

