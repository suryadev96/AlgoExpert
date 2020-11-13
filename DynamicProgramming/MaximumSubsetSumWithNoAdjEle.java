/*
Write a function that takes in an array of positive integers and return the maximum sum of non-adjacent elements in the 
array
*/

class MaximumSubsetSumWithNoAdjEle {

    public static int maximumSubsetSumWithNoAdjEle(int[] array) {
        if (array.length() == 0) {
            return 0;
        } else if (array.length() == 1) {
            return array[0];
        }

        int[] maxSums = array.clone();
        maxSums[1] = Math.max(array[0], array[1]);
        for (int i = 2; i < array.length; i++) {
            maxSums[i] = Math.max(maxSums[i - 1], maxSums[i - 2] + array[i]);
        }
        return maxSums[array.length - 1];
    }

    public static int maxSubsetSumNoAdjacent(int[] array) {
        int n = array.length;
        if (n == 0) return 0;
        if (n == 1) return array[0];

        int exclude = array[0];
        int include = Math.max(array[0], array[1]);

        for (int i = 2; i < n; i++) {
            int current = Math.max(include, exclude + array[i]);
            exclude = include;
            include = current;
        }
        return include;
    }

    public static void main(String[] args) {
        int[] array = {75, 105, 120, 75, 90, 135};
        System.out.println(maximumSubsetSumWithNoAdjEleEff(array));
    }
}	