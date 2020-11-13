/*

*/
import java.util.*;

class Program {
  public static int countContainedPermutations(String bigString, String smallString) {
      Map<Character,Integer> targetCharCounts = getTargetCharCounts(smallString);
		
		int l = 0;
		int r = 0;
		
		int n = bigString.length();
		
		Map<Character,Integer> winMap = new HashMap<>();
		
		int formed = 0;
		int required = targetCharCounts.size();
		
		int count = 0;
		
		while (r < n){
			
			char rCh = bigString.charAt(r);
			
			if (!targetCharCounts.containsKey(rCh)){
				l = r+1;
				winMap.clear();
				formed = 0;
				r++;
				continue;
			}
			
			winMap.put(rCh, winMap.getOrDefault(rCh,0) + 1);
			
			if (winMap.get(rCh) == targetCharCounts.get(rCh)){
				formed++;
			}
			
			//if we reached window size
	        if (r - l + 1 == smallString.length()){
	            if (formed == required){
	                count++;
	            }
	            char lc = bigString.charAt(l);
	            winMap.put(lc,winMap.get(lc)-1);
	            if (winMap.get(lc) < targetCharCounts.get(lc)){
	                formed--;
	            }
	            l++;
	        }
			r++;
		}
		
    return count;
  }
	
	public static Map<Character,Integer> getTargetCharCounts(String s){
		Map<Character,Integer> map = new HashMap<>();
		for (char ch : s.toCharArray()){
			map.put(ch,map.getOrDefault(ch,0)+1);
		}
		return map;
	}
}

//using only one map
import java.util.*;

class Program {
  public static int countContainedPermutations(String bigString, String smallString) {
    int[] hash = new int[256];
		for (char ch : smallString.toCharArray()){
			hash[ch]++;
		}
		
		int l=0;
		int r=0;
		
		int required = smallString.length();
		
		int count = 0;
		
		while (r < bigString.length()){
			
			char rc = bigString.charAt(r);
			
			//if present in the target
			if (hash[rc] > 0){
				required--;
			}
			
			hash[rc]--;
			
			r++;
			
			//this condition is like formed == required
			if (required == 0){
				count++;
			}
			
			if (r-l == smallString.length()){
				char lc = bigString.charAt(l);
				//this suggests that this character was present in the target; since we are removing from the window;
				//increase the count
				if (hash[lc] >= 0){
					required++;
				}
				hash[lc]++;
				l++;
			}
		}
		
    return count;
  }
}

