/*
Inorder flattening of a tree. Flattening should be done in place, meaning that the original data structure should be mutated.
no new structure should be created

A flattened Binary tree is a structure that's nearly identical to a Doubly Linked List where nodes follow the original tree's 
left-right order

	  1
	/   \
   2     3
  / \   /
 4   5 6
    / \
   7   8

4 <-> 2 <-> 7 <-> 5 <-> 8 <-> 1 <-> 6 <-> 3 //the leftmost node with value 4
*/
class TreeNode{

	TreeNode left;
	TreeNode right;
	int data;

	TreeNode(int data){
		this.data = data;
	}
}
class Question{

	public static TreeNode flattenBinaryTree(TreeNode root){
		flattenTree(root);
		return getLeftMost(root);
	}

	public static TreeNode getLeftMost(TreeNode node){
		while (node.left != null){
			node = node.left;
		}
		return node;
	}

	//at any given node in the inorder-traversal order, the node immediately to its left is the rightmost node of its left subtree
	//and the node immediately to its right is the leftmost node of its right subtree
	public static TreeNode[] flattenTree(TreeNode node){

		TreeNode leftMost; //in the right subtree
		TreeNode rightMost; //in the left subtree

		
		if (node.left == null){
			leftMost = node;
		}else{
			TreeNode[] leftAndRightMostNodes = flattenTree(node.left);
			connectNodes(leftAndRightMostNodes[1],node);
			leftMost = leftAndRightMostNodes[0];
		}


		if (node.right == null){
			rightMost = node;
		}else{
			TreeNode[] leftAndRightMostNodes = flattenTree(node.right);
			connectNodes(node,leftAndRightMostNodes[0]);
			rightMost = leftAndRightMostNodes[1];
		}
		return new TreeNode[]{leftMost,rightMost};
	}

	public static void connectNodes(TreeNode left, TreeNode right){
		left.right = right;
		right.left = left;
	}

	public static void main(String[] args){
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		root.left.right.left = new TreeNode(7);
		root.left.right.right = new TreeNode(8);
		root.right = new TreeNode(3);
		root.right.left = new TreeNode(6);

		root = flattenBinaryTree(root);
		linearTraversal(root);
	}

	public static  void linearTraversal(TreeNode root){
		while(root.right != null){
			System.out.print(root.data + "-->");
			root = root.right;
		}
		System.out.println(root.data);

		while (root.left != null){
			System.out.print(root.data + "-->");
			root = root.left;
		}
		System.out.println(root.data);
	}
}
/*
4-->2-->7-->5-->8-->1-->6-->3
3-->6-->1-->8-->5-->7-->2-->4
*/

