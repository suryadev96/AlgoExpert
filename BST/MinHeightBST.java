/*
array = [1,2,5,7,10,13,14,15,22];
*/
class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;

    public TreeNode(int data) {
        this.data = data;
    }
}

class Question {

    public static TreeNode createBST(int[] arr, int low, int high) {
        if (low <= high) {
            int mid = low + (high - low) / 2;
            TreeNode root = new TreeNode(arr[mid]);
            root.left = createBST(arr, low, mid - 1);
            root.right = createBST(arr, mid + 1, high);
            return root;
        }
        return null;
    }

    public static TreeNode createMinimalBST(int[] arr) {
        TreeNode root = createBST(arr, 0, arr.length - 1);
        return root;
    }


    public static void main(String[] args) {
        int[] array = {1, 2, 5, 7, 10, 13, 14, 15, 22};
        TreeNode root = createMinimalBST(array);
        inorder(root);
    }

    public static void inorder(TreeNode root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }
    }

}
//1 2 5 7 10 13 14 15 22 

import java.util.*;

class Program {
    public static BST minHeightBst(List<Integer> array) {
        return createBST(array, 0, array.size() - 1);
    }

    public static BST createBST(List<Integer> array, int low, int high) {
        if (low <= high) {
            int mid = low + (high - low) / 2;
            BST bst = new BST(array.get(mid));
            bst.left = createBST(array, low, mid - 1);
            bst.right = createBST(array, mid + 1, high);
            return bst;
        }
        return null;
    }

    static class BST {
        public int value;
        public BST left;
        public BST right;

        public BST(int value) {
            this.value = value;
            left = null;
            right = null;
        }

        public void insert(int value) {
            if (value < this.value) {
                if (left == null) {
                    left = new BST(value);
                } else {
                    left.insert(value);
                }
            } else {
                if (right == null) {
                    right = new BST(value);
                } else {
                    right.insert(value);
                }
            }
        }
    }
}
