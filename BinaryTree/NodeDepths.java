/*
The distance between a node in a Binary Tree and the tree's root is called the node's depth
Write a function that takes in a Binary Tree and returns the sum of all of its subtrees' nodes' depths

	    1
	  /  \
  	 2    3 
    / \  / \
	4 5 6   7 
	/\
	8 9

16
//The depth of the node with value 2 is 1
//The depth of the node with value 3 is 1
//The depth of the node with value 4 is 2
//The depth of the node with value 5 is 2
//Summing all of these sums yields 16
*/
import java.util.*;
class TreeNode{

	int data;
	TreeNode left;
	TreeNode right;

	public TreeNode(int data){
		this.data = data;
	}

}
class Question{

	public static int nodeDepths(TreeNode root){
		return nodeDepths(root,0);
	}

	public static int nodeDepths(TreeNode root,  int depth){
		if (root == null) return 0;

		return depth + nodeDepths(root.left,depth+1) 
						+ nodeDepths(root.right,depth+1);
	}

	public static void main(String[] args) {
		int[] arr = {1,2,3,4,5,6,7,8,9};
		TreeNode root = createTreeFromArray(arr);
		int count = nodeDepths(root);
		System.out.println(count);
	}

	public static TreeNode createTreeFromArray(int[] arr){
		if (arr.length > 0){
			TreeNode root = new TreeNode(arr[0]);
			Queue<TreeNode> queue = new LinkedList<>();
			queue.add(root);

			boolean done = false;
			int i=1;
			while (!done){
				TreeNode r = queue.element(); //note u should not remove this element from the queue until we fill in children for this node

				if (r.left == null){
					r.left = new TreeNode(arr[i]);
					i++;
					queue.add(r.left);
				}else if (r.right == null){
					r.right = new TreeNode(arr[i]);
					i++;
					queue.add(r.right);
				}else{
					queue.remove();
				}
				if (i == arr.length)done = true;
			}
			return root;
		}
		return null;
	}

}