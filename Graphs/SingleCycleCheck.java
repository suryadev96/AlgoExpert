/*
write a function that returns a boolean representing if the jumps in the array forms a single cycle.
A single cycle occurs if starting at any index in the array and following the jumps, every element in the array is visited
exactly once before landing back on the starting index
*/
import java.util.*;

class Question{

	//if there is a cycle present connecting all the nodes, then u can start from any index and should end up at same index
	public static boolean hasSingleCycle(int[] graph){

		int numOfElementsVisited = 0;
		int currentIdx = 0;

		while (numOfElementsVisited < graph.length){
			//if we reached the same index before visiting all the elements because there is only one edge going out from any index
			if (numOfElementsVisited > 0 && currentIdx == 0){
				return false;
			}
			numOfElementsVisited++;
			currentIdx = getNextIdx(currentIdx,graph);
		}
		return currentIdx == 0;
	}

	//getNextIdx identifies the next edge from the currentidx
	//note: there is only one edge going out from any index
	public static int getNextIdx(int currentIdx,int[] graph){
		int jump = graph[currentIdx];
		int nextIdx = (currentIdx + jump)%graph.length;
		return nextIdx >= 0 ? nextIdx : nextIdx + graph.length;
	}

	public static void main(String[] args){

		//graph edges are like
		//0-2
		//1-4
		//2-3
		//3-5
		//4-0
		//5-1
		//cycle path: 0->2->3->5->1->4->0
		int[] graph = {2,3,1,-4,-4,2};

		boolean hasCycle = hasSingleCycle(graph);
		System.out.println(hasCycle);

	}
}