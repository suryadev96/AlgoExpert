/*
The distance between a node in a Binary Tree and the tree's root is called the node's depth
Write a function that takes in a Binary Tree and returns the sum of all of its subtrees' nodes' depths

	     (0)1
	   	  /  \
(1)   	 2    3 (1)
     	/ \  / \
(1+2)	4 5 6   7 (1+2)
		/\
(1+2+3)	8 9

26
//The sum of the root tree's node depths is 16
//The sum of the tree rooted at 2's node depths is 6
//The sum of the tree rooted at 3's node depths is 2
//The sum of the tree rooted at 4's node depths is 2
//Summing all of these sums yields 26
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

	public static int allKindsOfNodeDepths(TreeNode root){
		return allKindsOfNodeDepths(root,0,0);
	}

	public static int allKindsOfNodeDepths(TreeNode root, int depthSum , int depth){
		if (root == null) return 0;

		depthSum += depth;

		return depthSum + allKindsOfNodeDepths(root.left, depthSum, depth+1) 
						+ allKindsOfNodeDepths(root.right, depthSum, depth+1);
	}

	public static int allKindsOfNodeDepthsEff(TreeNode root){
		return allKindsOfNodeDepthsEff(root,0);
	}

	public static int allKindsOfNodeDepthsEff(TreeNode root, int depth){
		if (root == null) return 0;

		int depthSum = (depth * (depth + 1))/2;

		return depthSum + allKindsOfNodeDepthsEff(root.left,depth+1)
						+ allKindsOfNodeDepthsEff(root.right,depth+1);
	}

	public static void main(String[] args) {
		int[] arr = {1,2,3,4,5,6,7,8,9};
		TreeNode root = createTreeFromArray(arr);
		int count = allKindsOfNodeDepths(root);
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