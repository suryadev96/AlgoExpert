import java.util.*;

class Program {
  public static int kadanesAlgorithm(int[] array) {
    int maxEndingHere = array[0];
		int maxSoFar = array[0];
		for (int i=1;i<array.length;i++){
			int num = array[i];
			maxEndingHere = Math.max(num, num + maxEndingHere);
			maxSoFar = Math.max(maxSoFar, maxEndingHere);
		}
    return maxSoFar;
  }
}

//my solution
import java.util.*;
class Program {
  public static int kadanesAlgorithm(int[] array) {
		int sum = 0;
		int max = Integer.MIN_VALUE;
    for (int i=0;i<array.length;i++){
			sum += array[i];
			if (sum > max){
				max = sum;
			}
			if (sum < 0){
				sum = 0;
			}
		}
    return max;
  }
}
