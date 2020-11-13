/*
From wikepedia, the concept of six degrees of seperation is the idea that all people are six or fewer , social connections
away from each other.

Write a function that returns the name of the person 
*/

import java.util.*;

class Graph{
	
	Map<String,String[]> graph;
	
	Set<String> visited;
	
	public Graph(Map<String,String[]> friendsList){
		this.graph = friendsList;
	}
	
	public int bfs(String source){
		visited = new HashSet<>();
		Queue<String> q = new LinkedList<>();
		q.add(source);
		
		int level = 0;
		
		int countUnknowns = 0;
		
		while (!q.isEmpty()){
			
			int size = q.size();
			
			if (level > 6){
				countUnknowns += size;
			}
			
			for (int i=0;i<size;i++){
				
				String u = q.poll();
				
				for (String nei : graph.get(u)){
					if (!visited.contains(nei)){
						q.add(nei);
						visited.add(nei);
					}
				}
			}
			level++;
		}
		for (String friend : graph.keySet()){
			if (!visited.contains(friend)){
				countUnknowns++;
			}
		}
		return countUnknowns;
	}
	
}

class Program {

 	public static String degreesOfSeparation(
      	Map<String, String[]> friendsLists, String personOne, String personTwo) {
   		Graph graph = new Graph(friendsLists);
		int countOne = graph.bfs(personOne);
		int countTwo = graph.bfs(personTwo);
		if (countOne == countTwo) return "";
		return countOne < countTwo ? personOne : personTwo;
 	}
}
