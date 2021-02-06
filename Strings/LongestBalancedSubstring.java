/*
string = "(()))("
4
*/
import java.util.*;

class Program {

  public int longestBalancedSubstring(String string) {
    int maxLen = 0;
		Stack<Integer> stack = new Stack<>();
		stack.push(-1);
		
		for (int i=0;i<string.length();i++){
			if (string.charAt(i) == '('){
				stack.push(i);
			}else{
				stack.pop();
				if (stack.isEmpty()){
					stack.push(i);
				}else{
					maxLen = Math.max(maxLen, i - s.peek());
				}
			}
		}
    return maxLen;
  }
}
