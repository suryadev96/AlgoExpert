/*
https://www.geeksforgeeks.org/minimum-edges-to-be-added-in-a-directed-graph-so-that-any-node-can-be-reachable-from-a-given-node/
First, let’s mark all the vertices reachable from X as good, using a simple DFS. Then, for each bad vertex (vertices which are not reachable from X) v, 
count the number of bad vertices reachable from v (it also can be done by simple DFS). Let this number be cntv. 
Now, iterate over all bad vertices in non-increasing order of cntv. 
For the current bad vertex v, if it is still not marked as good, run a DFS from it, marking all the reachable vertices as good, and increase the answer by 1
*/
import java.util.*;

class AirportNode{

	String airport;
	List<String> connections;

	//isreachable from starting airport
	boolean isReachable;

	//these are the airports that are unreachable from the starting airport but connected with the current airportNode
	//this is a subset of connections
	List<String> unreachableConnections;


	public AirportNode(String airport){
		this.airport = airport;
		connections = new ArrayList<String>();
		isReachable = true;
	}

}

class Question{

	public static int airportConnections(List<String> airports, List<List<String>> routes, String startingAirport){
		Map<String,AirportNode> airportGraph = createAirportGraph(airports,routes);
		List<AirportNode> unreachableAirportNodes = getUnreachableAirportNodes(airportGraph,airports,startingAirport);
		countUnreachableConnections(airportGraph,unreachableAirportNodes);
		return markUnreachableConnectionsByAddingConnection(airportGraph,unreachableAirportNodes);
	}

	public static int markUnreachableConnectionsByAddingConnection(Map<String,AirportNode> airportGraph, List<AirportNode> unreachableAirportNodes){

		unreachableAirportNodes.sort((a1,a2)-> a2.unreachableConnections.size() - a1.unreachableConnections.size());

		int numberOfNewConnections = 0;

		for (AirportNode airportNode : unreachableAirportNodes){
			if (airportNode.isReachable)continue;

			numberOfNewConnections++;

			//mark all connections as reachable
			for (String connection : airportNode.unreachableConnections){
				airportGraph.get(connection).isReachable = true;
			}
		}
		return numberOfNewConnections;
	}

	//this step is necessary to count the unreachable connections (from the starting airport) when done dfs from unreachableairportnode
	//u may think why this step is necessary since we have unreachableAiportNodes.connections => sort it in descending order and add the min connections
	//but this solution won't work because think of an unreachableAirportNode which has highest no of connections but are only to the nodes that are reachable from the startingairport
	//on the other hand, there may be one more unreachable airport node which has relatively less no of connections but are mostly to the nodes that are not reachable from the starting airport,
	//so u would want to consider only the size of unreachableconnections (here connection means airportnode)
	public static void countUnreachableConnections(Map<String,AirportNode> airportGraph, List<AirportNode> unreachableAirportNodes){
		for (AirportNode airportNode : unreachableAirportNodes){
			String airport = airportNode.airport;

			//please note that these are unreachable from the starting airport
			List<String> unreachableConnections = new ArrayList<>();

			Set<String> visitedAirports = new HashSet<>();

			dfsUnreachable(airportGraph,airport,unreachableConnections,visitedAirports);

			airportNode.unreachableConnections = unreachableConnections;
		}
	}

	//stores the unreachable connections (airport nodes) from starting node when done dfs from airport
	public static void dfsUnreachable(Map<String,AirportNode> airportGraph, String airport, List<String> unreachableConnections, Set<String> visitedAirports){
		//u dont want to store the connections which are reachable from starting airport
		if (airportGraph.get(airport).isReachable) return;

		if (visitedAirports.contains(airport))return;

		visitedAirports.add(airport);
		unreachableConnections.add(airport);

		List<String> connections = airportGraph.get(airport).connections;

		for (String connection : connections){
			dfsUnreachable(airportGraph,connection,unreachableConnections,visitedAirports);
		}
	}

	public static List<AirportNode> getUnreachableAirportNodes(Map<String,AirportNode> airportGraph, List<String> airports, String startingAirport){
		//nodes that can be visited from starting airport
		Set<String> visitedAirports = new HashSet<String>();

		dfs(airportGraph,startingAirport,visitedAirports);

		List<AirportNode> unreachableAirportNodes = new ArrayList<>();

		for (String airport : airports){
			if (visitedAirports.contains(airport))continue;
			AirportNode airportNode = airportGraph.get(airport);
			airportNode.isReachable = false;
			unreachableAirportNodes.add(airportNode);
		}
		return unreachableAirportNodes;
	}

