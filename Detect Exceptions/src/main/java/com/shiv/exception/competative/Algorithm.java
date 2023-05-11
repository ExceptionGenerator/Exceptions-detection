package com.shiv.exception.competative;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Algorithm {
    public static void main(String[] args) {
        int[] array={1,3,3,3,5,6};
        var algo=new Algorithm();
        long startTime=System.currentTimeMillis();
        System.out.println(getAdditionOfBinary("1111","1111"));
        System.out.println("Time - "+(System.currentTimeMillis()-startTime)+" ms");
    }

    /**
     * find square root without using built-in function
     * input 9
     * output 3
     * input 8
     * output 2
     * @param x
     * @return
     */
    public static int mySqrt(int x) {
        if(x==0)
            return x;
        if(x>0 && x<=3)
            return 1;
        for(int i=2;i<x;i++){
            if((i*i)==x)
                return i;
            else
            if(((i*i)<x && ((i+1)*(i+1))>x) || ((i*i)<x && ((i+1)*(i+1))<0))
                return i;
        }
        return 0;
    }

    /**
     * Input: a = "11", b = "1"
     * Output: "100"
     * Input: a = "1010", b = "1011"
     * Output: "10101"
     * @param a
     * @param b
     * @return
     */
    public static String getAdditionOfBinary(String a,String b){
        if(a.length()>b.length())
            b=fillZero(b,a.length()-b.length());
        if(a.length()<b.length())
            a=fillZero(a,b.length()-a.length());
        StringBuilder stringBuilder=new StringBuilder();
        int count=a.length()-1;
        boolean isCarry=false;
        while (count!=-1){
            if(a.charAt(count)=='1' && b.charAt(count)=='1'){
                if(isCarry){
                    isCarry=true;
                    stringBuilder.append("1");
                }
                else{
                    isCarry=true;
                    stringBuilder.append("0");
                }
            }
            else if((a.charAt(count)=='1' && b.charAt(count)=='0') || (a.charAt(count)=='0' && b.charAt(count)=='1')){
                if(isCarry){
                    isCarry=true;
                    stringBuilder.append("0");
                }
                else{
                    isCarry=false;
                    stringBuilder.append("1");
                }
            }
            else if(a.charAt(count)=='0' && b.charAt(count)=='0'){
                if(isCarry){
                    isCarry=false;
                    stringBuilder.append("1");
                }
                else
                    stringBuilder.append("0");
            }
            count--;
        }
        if(isCarry)
            stringBuilder.append("1");
        return stringBuilder.reverse().toString();
    }

    /**
     * append 0 left of the binary string
     * @param str
     * @param noOfTimes
     * @return
     */
    private static String fillZero(String str,int noOfTimes){
        for(int i=0;i<noOfTimes;i++)
            str="0"+str;
        return str;
    }

    /**
     * Given a sorted array of distinct integers and a target value,
     * return the index if the target is found. If not,
     * return the index where it would be if it were inserted in order.
     * Input: nums = [1,3,5,6], target = 5
     * Output: 2
     * Input: nums = [1,3,5,6], target = 2
     * Output: 1
     * Input: nums = [1,3,5,6], target = 7
     * Output: 4
     * You must write an algorithm with O(log n) runtime complexity.
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        int beginIndex=0;
        int lastIndex=nums.length-1;
        while(beginIndex<=lastIndex){
            int mid=(beginIndex+lastIndex)/2;
            if(target<nums[mid])
                lastIndex=mid-1;
            else if(target>nums[mid])
                beginIndex=mid+1;
            else
                return mid;
        }
        return beginIndex;
    }

    /**
     * sorted array
     * 1,2,2 -> 1,2 and k=1
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if(nums.length<=1)
            return nums.length;
        int[] temp=new int[nums.length];
        int index=0;
        for(int i=0;i<nums.length-1;i++)
            if(nums[i]!=nums[i+1])
                temp[index++]=nums[i];
        temp[index++]=nums[nums.length-1];
        for(int i=0;i<index+1;i++){
            nums[i]=temp[i];
        }
        return index;
    }


    public static void IoT83(String[] args) {
        String s ="My name is Shivmohan".toLowerCase();
        Map<Character,Integer> map=new HashMap<>();
        for(char ch:s.toCharArray()){
            if(map.get(ch)!=null)
                map.put(ch,map.get(ch)+1);
            else
                map.put(ch,1);
        }
        System.out.println(map);


        int num =961623;
        int numCopy=num;
        int digit=0;
        // reverse the num
        int rev=0;
        while (numCopy!=0){
            digit=numCopy%10;
            rev= rev*10+digit;
            numCopy/=10;
        }

// then print the digit with its length
        digit=0;
        num=rev;
        int length=0;
        while (num!=0){
            digit=num%10;
            num/=10;
            length++;
            System.out.println(digit);
        }
        System.out.println("Length-"+length); // 961623
    }

    public static String get(String str){
        String[] strings= str.split(" ");
        Arrays.stream(strings).forEach(System.out::println);
        return strings[strings.length-1];
    }
}
