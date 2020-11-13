import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class GroupAnagrams {

    public static List<List<String>> groupAnagrams(List<String> words) {
        if (words.size() == 0) return new ArrayList<List<String>>();

        //sorted words are corresponding sorted word for the actual word
        List<String> sortedWords = new ArrayList<String>();

        for (String word : words) {
            char[] charArray = word.toCharArray();
            Arrays.sort(charArray);
            String sortedWord = new String(charArray);
            sortedWords.add(sortedWord);
        }

        List<Integer> indices = IntStream.range(0, words.size()).boxed().collect(Collectors.toList());
        //indices are sorted in such a way that all the anagrams are grouped together. This is acheived with help of sorted words list
        indices.sort((a, b) -> sortedWords.get(a).compareTo(sortedWords.get(b)));

        List<List<String>> result = new ArrayList<List<String>>();
        List<String> currentAnagramGroup = new ArrayList<String>();

        //this variable is need to keep track of current anagram group
        String currentAnagram = sortedWords.get(indices.get(0));


        for (Integer index : indices) {
            String word = words.get(index);
            String sortedWord = sortedWords.get(index);

            if (sortedWord.equals(currentAnagram)) {
                currentAnagramGroup.add(word);
                continue;
            }

            result.add(currentAnagramGroup);

            //new current anagram group
            currentAnagramGroup = new ArrayList<String>(Arrays.asList(word));
            currentAnagram = sortedWord;
        }
        result.add(currentAnagramGroup);
        return result;
    }

    public static List<List<String>> groupAnagramsEff(List<String> words) {
        Map<String, List<String>> anagrams = new HashMap<String, List<String>>();

        for (String word : words) {
            char[] charArray = word.toCharArray();
            Arrays.sort(charArray);
            String sortedWord = new String(charArray);

            if (anagrams.containsKey(sortedWord)) {
                anagrams.get(sortedWord).add(word);
            } else {
                anagrams.put(sortedWord, new ArrayList<String>(Arrays.asList(word)));
            }
        }

        List<List<String>> output = new ArrayList<List<String>>();
        for (Map.Entry<String, List<String>> entry : anagrams.entrySet()) {
            output.add(entry.getValue());
        }
        return output;
    }

    public static void main(String[] args) {
        List<String> words = Arrays.asList("yo", "act", "flop", "tac", "cat", "oy", "olfp");
        List<List<String>> anagramGroups = groupAnagrams(words);
        printList(anagramGroups);
        anagramGroups = groupAnagramsEff(words);
        System.out.println();
        printList(anagramGroups);
    }

    public static void printList(List<List<String>> anagramGroups) {
        for (List<String> anagramGroup : anagramGroups) {
            for (String anagram : anagramGroup) {
                System.out.print(anagram + " ");
            }
            System.out.println();
        }
    }

}

/*
act tac cat 
flop olfp 
yo oy 

act tac cat 
flop olfp 
yo oy 
*/

//my solution
import java.util.*;

class Program {
    public static List<List<String>> groupAnagrams(List<String> words) {
        Map<String, List<String>> map = new HashMap<>();
        for (String word : words) {
            char[] charArray = word.toCharArray();
            Arrays.sort(charArray);
            String sortedKey = new String(charArray);
            map.computeIfAbsent(sortedKey, k -> new ArrayList<String>()).add(word);
        }
        return new ArrayList<>(map.values());
    }
}
