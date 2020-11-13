import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class TreeNode {
    Integer data;
    TreeNode left;
    TreeNode right;
    Integer count = 1; //elements smaller than or equal to the current element

    public TreeNode(Integer data) {
        this.data = data;
    }
}

class BST {

    TreeNode root;

    public BST(Integer data) {
        root = new TreeNode(data);
    }

    public int insert(Integer data) {
        int countSmaller = 0;
        TreeNode node = root;
        while (true) {
            if (node.data >= data) {
                node.count++;
                if (node.left == null) {
                    node.left = new TreeNode(data);
                    break;
                }
                node = node.left;
            } else { //root.data < data
                countSmaller += node.count;
                if (node.right == null) {
                    node.right = new TreeNode(data);
                    break;
                }
                node = node.right;
            }
        }
        return countSmaller;
    }

}

class Program {
    public static List<Integer> rightSmallerThan(List<Integer> array) {
        int n = array.size();
        if (n == 0) return new ArrayList<>();

        List<Integer> result = new ArrayList<>();
        result.add(0);

        BST bst = new BST(array.get(n - 1));
        for (int i = n - 2; i >= 0; i--) {
            result.add(bst.insert(array.get(i)));
        }
        Collections.reverse(result);
        return result;
    }
}
