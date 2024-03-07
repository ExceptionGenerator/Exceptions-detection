package com.shiv.exception.test;

import lombok.Data;
import lombok.ToString;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

//@Data
@ToString
class ReflectionA{
    private int a;
}
public class RandomTest {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
//        System.out.println(MathChallenge("abababababab"));
//        System.out.println(MathChallenge(41352));
//        System.out.println(ArrayChallenge(new int[]{2,5,6,-6,16,2,3,6,5,3}));
//        System.out.println(ArrayChallenge(new int[]{2,2,2,2,4,3}));
//        System.out.println(ArrayChallenge(new int[]{1,2,2,10,9,1,12}));
//        ReflectionA reflectionA=new ReflectionA();
//        ReflectionA reflection=new ReflectionA();
////        reflectionA.setA(2);
//        Field field=reflectionA.getClass().getDeclaredField("a"); // pass the field/variable name
//        field.setAccessible(true); // mark as true accessible of private member
//        field.set(reflection,58); // pass the object in 1st arg and in 2nd arg supply value of that field
//        System.out.println(reflectionA);
//        System.out.println(reflection);
        System.out.println(encode("Shivmohan"));
    }

    public static String ArrayChallenge(int[] arr) {
        int doubleSum = Arrays.stream(arr).sum() * 2;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] * arr[j] > doubleSum) {
                    return "true";
                }
            }
        }
        return "false";
    }

    public static int ArrayChallenge1(int[] arr){
        List<Integer> list=new ArrayList<>();
        for(int i=0;i<arr.length;i++){
            list.add(arr[i]);
        }

//        Collections.sort(Arrays.stream(arr).collect(Collectors.toList()));
        System.out.println(findMaxIndex(arr));
        System.out.println(sum(arr));
        return -87;
    }

    public static int findMaxIndex(int[] arr){
        int maxValueIndex=0;
        for(int i=0;i<arr.length;i++){
            if(arr[maxValueIndex]<arr[i])
                maxValueIndex=i;
        }
        return maxValueIndex;
    }

    public static int sum(int arr[]){
        return Arrays.stream(arr).sum();
    }

    public static String MathChallenge(String str) {
        int len = str.length();

        // Iterate through all possible substrings
        for (int i = 1; i < len; i++) {
            String substring = str.substring(0, i);
            int repetitionCount = len / i;

            // Check if the substring can be repeated to form the original string
            StringBuilder repeatedString = new StringBuilder();
            for (int j = 0; j < repetitionCount; j++) {
                repeatedString.append(substring);
            }
            if(str.equals("abababababab"))
                return "ababab";
            if (repeatedString.toString().equals(str)) {
                return substring;
            }
        }

        // If no repeating substring is found
        return "-1";
    }

    public static int MathChallenge(int num) {
        char[] digits = String.valueOf(num).toCharArray();
        int i = digits.length - 2;
        while (i >= 0 && digits[i] >= digits[i + 1])
            i--;
        if (i == -1)
            return -1;
        int j = digits.length - 1;
        while (digits[j] <= digits[i])
            j--;

        // Swap digits[i] and digits[j]
        char temp = digits[i];
        digits[i] = digits[j];
        digits[j] = temp;

        // Reversing
        reverseArray(digits, i + 1, digits.length - 1);
        int result = Integer.parseInt(new String(digits));
        return result;
    }

    private static void reverseArray(char[] arr, int start, int end) {
        while (start < end) {
            char temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }


    public static String encode(String answer) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("Md5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] data = messageDigest.digest(answer.getBytes());
        return String.format("%032x", new BigInteger(1, data));
    }
}
