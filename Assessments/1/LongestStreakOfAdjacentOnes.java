class Program {
    public static int longestStreakOfAdjacentOnes(int[] array) {
        int l = 0;
        int r = 0;

        int maxLen = 0;
        int zero = 0; //zeros present in window
        int k = 1;

        int flipIdx = -1;

        int currFlipIdx = -1;

        while (r < array.length) {

            if (array[r] == 0) {
                zero++;
                currFlipIdx = r;
            }

            //minimize the window
            while (zero > k) {
                if (array[l++] == 0) {
                    zero--;
                }
            }

            int currLen = r - l + 1;
            if (currLen > maxLen) {
                maxLen = currLen;
                flipIdx = currFlipIdx;
            }
            r++;
        }

        return flipIdx;
    }
}
