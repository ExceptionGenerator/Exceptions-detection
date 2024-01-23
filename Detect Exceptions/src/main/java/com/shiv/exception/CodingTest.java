package com.shiv.exception;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static java.lang.System.out;

public class CodingTest {

    /**
     * 1 4 3 5 2
     * <p>
     * 1 4 3 5 2
     * 1 3 4 5 2
     * 1
     * <p>
     * 1 2 4 3 1
     *
     * @param arr
     */
    private static void sortArrayUsingBubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1])
                    swap(arr, j, j + 1);
            }
        }
    }

    private static void sortArrayUsingSelectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int smallest = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[smallest] > arr[j])
                    smallest = j;
            }
            swap(arr, smallest, i);
        }
    }

    private static void sortArrayUsingInsertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int currentElement = arr[i];
            int j = i - 1;
            while (j >= 0 && currentElement < arr[j]) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = currentElement;
        }
    }

    /**
     * 123
     * <p>
     * 321
     * 321
     *
     * @param arr
     * @param i
     * @param j
     */
    private static void swap(int[] arr, int i, int j) {

        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        char ch = 'd';
        out.println(Integer.parseInt(String.valueOf(ch)));
    }

    /**
     * 5 /2 -> 2
     * 9/2 -> 4
     *
     * @param numerator
     * @param denominator
     * @return
     */
    private static int divide(int numerator, int denominator) {
        int result = 0;
        for (int i = denominator; i <= numerator; i += denominator)
            result++;
        return result;
    }

    public static String removeStars(String s) {
        Stack<Character> stack = new Stack();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != '*')
                stack.push(chars[i]);
            else if (stack.lastElement() != null) {
                stack.pop();
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stack.forEach(stringBuilder::append);
        return stringBuilder.toString();
    }

