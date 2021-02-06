/*
k - no of workers
array - duration of tasks
each worker must complete 2 unique tasks and can only work one task at a time
Tasks - 2*k
All tasks are independent
All tasks are executed in parallel by the workers

Time taken to complete all tasks - longest pair of tasks

Write a function to return optimal assignment of tasks to each worker such that tasks are completed as fast as possible.
return list of pairs . each pair is tasks taken by 1 worker

k = 3
tasks = [1,3,5,3,1,4]

[
	[0,2]  // tasks[0] = 1, tasks[2] = 5 | 1 + 5 = 6
	[4,5]  // tasks[4] = 1, tasks[5] = 4 | 1 + 4 = 5
	[1,3]  // tasks[1] = 3, tasks[3] = 3 | 3 + 3 = 6 
]
Math.max(6,5,6) = 6

Sort the array and pair the largest and smallest 
*/
import java.util.*;

class Question{

	public List<List<Integer>> taskAssignment(int k, List<Integer> tasks){
		List<List<Integer>> result = new ArrayList<>();
		Map<Integer, List<Integer>> taskDurationToIndices = getTaskDurationsToIndices(tasks);

		Collections.sort(tasks);

		for (int i=0;i<k;i++){

			int task1Duration = tasks.get(i);
			List<Integer> indicesWithTask1Duration = taskDurationToIndices.get(task1Duration);
			int task1Index = indicesWithTask1Duration.remove(indicesWithTask1Duration.size()-1);

			int task2Duration = tasks.get(tasks.size() - 1 - i);
			List<Integer> indicesWithTask2Duration = taskDurationToIndices.get(task2Duration);
			int task2Index = indicesWithTask2Duration.remove(indicesWithTask2Duration.size()-1);

			List<Integer> pair = new ArrayList<>();
			pair.add(task1Index);
			pair.add(task2Index);

			result.add(pair);
		}
		return result;
	}

	public Map<Integer,List<Integer>> getTaskDurationsToIndices(List<Integer> tasks){
		Map<Integer,List<Integer>> map = new HashMap<>();

		for (int i=0;i<tasks.size();i++){
			map.computeIfAbsent(tasks.get(i), k -> new ArrayList<>()).add(i);
		}
		return map;
	}

}