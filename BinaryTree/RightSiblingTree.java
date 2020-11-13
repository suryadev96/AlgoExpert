/*
A right sibling tree is obtained by making every node in binary tree have its right property point to its right sibling (node immediately
to its right on the same level) instead of its right child.
if right sibling is null, point to null

A node's right sibling is the node immediately to its right on the same level or null if there is no node immediately to its right

The transformation should be done in place, meaning that the original data structure should be mutated

	    1
	 /    \
	2      3
   / \    / \
  4  5   6   7
 / \ \   /  /  \
8  9 10 11  12 13
		/
	   14

       1
      /
     2-----------3
    /           /
	4-----5----6-----7
   /		   /     /
   8--9     10-11    12-13  //the node with value 10 no longer has a node pointing to it
   			   /
   			  14
*/
class TreeNode {

    TreeNode left;
    TreeNode right;
    int data;

    TreeNode(int data) {
        this.data = data;
    }
}

class Question {


    //there are two patterns: if a node is the left child of another node, its right sibling is its parent right child;
    //if a node is the right child of another node, its right sibiling is its parent right siblings left child

    //you will need to find a way to quickly access a node parent's right child and a node parent's right sibling;
    //this wont be trivial because the second one implies that the node parent's right pointer gets overwritten

    //transform left subtree and edit the node right pointer to point to right sibling and transform right subtree
    //this sequencing of operations will allow left child nodes to always access parent's right child (before their parent;s right pointer
    //gets overwritten to point to the parents right sibling) and will allow right child nodes to always access their parent right sibling(after the parent right pointer gets overwritten)
    public static TreeNode rightSiblingTree(TreeNode root) {
        mutate(root, null, false);
        return root;
    }

    public static void mutate(TreeNode node, TreeNode parent, boolean isLeftChild) {
        if (node == null) return;

        TreeNode left = node.left;
        TreeNode right = node.right;

        mutate(left, node, true);

        if (parent == null) {
            node.right = null; //only valid for root
        } else if (isLeftChild) {
            node.right = parent.right;
        } else { //right child
            if (parent.right == null) {
                node.right = null;
            } else {
                node.right = parent.right.left;
            }
        }
        mutate(right, node, false);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(8);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(6);

        rightSiblingTree(root);
    }

}

