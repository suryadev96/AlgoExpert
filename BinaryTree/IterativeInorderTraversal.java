class TreeNode{

	TreeNode left;
	TreeNode right;
	TreeNode parent;
	int data;

	TreeNode(int data){
		this.data = data;
	}
}

class Question3{

	public static void main(String[] args){
		int[] arr = {1,2,3,4,5,6,7,8,9};
		TreeNode root = createMinimalBST(arr);
		//inorder(root);
		iterativeInOrderTraversal(root);
	}

	public static void iterativeInOrderTraversal(TreeNode root){

		TreeNode previousNode = null;
		TreeNode currentNode = root;

		while (currentNode != null){
			TreeNode nextNode;

			if (previousNode == null || previousNode == currentNode.parent){
				if (currentNode.left != null){
					nextNode = currentNode.left;
				}else{
					System.out.println(currentNode.data);
					nextNode = currentNode.right != null ? currentNode.right : currentNode.parent;
				}
			} else if (previousNode == currentNode.left){
				System.out.println(currentNode.data);
				nextNode = currentNode.right != null ? currentNode.right : currentNode.parent;
			}else{ // previousNode == currentNode.right; in this case u recur back to the parent
				nextNode = currentNode.parent;
			}
			previousNode = currentNode;
			currentNode = nextNode;
		}
	}

	public static TreeNode createBST(int[] arr, int low, int high){
		if (low <= high){
			int mid = low + (high-low)/2;

			TreeNode root = new TreeNode(arr[mid]);
			root.left = createBST(arr,low,mid-1);
			if (root.left != null){
				root.left.parent = root;
			}
			root.right = createBST(arr,mid+1,high);
			if (root.right != null){
				root.right.parent = root;
			}
			return root;
		}
		return null;
	}

	public static TreeNode createMinimalBST(int[] arr){
		return createBST(arr,0,arr.length-1);
	}

	public static void inorder(TreeNode root){
		if (root != null){
			inorder(root.left);
			System.out.print(root.data + " ");
			inorder(root.right);
		}
	}

}

//my solution:
import java.util.function.Function;

class Program {
  public static void iterativeInOrderTraversal(
      BinaryTree tree, Function<BinaryTree, Void> callback) {
    
		BinaryTree prev = null;
		BinaryTree curr = tree;
		
		while (curr != null){
			BinaryTree next;
			//only if prev is behind curr => it means we have to go left
			if (prev == null || prev == curr.parent){ 
				if (curr.left != null){
					next = curr.left;
				}else{
					//we reached leftmost end; either we have to go right now or trace back to parent
					callback.apply(curr);
					next = curr.right != null ? curr.right : curr.parent;
				}
			}else if (prev == curr.left){ //completed left subtree
				callback.apply(curr);
				next = curr.right != null ? curr.right : curr.parent;
			}else{
				next = curr.parent;
			}
			prev = curr;	
			curr = next;
		}
		
  }

  static class BinaryTree {
    public int value;
    public BinaryTree left;
    public BinaryTree right;
    public BinaryTree parent;

    public BinaryTree(int value) {
      this.value = value;
    }

    public BinaryTree(int value, BinaryTree parent) {
      this.value = value;
      this.parent = parent;
    }
  }
}
