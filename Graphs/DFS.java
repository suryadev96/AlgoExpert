import java.util.*;

class Node{
	String name;
	List<Node> children = new ArrayList<Node>();

	public Node(String name){
		this.name = name;
	}

	//O(v + e)time | O(v) time
	//pre order traversal
	public List<String> depthFirstSearch(List<String> array){
		array.add(this.name);
		for (int i=0;i<children.size();i++){
			children.get(i).depthFirstSearch(array);
		}
		return array;
	}

	public Node addChild(String name){
		Node child = new Node(name);
		children.add(child);
		return this;
	}
}

class Question{

	public static void main(String[] args){
		Node root = new Node("A");
		root.addChild("B").addChild("C").addChild("D");
		root.children.get(0).addChild("E").addChild("F");
		root.children.get(0).children.get(1).addChild("I").addChild("J");
		root.children.get(2).addChild("G").addChild("H");
		root.children.get(2).children.get(0).addChild("K");

		List<String> traversal = new ArrayList<>();
		root.depthFirstSearch(traversal);
		printList(traversal);
	}

	public static void printList(List<String> traversal){
		for (String s : traversal){
			System.out.print(s + " ");
		}
	}
}
/*
A B E F I J C D G K H 
*/