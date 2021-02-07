import java.util.*;

class Program {
  // This is an input class. Do not edit.
  static class BinaryTree {
    public int value;
    public BinaryTree left = null;
    public BinaryTree right = null;

    public BinaryTree(int value) {
      this.value = value;
    }
  }

  static int diameter;
	
  public int binaryTreeDiameter(BinaryTree tree) {
    diameter = 0;
		depth(tree);
    return diameter;
  }
	
	public int depth(BinaryTree root){
		if (root == null) return 0;
		
		int l = depth(root.left);
		int r = depth(root.right);
		
		diameter = Math.max(diameter, l + r);
		return Math.max(l,r) + 1;
	}
}

//passing local state solution
class Program{

	static class BinaryTree {
    	public int value;
    	public BinaryTree left = null;
    	public BinaryTree right = null;

    	public BinaryTree(int value) {
      		this.value = value;
    	}
  	}


  	static class TreeInfo {
  		public int diameter;
  		public int height;

  		public TreeInfo(int diameter, int height){
  			this.diameter = diameter;
  			this.height = height;
  		}
  	}

  	public int binaryTreeDiameter(BinaryTree tree){
  		return depth(tree).diameter;
  	}

  	public TreeInfo depth(BinaryTree tree) {
  		if (tree == null) return new TreeInfo(0,0);

  		TreeInfo left = depth(tree.left);
  		TreeInfo right = depth(tree.right);

  		int maxDiameterSoFar = Math.max(left.diameter, right.diameter);
  		int longestPathThroughRoot = left.height + right.height;
  		int currentDiameter = Math.max(longestPathThroughRoot, maxDiameterSoFar);

  		int currentHeight = 1 + Math.max(left.height, right.height);

  		return new TreeInfo(currentDiameter, currentHeight); 
  	}
}