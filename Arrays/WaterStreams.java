import java.util.*;

class WaterStreams{

	public static double[] waterfallStreams(double[][] array, int source){

		double[] rowAbove = array[0];

		//we will use -1 to represent water , since 1 is used for a block
		rowAbove[source] = -1;

		for (int row=1;row<array.length;row++){

			double[] currentRow = array[row];

			for (int idx=0; idx < currentRow.length; idx++){

				double valueAbove = rowAbove[idx];

				boolean hasWaterAbove = valueAbove < 0;

				boolean hasBlock = currentRow[idx] == 1.0; //if current cell is block ; then water slipts around

				if (!hasWaterAbove) continue; //if do not has water above ; then continue to next cell

				if (!hasBlock) {
					//if there is no block in the current cell, move the water down
					currentRow[idx] += valueAbove;
					continue;
				}

				double splitWater = valueAbove/2;

				//Move water right
				int rightIdx = idx + 1;
				while (rightIdx < rowAbove.length){
					if (rowAbove[rightIdx] == 1.0){ //if there is a block in the way when it is flowing right
						break;
					}
					if (currentRow[rightIdx] != 1){ //if it has flown right one cell and if there is no block below it
						currentRow[rightIdx] += splitWater;
						break;
					}
					rightIdx++;
				}

				//Move water left;
				int leftIdx = idx - 1;
				while (leftIdx >= 0){
					if (rowAbove[leftIdx] == 1.0){ //if there is a block in the way when it is flowing right
						break;
					}
					if (currentRow[leftIdx] != 1.0){ //if there is no block and if there is no block below us
						currentRow[leftIdx] += splitWater;
						break; //because water will no longer travel left
					}
					leftIdx--;
				}
			}
			rowAbove = currentRow;
		}

		double[] buckets = new double[rowAbove.length];
		for (int i=0; i< rowAbove.length; i++){
			double water = rowAbove[i];
			if (water == 0 ){
				buckets[i] = water;
			}else{
				buckets[i] = water * -100;
			}
		}
		return buckets;
	}


	public static void main(String[] args) {
		double[][] array = {
			{0,0,0,0,0,0,0},
			{1,0,0,0,0,0,0},
			{0,0,1,1,1,0,0},
			{0,0,0,0,0,0,0},
			{1,1,1,0,0,1,0},
			{0,0,0,0,0,0,1},
			{0,0,0,0,0,0,0}
		};
		int source = 3;
		double[] buckets = waterfallStreams(array,source);
		for (double buck : buckets){
			System.out.print(buck + " ");
		}
	}
}
//0.0 0.0 0.0 25.0 25.0 0.0 0.0 