import java.util.*;

class Question{

	public static List<Integer> spiralTraversal(int[][] arr){

		List<Integer> result = new ArrayList<>();

		int startRow = 0;
		int endRow = arr.length-1;

		int startCol = 0;
		int endCol = arr[0].length-1;

		while(startRow <= endRow && startCol <= endCol){
			for (int col=startCol;col<=endCol;col++){
				result.add(arr[startRow][col]);
			}

			for (int row=startRow+1;row<=endRow;row++){
				result.add(arr[row][endCol]);
			}

			for (int col=endCol-1;col>=startCol;col--){
				if(startRow == endRow) break;
				result.add(arr[endRow][col]);
			}

			for (int row=endRow-1;row>startRow;row--){ //note row>startRow here
				if (startCol == endCol)break;
				result.add(arr[row][startCol]);
			}

			startRow++;
			endRow--;
			startCol++;
			endCol--;
		}
		return result;
	}

	public static List<Integer> spiralFill(int[][] arr){

		List<Integer> result = new ArrayList<>();

		int startRow = 0;
		int endRow = arr.length-1;

		int startCol = 0;
		int endCol = arr[0].length-1;

		spiralFillRecursive(arr,startRow,endRow,startCol,endCol,result);
		return result;
	}

	public static void spiralFillRecursive(int[][] arr, int startRow, int endRow, int startCol, int endCol, List<Integer> result){

		if (startRow > endRow || startCol > endCol)return;

		for (int col=startCol;col<=endCol;col++){
			result.add(arr[startRow][col]);
		}

		for (int row=startRow+1;row<=endRow;row++){
			result.add(arr[row][endCol]);
		}

		for (int col=endCol-1;col>=startCol;col--){
			if(startRow == endRow) break;
			result.add(arr[endRow][col]);
		}

		for (int row=endRow-1;row>startRow;row--){
			if (startCol == endCol)break;
			result.add(arr[row][startCol]);
		}

		spiralFillRecursive(arr,startRow+1,endRow-1,startCol+1,endCol-1,result);
	}



	public static void main(String[] args){
		int[][] arr = {
			{1,2,3,4},
			{12,13,14,5},
			{11,16,15,6},
			{10,9,8,7}
		};

		List<Integer> result = spiralTraversal(arr);
		printList(result);
		result = spiralFill(arr);
		printList(result);
	}

	public static void printList(List<Integer> result){
		for (Integer i : result){
			System.out.print(i + " ");
		}
		System.out.println();
	}

}