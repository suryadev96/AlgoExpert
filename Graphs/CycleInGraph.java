/*
edges = [
	[1,3]
	[2,3,4],
	[0],
	[],
	[2,5],
	[]
]

Output:
true 
There are multiple cycles in the graph
0 -> 1 -> 2 -> 0
0 -> 1 -> 4 -> 2 -> 0
1 -> 2 -> 0 -> 1
*/
import java.util.*;

class Question{

	public boolean cycleInGraph(int[][] edges){
		int n = edges.length;

		boolean[] visited = new boolean[n];
		boolean[] recStack = new boolean[n];

		for (int i=0;i<n;i++){
			if (visited[i]) continue;
			boolean containsCycle = hasCycle(i,edges,visited,recStack);
			if (containsCycle) return true;
		}
		return false;
	}

	public boolean hasCycle(int i, int[][] edges, boolean[] visited,  boolean[] recStack){
		visited[i] = true;
		if (recStack[i]) return true;
		recStack[i] = true;

		for (int nei : edges[i]){
			boolean containsCycle = hasCycle(nei, edges, visited, recStack);
			if (containsCycle) return true;
		}
		recStack[i] = false;
		return false;
	}

}

//solution 2 using colors
import java.util.*;

class Question{

	static int WHITE = 0; //not visited
	static int GREY = 1; //visiting
	static int BLACK = 2; //visited

	public boolean cycleInGraph(int[][] edges){
		int n = edges.length;

		int[] colors = new int[n];
		Arrays.fill(colors, WHITE);

		for (int i=0;i<n;i++){
			if (colors[i] == BLACK) continue;
			boolean containsCycle = hasCycle(i,edges,colors);
			if (containsCycle) return true;
		}
		return false;
	}

	public boolean hasCycle(int i, int[][] edges, int[] colors){
		if (colors[i] == GREY) return true;
		colors[i] = GREY;

		for (int nei : edges[i]){
			boolean containsCycle = hasCycle(nei, edges, colors);
			if (containsCycle) return true;
		}
		colors[i] = BLACK;
		return false;
	}

}
