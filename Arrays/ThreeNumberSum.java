/*
non-empty array of distinct integers . find all triplets in the array that sum upto the target sum .
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ThreeNumberSum {

    public static List<Integer[]> threeNumberSum(int[] array, int targetSum) {
        Arrays.sort(array);
        List<Integer[]> triplets = new ArrayList<Integer[]>();

        for (int i = 0; i < array.length - 2; i++) {

            int left = i + 1;
            int right = array.length - 1;

            while (left < right) {
                int currentSum = array[i] + array[left] + array[right];
                if (currentSum == targetSum) {
                    Integer[] triplet = {array[i], array[left], array[right]};
                    triplets.add(triplet);
                    left++;
                    right--;
                } else if (currentSum < targetSum) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return triplets;
    }

    public static void main(String[] args) {
        int[] array = {12, 3, 1, 2, -6, 5, -8, 6};
        int targetSum = 0;
        System.out.println(threeNumberSum(array, targetSum));
    }

}
/*

 */