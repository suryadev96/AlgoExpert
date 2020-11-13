import java.util.*;

/*
for list [1,2,3] there exists some permutations starting with 1 , some starting with 2 and some starting with 3.f
for permuations starting with 1, there will be permutation where 2 is second number  and one where 3 is second number
*/
class Question{

	public static List<List<Integer>> getPermutations(List<Integer> array){
		List<List<Integer>> permutations = new ArrayList<List<Integer>>();
		getPermutations(0,array,permutations); 
		return permutations;    
	}

	//permutations from arr[i,n-1]
	public static void getPermutations(int i, List<Integer> array, List<List<Integer>> permutations){
		if (i == array.size() - 1){
			permutations.add(new ArrayList<Integer>(array));
		}else{
			for (int j=i;j<array.size();j++){
				swap(array,i,j);
				getPermutations(i+1,array,permutations);
				swap(array,i,j); //backtrack
			}
		}
	}

	public static void swap(List<Integer> array, int i, int j){
		Integer tmp = array.get(i);
		array.set(i,array.get(j));
		array.set(j,tmp);
	}

	public static void main(String[] args){
		List<Integer> array = Arrays.asList(1,2,3);
		List<List<Integer>> permutations = getPermutations(array);
		for (List<Integer> permutation : permutations){
			for (Integer i : permutation){
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}

}

//my solution
import java.util.*;

class Program {
  public static List<List<Integer>> getPermutations(List<Integer> array) {
    List<List<Integer>> result = new ArrayList<>();
		helper(array,0,array.size()-1,result);
    return result;
  }
	
	public static void helper(List<Integer> array, int l, int r, List<List<Integer>> result){
		if (l == r){
			result.add(new ArrayList<Integer>(array));
			return;
		}
		for (int i=l;i<=r;i++){
			swap(array,l,i);
			helper(array,l+1,r,result);
			swap(array,l,i); //backtracking
		}
	}
	
	public static void swap(List<Integer> array, int i, int j){
		Integer tmp = array.get(i);
		array.set(i,array.get(j));
		array.set(j,tmp);
	}
}
