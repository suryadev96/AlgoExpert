import java.util.*;

/*
each item holds two integer values; first one is cost of the item and second one is it's weight
you are given the maximum capacity of knapsack . your goal is to fit items in knapsack by maximizing the value

function should return the maximum combined value of items and the indices of the items picked
*/
class Knapsack{

	public static List<List<Integer>> knapsackProblem(int[][] items, int capacity){
		int[][] knapsackValues = new int[items.length + 1][capacity + 1];

		//no need to set the base cases because initializing the 2d array will set the values to zero
		//here in this case if u dont pick any item, and for all the values of capacity ; the value is zero
		//for (int c=0; c <= capacity;c++){
			//knapsackValues[0][c] = 0;
		//}

		for (int i=1;i<=items.length;i++){
			int currentWeight = items[i-1][1];
			int currentValue = items[i-1][0];

			for (int c =0; c<= capacity;c++){
				if (currentWeight > c){ //u can't pick the current item as weight of item is greater than capacity of knapsack
					knapsackValues[i][c] = knapsackValues[i-1][c];
				} else{ //u can chose to not pick the current item or pick the current item
					knapsackValues[i][c] = Math.max(knapsackValues[i-1][c], knapsackValues[i-1][c- currentWeight]+ currentValue);
				}
			}
		}
		return getKnapsackItems(knapsackValues, items, knapsackValues[items.length][capacity]);
	}

	public static List<List<Integer>> getKnapsackItems(int[][] knapsackValues, int[][] items, int value){

		List<List<Integer>> sequence = new ArrayList<List<Integer>>();

		List<Integer> totalValue = new ArrayList<Integer>();
		totalValue.add(value);
		sequence.add(totalValue);
		sequence.add(new ArrayList<Integer>());

		int i = knapsackValues.length-1;
		int c = knapsackValues[0].length -1;

		//until items are greater than 0; if u dont have any items; then u canot pick anything
		while(i > 0){
			//means u dont pick the current item
			if (knapsackValues[i][c] == knapsackValues[i-1][c]){
				i--;
			} else{ //means u picked the current item; add the index of current item 
				sequence.get(1).add(0,i-1);
				c -= items[i-1][1];
				i--;
			}
			if (c == 0){ //if capacity becomes zero. u cannot pick any more items
				break;
			}
		}
		return sequence;
	}

	public static void main(String[] args){
		int[][] items = {{1,2},{4,3},{5,6},{6,7}};
		int capacity = 10;
		List<List<Integer>> knapsack = knapsackProblem(items,capacity);
		System.out.println(knapsack); //first index contains maximum value ; second index contains items picked
	}
}
/*
[[10], [1, 3]]
*/
import java.util.*;

class Program {
  public static List<List<Integer>> knapsackProblem(int[][] items, int capacity) {
    // Write your code here.
		int[][] knapsack = new int[items.length+1][capacity+1];
		
		//if 0 items => no value
		//if 0 capacity => no value
		
		for (int i=1;i<=items.length;i++){
			int currentValue = items[i-1][0];
			int currentWeight = items[i-1][1];
			for (int j=0;j<=capacity;j++){
				if (currentWeight > j){
					knapsack[i][j] = knapsack[i-1][j];
				}else{
					knapsack[i][j] = Math.max(knapsack[i-1][j], knapsack[i-1][j-currentWeight] + currentValue);
				}
			}
		}
 		return getKnapsackItems(items,knapsack,knapsack[items.length][capacity]);
  }
	
	public static List<List<Integer>> getKnapsackItems(int[][] items, int[][] dp, int totalValue){
		int i = dp.length-1;
		int j = dp[0].length-1;
		
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		result.add(Arrays.asList(totalValue));
		result.add(new ArrayList<Integer>());
		while (i != 0){
			if (dp[i][j] == dp[i-1][j]){
				i--;
			}else{
				result.get(1).add(0,i-1);
				j -= items[i-1][1];
				i--;
			}
			if (j == 0) break;
		}
		return result;
	}
}
