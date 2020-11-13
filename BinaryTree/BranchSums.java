/*
return a list of its branch sums ordered from leftmost branch sum to rightmost branch sum

A branch sum is the sum of all values in a binary tree branch. A binary tree branch is a path of nodes that starts at 
the root node and ends at any leaf node

[15,16,18,10,11]
15 = 1 + 2 + 4 + 8
16 = 1 + 2 + 4 + 9
18 = 1 + 2 + 5 + 10
10 = 1 + 3 + 6
11 = 1 + 3 + 7
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

class TreeNode {

    TreeNode left;
    TreeNode right;
    int data;

    public TreeNode(int data) {
        this.data = data;
    }
}

class Question {

    public static List<Integer> branchSums(TreeNode root) {
        List<Integer> sums = new ArrayList<>();
        branchSumsUtil(root, 0, sums);
        return sums;
    }

    public static void branchSumsUtil(TreeNode node, int sum, List<Integer> sums) {

        if (node == null) return;

        //int currentSum = sum + node.data : both are same because stack stores local variable in each stack
        sum += node.data;

        if (node.left == null && node.right == null) {
            sums.add(sum);
            return;
        }
        branchSumsUtil(node.left, sum, sums);
        branchSumsUtil(node.right, sum, sums);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        TreeNode root = createTreeFromArray(arr);
        List<Integer> branchSums = branchSums(root);
        printList(branchSums);
    }

    public static void printList(List<Integer> branchSums) {
        for (Integer i : branchSums) {
            System.out.print(i + " ");
        }
    }

    public static void inorder(TreeNode root) {
        if (root != null) {
            inorder(root.left);
            System.out.println(root.data + " ");
            inorder(root.right);
        }
    }

    public static TreeNode createTreeFromArray(int[] arr) {

        TreeNode root = new TreeNode(arr[0]);

        boolean done = false;

        int i = 1;

        Queue<TreeNode> q = new LinkedList<>();

        q.add(root);

        while (!done) {

            TreeNode temp = q.element();

            if (temp.left == null) {
                temp.left = new TreeNode(arr[i++]);
                q.add(temp.left);
            } else if (temp.right == null) {
                temp.right = new TreeNode(arr[i++]);
                q.add(temp.right);
            } else {
                q.remove();
            }
            done = (i == arr.length);
        }
        return root;
    }

}
/*
15 16 18 10 11
*/