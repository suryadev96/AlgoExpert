class MonotonicArray {

    public static boolean isMonotonic(int[] arr) {
        //u cannot identify the trend if there are only 2 elements
        if (arr.length <= 2) return true;

        //direction is positive for non-decreasing and negative for non-increasing
        int direction = arr[1] - arr[0];

        for (int i = 2; i < arr.length; i++) {

            //direction is previous seen trend in the array. if no trend is observed previously then check the current direction
            //and continue
            if (direction == 0) {
                direction = arr[i] - arr[i - 1];
                continue;
            }

            //if direction observed previously is either positive or negative, then check if the current direction breaks the
            //previous observation
            if (breaksDirection(direction, arr[i - 1], arr[i])) {
                return false;
            }
        }
        return true;
    }

    public static boolean breaksDirection(int direction, int previous, int current) {
        int currDirection = current - previous;

        //if previous direction is postive and if current direction is less than 0 then it breaks the trend
        if (direction > 0) return currDirection < 0;

        //if previosu dierction is negative and if current direction is positive then it breaks the trend
        return currDirection > 0;
    }

    public static boolean isMonotonicEff(int[] arr) {
        boolean isNonDecreasing = true;
        boolean isNonIncreasing = true;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                isNonDecreasing = false;
            }
            if (arr[i] > arr[i - 1]) {
                isNonIncreasing = false;
            }
        }
        return isNonDecreasing || isNonIncreasing;
    }

    public static void main(String[] args) {
        int[] arr = {-1, -5, -10, -1100, -1100, -1101, -1102, -9001};

        boolean isMonotonic = isMonotonic(arr);
        System.out.println(isMonotonic);

        isMonotonic = isMonotonicEff(arr);
        System.out.println(isMonotonic);

    }

}