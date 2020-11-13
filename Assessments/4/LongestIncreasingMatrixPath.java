import java.util.ArrayList;

class Program {

    static int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public static int longestIncreasingMatrixPath(ArrayList<ArrayList<Integer>> matrix) {

        int m = matrix.size();
        int n = matrix.get(0).size();
        int[][] mat = convertMatrix(matrix, m, n);

        int[][] dp = new int[m][n];

        int maxLen = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                maxLen = Math.max(maxLen, dfs(mat, dp, i, j));
            }
        }
        return maxLen;
    }

    public static int dfs(int[][] mat, int[][] dp, int i, int j) {
        if (dp[i][j] != 0) return dp[i][j];

        //every number itself forms longest increasing path
        int maxLen = 1;
        for (int[] d : dirs) {
            int x = i + d[0];
            int y = j + d[1];

            if (isSafe(x, y, mat, i, j)) {
                maxLen = Math.max(maxLen, dfs(mat, dp, x, y) + 1);
            }
        }
        dp[i][j] = maxLen;
        return dp[i][j];
    }

    private static boolean isSafe(int x, int y, int[][] mat, int i, int j) {
        int m = mat.length;
        int n = mat[0].length;
        return x >= 0 && x < m && y >= 0 && y < n && mat[x][y] > mat[i][j];
    }

    public static int[][] convertMatrix(ArrayList<ArrayList<Integer>> matrix, int m, int n) {
        int[][] mat = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j] = matrix.get(i).get(j);
            }
        }
        return mat;
    }
}
