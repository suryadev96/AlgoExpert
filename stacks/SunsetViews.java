/*
Given an array of buildings and a direction that all of the buildings face, return an array of the indices of the buildings that can see the 
sunset

A building can see the sunset if it's strictly taller than all of the buildings that come after it in the direction it faces

The input array named buildings contains positive, non-zero integers representing the heights of the buildings . 
A building at index i has a height denoted by buildings[i]. All of the buildings face the same direction either east or west, denoted
by the input string named direction, which will always be equal to either "EAST" OR "WEST"

buildings = [3,5,4,4,3,1,3,2]
direction = "EAST"

[1,3,6,7]

buildings = [3,5,4,4,3,1,3,2]
direction = "WEST"

indices in the array must be sorted in ascending order

[0,1]
*/
import java.util.*;
class Question{

	public List<Integer> sunsetViews(int[] buildings, String direction){

		List<Integer> result = new ArrayList<>();

		//east going forward
		//west coming backward
		int i = direction.equals("EAST") ? 0 : buildings.length - 1;
		int step = direction.equals("EAST") ? 1 : -1;

		//when u encounter height higher than the top of the stack. pop the elements from the stack

		Stack<Integer> s = new Stack<>();

		while (i >= 0 && i < buildings.length) {

			int height = buildings[i];

			while (!s.isEmpty() && height >= buildings[s.peek()]) {
				s.pop();
			}
			s.push(i);

			i += step;
		}

		//stack always contains elements in increasing order if east
		//stack alwyas contains elements in decreasing order if west

		if (direction.equals("EAST")){
			while (!s.isEmpty()){
				result.add(0,s.pop());
			}
			return result;
		}

		while (!s.isEmpty()){
			result.add(s.pop());
		}
		return result;
	}

	 public List<Integer> sunsetViews(int[] buildings, String direction) {

    	List<Integer> result = new ArrayList<>();

		//west going forward
		//east coming backward
		int i = direction.equals("WEST") ? 0 : buildings.length - 1;
		int step = direction.equals("WEST") ? 1 : -1;

		//when u encounter height higher than the top of the stack ; store the result and push to the stack

		Stack<Integer> s = new Stack<>();

		while (i >= 0 && i < buildings.length) {
			int height = buildings[i];

			if ( (!s.isEmpty() && height > buildings[s.peek()] ) || s.isEmpty()){
				s.push(i);
			}
			i += step;
		}

		//stack always contains elements in decreasing order if east
		//stack alwyas contains elements in increasing order if west

		if (direction.equals("WEST")){
			while (!s.isEmpty()){
				result.add(0,s.pop());
			}
		}

		while (!s.isEmpty()){
			result.add(s.pop());
		}
		return result;
  	}

}