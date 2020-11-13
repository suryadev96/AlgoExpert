import java.util.*;

class BalancedBrackets {

    // O(n) time | O(n) space
    public static boolean balancedBrackets(String str) {
        String openingBrackets = "([{";
        String closingBrackets = ")]}";

        Map<Character, Character> matchingBrackets = new HashMap<Character, Character>();

        matchingBrackets.put(')', '(');
        matchingBrackets.put(']', '[');
        matchingBrackets.put('}', '{');

        List<Character> stack = new ArrayList<Character>();

        for (int i = 0; i < str.length(); i++) {
            char letter = str.charAt(i);
            if (openingBrackets.indexOf(letter) != -1) {
                stack.add(letter);
            } else if (closingBrackets.indexOf(letter) != -1) {
                if (stack.size() == 0) {
                    return false;
                }
                if (stack.get(stack.size() - 1) == matchingBrackets.get(letter)) {
                    stack.remove(stack.size() - 1);
                } else {
                    return false;
                }
            }
        }
        return stack.size() == 0;
    }

    public static void main(String[] args) {
        String brackets = "([])(){}(())()()";
        boolean isBalanced = balancedBrackets(brackets);
        System.out.println(isBalanced);
    }
}

//my solution
import java.util.*;

class Program {
    public static boolean balancedBrackets(String str) {
        Map<Character, Character> matchingBraces = new HashMap<>();
        matchingBraces.put(')', '(');
        matchingBraces.put('}', '{');
        matchingBraces.put(']', '[');

        //push down automaton
        Stack<Character> pda = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            //if u encounter open brace, push it to the stack
            if (ch == '(' || ch == '{' || ch == '[') {
                pda.push(ch);
            } else if (ch == ')' || ch == '}' || ch == ']') {
                //match close and open brace
                if (pda.isEmpty()) return false;
                else if (pda.pop() != matchingBraces.get(ch)) {
                    return false;
                }
            }
        }
        return pda.isEmpty();
    }
}
