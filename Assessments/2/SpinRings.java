/*
Spin the rings in the clockwise direction. In other words , every value in each respective ring should be shifted by one position
in a clockwise direction.

The operation should be done in place. It should mutate the input array
*/
import java.util.*;

class Program {
  public static void spinRings(List<List<Integer>> array) {
   		int m = array.size()-1;
		
		int startRow = 0;
		int endRow = m;
		
		int startCol = 0;
		int endCol = m;
		
		while (startRow < endRow && startCol < endCol){
			
			int prev = array.get(startRow+1).get(startCol);
			
			//do rotation in the first layer
			
			//top side
			for (int i=startCol;i<=endCol;i++){
				int temp = array.get(startRow).get(i);
				array.get(startRow).set(i,prev);
				prev = temp;
			}
			
			//right side
			for (int i=startRow+1;i<=endRow;i++){
				int temp = array.get(i).get(endCol);
				array.get(i).set(endCol,prev);
				prev = temp;
			}
			
			//down side
			for (int i=endCol-1;i>=startCol;i--){
				if (startRow == endRow) break;
				int temp = array.get(endRow).get(i);
				array.get(endRow).set(i,prev);
				prev = temp;
			}
			
			//left side
			for (int i=endRow-1;i>startRow;i--){
				if (startCol == endCol) break;
				int temp = array.get(i).get(startCol);
				array.get(i).set(startCol,prev);
				prev = temp;
			}
			
			startRow++;
			endRow--;
			startCol++;
			endCol--;
		}
		
  	}
}
