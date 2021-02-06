/*
array representing the amounts of time that specific queries take to execute
Only 1 query can be executed at a time, but queries can be executed in any order

A Query's waiting time is defined as amount of time that it must wait before its execution starts.

array = [1,4,5]

return minimum amount of total waiting time for all of the queries

if queries were executed in the order of [5,1,4] 
then (0) + (5) + (5+1) = 11.


Sample Input 
queries = [3,2,1,2,6]
Output = 17
*/
import java.util.*;

class Question {

	public int minimumWaitingTime(int[] queries){
		Arrays.sort(queries);

		int totalWaitingTime = 0;
		for (int i=0; i<queries.length;i++){
			int duration = queries[i];
			int queriesLeft = queries.length - (i+1);
			totalWaitingTime += duration * queriesLeft;
		}
		return totalWaitingTime;
	}

}