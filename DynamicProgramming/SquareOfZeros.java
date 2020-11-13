/*
Find the largest square sub-matrix which is surrounded by all 1's

[1,1,1,0,1,0]
[0,0,0,0,0,1]
[0,1,1,1,0,1]
[0,0,0,1,0,1]
[0,1,1,1,0,1]
[0,0,0,0,0,1]
*/
import java.util.*;
class Question{

	public static int findLargestSquareSubMatrix(int[][] matrix){
		int m = matrix.length;
		int n = matrix[0].length;

		int[][] X = new int[m][n]; //horizontal
		int[][] Y = new int[m][n]; //vertical 

		for (int i=0;i<m;i++){
			for (int j=0;j<n;j++){
				if (matrix[i][j] != 0){
					if (i == 0){
						Y[i][j] = 1;
					}else{
						Y[i][j] = Y[i-1][j] + 1;
					}

					if (j == 0){
						X[i][j] = 1;
					}else{
						X[i][j] = X[i][j-1] + 1;
					}
				}
			}
		}

		int maxSqLen = 0;

		for (int i=m-1;i>=1;i--){
			for (int j=n-1;j>=1;j--){

				int len = Math.min(X[i][j], Y[i][j]);

				while (len > 0){

					boolean isSquare = Y[i][j-len+1] >= len && X[i-len+1][j] >= len;

					if (isSquare && maxSqLen < len){
						maxSqLen = len;
						break;
					}

					len--; //reduce the length by 1 and check for smaller squares ending at current cell
				}

			}
		}
		return maxSqLen;
	}

	  public static boolean squareOfZeroes(List<List<Integer>> matrix) {
    	int[][] mat = convertToIntegerMatrix(matrix);
		int n = mat.length;
		
		int[][] h = new int[n][n];
		for (int i=0;i<n;i++){
			for (int j=0;j<n;j++){
				if (j == 0){
					h[i][j] = mat[i][j] == 0 ? 1 : 0;
				}else{
					h[i][j] = mat[i][j] == 0 ? h[i][j-1] + 1 : 0;
				}
			}
		}
		
		int[][] v = new int[n][n];
		for (int i=0;i<n;i++){
			for (int j=0;j<n;j++){
				if (i == 0){
					v[i][j] = mat[i][j] == 0 ? 1 : 0;
				}else{
					v[i][j] = mat[i][j] == 0 ? v[i-1][j] + 1 : 0;
				}
			}
		}
		
		for (int i=n-1;i>=0;i--){
			for (int j=n-1;j>=0;j--){
				int possibleSqLen = Math.min(v[i][j],h[i][j]);
				for (int sz=2;sz<=possibleSqLen;sz++){
					if (v[i][j-sz+1] >= sz && h[i-sz+1][j] >= sz) return true;
				}
			}
		}
    return false;
  	}
	
	public static int[][] convertToIntegerMatrix(List<List<Integer>> matrix){
		int n = matrix.size();
		int[][] mat = new int[n][n];
		for (int i=0;i<n;i++){
			for (int j=0;j<n;j++){
				mat[i][j] = matrix.get(i).get(j);
			}
		}
		return mat;
	}

	public static void main(String[] args) {
		int[][] matrix = {
			{ 1, 1, 1, 1, 1, 1 },
			{ 1, 0, 1, 1, 0, 1 },
			{ 0, 1, 1, 0, 0, 1 },
			{ 1, 1, 1, 1, 1, 1 },
			{ 1, 0, 0, 1, 0, 1 },
			{ 1, 0, 1, 1, 0, 0 },
			{ 1, 0, 1, 0, 1, 1 },
			{ 1, 1, 1, 0, 1, 1 }
		};
		System.out.println(findLargestSquareSubMatrix(matrix));
	}

}