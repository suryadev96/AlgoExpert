import java.util.Arrays;
class MinNoOfJumps{

	public static int minNoOfJumps(int[] array){
		if (array.length == 1){
			return 0;
		}
		int jumps = 0;
		int maxReach = array[0];
		int steps = array[0];

		for (int i = 1; i< array.length -1 ;i++){
			maxReach = Math.max(maxReach, i + array[i]);
			steps--;

			if (steps == 0){
				jumps++;
				steps = maxReach - i;
			}
		}
		return jumps+1;
	}

	//DP solution
	public static int minNoOfJumps(int[] array){
		int[] jumps = new int[array.length];
		Arrays.fill(jumps, Integer.MAX_VALUE);
		jumps[0] = 0;
		for (int i=1;i<array.length;i++){
			for (int j=0;j<i;j++){
				if (array[j] + j >= i){
					jumps[i] = Math.min(jumps[j] + 1, jumps[i]);
				}
			}
		}
		return jumps[jumps.length - 1];
	}

	public static int minNumberOfJumpsMySolution(int[] array) {
    	if (array.length == 1) return 0;
		
		int minJumps = 1;
		int maxReach = array[0];
		int maxSteps = array[0];
		
		for (int i=1;i<array.length;i++){
			if (i > maxSteps){
				maxSteps = maxReach;
				minJumps++;
			}
			maxReach = Math.max(maxReach,i + array[i]);
		}
		return minJumps;
  	}

}