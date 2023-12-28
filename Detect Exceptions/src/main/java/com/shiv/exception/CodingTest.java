package com.shiv.exception;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static java.lang.System.*;
public class CodingTest {

    /**
     *  1 4 3 5 2
     *
     *  1 4 3 5 2
     *  1 3 4 5 2
     *  1
     *
     * 1 2 4 3 1
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

    private static void sortArrayUsingSelectionSort(int[] arr){
        for(int i=0;i<arr.length;i++){
            int smallest=i;
            for(int j=i;j<arr.length;j++){
                if(arr[smallest]>arr[j])
                    smallest=j;
            }
            swap(arr,smallest,i);
        }
    }

    private static void sortArrayUsingInsertionSort(int[] arr){
        for(int i=1;i<arr.length;i++){
            int currentElement=arr[i];
            int j=i-1;
            while (j>=0 && currentElement<arr[j]){
                arr[j+1]=arr[j];
                j--;
            }
            arr[j+1]=currentElement;
        }
    }

    private static void swap(int[] arr,int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }

    /**
     *
     * @param arr {1,3,5,6,8,9,11}
     * @param keyword
     * @return
     */
    private static int searchUsingBinarySearch(int[] arr,int keyword){
        int low=0;
        int high=arr.length-1;
        while (low<=high){
            int mid=(low+high)/2;
            if(keyword<arr[mid])
                high=mid-1;
            else if(keyword>arr[mid])
                low=mid+1;
            else
                return mid;
        }
        return -1;
    }
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (Character character : s.toCharArray()) {
            if(!isClosableBracket(character))
                stack.push(character);
            else {
                if(stack.size()==0)
                    return false;
                Character lastBracket=stack.lastElement();
                if(lastBracket==null)
                    return false;
                if(getCloseBracketOfOpen(lastBracket)==character)
                    stack.pop();
                else
                    return false;
            }
        }
        return stack.size() == 0;
    }

    private static Character getCloseBracketOfOpen(Character character){
        if(character=='(')
            return ')';
        else if (character=='{')
            return '}';
        else if(character=='[')
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
     * @param s
     * @return
     */
    public static String removeDuplicateLetters(String s) {
        Stack<Character> stack=new Stack<>();
        for(Character character:s.toCharArray()){
            int stackElementIndex=stack.search(character);
            if(stackElementIndex==-1){
                stack.push(character);
            }
            else {
                stack=changeInLexicalOrder(stack,character);
            }
        }
        StringBuilder stringBuilder=new StringBuilder();
        stack.forEach(character->stringBuilder.append(character));
        return stringBuilder.toString();
    }

    private static Stack<Character> changeInLexicalOrder(Stack<Character> stack,Character lastElement){
        int stackElementIndex=stack.search(lastElement);
        Object[] chars1=stack.toArray();
        stack.remove(stack.size()-stackElementIndex);
        stack.push(lastElement);
        Object[] chars2=stack.toArray();
        Stack<Character> stackResult=new Stack<>();
        for(int i=0;i<chars1.length;i++){
            if(((char)chars1[i])<((char)chars2[i])){
                Arrays.stream(chars1).forEach(char2->stackResult.push((Character) char2));
                return stackResult;
            }else if(((char)chars2[i])<((char)chars1[i])){
                Arrays.stream(chars2).forEach(char2->stackResult.push((Character) char2));
                return stackResult;
            }
        }
        return stackResult;
    }


    public static void main(String[] args) throws InterruptedException {
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
    private static void graphLearning(){
        ArrayList<Integer> graph[]=new ArrayList[3];
        addVertex(graph,2,0);
        addVertex(graph,1,0);
        addVertex(graph,0,1);
        addVertex(graph,2,1);
        addVertex(graph,0,2);
        addVertex(graph,1,2);
        out.println(Arrays.toString(graph));
    }

    private static void addVertex(ArrayList<Integer>[] graph,int vertex,int sourceIndex){
        if(graph[sourceIndex]==null)
            graph[sourceIndex]=new ArrayList<>();
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


}

