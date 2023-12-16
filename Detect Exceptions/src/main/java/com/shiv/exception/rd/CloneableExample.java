package com.shiv.exception.rd;

import lombok.*;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

@Data
@RequiredArgsConstructor
public class CloneableExample implements Cloneable {
    @NonNull
    private int number;
    private String name;

    @Override
    public CloneableExample clone() {
        try {
            return (CloneableExample) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public static void main2(String[] args) {
//        CloneableExample cloneableExample=new CloneableExample(12);
//        cloneableExample.setName("Shiv");
//        cloneableExample.setNumber(12);
//        System.out.println(cloneableExample);
//        CloneableExample cloneableExampleCloned=cloneableExample.clone();
//        cloneableExampleCloned.setNumber(24);
//        System.out.println(cloneableExample);
//        System.out.println(cloneableExampleCloned);
//        System.out.println(revWordInASentence("Shivmohan hello bhai kaha ho  "));
//        System.out.println(groupAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"}));

        Stack<Character> stack = new Stack<>();
        stack.add('{');
        stack.add('(');
        stack.add('(');
        stack.add(']');
        System.out.println(stack.lastElement());
//        System.out.println(Arrays.toString(removeMatchedAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"},generateAnagram("eat"))));
    }

    public static String revWordInASentence(String sentence) {
        sentence = sentence.trim();
        final StringBuilder stringBuilder = new StringBuilder();
        final var words = sentence.split(" ");
        for (int i = words.length - 1; i >= 0; i--)
            if (!words[i].isBlank())
                stringBuilder.append(words[i]).append(" ");
        return stringBuilder.toString().trim();
    }

    /**
     * Input: strs = ["eat","tea","tan","ate","nat","bat"]
     * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
     * <p>
     * Example 2:
     * <p>
     * Input: strs = [""]
     * Output: [[""]]
     * <p>
     * Example 3:
     * <p>
     * Input: strs = ["a"]
     * Output: [["a"]]
     *
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        final List<List<String>> resultAnagrams = new ArrayList<>();
        while (true) {
            if (strs.length == 0)
                break;
            String word = strs[0];
            List<String> anagramResult = new ArrayList<>();
            resultAnagrams.add(anagramResult);
            List<String> groupAnagram = generateAnagram(word);
            System.out.println("word-" + word + ", anagram - " + groupAnagram);
            strs = removeMatchedAnagrams(strs, groupAnagram, anagramResult);
        }
        return resultAnagrams;
    }

    private static String[] removeMatchedAnagrams(String[] inputAnagrams, List<String> anagramGroups, List<String> anagramResult) {
        for (int i = 0; i < inputAnagrams.length; i++) {
            if (anagramGroups.contains(inputAnagrams[i])) {
                anagramResult.add(inputAnagrams[i]);
                inputAnagrams[i] = null;
            }
        }
        String[] array = new String[inputAnagrams.length - Arrays.stream(inputAnagrams).filter(Objects::isNull).toList().size()];
        int index = -1;
        for (int i = 0; i < inputAnagrams.length; i++) {
            if (inputAnagrams[i] != null)
                array[++index] = inputAnagrams[i];
        }
        return array;
    }

    /**
     * eat
     * eat, ate, tea
     *
     * @param word
     * @return
     */
    private static List<String> generateAnagram(String word) {
        final List<String> anagrams = new ArrayList<>();
        anagrams.add(word);
        anagrams.add(new StringBuilder(word).reverse().toString());
        String string = word;
        for (int i = 1; i < word.length(); i++) {
            string = string.substring(1) + string.charAt(0);
            anagrams.add(string);
        }
        return anagrams;
    }

    public static void main(String[] args) {
        System.out.println(isValidParenthesis("{((()))[]}".toCharArray()));
    }

    /**
     * sample below test cases below
     * <p>
     * {()[]} - true
     * {()[)} - false
     * {((()))[]} - true
     * {{} - false
     * [(()){}] - true
     *
     * @param parenthesis
     * @return
     */
    public static boolean isValidParenthesis(char[] parenthesis) {
        Stack<BracketCloseable> stack = new Stack<>();
        for (int i = 0; i < parenthesis.length; i++) {
            if (!closeableBracket(parenthesis[i]))
                stack.push(new BracketCloseable(parenthesis[i], null));
            else {
                var lastElementFromStack = stack.lastElement();
                if (lastElementFromStack.getClose() != null)
                    return false;
                else {
                    if (getCloseableBracket(lastElementFromStack.getOpen()) != parenthesis[i])
                        return false;
                    else {
                        lastElementFromStack.setClose(parenthesis[i]);
                        stack.remove(lastElementFromStack);
                    }
                }
            }
        }
        return true;
    }

    private static char getCloseableBracket(char ch) {
        if (ch == '(')
            return ')';
        else if (ch == '{')
            return '}';
        else if (ch == '[')
            return ']';
        return 0;
    }

    private static boolean closeableBracket(char ch) {
        if (ch == ')')
            return true;
        else if (ch == '}')
            return true;
        else if (ch == ']')
            return true;
        return false;
    }

}

@Data
@AllArgsConstructor
@NoArgsConstructor
class BracketCloseable {
    Character open;
    Character close;
}
