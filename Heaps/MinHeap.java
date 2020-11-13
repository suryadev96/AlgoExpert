import java.util.*;

class MinHeap{

	List<Integer> heap = new ArrayList<Integer>();

	public MinHeap(List<Integer> array){
		heap = buildHeap(array);
	}

	public List<Integer> buildHeap(List<Integer> array){
		int firstParentIdx = (array.size()-2)/2;
		for (int currentIdx = firstParentIdx; currentIdx >=0 ; currentIdx--){
			siftDown(currentIdx,array.size()-1,array);
		}
		return array;
	}

	public void siftDown(int currentIdx, int endIdx, List<Integer> heap){
		int childOneIdx = currentIdx*2 + 1;
		while(childOneIdx <= endIdx){
			int childTwoIdx = currentIdx*2 + 2 <= endIdx ? currentIdx*2 + 2 : -1;
			int idxToSwap;
			if (childTwoIdx != -1 && heap.get(childTwoIdx) < heap.get(childOneIdx)){
				idxToSwap = childTwoIdx;
			}else{
				idxToSwap = childOneIdx;
			}
			if (heap.get(idxToSwap) < heap.get(currentIdx)){
				swap(currentIdx,idxToSwap,heap);
				currentIdx = idxToSwap;
				childOneIdx = currentIdx*2 + 1;
			}else{
				return;
			}
		}
	}

	public void siftUp(int currentIdx, List<Integer> heap){
		int parentIdx = (currentIdx - 1)/2;
		while (currentIdx > 0 && heap.get(currentIdx) < heap.get(parentIdx)){
			swap(currentIdx,parentIdx,heap);
			currentIdx = parentIdx;
			parentIdx = (currentIdx - 1)/2;
		}
	}

	public int peek(){
		return heap.get(0);
	}

	public int remove(){
		swap(0,heap.size()-1,heap);
		int valueToRemove = heap.get(heap.size()-1);
		heap.remove(heap.size()-1);
		siftDown(0,heap.size()-1,heap);
		return valueToRemove;
	}

	public void insert(Integer value){
		heap.add(value);
		siftUp(heap.size()-1,heap);
	}

	public void swap(int i, int j, List<Integer> heap){
		Integer temp = heap.get(j);
		heap.set(j,heap.get(i));
		heap.set(i,temp);
	}

}

class Question{

	public static void main(String[] args){
		List<Integer> heapArr = new ArrayList<Integer>(Arrays.asList(9,8,7,6,5,4,3,2,1));
		MinHeap heap = new MinHeap(heapArr);
		heap.insert(76);
		heap.remove();
		System.out.println(heap.peek());
		heap.remove();
		System.out.println(heap.peek());
		heap.insert(87);
	}
}