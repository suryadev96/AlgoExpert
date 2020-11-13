/*
A string is said to be special if its exactly made up of atleast two instances of other strings in the input list of strings

Inorder for a string to be special , just containing two instances of other strings isn't sufficient .
String must be exactly made up of those other strings .

Note that strings can be repeated to form a special string ; ["a","aaa"], the string "aaa" is special
*/

class Program {
    public static List<String> specialStrings(String[] strings) {
        Set<String> wordSet = new HashSet<>(Arrays.asList(strings));

        List<String> result = new ArrayList<>();

        for (String s : strings) {
            wordSet.remove(s);
            if (wordBreak(s, wordSet)) {
                result.add(s);
            }
            wordSet.add(s);
        }
        return result;
    }

    public static boolean wordBreak(String s, Set<String> wordSet) {
        boolean[] dp = new boolean[s.length() + 1];

        dp[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    public static void main(String[] args) {
        String[] strings = {"foobarbaz", "foo", "bar", "foobarfoo", "baz", "foobaz", "foofoofoo", "foobazar"};
        System.out.println(specialStrings(strings));
    }
}

//use of trie
import java.util.*;

class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    boolean isWord;
}

class Trie {

    TrieNode root = new TrieNode();

    public Trie(String[] strings) {
        for (String s : strings) {
            TrieNode node = root;
            for (char ch : s.toCharArray()) {
                node = node.children.computeIfAbsent(ch, k -> new TrieNode());
            }
            node.isWord = true;
        }
    }

    public boolean isSpecial(String s) {
        return dfs(s, root, 0, 0);
    }

    private boolean dfs(String s, TrieNode node, int index, int count) {
        if (index == s.length()) {
            if (node.isWord && count >= 1) {
                return true;
            } else {
                return false;
            }
        }

        //if currentNode is word; we can either go back to root or stay there and continue forward
        if (node.isWord) {
            boolean isSpecial = dfs(s, root, index, count + 1);
            if (isSpecial) return true;
        }

        char ch = s.charAt(index);

        if (!node.children.containsKey(ch)) return false;

        TrieNode nextNode = node.children.get(ch);

        return dfs(s, nextNode, index + 1, count);
    }

}

class Program {
    public static List<String> specialStrings(String[] strings) {
        List<String> result = new ArrayList<>();
        Trie trie = new Trie(strings);
        for (String s : strings) {
            if (trie.isSpecial(s)) {
                result.add(s);
            }
        }
        return result;
    }
}

