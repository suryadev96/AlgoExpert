/*
You're given a 2D array containing 0s and 1s, 0 - free space; 1 - obstacle. Think of array as grid-shaped graph.
You're given four inputs : startRow, startCol, endRow, endCol representing startNode and endNode in the graph

Write a function that finds the shortest path using the A* Search ALgorithm

The shortest path should be returned as an array of node positions, where each node position [row,col].
Node positions should be ordered from startNode to endNode

Note:
1. from each node, travel four directions
2. distance between all neighbouring nodes in the graph is 1
3. The startNode, endNode are in empty spaces
4. There will be atmost 1 shortest path from startNode to endNode

Input:
startRow = 0
startCol = 1
endRow = 4
endCol = 3
graph = [
	[0,0,0,0,0],
	[0,1,1,1,0],
	[0,0,0,0,0],
	[1,0,1,1,1],
	[0,0,0,0,0]
]

Output:

[[0,1], [0,0], [1,0], [2,0], [2,1], [3,1], [4,1], [4,2], [4,3]]

//The shortestpath 

[
	[., ., 0, 0, 0],
	[., 1, 1, 1, 0],
	[., ., 0, 0, 0],
	[1, ., 1, 1, 1],
	[0, ., ., ., 0]
]
*/

/*
SOLUTION:

A* algorithm is known as informed search algorithm. we use something known as heuristic to try to determine which node we should visit next
Heuristic is pretty much an educated guess that we try to make about how far away a certain node or a certain position is from the end node

1. How long or how much distance has it taken us to get to the current position
2. How long do we think or guess that it's gonna take to get to the end position

different type of heuristic formulas

THe goal of A* is to visit as few nodes as possible 

The consideration for the next visiting nodes based on 3 factors: these factors are called as scores or costs associated with every single node
H , G, F

H - Heuristic score (how far is the end node from the H node)
G - Current shortest distance from the start node to this given node
F - G + H

Node is chosen based on the current smallest F score

Manhattan Distance is Heuristic we want to use
also called L distance 

Dijkstras Algo will expand its search in a flower like pattern ; kind of expand equally in all of the available directions
A* Algo will expand like a line or an arrow where its expanding directly towards the end node. This is due to the bias or the heuristic
This additional value H biases the algorithm and makes it favor going in a direction that is closer in terms of our heuristic formula to the end node

This typically makes A* run much faster than Dijkstra algo
*/
import java.util.*;

class Node {

	String id;
	int row;
	int col;
	int value; //free or obstacle
	int gScore; //distance from start
	int fScore; //estimated distance to the end based on heuristic formula + gScore
	Node previous;

	public Node(int row, int col, int value){
		this.id = String.valueOf(row) + "-" + String.valueOf(col);
		this.row = row;
		this.col = col;
		this.value = value;
		this.gScore = Integer.MAX_VALUE;
		this.fScore = Integer.MAX_VALUE;
		this.previous = null;
	}
}

class MinHeap {

	List<Node> heap = new ArrayList<>();
	Map<String,Integer> nodePositions = new HashMap<>(); //useful for decrease key operation

	public MinHeap(List<Node> array){
		for (int i=0;i<array.size();i++) {
			Node node = array.get(i);
			nodePositions.put(node.id, i);
		}
		heap = buildHeap(array);
	}

	public List<Node> buildHeap(List<Node> array){
		int firstParentIdx = (array.size() - 2) / 2;
		for (int currentIdx = firstParentIdx; currentIdx >= 0; currentIdx--) {
			siftDown(currentIdx, array.size()-1, array);
		}
		return array;
	}

	public boolean isEmpty(){
		return heap.size() == 0;
	}

	public void siftDown(int currentIdx, int endIdx, List<Node> heap){
		int childOneIdx = currentIdx * 2 + 1;

		while (childOneIdx <= endIdx) {

			int childTwoIdx = currentIdx * 2 + 2 <= endIdx ? currentIdx * 2 + 2 : -1;

			int idxToSwap;

			if (childTwoIdx != -1 && heap.get(childTwoIdx).fScore < heap.get(childOneIdx).fScore ){
				idxToSwap = childTwoIdx;
			}else{
				idxToSwap = childOneIdx;
			}

			if (heap.get(idxToSwap).fScore < heap.get(currentIdx).fScore) {
				swap(currentIdx, idxToSwap);
				currentIdx = idxToSwap;
				childOneIdx = 2 * currentIdx + 1;
			} else {
				return;
			}
		}
	}

