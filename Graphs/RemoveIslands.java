/*
2D array containing 0s and 1s.
1 - black; 0 - white

Island is defined as 1's horizontally or vertically adjacent and none of those 1s are in firstrow, firstcolumn, lastcolumn of the matrix

Islands as patches of black that dont touch the border of the two-toned image

Write a function to return the matrix with all of the islands removed.

matrix = [
	[1,0,0,0,0,0],
	[0,1,0,1,1,1],
	[0,0,1,0,1,0],
	[1,1,0,0,1,0],
	[1,0,1,1,0,0],
	[1,0,0,0,0,1]
]


[
	[1,0,0,0,0,0],
	[0,0,0,1,1,1],
	[0,0,0,0,1,0],
	[1,1,0,0,1,0],
	[1,0,0,0,0,0],
	[1,0,0,0,0,1]
]
*/
import java.util.*;

class Question{

	static int[][] dir = {{-1,0},{0,-1},{1,0},{0,1}};

	public int[][] removeIslands(int[][] matrix){

		int m = matrix.length;
		int n = matrix[0].length;

		for (int i=0;i<m;i++){
			for (int j=0;j<n;j++){
				boolean rowBorder = i == 0 || i == m - 1;
				boolean colBorder = j == 0 || j == n - 1;
				boolean isBorder = rowBorder || colBorder;

				if (!isBorder) continue;

				if (matrix[i][j] != 1) continue;

				dfsAndMark2s(matrix,i,j);
			}
		}

		for (int i=0;i<m;i++){
			for (int j=0;j<n;j++){
				if (matrix[i][j] == 1){
					matrix[i][j] = 0;
				}else if (matrix[i][j] == 2){
					matrix[i][j] = 1;
				}
			}
		}
		return matrix;
	}

	public void dfsAndMark2s(int[][] matrix, int i, int j){

		matrix[i][j] = 2;

		for (int[] d : dir){
			int x = d[0] + i;
			int y = d[1] + j;

			if (isSafe(matrix,x,y) && matrix[x][y] == 1){
				dfsAndMark2s(matrix,x,y);
			}
		}
	}

	public boolean isSafe(int[][] matrix, int x, int y){
		return x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length;
	}

}