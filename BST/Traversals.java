import java.util.*;

class Program {
  public static List<Integer> inOrderTraverse(BST tree, List<Integer> array) {
    if (tree != null){
			inOrderTraverse(tree.left,array);
			array.add(tree.value);
			inOrderTraverse(tree.right,array);
		}
		return array;
  }

  public static List<Integer> preOrderTraverse(BST tree, List<Integer> array) {
    if (tree != null){
			array.add(tree.value);
			preOrderTraverse(tree.left,array);
			preOrderTraverse(tree.right,array);
		}
    return array;
  }

  public static List<Integer> postOrderTraverse(BST tree, List<Integer> array) {
    if (tree != null){
			postOrderTraverse(tree.left,array);
			postOrderTraverse(tree.right,array);
			array.add(tree.value);
		}
    return array;
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
