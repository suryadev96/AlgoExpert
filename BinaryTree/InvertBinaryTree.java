import java.util.Queue;

class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;

    TreeNode(int data) {
        this.data = data;
    }
}

class Question {


    public static void invert(TreeNode root) {
        if (root == null) return;
        swapLeftAndRight(root);
        invert(root.left);
        invert(root.right);
    }

    public static void invertBinaryTree(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            TreeNode current = q.poll();
            if (current == null) continue;

            swapLeftAndRight(current);

            if (current.left != null) {
                q.add(current.left);
            }

            if (current.right != null) {
                q.add(current.right);
            }
        }
    }

    public static void swapLeftAndRight(TreeNode root) {
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        TreeNode root = createTreeFromArray(arr);
        invertBinaryTree(root);
        levelOrderTraversal(root);
    }

    public static void levelOrderTraversal(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty()) {

            TreeNode tempNode = queue.poll();
            System.out.print(tempNode.data + " ");

            if (tempNode.left != null) {
                queue.add(tempNode.left);
            }

            if (tempNode.right != null) {
                queue.add(tempNode.right);
            }
        }
    }

    public static TreeNode createTreeFromArray(int[] arr) {
        if (arr.length > 0) {
            TreeNode root = new TreeNode(arr[0]);
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);

            boolean done = false;
            int i = 1;
            while (!done) {
                TreeNode r = queue.element(); //note u should not remove this element from the queue until we fill in children for this node

                if (r.left == null) {
                    r.left = new TreeNode(arr[i]);
                    i++;
                    queue.add(r.left);
                } else if (r.right == null) {
                    r.right = new TreeNode(arr[i]);
                    i++;
                    queue.add(r.right);
                } else {
                    queue.remove();
                }
                if (i == arr.length) done = true;
            }
            return root;
        }
        return null;
    }
}
/*
1 3 2 7 6 5 4 
*/