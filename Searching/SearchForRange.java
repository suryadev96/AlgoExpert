class Question {

    public static int[] searchForRange(int[] array, int target) {
        int[] finalRange = {-1, -1};
        alteredBinarySearch(array, target, 0, array.length - 1, finalRange, true);
        alteredBinarySearch(array, target, 0, array.length - 1, finalRange, false);
        return finalRange;
    }

    public static void alteredBinarySearchIter(int[] array, int target, int left, int right, int[] finalRange, boolean goLeft) {
        while (left <= right) {
            int mid = (left + right) / 2;
            if (array[mid] < target) {
                left = mid + 1;
            } else if (array[mid] > target) {
                right = mid - 1;
            } else {
                if (goLeft) {
                    if (mid == 0 || array[mid - 1] != target) {
                        finalRange[0] = mid;
                        return;
                    } else {
                        right = mid - 1;
                    }
                } else {
                    if (mid == array.length - 1 || array[mid + 1] != target) {
                        finalRange[1] = mid;
                        return;
                    } else {
                        left = mid + 1;
                    }
                }
            }
        }
    }

    //target can be found in between left and right inclusive
    public static void alteredBinarySearch(int[] array, int target, int left, int right, int[] finalRange, boolean goLeft) {
        if (left > right) return;

        int mid = (left + right) / 2;

        if (array[mid] < target) {
            alteredBinarySearch(array, target, mid + 1, right, finalRange, goLeft);
        } else if (array[mid] > target) {
            alteredBinarySearch(array, target, left, mid - 1, finalRange, goLeft);
        } else {
            if (goLeft) {
                if (mid == 0 || array[mid - 1] != target) {
                    finalRange[0] = mid;
                } else {
                    alteredBinarySearch(array, target, left, mid - 1, finalRange, goLeft);
                }
            } else {
                if (mid == array.length - 1 || array[mid + 1] != target) {
                    finalRange[1] = mid;
                } else {
                    alteredBinarySearch(array, target, mid + 1, right, finalRange, goLeft);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {0, 1, 21, 33, 45, 45, 45, 45, 45, 61, 71, 73};
        int target = 45;
        int[] range = searchForRange(array, target);
        for (Integer i : range) {
            System.out.print(i + " ");
        }
    }

}

//my solution
import java.util.*;

class Program {
    public static int[] searchForRange(int[] array, int target) {
        int[] finalRange = {-1, -1};
        binarySearch(array, target, finalRange, true);
        binarySearch(array, target, finalRange, false);
        return finalRange;
    }

    public static void binarySearch(int[] array, int target, int[] finalRange, boolean goLeft) {
        int left = 0;
        int right = array.length - 1;

        //when going left;
        //before left , all elements are less than target
        //after right , all elements are greater or equal to target
        //left is required

        //when going right
        //before left, all elements are less than or equal to target
        //after right, all elements are greater than target
        //right is required

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (goLeft) {
                if (array[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                if (array[mid] <= target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        if (goLeft) {
            if (left >= array.length || array[left] != target) finalRange[0] = -1;
            else finalRange[0] = left;
        } else {
            if (right < 0 || array[right] != target) finalRange[1] = -1;
            else finalRange[1] = right;
        }
    }

}
