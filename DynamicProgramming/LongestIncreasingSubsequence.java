/*
write a function that returns the longest increasing subsequence in the array
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Question {

    //O(n^2) time
    public static List<Integer> longestIncreasingSubsequence(int[] array) {
        int[] sequences = new int[array.length];
        Arrays.fill(sequences, Integer.MIN_VALUE);
        //length of lis ending with index i
        int[] lengths = new int[array.length];
        Arrays.fill(lengths, 1);

        int maxLengthIdx = 0;

        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < i; j++) {
                if (array[j] < array[i] && lengths[j] + 1 >= lengths[i]) {
                    lengths[i] = lengths[j] + 1;
                    sequences[i] = j;
                }
            }
            if (lengths[i] > lengths[maxLengthIdx]) {
                maxLengthIdx = i;
            }
        }
        return buildSequence(array, sequences, maxLengthIdx);
    }


    public static List<Integer> longestIncreasingSubsequenceBS(int[] array) {

        int[] sequences = new int[array.length];

        //indices array store the index of the number which ends for the lis of length i
        //if a number is greater than indices[mid]; then u can eliminate all the previous indexs because u already found a lis ending with this number
        //if a number is lesser than indices[mid]; then u can eliminate right half because all those elements must have values greater than indices[mid]
        //because indices[mid] is the smallest number that ends with lis of length mid. so there is no way of forming lis of length greater than mid.
        int[] indices = new int[array.length + 1];

        Arrays.fill(indices, Integer.MIN_VALUE);

        //lis length until now found
        int length = 0;

        for (int i = 0; i < array.length; i++) {
            int num = array[i];

            int newLength = binarySearch(1, length, indices, array, num);

            sequences[i] = indices[newLength - 1]; //index of the number that ends in previous length lis

            indices[newLength] = i; // index of the smaller number that ends with lis of length newLength

            length = Math.max(length, newLength); //maximum lis length found till now
        }

        return buildSequence(array, sequences, indices[length]);
    }

    //returns the newLength ; startIdx and endIdx represents the least and max length of lis that is possible
    public static int binarySearch(int startIdx, int endIdx, int[] indices, int[] array, int num) {
        if (startIdx > endIdx) {
            return startIdx;
        }
        int middleIdx = (startIdx + endIdx) / 2;

        if (array[indices[middleIdx]] < num) {
            startIdx = middleIdx + 1;
        } else {
            endIdx = middleIdx - 1;
        }
        return binarySearch(startIdx, endIdx, indices, array, num);
    }

    public static List<Integer> buildSequence(int[] array, int[] sequences, int currentIdx) {
        List<Integer> sequence = new ArrayList<Integer>();
        while (currentIdx != Integer.MIN_VALUE) {
            sequence.add(0, array[currentIdx]);
            currentIdx = sequences[currentIdx];
        }
        return sequence;
    }

    public static void main(String[] args) {
        int[] array = {5, 7, -24, 12, 10, 2, 3, 12, 5, 6, 35};
        List<Integer> lis = longestIncreasingSubsequenceBS(array);
        System.out.println(lis);
    }

}