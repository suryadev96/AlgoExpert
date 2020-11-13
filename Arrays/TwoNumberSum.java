/*
non-empty array of distinct integers and an integer representing target sum. 
*/

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class TwoNumberSum {

    public static int[] twoNumberSum(int[] array, int targetSum) {
        Set<Integer> nums = new HashSet<>();
        for (int num : array) {
            int complement = targetSum - num;
            if (nums.contains(complement)) {
                return new int[]{complement, num};
            } else {
                nums.add(num);
            }
        }
        return new int[0];
    }

    public static int[] twoNumberSumEff(int[] array, int targetSum) {
        Arrays.sort(array);
        int left = 0;
        int right = array.length - 1;

        while (left < right) {
            int currentSum = array[left] + array[right];

            if (currentSum == targetSum) {
                return new int[]{array[left], array[right]};
            } else if (currentSum < targetSum) {
                left++;
            } else if (currentSum > targetSum) {
                right--;
            }
        }
        return new int[0];
    }

    public static void main(String[] args) {
        int[] array = {3, 5, -4, 8, 11, 1, -1, 6};
        int targetSum = 10;
        int[] result = twoNumberSum(array, targetSum);
    }

}
