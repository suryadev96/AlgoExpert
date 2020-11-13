/*
return array elements in zigzag order

ZigZag order starts at the top left corner of the 2 dimensional array, goes down by one element and proceeds in a zig zag 
pattern all the way to the bottom right corner

array = [
	[1, 3, 4, 10],
	[2, 5, 9, 11],
	[6, 8,12, 15],
	[7,13,14, 16]
]

Output: [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16]
*/
import java.util.*;
class ZigZag{

	/*
	U are always going in two directions going down or going up
	When u are going down , check if u are at left and down permieter of the array because tha's where 
							u will either have to move down or right and change the direction to upward
	When u are going up, check if u are at top and right perimeter of the array because that's where 
							u will either have to move right or down and change the direction to downward
	*/
	public static List<Integer> zigZagTraversal(List<List<Integer>> array){
		int height = array.size()-1;
		int width = array.get(0).size()-1;

		List<Integer> result = new ArrayList<Integer>();

		int row = 0;
		int col = 0;

		boolean goingDown = true;
		while (!isOutOfBounds(row,col,height, width)){
			result.add(array.get(row).get(col));
			if (goingDown){
				if (col == 0 || row == height){
					goingDown = false;
					//order of if else is important as we go down we always touch left wall every time
					//so if we put condition otherwise we reach out of bounds; otherwise when we reach the row == height;
					//we move right to go up
					if (row == height){ 
						col++;
					} else{
						row++;
					}
				}else{
					row++;
					col--;
				}	
			} else{
				if (row == 0 || col == width){
					goingDown = true;
					if (col == width){
						row++;
					}else{
						col++;
					}
				}else{
					row--;
					col++;
				}
			}
		}
		return result;
	}

	public static boolean isOutOfBounds(int row, int col, int height, int width){
		return row < 0 || row > height || col < 0 || col > width;
	}

	public static void main(String[] args){
		List<List<Integer>> array = new ArrayList<>();
		array.add(new ArrayList<>(Arrays.asList(1, 3, 4, 10)));
		array.add(new ArrayList<>(Arrays.asList(2, 5, 9, 11)));
		array.add(new ArrayList<>(Arrays.asList(6, 8, 12,15)));
		array.add(new ArrayList<>(Arrays.asList(7,13, 14,16)));		
		List<Integer> zigZag = zigZagTraversal(array);
		System.out.println(zigZag);
	}
}
/*
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16]
*/