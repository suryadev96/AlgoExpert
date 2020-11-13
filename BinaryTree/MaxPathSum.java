/*
Max path sum . A path is a collection of connected nodes in a tree where no node is connected to more than two other nodes;
a path sum is the sum of the values of the nodes in a particular path
*/
import java.util.*;
class TreeNode{
	int data;
	TreeNode left;
	TreeNode right;

	TreeNode(int data){
		this.data = data;
	}
}
class Question{

	static int res = 0;

	public static int maxPathSum(TreeNode root){

		if (root == null)return 0;

		int l = maxPathSum(root.left);
		int r = maxPathSum(root.right);

		int max_single = Math.max(Math.max(l,r) + root.data , root.data);

		int max_top = Math.max(max_single, l + r + root.data);

		res = Math.max(res,max_top);
		return max_single;
	}

	//Algo expert solution
	public static int maxPathSumRefined(TreeNode root){
		List<Integer> maxSumArray = findMaxSum(root);
		return maxSumArray.get(1);
	}

	public static List<Integer> findMaxSum(TreeNode root){
		if (root == null){
			return new ArrayList<Integer>(Arrays.asList(0,Integer.MIN_VALUE));
		}
		List<Integer> leftMaxSumArray = findMaxSum(root.left);
		Integer leftMaxSumAsBranch = leftMaxSumArray.get(0);
		Integer leftMaxPathSum = leftMaxSumArray.get(1);

		List<Integer> rightMaxSumArray = findMaxSum(root.right);
		Integer rightMaxSumAsBranch = rightMaxSumArray.get(0);
		Integer rightMaxPathSum = rightMaxSumArray.get(1);

		Integer maxChildSumAsBranch = Math.max(leftMaxSumAsBranch, rightMaxSumAsBranch);
		Integer maxSumAsBranch = Math.max(maxChildSumAsBranch + root.data , root.data);
		
		Integer maxSumAsRootNode = Math.max(leftMaxSumAsBranch + root.data + rightMaxSumAsBranch, maxSumAsBranch);

		int maxPathSum = Math.max(leftMaxPathSum, Math.max(rightMaxPathSum, maxSumAsRootNode));

		return new ArrayList<Integer>(Arrays.asList(maxSumAsBranch, maxPathSum));
	}

	public static void main(String[] args){
		int[] arr= {1,2,3,4,5,6,7};
		TreeNode root = createTreeFromArray(arr);
		maxPathSum(root);
		System.out.println(res);
	}

	public static void levelOrderTraversal(TreeNode root){
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		while (!queue.isEmpty()){

			TreeNode tempNode = queue.poll();
			System.out.print(tempNode.data + " ");

			if (tempNode.left != null){
				queue.add(tempNode.left);
			}

			if (tempNode.right != null){
				queue.add(tempNode.right);
			}
		}
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

//my solution
import java.util.*;

class Program {
  public static int maxPathSum(BinaryTree tree) {
    int[] result = findMaxSum(tree);
		return result[1];
  }
	
	public static int[] findMaxSum(BinaryTree root){
		if (root == null){
			return new int[]{0,Integer.MIN_VALUE};
		}
		
		int[] left = findMaxSum(root.left);
		
		int[] right = findMaxSum(root.right);
		
		int left_single = left[0];
		int right_single = right[0];
		
		int left_max = left[1];
		int right_max = right[1];
		
		int max_single = Math.max(Math.max(left_single,right_single) + root.value, root.value);
		
		int max_top = Math.max(max_single, left_single + right_single + root.value);
		
		int root_max = Math.max(Math.max(left_max,right_max),max_top);
		
		return new int[]{max_single, root_max};
	}

  static class BinaryTree {
    public int value;
    public BinaryTree left;
    public BinaryTree right;

    public BinaryTree(int value) {
      this.value = value;
    }
  }
}
