/*
you're given a list of aribitray jobs that need to be completed.

jobs = [1,2,3,4]
deps = [[1,2],[1,3],[3,2],[4,2],[4,3]]

A dependency is represented as a pair of jobs where the first job is a prerequisite of the second one.
*/

import java.util.*;

class Graph {

    Map<Integer, List<Integer>> graph;
    Map<Integer, Integer> indegree;

    public Graph(List<Integer> jobs, List<Integer[]> deps) {
        graph = new HashMap<>();
        indegree = new HashMap<>();
        addNodes(jobs);
        addEdges(deps);
    }

    public void addNodes(List<Integer> jobs) {
        for (Integer job : jobs) {
            graph.putIfAbsent(job, new ArrayList<>());
            indegree.put(job, 0);
        }
    }

    public void addEdges(List<Integer[]> deps) {
        for (Integer[] dep : deps) {
            graph.get(dep[0]).add(dep[1]);
            indegree.put(dep[1], indegree.get(dep[1]) + 1);
        }
    }

    public List<Integer> topologicalSort() {
        Queue<Integer> q = new LinkedList<>();
        for (Map.Entry<Integer, Integer> entry : indegree.entrySet()) {
            if (entry.getValue() == 0) {
                q.add(entry.getKey());
            }
        }
        List<Integer> result = new ArrayList<>();
        int jobsCompleted = 0;
        while (!q.isEmpty()) {
            Integer job = q.poll();
            jobsCompleted++;
            result.add(job);
            for (Integer nei : graph.getOrDefault(job, new ArrayList<>())) {
                indegree.put(nei, indegree.get(nei) - 1);
                if (indegree.get(nei) == 0) {
                    q.add(nei);
                }
            }
        }
        if (jobsCompleted == graph.size()) return result;
        return new ArrayList<Integer>();
    }

}

class Program {

    public static List<Integer> topologicalSort(List<Integer> jobs, List<Integer[]> deps) {
        Graph graph = new Graph(jobs, deps);
        return graph.topologicalSort();
    }
}