//    public static void main(String[] args) {
//        StringBuilder stringBuilder=new StringBuilder("shiv");
//        out.println(removeStars("leet**cod*e"));
//        out.println(removeStars("eraswe*****"));
//    }

    /**
     * @param arr     {1,3,5,6,8,9,11}
     * @param keyword
     * @return
     */
    private static int searchUsingBinarySearch(int[] arr, int keyword) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (keyword < arr[mid])
                high = mid - 1;
            else if (keyword > arr[mid])
                low = mid + 1;
            else
                return mid;
        }
        return -1;
    }

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (Character character : s.toCharArray()) {
            if (!isClosableBracket(character))
                stack.push(character);
            else {
                if (stack.size() == 0)
                    return false;
                Character lastBracket = stack.lastElement();
                if (lastBracket == null)
                    return false;
                if (getCloseBracketOfOpen(lastBracket) == character)
                    stack.pop();
                else
                    return false;
            }
        }
        return stack.size() == 0;
    }

    private static Character getCloseBracketOfOpen(Character character) {
        if (character == '(')
            return ')';
        else if (character == '{')
            return '}';
        else if (character == '[')
            return ']';
        else
            return null;
    }

    private static boolean isClosableBracket(Character character) {
        if (character == ')')
            return true;
        else if (character == '}')
            return true;
        else if (character == ']')
            return true;
        else
            return false;
    }

    /**
     * input - cbacdcbc
     * output - acdb
     * cbacdcbc  -> bacdb  -> acdb
     *
     * @param s
     * @return
     */
    public static String removeDuplicateLetters(String s) {
        Stack<Character> stack = new Stack<>();
        for (Character character : s.toCharArray()) {
            int stackElementIndex = stack.search(character);
            if (stackElementIndex == -1) {
                stack.push(character);
            } else {
                stack = changeInLexicalOrder(stack, character);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stack.forEach(character -> stringBuilder.append(character));
        return stringBuilder.toString();
    }

    private static Stack<Character> changeInLexicalOrder(Stack<Character> stack, Character lastElement) {
        int stackElementIndex = stack.search(lastElement);
        Object[] chars1 = stack.toArray();
        stack.remove(stack.size() - stackElementIndex);
        stack.push(lastElement);
        Object[] chars2 = stack.toArray();
        Stack<Character> stackResult = new Stack<>();
        for (int i = 0; i < chars1.length; i++) {
            if (((char) chars1[i]) < ((char) chars2[i])) {
                Arrays.stream(chars1).forEach(char2 -> stackResult.push((Character) char2));
                return stackResult;
            } else if (((char) chars2[i]) < ((char) chars1[i])) {
                Arrays.stream(chars2).forEach(char2 -> stackResult.push((Character) char2));
                return stackResult;
            }
        }
        return stackResult;
    }


    public static void main1(String[] args) throws InterruptedException {
        out.println(removeDuplicateLetters("bcabc"));
//        out.println('a'<'b');
        // c d a
//        Stack<Character> characters=new Stack<>();
//        characters.push('a');
//        characters.push('c');
//        characters.push('b');
//        characters.push('d');
//        Object[] chars=characters.toArray();
//        for(Object object:chars){
//            out.println((char)object);
//        }
//        int index=characters.search('a');
//        out.println(index);
//        out.println(characters.get(characters.size()-index));
//        characters.remove(characters.size()-index);
//        out.println(characters);
    }

    /**
     * Graph representation
     * 0 -> 2,1
     * 1 -> 0,2
     * 2 -> 0,1
     */
    private static void graphLearning() {
        ArrayList<Integer> graph[] = new ArrayList[3];
        addVertex(graph, 2, 0);
        addVertex(graph, 1, 0);
        addVertex(graph, 0, 1);
        addVertex(graph, 2, 1);
        addVertex(graph, 0, 2);
        addVertex(graph, 1, 2);
        out.println(Arrays.toString(graph));
    }

    private static void addVertex(ArrayList<Integer>[] graph, int vertex, int sourceIndex) {
        if (graph[sourceIndex] == null)
            graph[sourceIndex] = new ArrayList<>();
        graph[sourceIndex].add(vertex);
    }

    synchronized public void test(Integer number) throws InterruptedException {
        CodingTest codingTest = new CodingTest();
        System.out.println("Started----" + number);
        synchronized (number) {
            System.out.println("synchronized block executed - " + number + " , thread - " + Thread.currentThread().getName());
            if (number % 2 == 0)
                TimeUnit.SECONDS.sleep(6);
            System.out.println("synchronized block exited - " + number + " , thread - " + Thread.currentThread().getName());
        }
        System.out.println("Ended----" + number);
    }

    public static void main2(String[] args) {
        List<Integer> fibonacciSeriesList = new ArrayList<>();
        fibonacciSeries(9999, fibonacciSeriesList);
        out.println(fibonacciSeriesList);
    }


    private class Solution {
        Integer[] stack;
        int tos = -1;

        private boolean push(int data) {
            if (!isStackFull()) {
                tos++;
                stack[tos] = data;
                return true;
            } else {
                return false;
            }
        }

        private boolean pop() {
            if (isStackEmpty()) {
                return false;
            } else {
                stack[tos] = null;
                tos--;
                return true;
            }
        }

        private boolean isStackFull() {
            return stack.length - 1 == tos;
        }

        private boolean isStackEmpty() {
            return tos == -1;
        }

        public List<String> buildArray(int[] target, int n) {
            stack = new Integer[target.length];

            Stack<Integer> stack = new Stack<>();
            List<String> result = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
//                if()
                if (isStackFull()) {
                    result.add("Pop");
                }
            }
            return result;
        }
    }

    public static void fibonacciSeries(int limit, List<Integer> fibonacciSeries) {
        fibonacciSeries(0, 0, 1, limit, fibonacciSeries);
    }

    private static void fibonacciSeries(int start, int last, int result, int limit, List<Integer> fibonacciSeries) {
        if (result > limit) ;
        else {
            fibonacciSeries.add(result);
            start = last;
            last = result;
            result = start + last;
            fibonacciSeries(start, last, result, limit, fibonacciSeries);
        }
    }

    /**
     * ["BrowserHistory","visit","visit","visit","back","back","forward","visit","forward","back","back"]
     * [["leetcode.com"],["google.com"],["facebook.com"],["youtube.com"],[1],[1],[1],["linkedin.com"],[2],[2],[7]]
     * <p>
     * current - leetcode.com
     * current - google.com
     * current - facebook.com
     * current - youtube.com
     * back - 1
     * back - 1
     * forward - 1
     * current - linkedin.com
     * forward - 2
     * back - 2
     * back - 7
     * <p>
     * [null,null,null,null,"facebook.com","google.com","facebook.com",null,"linkedin.com","facebook.com","leetcode.com"]
     * <p>
     * [null,null,null,null,"facebook.com","google.com","facebook.com",null,"linkedin.com","google.com","leetcode.com"]
     */
    class BrowserHistory {

        private Node head;
        private Node current;

        public BrowserHistory(String homepage) {
            head = new Node(homepage);
            current = head;
        }

        public void visit(String url) {
            addNode(new Node(url));
        }

        public String back(int steps) {
            while (current.prevNode != null) {
                steps--;
                current = current.prevNode;
                if (steps == 0)
                    break;
            }
            return current.url;
        }

        public String forward(int steps) {
            while (current.nextNode != null) {
                steps--;
                current = current.nextNode;
                if (steps == 0)
                    break;
            }
            return current.url;
        }

        private void addNode(Node node) {
            current.nextNode = node;
            node.prevNode = current;
            current = current.nextNode;
        }
    }

    private class Node {
        Node prevNode;
        Node nextNode;
        String url;

        public Node(String url) {
            this.url = url;
        }
    }

    /**
     * Your BrowserHistory object will be instantiated and called as such:
     * BrowserHistory obj = new BrowserHistory(homepage);
     * obj.visit(url);
     * String param_2 = obj.back(steps);
     * String param_3 = obj.forward(steps);
     */


    public static void main(String[] args) throws Throwable {
//        Date date=new Date(1698935856355L);
//        String date1="14-08-2023";
//        String date2="08-09-2023";
//        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss:SSS");
//        SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("dd-MM-yyyy");
//        System.out.println("Time from - "+simpleDateFormat1.parse(date1).getTime());
//        System.out.println("Time to - "+simpleDateFormat1.parse(date2).getTime());
//        System.out.println(date);
//        System.out.println(simpleDateFormat.format(date));
        SecureRandom secureRandom = SecureRandom.getInstanceStrong();
        UUID requestId = new UUID(secureRandom.nextLong(), secureRandom.nextLong());
        out.println(requestId);
        out.println(UUID.randomUUID());

    }
}

