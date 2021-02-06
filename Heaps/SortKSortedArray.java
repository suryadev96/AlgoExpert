/*
Integer K, K-sorted array of integers 
K-sorted array is a partially sorted array in which all elements are atmost k positions away from their sorted position.

array = [3,2,1,5,4,7,6,5]
k = 3

[1,2,3,4,5,5,6,7]
*/
import java.util.*;

class MinHeap {

    List<Integer> heap = new ArrayList<Integer>();

    public MinHeap(List<Integer> array) {
        heap = buildHeap(array);
    }

    public List<Integer> buildHeap(List<Integer> array) {
        int firstParentIdx = (array.size() - 2) / 2;
        for (int currentIdx = firstParentIdx; currentIdx >= 0; currentIdx--) {
            siftDown(currentIdx, array.size() - 1, array);
        }
        return array;
    }

    public void siftDown(int currentIdx, int endIdx, List<Integer> heap) {
        int childOneIdx = currentIdx * 2 + 1;
        while (childOneIdx <= endIdx) {
            int childTwoIdx = currentIdx * 2 + 2 <= endIdx ? currentIdx * 2 + 2 : -1;
            int idxToSwap;
            if (childTwoIdx != -1 && heap.get(childTwoIdx) < heap.get(childOneIdx)) {
                idxToSwap = childTwoIdx;
            } else {
                idxToSwap = childOneIdx;
            }
            if (heap.get(idxToSwap) < heap.get(currentIdx)) {
                swap(currentIdx, idxToSwap, heap);
                currentIdx = idxToSwap;
                childOneIdx = currentIdx * 2 + 1;
            } else {
                return;
            }
        }
    }

    public void siftUp(int currentIdx, List<Integer> heap) {
        int parentIdx = (currentIdx - 1) / 2;
        while (currentIdx > 0 && heap.get(currentIdx) < heap.get(parentIdx)) {
            swap(currentIdx, parentIdx, heap);
            currentIdx = parentIdx;
            parentIdx = (currentIdx - 1) / 2;
        }
    }

    public int peek() {
        return heap.get(0);
    }

    public int remove() {
        swap(0, heap.size() - 1, heap);
        int valueToRemove = heap.get(heap.size() - 1);
        heap.remove(heap.size() - 1);
        siftDown(0, heap.size() - 1, heap);
        return valueToRemove;
    }

    public void insert(Integer value) {
        heap.add(value);
        siftUp(heap.size() - 1, heap);
    }

    public void swap(int i, int j, List<Integer> heap) {
        Integer temp = heap.get(j);
        heap.set(j, heap.get(i));
        heap.set(i, temp);
    }

    public boolean isEmpty(){
    	return heap.size() == 0;
    }

}

//Time complexity O(n * logk)
//Space complexity O(k)
class Question{

	public int[] sortKSortedArray(int[] array, int k){
		List<Integer> heapValues = new ArrayList<>();
		for (int i=0;i<Math.min(k+1,array.length); i++){
			heapValues.add(array[i]);
		}

		MinHeap heap = new MinHeap(heapValues);

		int sortedIndex = 0;

		for (int i=k+1;i<array.length;i++){
			int min = heap.remove();
			array[sortedIndex] = min;
			sortedIndex++;

			heap.insert(array[i]);
		}

		while (!heap.isEmpty()){
			int min = heap.remove();
			array[sortedIndex] = min;
			sortedIndex++;
		}
		return array;
	}

}