	public void siftUp(int currentIdx){
		int parentIdx = (currentIdx - 1) / 2;

		while (currentIdx > 0 && heap.get(currentIdx).fScore < heap.get(parentIdx).fScore) {
			swap(currentIdx, parentIdx);
			currentIdx = parentIdx;
			parentIdx = (currentIdx - 1) / 2;
		}
	}

	public Node remove(){
		if (isEmpty()) return null;

		swap(0, heap.size()-1);
		Node lastNode = heap.get(heap.size()-1);
		heap.remove(heap.size()-1);
		nodePositions.remove(lastNode.id);
		siftDown(0, heap.size()-1, heap);
		return lastNode;
	}

	public void insert(Node node){
		heap.add(node);
		nodePositions.put(node.id, heap.size() - 1);
		siftUp(heap.size()-1);
	}

	public void swap(int i, int j){
		nodePositions.put(heap.get(i).id, j);
		nodePositions.put(heap.get(j).id, i);
		Node temp = heap.get(i);
		heap.set(i, heap.get(j));
		heap.set(j, temp);
	}

	public boolean containsNode(Node node){
		return nodePositions.containsKey(node.id);
	}

	public void update(Node node){
		siftUp(nodePositions.get(node.id));
	}

}

class Program {

	public int[][] aStarAlgorithm(int startRow, int startCol, int endRow, int endCol, int[][] graph){
		List<List<Node>> nodes = intializeNodes(graph);

		Node startNode = nodes.get(startRow).get(startCol);
		Node endNode = nodes.get(endRow).get(endCol);

		startNode.gScore = 0;
		startNode.fScore = calculateManhattanDistance(startNode, endNode);

		List<Node> nodesToVisitList = new ArrayList<>();
		nodesToVisitList.add(startNode);

		MinHeap heap = new MinHeap(nodesToVisitList);

		while (!heap.isEmpty()) {

			Node currentMinDistanceNode = heap.remove();

			if (currentMinDistanceNode == endNode) {
				break;
			}

			List<Node> neighbours = getNeighbouringNodes(currentMinDistanceNode, nodes);

			for (Node nei : neighbours) {
				//if obstacle
				if (nei.value == 1){
					continue;
				}

				//check for already visited condition
				if (currentMinDistanceNode.gScore + 1 >= nei.gScore) continue;

				nei.previous = currentMinDistanceNode;
				nei.gScore = currentMinDistanceNode.gScore + 1;
				nei.fScore = nei.gScore + calculateManhattanDistance(nei, endNode);

				if (!heap.containsNode(nei)) {
					heap.insert(nei);
				}else {
					heap.update(nei);
				}
			}
		}
		return reconstructPath(endNode);
	}

	public int[][] reconstructPath(Node endNode){
		if (endNode.previous == null) return new int[][]{};

		Node currentNode = endNode;

		List<List<Integer>> path = new ArrayList<>();

		while (currentNode != null) {
			List<Integer> nodeData = new ArrayList<>();
			nodeData.add(currentNode.row);
			nodeData.add(currentNode.col);
			path.add(nodeData);
			currentNode = currentNode.previous;
		}

		int[][] res = new int[path.size()][2];
		for (int i=0;i<res.length;i++){
			res[i][0] = path.get(res.length-1-i).get(0);
			res[i][1] = path.get(res.length-1-i).get(1);
		}
		return res;
	}

	public List<List<Node>> intializeNodes(int[][] graph){
		List<List<Node>> nodes = new ArrayList<>();

		for(int i=0;i<graph.length;i++){
			List<Node> nodeList = new ArrayList<>();
			nodes.add(nodeList);
			for (int j=0;j<graph[i].length;j++){
				nodes.get(i).add(new Node(i,j,graph[i][j]));
			}
		} 
		return nodes;
	}

	public int calculateManhattanDistance(Node currentNode, Node endNode){
		int currentRow = currentNode.row;
		int currentCol = currentNode.col;

		int endRow = endNode.row;
		int endCol = endNode.col;

		return Math.abs(currentRow - endRow) + Math.abs( currentCol - endCol);
	}

	public List<Node> getNeighbouringNodes(Node node, List<List<Node>> nodes){
		List<Node> neighbours = new ArrayList<>();

		int m = nodes.size();
		int n = nodes.get(0).size();

		int[][] dir = {{-1,0},{1,0},{0,1},{0,-1}};

		for (int[] d : dir) {
			int row = node.row + d[0];
			int col = node.col + d[1];
			if (isSafe(row,col,m,n)) {
				neighbours.add(nodes.get(row).get(col));
			}
		}
		return neighbours;
	}

	public boolean isSafe(int row , int col, int m, int n){
		return row < m && row >= 0 && col < n && col >= 0;
	}
}
