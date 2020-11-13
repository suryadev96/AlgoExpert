/*
Write a function that takes in an array of integers and returns an array of length 2 representing the largest range of integers

The first number in the output array should be the first number in the range
while the second number should be the last number in the range

A range of numbers is defined as a set of numbers that comes right after each other in the set of real integers.

output array [2,6] represents {2,3,4,5,6} which is a range of length 5 . note numbers need not be sorted or adjacent in the input array
*/

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class LargestRange {

    public static int[] largestRange(int[] array) {
        int[] bestRange = new int[2];

        int longestLength = 0;

        Map<Integer, Boolean> nums = new HashMap<Integer, Boolean>();
        for (int num : array) {
            nums.put(num, true);
        }

        for (int num : array) {

            //num is already visited
            if (!nums.get(num)) {
                continue;
            }

            //num is visited
            nums.put(num, false);

            int currentLength = 1;
            int left = num - 1;
            int right = num + 1;


            while (nums.containsKey(left)) {
                nums.put(left, false);
                currentLength++;
                left--;
            }

            while (nums.containsKey(right)) {
                nums.put(right, false);
                currentLength++;
                right++;
            }

            if (currentLength > longestLength) {
                longestLength = currentLength;
                bestRange = new int[]{left + 1, right - 1};
            }
        }
        return bestRange;
    }

    //My solution
    public static int[] largestRangeMySol(int[] array) {
        int[] longestRange = new int[2];
        int longestLength = 0;

        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < array.length; i++) {
            set.add(array[i]);
        }

        for (int i = 0; i < array.length; i++) {

            int num = array[i];

            if (set.contains(num - 1)) {
                continue;
            }

            int len = 1;
            int start = num;
            int end = num + 1;
            while (set.contains(end)) {
                len++;
                end++;
            }

            if (len > longestLength) {
                longestLength = len;
                longestRange = new int[]{start, end - 1};
            }
        }
        return longestRange;
    }

    public static void main(String[] args) {
        int[] nums = {1, 11, 3, 0, 15, 5, 2, 4, 10, 7, 12, 6};
        int[] range = largestRange(nums);
        System.out.println(range[0] + "-" + range[1]);
    }
}
