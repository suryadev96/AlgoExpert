import java.util.Arrays;
import java.util.List;

class MoveElementToEnd {

    public static List<Integer> moveElementToEnd(List<Integer> array, int toMove) {

        int i = 0;
        int j = array.size() - 1;

        //move j as long as it points to toMove ; j looks for the other than toMove element always; elements to the right of j are all toMove elements
        //move i as long as it doesnot point to toMove; i looks for the toMove element always to swap it to the end;
        // if i crosses j; then i need not worry about to swap the elements any more because all the elements are toMove elements
        while (i < j) {
            while (i < j && array.get(j) == toMove) j--;
            if (array.get(i) == toMove) swap(i, j, array);
            i++;
        }
        return array;
    }

    //My solution 1
    public static List<Integer> moveElementToEndMySol1(List<Integer> array, int toMove) {
        //before left, there is no toMove element
        //after right, there is only toMove element
        int left = 0;
        int right = array.size() - 1;
        while (left < right) {

            while (left < right && array.get(right) == toMove) right--;

            while (left < right && array.get(left) != toMove) left++;

            swap(left, right, array);
        }
        return array;
    }

    //My solution 2
    public static List<Integer> moveElementToEndMySol2(List<Integer> array, int toMove) {
        //before left, there is no toMove element
        //after right, there is only toMove element
        int left = 0;
        int right = array.size() - 1;
        while (left < right) {
            if (array.get(left) == toMove && array.get(right) != toMove) {
                swap(left, right, array);
            }
            if (array.get(left) != toMove) left++;
            if (array.get(right) == toMove) right--;
        }
        return array;
    }

    public static void swap(int i, int j, List<Integer> array) {
        int temp = array.get(j);
        array.set(j, array.get(i));
        array.set(i, temp);
    }

    public static void main(String[] args) {
        List<Integer> array = Arrays.asList(1, 4, 3, 4, 4, 5, 4, 6, 7);
        System.out.println(moveElementToEnd(array, 4));
    }
}
