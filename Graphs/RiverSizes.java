/*
0 represents island, 1 represents river . A river consits of any number of 1 s either horizontally or vertically adjacent.
number of adjacent 1 forming a river . determine its size
*/
/*
Given N x M character matrix A of O's and X's, where O = white, X = black.
Return the number of black shapes. A black shape consists of one or more adjacent X's (diagonals not included)
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Question {

    static int riverSize = 0;

    public static ArrayList<Integer> countIslands(List<String> islands) {
        if (islands == null || islands.size() == 0) return null;

        int m = islands.size();
        int n = islands.get(0).length();

        char[][] board = new char[m][n];

        for (int i = 0; i < m; i++) {
            String s = islands.get(i);
            for (int j = 0; j < n; j++) {
                char c = s.charAt(j);
                board[i][j] = c;
            }
        }
        return helper(board);
    }

    public static ArrayList<Integer> helper(char[][] board) {
        int m = board.length;
        int n = board[0].length;

        int count = 0;

        ArrayList<Integer> riverSizes = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'X') {
                    dfs(board, i, j, m, n);
                    riverSizes.add(riverSize);
                    riverSize = 0;
                }
            }
        }
        return riverSizes;
    }

    //visits all the cells which are 'X' from (i,j)
    public static void dfs(char[][] board, int i, int j, int m, int n) {
        if (i < 0 || j < 0 || i == m || j == n || board[i][j] == 'O') return;

        //makes the current cell as visited
        riverSize++;
        board[i][j] = 'O';

        dfs(board, i + 1, j, m, n);
        dfs(board, i - 1, j, m, n);
        dfs(board, i, j + 1, m, n);
        dfs(board, i, j - 1, m, n);
    }

    public static void main(String[] args) {
        List<String> islands = Arrays.asList("XOOXO", "XOXOO", "OOXOX", "XOXOX", "XOXXO");
        ArrayList<Integer> riverSizes = countIslands(islands);
        for (Integer i : riverSizes) {
            System.out.print(i + " ");
        }
    }
}
/*
2 1 5 2 2 
*/

//my solution:
import java.util.*;

class Program {

    static int[][] dirs = {{-1, 0}, {0, 1}, {0, -1}, {1, 0}};

    public static List<Integer> riverSizes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    int count = dfs(matrix, i, j, m, n);
                    result.add(count);
                }
            }
        }
        return result;
    }

    public static int dfs(int[][] matrix, int i, int j, int m, int n) {
        int count = 1;
        matrix[i][j] = 0; //visited
        for (int[] d : dirs) {
            int x = i + d[0];
            int y = j + d[1];
            if (isSafe(x, y, m, n) && matrix[x][y] == 1) {
                count += dfs(matrix, x, y, m, n);
            }
        }
        return count;
    }

    private static boolean isSafe(int x, int y, int m, int n) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }
}
