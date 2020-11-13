/*
Write a function that takes in a BST and return the number of subtrees in the BST that are only made up of nodes with values
contained in the range
*/
class BSTValue {
    int min;
    int max;

    public BSTValue(int min, int max) {
        this.min = min;
        this.max = max;
    }
}

class Program {

    static int count;

    public static int subtreesWithinRange(BST tree, int[] targetRange) {
        count = 0;
        helper(tree, targetRange);
        return count;
    }

    public static BSTValue helper(BST tree, int[] range) {
        if (tree == null) {
            return new BSTValue(Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        BSTValue left = helper(tree.left, range);
        BSTValue right = helper(tree.right, range);

        int root = tree.value;

        int min = left.min;
        if (min == Integer.MIN_VALUE) {
            min = root;
        }

        int max = right.max;
        if (max == Integer.MAX_VALUE) {
            max = root;
        }

        if (min >= range[0] && max <= range[1]) {
            count++;
        }
        return new BSTValue(min, max);
    }

    // This is an input class. Do not edit.
    static class BST {
        public int value;
        public BST left;
        public BST right;

        public BST(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }
}

//optimized solution
class Program {

    static int count;

    public static int subtreesWithinRange(BST tree, int[] targetRange) {
        count = 0;
        helper(tree, targetRange);
        return count;
    }

    public static boolean helper(BST tree, int[] range) {
        if (tree == null) {
            return true;
        }

        boolean left = helper(tree.left, range);
        boolean right = helper(tree.right, range);

        int root = tree.value;

        if (left && right && root >= range[0] && root <= range[1]) {
            count++;
            return true;
        }
        return false;
    }

    // This is an input class. Do not edit.
    static class BST {
        public int value;
        public BST left;
        public BST right;

        public BST(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }
}

