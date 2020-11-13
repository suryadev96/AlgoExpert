/*
Write a function that takes in two non-empty arrays of integers , find the pair of the numbers whose absolute difference
is closest to zero and return an array containing these 2 integers with the number from the first array in the first position

You can assume that there will only be one pair of numbers with the smallest difference

arrayOne = [-1,5,10,20,28,3]
arrayTwo = [26,134,135,15,17]
[28,26]
*/

import java.util.Arrays;

class SmallestDifference {

    //my solution
    public static int[] smallestDifferenceMySolution(int[] arrayOne, int[] arrayTwo) {
        Arrays.sort(arrayOne);
        Arrays.sort(arrayTwo);
        int i = 0;
        int j = 0;

        int m = arrayOne.length;
        int n = arrayTwo.length;

        int closestDiff = Integer.MAX_VALUE;
        int[] pair = new int[2];

        while (i < m && j < n) {

            int a = arrayOne[i];
            int b = arrayTwo[j];

            if (a < b) {
                i++;
            } else if (a > b) {
                j++;
            } else {
                return new int[]{a, b};
            }

            int currentDiff = Math.abs(a - b);
            if (currentDiff < closestDiff) {
                closestDiff = currentDiff;
                pair[0] = a;
                pair[1] = b;
            }
        }
        return pair;
    }

    public static int[] smallestDifference(int[] arrayOne, int[] arrayTwo) {
        Arrays.sort(arrayOne);
        Arrays.sort(arrayTwo);

        int idxOne = 0;
        int idxTwo = 0;

        int smallest = Integer.MAX_VALUE;
        int current = Integer.MAX_VALUE;

        int[] smallestPair = new int[2];

        while (idxOne < arrayOne.length && idxTwo < arrayTwo.length) {

            int firstNum = arrayOne[idxOne];
            int secondNum = arrayTwo[idxTwo];

            if (firstNum < secondNum) {
                current = secondNum - firstNum;
                idxOne++;
            } else if (secondNum < firstNum) {
                current = firstNum - secondNum;
                idxTwo++;
            } else {
                return new int[]{firstNum, secondNum};
            }

            if (smallest > current) {
                current = smallest;
                smallestPair = new int[]{firstNum, secondNum};
            }
        }
        return smallestPair;
    }

    public static void main(String[] args) {
        int[] arrayOne = {-1, 5, 10, 20, 28, 3};
        int[] arrayTwo = {26, 134, 135, 15, 17};
        System.out.println(Arrays.toString(smallestDifference(arrayOne, arrayTwo)));
    }
}