/*
start = 0
edges = [
	[[1,7]],
	[[2,6], [3,20], [4,3]],
	[[3,14]],
	[[4,2]],
	[],
	[]
]

Output:
[0,7,13,27,10,-1]
*/
import java.util.*;

class Item {
	int vertex;
	int distance;

	public Item(int vertex, int distance){
		this.vertex = vertex;
		this.distance = distance;
	}
}

class MinHeap {

	//we need a vertex map to quickly look up for a vertex location in the heap ; useful for decreaseKey operation
	Map<Integer,Integer> vertexMap = new HashMap<>();

	List<Item> heap = new ArrayList<>();

	public MinHeap(List<Item> array){
		for (int i=0;i<array.size();i++){
			Item item = array.get(i);
			vertexMap.put(item.vertex, item.vertex);
		}
		heap = buildHeap(array);
	}

	public List<Item> buildHeap(List<Item> array){
		int firstParentIdx = (array.size() - 2) / 2;
		for (int currentIdx = firstParentIdx; currentIdx >= 0; currentIdx--){
			siftDown(currentIdx, array.size()-1, array);
		}
		return array;
	}

	public void siftDown(int currentIdx, int endIdx, List<Item> heap){
		int childOneIdx = currentIdx * 2 + 1;

		while (childOneIdx <= endIdx) {

			int childTwoIdx = currentIdx * 2 + 2 <= endIdx ? currentIdx * 2 + 2 : -1;

			int idxToSwap;

			if (childTwoIdx != -1 && heap.get(childTwoIdx).distance < heap.get(childOneIdx).distance ){
				idxToSwap = childTwoIdx;
			}else{
				idxToSwap = childOneIdx;
			}

			if (heap.get(idxToSwap).distance < heap.get(currentIdx).distance) {
				swap(currentIdx, idxToSwap);
				currentIdx = idxToSwap;
				childOneIdx = 2 * currentIdx + 1;
			} else {
				return;
			}
		}
	}

	public void swap(int i, int j){
		vertexMap.put(heap.get(i).vertex, j);
		vertexMap.put(heap.get(j).vertex, i);
		Item temp = heap.get(i);
		heap.set(i, heap.get(j));
		heap.set(j, temp);
	}

	public void update(int vertex, int value){
		heap.set(vertexMap.get(vertex), new Item(vertex,value));
		siftUp(vertexMap.get(vertex));
	}

	public void siftUp(int currentIdx){
		int parentIdx = (currentIdx - 1) / 2;

		while (currentIdx > 0 && heap.get(currentIdx).distance < heap.get(parentIdx).distance) {
			swap(currentIdx, parentIdx);
			currentIdx = parentIdx;
			parentIdx = (currentIdx - 1) / 2;
		}
	}

	public boolean isEmpty(){
		return heap.size() == 0;
	}

	public Item remove(){
		if (isEmpty()) return null;

		swap(0, heap.size()-1);
		Item lastItem = heap.get(heap.size()-1);
		heap.remove(heap.size()-1);
		vertexMap.remove(lastItem.vertex);
		siftDown(0, heap.size()-1, heap);
		return new Item(lastItem.vertex, lastItem.distance);
	}

}

class Program{

	public int[] dijkstrasAlgorithm(int start, int[][][] edges){

		int n = edges.length;

		int[] minDistances = new int[n];
		Arrays.fill(minDistances, Integer.MAX_VALUE);
		minDistances[start] = 0;

		List<Item> minDistancePairs = new ArrayList<>();
		for (int i=0;i<n;i++){
			Item item = new Item(i, Integer.MAX_VALUE);
			minDistancePairs.add(item);
		}

		MinHeap heap = new MinHeap(minDistancePairs);
		heap.update(start, 0);

		while (!heap.isEmpty()) {
			Item heapItem = heap.remove();
			int vertex = heapItem.vertex;
			int currentMinDistance = heapItem.distance;

			if (currentMinDistance == Integer.MAX_VALUE) break;

			for (int[] edge : edges[vertex]) {
				int destination = edge[0];
				int distanceToDestination = edge[1];
				int newPathDistance = currentMinDistance + distanceToDestination;
				int currentDestinationDistance = minDistances[destination];

				if (newPathDistance < currentDestinationDistance) {
					minDistances[destination] = newPathDistance;
					heap.update(destination, newPathDistance);
				}
			}
		}

		int[] result = new int[n];
		for (int i=0;i<n;i++){
			if (minDistances[i] == Integer.MAX_VALUE) {
				result[i] = -1;
			}else{
				result[i] = minDistances[i];
			}
		}
		return result;
	}
}