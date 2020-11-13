import java.util.*;

class Program {
  public static String shortenPath(String path) {
    String[] directories = path.split("/");
		
		Stack<String> stack = new Stack<>();
		
		boolean startsWithPath = path.charAt(0) == '/';
		
		if (startsWithPath) stack.add(""); //edge case
		
		for (String directory : directories){
			if (directory.equals("..")){
				//if ".." is first encountered => that means path is relative so push ".." also
				//or if stack top is ".." => that means path is relative
				if (stack.isEmpty() || stack.peek().equals("..")){
					stack.push(directory);
				}else if (!stack.peek().equals("")){ //if there is a directory then pop the stack; u cannot pop the root
					stack.pop();
				}
			}else if (!directory.isEmpty() && !directory.equals(".")){
				stack.push(directory);
			}
		}
    
		StringBuilder sb = new StringBuilder();
		for (String s : stack){
			sb.append(s);
			sb.append("/");
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.length() == 0 ? "/" : sb.toString();
  }
}
/*
Think of edge cases like 
../../foo/bar/zoo => ../../foo/bar/zoo
/../../foo/bar/zoo => /foo/bar/zoo

while printing the stack
..|.. => /../.. => to avoid this if the path starts with "/" then we push the "" so we can add "/" seperating out the tokens
*/