	public static void dfs(Map<String,AirportNode> airportGraph, String airport, Set<String> visitedAirports){
		if (visitedAirports.contains(airport)) return;

		visitedAirports.add(airport);
		List<String> connections = airportGraph.get(airport).connections;
		for (String connection : connections){
			dfs(airportGraph,connection,visitedAirports);
		}
	}

	public static Map<String,AirportNode> createAirportGraph(List<String> airports, List<List<String>> routes){
		Map<String,AirportNode> airportGraph = new HashMap<>();
		for (String airport : airports){
			airportGraph.put(airport,new AirportNode(airport));
		}
		for (List<String> route : routes){
			String airport = route.get(0);
			String connection = route.get(1);
			airportGraph.get(airport).connections.add(connection);
		}
		return airportGraph;
	}

	public static void main(String[] args){
		List<String> airports = Arrays.asList(
			"BGI","CDG","DEL","DOH","DSM","EWR","EYW","HND","ICN","JFK","LGA","LHR","ORD","SAN","SFO","SIN","TLV","BUD");

		List<List<String>> routes = new ArrayList<List<String>>();
		routes.add(Arrays.asList("DSM","ORD"));
		routes.add(Arrays.asList("ORD","BGI"));
		routes.add(Arrays.asList("BGI","LGA"));
		routes.add(Arrays.asList("SIN","CDG"));
		routes.add(Arrays.asList("CDG","SIN"));
		routes.add(Arrays.asList("CDG","BUD"));
		routes.add(Arrays.asList("DEL","DOH"));
		routes.add(Arrays.asList("DEL","CDG"));
		routes.add(Arrays.asList("TLV","DEL"));
		routes.add(Arrays.asList("EWR","HND"));
		routes.add(Arrays.asList("HND","ICN"));
		routes.add(Arrays.asList("HND","JFK"));
		routes.add(Arrays.asList("ICN","JFK"));
		routes.add(Arrays.asList("JFK","LGA"));
		routes.add(Arrays.asList("EYW","LHR"));
		routes.add(Arrays.asList("LHR","SFO"));
		routes.add(Arrays.asList("SFO","SAN"));
		routes.add(Arrays.asList("SFO","DSM"));
		routes.add(Arrays.asList("SAN","EYW"));

		String startingAirport = "LGA";

		int minConnections = airportConnections(airports,routes,startingAirport);
		System.out.println(minConnections);
	}
}

//my solution:
import java.util.*;

class BadNode{
	int count;
	String node;
	
	public BadNode(int count, String node){
		this.count = count;
		this.node = node;
	}
}
class Graph{
	
	Map<String,List<String>> graph = new HashMap<>();

	//this set can also be used as visited 
	Set<String> goodSet = new HashSet<>();

	Set<String> visited = new HashSet<>();
	
	List<BadNode> badNodes = new ArrayList<>();
	
	public Graph(List<String> airports, List<List<String>> routes){
		addNodes(airports);
		addEdges(routes);
	}
	
	public void addNodes(List<String> airports){
		for (String airport : airports){
			graph.put(airport,new ArrayList<String>());
		}
	}
	
	public void addEdges(List<List<String>> routes){
		for (List<String> route : routes){
			String src = route.get(0);
			String dst = route.get(1);
			graph.get(src).add(dst);
		}
	}
	
	public int minimumEdges(String start){
		
		//mark all vertices that are reachable from the start as good
		dfs1(start);
		
		//from each bad vertex, count the connections
		for (String airport : graph.keySet()){
			if (!goodSet.contains(airport)){
				visited = new HashSet<>();
				int count = dfs2(airport);
				badNodes.add(new BadNode(count,airport));
			}
		}
		
		Collections.sort(badNodes, (a,b) -> b.count - a.count);
		
		int minConnections = 0;
		
		for (BadNode badNode : badNodes){
			if (!goodSet.contains(badNode.node)){
				minConnections++;
				dfs1(badNode.node); //mark badnode and reachable nodes as good since we added connection
			}
		}
		return minConnections;
	}
	
	//counts the connections from each bad vertex
	public int dfs2(String u){
		int count = 1;
		visited.add(u);
		
		for (String v : graph.get(u)){
			if (!visited.contains(v) && !goodSet.contains(v)){
				count += dfs2(v);
			}
		}
		return count;
	}
	
	public void dfs1(String u){
		goodSet.add(u);
		for (String v : graph.get(u)){
			if (!goodSet.contains(v)){ //goodSet can be used as visited map
				dfs1(v);
			}
		}
	}
}

class Program {
  public static int airportConnections(
    List<String> airports, List<List<String>> routes, String startingAirport) {
    Graph graph = new Graph(airports,routes);
	return graph.minimumEdges(startingAirport);
  }
}
