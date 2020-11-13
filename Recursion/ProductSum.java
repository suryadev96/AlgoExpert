/*
the product sum of a special array is the sum of its elements where special arrays inside it are summed themselves and then multiplied
by their level of depth
[5,2,[7,-1],3,[6,[-13,8],4]]
12 => 5 + 2 + 2*(7-1) + 3 + 2 * (6 + 3*(-13+8) + 4)
*/
import java.util.*;
class Question{

	public static int productSum(List<Object> array){
		return productSumHelper(array,1);
	}

	public static int productSumHelper(List<Object> array, int multiplier){
		int sum = 0;
		for (Object el : array){
			if (el instanceof ArrayList){
				ArrayList<Object> ls = (ArrayList<Object>) el;
				sum += productSumHelper(ls,multiplier+1);
			}else{
				sum += (int)el;
			}
		}
		return sum*multiplier;
	}

	public static void main(String[] args){
		List<Object> array = new ArrayList<Object>(Arrays.asList(5,2,new ArrayList<Object>(Arrays.asList(7,-1)),3,
		new ArrayList<Object>(Arrays.asList(6, new ArrayList<Object>(Arrays.asList(-13,8)),4)) ));

		int sum = productSum(array);
		System.out.println(sum);
	}

}