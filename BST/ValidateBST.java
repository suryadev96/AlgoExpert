class Program {
    public static boolean validateBst(BST tree) {
        return isValid(tree, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    //all values in left subtree is strictly less than root
    //all values in right subtree is greater or equal to root
    public static boolean isValid(BST tree, int min, int max) {
        if (tree == null) return true;
        if (tree.value < min || tree.value >= max) {
            return false;
        }
        return isValid(tree.left, min, tree.value) && isValid(tree.right, tree.value, max);
    }

    static class BST {
        public int value;
        public BST left;
        public BST right;

        public BST(int value) {
            this.value = value;
        }
    }
}
