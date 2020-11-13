/*
return the size of the largest binary search tree contained in it.
*/
class BSTValue{
	int min;
	int max;
	boolean isBST;
	int count;
	
	public BSTValue(int min, int max,int count, boolean isBST){
		this.min = min;
		this.max = max;
		this.isBST = isBST;
		this.count = count;
	}
}
class Program {
	
  static int size;
	
  public static int largestBstSize(BinaryTree tree) {
	size = 0;
    helper(tree);
    return size;
  }
	
	public static BSTValue helper(BinaryTree node){
		if (node == null){
			return new BSTValue(Integer.MAX_VALUE,Integer.MIN_VALUE,0,true);
		}
		
		BSTValue left = helper(node.left);
		BSTValue right = helper(node.right);
		
		if (!left.isBST || !right.isBST) return new BSTValue(-1,-1,0,false);
		
		if (node.value <= left.max || node.value > right.min){
			return new BSTValue(-1,-1,0,false);
		}
		
		int count = left.count + right.count + 1;
		
		int min = left.min;
		if (left.count == 0){
			min = node.value;
		}
		
		int max = right.max;
		if (right.count == 0){
			max = node.value;
		}
		
		size = Math.max(size, count);
		
		return new BSTValue(min,max,count,true);
	}

  // This is an input class. Do not edit.
  static class BinaryTree {
    int value;
    BinaryTree left;
    BinaryTree right;

    public BinaryTree(int value) {
      this.value = value;
      left = null;
      right = null;
    }
  }
}
