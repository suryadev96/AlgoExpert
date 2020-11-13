/*
Write a function that takes in a non-empty list of non-empty sorted arrays of integers 
return merged list of all arrays
*/
import java.util.*;
class Program {
  	public static List<Integer> mergeSortedArrays(List<List<Integer>> arrays) {
		PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> arrays.get(a[0]).get(a[1]) - arrays.get(b[0]).get(b[1]));
		
		for (int i=0;i<arrays.size();i++){
			pq.offer(new int[]{i,0});
		}
		
		List<Integer> result = new ArrayList<>();
		
		while (!pq.isEmpty()){
			
			int[] el = pq.poll();
			
			result.add(arrays.get(el[0]).get(el[1]));
			
			if (el[1] < arrays.get(el[0]).size()-1){
				el[1]++;
				pq.offer(el);
			}
		}
    	return result;
  	}

  	public static void main(String[] args) {
	  	List<List<Integer>> arrays = new ArrayList<List<Integer>>();
	  	arrays.add(Arrays.asList(1,5,9,21));
	  	arrays.add(Arrays.asList(-1,0));
	  	arrays.add(Arrays.asList(-124,81,121));
		arrays.add(Arrays.asList(3,6,12,20,150));
	  	return mergeSortedArrays(arrays);	
  	}
}
