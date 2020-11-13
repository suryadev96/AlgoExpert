import java.util.*;

class WaterArea{

	public static int waterArea(int[] heights){
		//array which captures the water trapped above it
		int[] maxes = new int[heights.length];

		int leftMax = 0;

		//after this loop, maxes will have the left tall piller for every pillar
		for (int i=0;i<heights.length;i++){
			int height = heights[i];
			maxes[i] = leftMax;
			leftMax = Math.max(leftMax,height);
		}

		int rightMax = 0;
		for (int i=heights.length-1;i>=0;i--){
			//minHeight is the min of left max pillar and right max pillar
			int minHeight = Math.min(rightMax, maxes[i]);
			int height = heights[i];
			if (minHeight > height){
				maxes[i] = minHeight - height;
			} else{
				maxes[i] = 0;
			}
			rightMax = Math.max(rightMax,height);
		}

		int total = 0;
		for (int i=0; i<heights.length;i++){
			total += maxes[i];
		}
		return total;
	}

	 public static int waterArea(int[] heights) {
		int n = heights.length;
		if (n == 0 || n == 1) return 0;
    	int[] left = new int[n];
		left[0] = heights[0];
		for (int i=1;i<n;i++){
			left[i] = Math.max(left[i-1],heights[i]);
		}
		int[] right = new int[n];
		right[n-1] = heights[n-1];
		for (int i=n-2;i>=0;i--){
			right[i] = Math.max(right[i+1],heights[i]);
		}
		int waterArea = 0;
		for (int i=0;i<n;i++){
			waterArea += Math.min(left[i],right[i]) - heights[i];
		}
		return waterArea;
  	}

  	//using 2 pointer approach
  	public static int waterArea(int[] heights){
  		if (heights.length == 0) return 0;
  		int leftIdx = 0;
  		int rightIdx = heights.length-1;

  		int leftMax = heights[leftIdx];
  		int rightMax = heights[rightIdx];

  		int waterArea = 0;

  		while (leftIdx < rightIdx){
  			//surface area of the next bar is limited by the bar on the leftside
  			if (heights[leftIdx] < heights[rightIdx]){
  				leftIdx++;
  				leftMax = Math.max(leftMax,heights[leftIdx]);
  				waterArea += leftMax - heights[leftIdx];
  			}else{ //surface area of the next bar is limited by the bar on the right side
  				rightIdx--;
  				rightMax = Math.max(rightMax,heights[rightIdx]);
  				waterArea += rightMax - heights[rightIdx];
  			}
  		}
  		return waterArea;
  	}
}