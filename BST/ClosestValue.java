class TreeNode{

	int data;
	TreeNode left;
	TreeNode right;

	public TreeNode(int data){
		this.data = data;
	}
}
class BST{

	TreeNode root;

	public BST(){
		root = null;
	}

	public void insert(int key){
		root = insertRec(root,key);
	}

	public TreeNode insertRec(TreeNode root, int key){

		if (root == null){
			root = new TreeNode(key);
			return root;
		}

		if (key < root.data){
			root.left = insertRec(root.left,key);
		}else if (key > root.data){
			root.right = insertRec(root.right,key);
		}
		return root;
	}

	public  void inorder(){
		inorderRec(root);
	}

	public  void inorderRec(TreeNode root){
		if (root == null)
			return;
		inorderRec(root.left);
		System.out.print(root.data + " ");
		inorderRec(root.right);
	}

	public  TreeNode search(int key){
		return searchRec(root,key);
	}

	public  TreeNode searchRec(TreeNode root, int key){
		if (root == null || root.data == key){
			return root;
		}
		if (root.data > key){
			return searchRec(root.left,key);
		}
		return searchRec(root.right,key);
	}

	public  void remove(int key){
		root = removeRec(root,key);
	}

	public TreeNode removeRec(TreeNode root, int key){

		if (root == null)return null;

		if (key < root.data){
			root.left = removeRec(root.left,key);
		}else if (key > root.data){
			root.right = removeRec(root.right,key);
		}//node to be deleted
		else{

			//node with only one child or no child
			if (root.left == null){
				return root.right;
			}else if (root.right == null){
				return root.left;
			}

			//node with two children. get the inorder successor
			TreeNode succParent = root;

			TreeNode successor = root.right;

			while (successor.left != null){
				succParent = successor;
				successor = successor.left;
			}

			if (succParent != root){
				succParent.left = successor.right;
			}else{
				succParent.right = successor.right;
			}

			root.data = successor.data;

			//root.key = minValue(root.right);
			//root.right = removeRec(root.right,root.key);
		}
		return root;
	}

	public int minValue(TreeNode root){
		int minValue = root.data;

		while (root.left != null){
			minValue = root.left.data;
			root = 	root.left;
		}
		return minValue;
	}

	public boolean validateBST(){
		return validateBSTRec(root,Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	//current bst rooted at root must always have values lie in the range of min,max
	public boolean validateBSTRec(TreeNode root, int minValue, int maxValue){
		if (root.data < minValue || root.data > maxValue){
			return false;
		}

		if (root.left != null && !validateBSTRec(root.left,minValue,root.data)){
			return false;
		}

		if (root.right!=null && !validateBSTRec(root.right,root.data,maxValue)){
			return false;
		}
		return true;
	}

	public int closest(int target){
		return closestRec(root,target,Integer.MAX_VALUE);
	} 

	//closest represents the node that is close to target
	public int closestRec(TreeNode root, int target, int closest){

		if (root == null)return closest;

		int currentCloseValue = Math.abs(target - root.data);

		int closeValue = Math.abs(target - closest);

		if (currentCloseValue < closeValue){
			closest = root.data;
		}

		if (target < root.data){
			return closestRec(root.left,target,closest);
		}
		return closestRec(root.right,target,closest);
	}
}

class Question{

	public static void main(String[] args){
		BST tree = new BST();
		tree.insert(50); 
        tree.insert(30); 
        tree.insert(20); 
        tree.insert(40); 
        tree.insert(70); 
        tree.insert(60); 
        tree.insert(80); 
  
        System.out.println("INSERTION");
  		tree.inorder();

  		TreeNode node = tree.search(70);
  		System.out.println("\nSEARCH");
  		System.out.println(node.data);

  		//		50
  		//    30     70
  		// 20   40 60  80

  		//Node to be deleted is leaf: simply remove from the tree
  		tree.remove(20);

  		//Node to be delted has only one child: copy the child to the node and delete the child
  		tree.remove(30);

  		//Node to be deleted has two children: find inorder successor of the node. Copy the contents of inorder successor 
  		//of the node and delete the inorder successor
  		tree.remove(50);
  		System.out.println("REMOVAL");
  		tree.inorder();
  		System.out.println("\nVALIDATION");
  		System.out.println(tree.validateBST());
  		System.out.println("CLOSEST");
  		int closest = tree.closest(74);
  		System.out.println(closest);
	}
}
/*
INSERTION
20 30 40 50 60 70 80 
SEARCH
70
REMOVAL
40 60 70 80 
VALIDATION
true
CLOSEST
70
*/

//my solution
import java.util.*;

class Program {
	
  static int closestValue = Integer.MAX_VALUE;
	
  public static int findClosestValueInBst(BST tree, int target) {
		if (tree == null) return closestValue;
		
    	if (tree.value == target){
			return tree.value;
		}
		
		int currentCloseValue = Math.abs(tree.value - target);
		int closeValue = Math.abs(closestValue - target);
		
		if (currentCloseValue < closeValue){
			closestValue = tree.value;
		}
		if (tree.value < target){
			return findClosestValueInBst(tree.right,target);
		}else{
			return findClosestValueInBst(tree.left,target);
		}
  }

  static class BST {
    public int value;
    public BST left;
    public BST right;

    public BST(int value) {
      this.value = value;
    }
  }
}
