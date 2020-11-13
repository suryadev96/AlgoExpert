/*
number of binary tree topologies are different structure that can be formed with n nodes
n = 2 => two configurations: root with left , root with right
*/
import java.util.*;
class Question{

	public static int numberOfBinaryTreeTopologies(int n){
		if (n == 0)return 1;

		int numberOfTrees = 0;

		for (int leftTreeSize = 0; leftTreeSize < n; leftTreeSize++){
			int rightTreeSize = n - 1 - leftTreeSize;
			int numberOfLeftTrees = numberOfBinaryTreeTopologies(leftTreeSize);
			int numberOfRightTrees = numberOfBinaryTreeTopologies(rightTreeSize);
			numberOfTrees += numberOfLeftTrees*numberOfRightTrees;
		}
		return numberOfTrees;
	}

	public static int numberOfBinaryTreeTopologiesMemo(int n){
		//stores how the number of topologies for each n
		Map<Integer,Integer> cache = new HashMap<Integer,Integer>();

		cache.put(0,1);

		return numberOfBinaryTreeTopologiesMemoUtil(n,cache);
	}

	public static int numberOfBinaryTreeTopologiesMemoUtil(int n, Map<Integer,Integer> cache){
		if (cache.containsKey(n)){
			return cache.get(n);
		}
		int numberOfTrees = 0;
		for (int leftTreeSize = 0; leftTreeSize < n ; leftTreeSize++){
			int rightTreeSize = n - 1 - leftTreeSize;
			int numberOfLeftTrees = numberOfBinaryTreeTopologies(leftTreeSize);
			int numberOfRightTrees = numberOfBinaryTreeTopologies(rightTreeSize);
			numberOfTrees += numberOfLeftTrees*numberOfRightTrees;
		}
		cache.put(n,numberOfTrees);
		return numberOfTrees;
	}

	public static int numberOfBinaryTreeTopologiesBottomUp(int n){
		//stores the number of topologies for each n (here index = n)
		List<Integer> cache = new ArrayList<Integer>();
		cache.add(1);

		for (int m=1;m<=n;m++){
			int numberOfTrees = 0;
			for (int leftTreeSize = 0; leftTreeSize < m ; leftTreeSize++){
				int rightTreeSize = m - 1 - leftTreeSize;
				int numberOfLeftTrees = cache.get(leftTreeSize);
				int numberOfRightTrees = cache.get(rightTreeSize);
				numberOfTrees += numberOfLeftTrees * numberOfRightTrees;
			}
			cache.add(numberOfTrees);
		}
		return cache.get(n);
	}

	public static void main(String[] args){
		int n = 5;
		int topologies = numberOfBinaryTreeTopologies(n);
		System.out.println(topologies);
		topologies = numberOfBinaryTreeTopologiesMemo(n);
		System.out.println(topologies);
		topologies = numberOfBinaryTreeTopologiesBottomUp(n);
		System.out.println(topologies);
	}
}

//my solution:
import java.util.*;

class Program {
  public static int numberOfBinaryTreeTopologies(int n) {
    int[] dp = new int[n+1];
		dp[0] = 1;
		for (int k=1;k<=n;k++){
			for (int left=0;left<k;left++){
				int right = k-left-1;
				dp[k] += dp[left]*dp[right];
			}
		}
    return dp[n];
  }
}
