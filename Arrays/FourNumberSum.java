/*
Write a Function that takes in a non-empty array of distinct integers and an integer representing a target sum.
The function should find all quadruplets in the array that sum up to the target sum and return a two dimensional array of 
all these quadruplets in no particular order.

If no four numbers sum upto a target sum, the function should return an empty array
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class FourNumberSum {

    public static List<Integer[]> fourNumberSum(int[] array, int targetSum) {

        Map<Integer, List<Integer[]>> allPairSums = new HashMap<Integer, List<Integer[]>>();
        List<Integer[]> quadruplets = new ArrayList<Integer[]>();

        for (int i = 1; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                int currentSum = array[i] + array[j];
                int difference = targetSum - currentSum;
                if (allPairSums.containsKey(difference)) {
                    for (Integer[] pair : allPairSums.get(difference)) {
                        Integer[] quadruplet = {pair[0], pair[1], array[i], array[j]};
                        quadruplets.add(quadruplet);
                    }
                }
            }
            for (int k = 0; k < i; k++) {
                int currentSum = array[k] + array[i];
                Integer[] pair = {array[k], array[i]};
                if (!allPairSums.containsKey(currentSum)) {
                    List<Integer[]> pairGroup = new ArrayList<Integer[]>();
                    pairGroup.add(pair);
                    allPairSums.put(currentSum, pairGroup);
                } else {
                    allPairSums.get(currentSum).add(pair);
                }
            }
        }
        return quadruplets;
    }

    //My solution
    public static List<Integer[]> fourNumberSumMySol(int[] array, int targetSum) {
        Map<Integer, List<Integer[]>> pairsMap = new HashMap<>();

        List<Integer[]> result = new ArrayList<>();

        for (int i = 1; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                int sum = array[i] + array[j];
                int complement = targetSum - sum;

                if (pairsMap.containsKey(complement)) {
                    for (Integer[] pair : pairsMap.get(complement)) {
                        result.add(new Integer[]{pair[0], pair[1], array[i], array[j]});
                    }
                }
            }

            for (int k = 0; k < i; k++) {
                int pairSum = array[k] + array[i];
                Integer[] pair = {array[k], array[i]};
                pairsMap.computeIfAbsent(pairSum, key -> new ArrayList<Integer[]>()).add(pair);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] array = {7, 6, 4, -1, 1, 2};
        int targetSum = 16;
        System.out.println(fourNumberSum(array, targetSum));
    }
}
