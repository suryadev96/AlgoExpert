/*
Write a function that takes in a 2D array . return a list of elements that appear atleast once in each row and each column
of the matrix
*/

import java.util.*;

class Program {
    public static List<Integer> repeatedMatrixValues(ArrayList<ArrayList<Integer>> matrix) {
        int m = matrix.size();
        int n = matrix.get(0).size();

        Map<Integer, Set<Integer>> colMap = new HashMap<>();

        Map<Integer, Set<Integer>> rowMap = new HashMap<>();

        //first column
        for (int i = 0; i < m; i++) {
            int curr = matrix.get(i).get(0);
            rowMap.computeIfAbsent(curr, k -> new HashSet<>()).add(i + 1);
            colMap.computeIfAbsent(curr, k -> new HashSet<>()).add(0 + 1);
        }

        //first row
        for (int j = 0; j < n; j++) {
            int curr = matrix.get(0).get(j);
            colMap.computeIfAbsent(curr, k -> new HashSet<>()).add(j + 1);
            rowMap.computeIfAbsent(curr, k -> new HashSet<>()).add(0 + 1);
        }

        List<Integer> result = new ArrayList<>();

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                int curr = matrix.get(i).get(j);
                if (colMap.containsKey(curr) && rowMap.containsKey(curr)) {
                    colMap.get(curr).add(j + 1);
                    rowMap.get(curr).add(i + 1);
                }
            }
        }

        for (Map.Entry<Integer, Set<Integer>> entry : rowMap.entrySet()) {
            int num = entry.getKey();
            if (colMap.containsKey(num)) {
                int targetSum = m * (m + 1) / 2 + n * (n + 1) / 2;
                int actualSum = 0;
                for (Integer i : entry.getValue()) {
                    actualSum += i;
                }
                for (Integer i : colMap.get(num)) {
                    actualSum += i;
                }
                if (actualSum == targetSum) {
                    result.add(num);
                }
            }
        }

        return result;
    }
}
