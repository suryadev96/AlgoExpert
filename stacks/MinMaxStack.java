import java.util.*;

class MinMaxStack{

	List<Map<String,Integer>> minMaxStack = new ArrayList<Map<String,Integer>>();
	List<Integer> stack = new ArrayList<Integer>();

	public int peek(){
		return stack.get(stack.size()-1);
	}

	public int pop(){
		minMaxStack.remove(minMaxStack.size()-1);
		return stack.remove(stack.size()-1);
	}

	public void push(int number){

		Map<String,Integer> newMinMax = new HashMap<String,Integer>();
		newMinMax.put("min", number );
		newMinMax.put("max", number);

		if (minMaxStack.size() > 0){
			Map<String,Integer> lastMinMax = new HashMap<String,Integer>(minMaxStack.get(minMaxStack.size()-1));
			newMinMax.replace("min", Math.min(lastMinMax.get("min"), number));
			newMinMax.replace("max", Math.max(lastMinMax.get("max"), number));
		}
		minMaxStack.add(newMinMax);
		stack.add(number);
	}

	public int getMin(){
		return minMaxStack.get(minMaxStack.size() - 1).get("min");
	}

	public int getMax(){
		return minMaxStack.get(minMaxStack.size() - 1).get("max");
	}
}

//my solution
import java.util.*;

class Program {
  // Feel free to add new properties and methods to the class.
  static class MinMaxStack{
		
	Stack<Integer> s = new Stack<Integer>();
	Stack<Integer> minStack = new Stack<Integer>();
	Stack<Integer> maxStack = new Stack<Integer>();
		
    public int peek() {
      return s.peek();
    }

    public int pop() {
	    int value = s.pop();
		if (value == getMin()) minStack.pop();
		if (value == getMax()) maxStack.pop();
	    return value;
    }

    public void push(Integer number) {
		if (number <= getMin()){
			minStack.push(number);
		}
		if (number >= getMax()){
			maxStack.push(number);
		}
		s.push(number);
    }

    public int getMin() {
      	if (minStack.isEmpty()){
			return Integer.MAX_VALUE;
		}
      	return minStack.peek();
    }

    public int getMax() {
    	if (maxStack.isEmpty()){
			return Integer.MIN_VALUE;
		}
      	return maxStack.peek();
    }
  }
}
