/*
Given two non-empty arrays of integers, write a function that determines if the second array is a subsequence of first one.

array = [5,1,22,25,6,-1,8,10];
sequence = [1,6,-1,10]
*/
class ValidateSubsequence {

    public static boolean isValidSubsequence(int[] array, int[] sequence) {
        int arrIdx = 0;
        int seqIdx = 0;

        int m = array.length;
        int n = sequence.length;

        while (arrIdx < m && seqIdx < n) {
            if (array[arrIdx] == sequence[seqIdx]) {
                seqIdx++;
            }
            arrIdx++;
        }
        return seqIdx == n;
    }

    public static void main(String[] args) {
        int[] array = {5, 1, 22, 25, 6, -1, 8, 10};
        int[] sequence = {1, 6, -1, 10};
        System.out.println(isValidSubsequence(array, sequence));
    }

}