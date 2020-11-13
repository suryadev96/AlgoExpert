/*
phoneNumber = "3662277"
words = ["foo","bar","baz","foobar","emo","cap","car","cat"]

["bar","cap","car","emo","foo","foobar"]
*/

import java.util.*;

class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    String word;
}

class Trie {

    TrieNode root = new TrieNode();

    Map<Character, List<Character>> keyMap;

    public Trie(String[] words) {
        for (String s : words) {
            TrieNode node = root;
            for (char ch : s.toCharArray()) {
                node = node.children.computeIfAbsent(ch, k -> new TrieNode());
            }
            node.word = s;
        }
        intializeKeyMap();
    }

    public void intializeKeyMap() {
        keyMap = new HashMap<>();
        keyMap.put('2', Arrays.asList('a', 'b', 'c'));
        keyMap.put('3', Arrays.asList('d', 'e', 'f'));
        keyMap.put('4', Arrays.asList('g', 'h', 'i'));
        keyMap.put('5', Arrays.asList('j', 'k', 'l'));
        keyMap.put('6', Arrays.asList('m', 'n', 'o'));
        keyMap.put('7', Arrays.asList('p', 'q', 'r', 's'));
        keyMap.put('8', Arrays.asList('t', 'u', 'v'));
        keyMap.put('9', Arrays.asList('w', 'x', 'y', 'z'));
    }

    public List<String> findWords(String phoneNumber) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < phoneNumber.length(); i++) {
            for (int j = i + 1; j <= phoneNumber.length(); j++) {
                String number = phoneNumber.substring(i, j);
                dfs(number, 0, root, result);
            }
        }
        return result;
    }

    private void dfs(String phoneNumber, int index, TrieNode node, List<String> result) {
        if (index == phoneNumber.length()) {
            if (node.word != null) {
                result.add(node.word);
                node.word = null; //so that same word is not capture twice
            }
            return;
        }

        char ch = phoneNumber.charAt(index);

        for (Character c : keyMap.getOrDefault(ch, new ArrayList<Character>())) {

            if (!node.children.containsKey(c)) continue;

            TrieNode nextNode = node.children.get(c);

            dfs(phoneNumber, index + 1, nextNode, result);

        }

    }

}

class Program {
    public static List<String> wordsInPhoneNumber(String phoneNumber, String[] words) {
        Trie trie = new Trie(words);
        return trie.findWords(phoneNumber);
    }
}

//optimized solution:
import java.util.*;

class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    String word;
}

class Trie {

    TrieNode root = new TrieNode();

    Map<Character, List<Character>> keyMap;

    public Trie(String[] words) {
        for (String s : words) {
            TrieNode node = root;
            for (char ch : s.toCharArray()) {
                node = node.children.computeIfAbsent(ch, k -> new TrieNode());
            }
            node.word = s;
        }
        intializeKeyMap();
    }

    public void intializeKeyMap() {
        keyMap = new HashMap<>();
        keyMap.put('2', Arrays.asList('a', 'b', 'c'));
        keyMap.put('3', Arrays.asList('d', 'e', 'f'));
        keyMap.put('4', Arrays.asList('g', 'h', 'i'));
        keyMap.put('5', Arrays.asList('j', 'k', 'l'));
        keyMap.put('6', Arrays.asList('m', 'n', 'o'));
        keyMap.put('7', Arrays.asList('p', 'q', 'r', 's'));
        keyMap.put('8', Arrays.asList('t', 'u', 'v'));
        keyMap.put('9', Arrays.asList('w', 'x', 'y', 'z'));
    }

    public List<String> findWords(String phoneNumber) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < phoneNumber.length(); i++) {
            dfs(phoneNumber, i, root, result);
        }
        return result;
    }

    private void dfs(String phoneNumber, int index, TrieNode node, List<String> result) {

        if (node.word != null) {
            result.add(node.word);
            node.word = null; //so that same word is not capture twice
        }

        if (index == phoneNumber.length()) {
            return;
        }

        char ch = phoneNumber.charAt(index);

        for (Character c : keyMap.getOrDefault(ch, new ArrayList<Character>())) {

            if (!node.children.containsKey(c)) continue;

            TrieNode nextNode = node.children.get(c);

            dfs(phoneNumber, index + 1, nextNode, result);

        }
    }

}

class Program {
    public static List<String> wordsInPhoneNumber(String phoneNumber, String[] words) {
        Trie trie = new Trie(words);
        return trie.findWords(phoneNumber);
    }
}

