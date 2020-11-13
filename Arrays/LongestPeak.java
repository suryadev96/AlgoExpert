/*
returns the longest peak in the array
peak is defined as three or more adjacent integers in the array that are strictly increasing until they reach a tip (highest value in the peak)
at which point they become strictly decreasing

1,2,3,3,4,0,10,6,5,-1,-3,2,3

6 //0 10 6 5 -1 -3 
*/

class LongestPeak {

    //iterate through the array and treat every integer as potential tip of a peak
    public static int longestPeak(int[] array) {
        int longestPeakLength = 0;

        //since peak must be of length minimum 3
        int i = 1;

        //array.length - 1 cannot be potential peak

        while (i < array.length - 1) {

            boolean isPeak = array[i - 1] < array[i] && array[i] > array[i + 1];

            if (!isPeak) {
                i++;
                continue;
            }

            //expand to the left of the peak and check that it must be strictly increasing upto the peak
            int leftIdx = i - 2;
            while (leftIdx >= 0 && array[leftIdx] < array[leftIdx + 1]) {
                leftIdx--;
            }

            //when leftIdx is not lesser than the next element that means peak left part ended

            //expand to the right of the peak
            int rightIdx = i + 2;
            while (rightIdx < array.length && array[rightIdx] < array[rightIdx - 1]) {
                rightIdx++;
            }

            //when rightIdx is not lesser than the previous element that means right part ended

            //when u subtract rightIdx - leftIdx , it is excluding the leftIdx , -1 is for excluding the rightIdx also
            int currentPeakLength = rightIdx - leftIdx - 1;

            if (currentPeakLength > longestPeakLength) {
                longestPeakLength = currentPeakLength;
            }

            //next potential peak rightIdx
            i = rightIdx;
        }
        return longestPeakLength;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 3, 4, 0, 10, 6, 5, -1, -3, 2, 3};
        int longestPeak = longestPeak(arr);
        System.out.println(longestPeak);
    }

}
