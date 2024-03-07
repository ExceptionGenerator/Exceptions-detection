package com.shiv.exception.competative;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class Algorithm {
    public static void main1(String[] args) {
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

    /**
     * Example 1:
     *
     * Input: s = "aab" aba
     * Output: "aba"
     *
     * Example 2:
     *
     * Input: s = "aaab"
     * Output: ""
     * @param s
     * @return
     */
    public static String reOrganize(String s){
        char[] chars=s.toCharArray();
        for(int i=0;i<chars.length;i++){
            if(chars[i]==chars[i+1]){
                swap(chars,i+1,i+2);
                if (chars[i]==chars[i+1])
                    return "";
            }
        }
        return chars.toString();
    }



    private static void swap(char[] chars,int firstIndex,int secondIndex){
        char temp=chars[firstIndex];
        chars[firstIndex]=chars[secondIndex];
        chars[secondIndex]=temp;
    }

    /**
     * [3,2,3] >> 6 >> [0,2]
     1 2 3 5
     1>>2
     2>>3
     3>>5
     */
    public static int[] twoSum(int[] nums, int target) {
        int[] indices=new int[2];
        for(int j=0;j<nums.length;j++){
            for(int i=0;i<nums.length;i++){
                if(i!=j && (nums[j]+nums[i]) == target){
                    indices[0]=j;
                    indices[1]=i;
                    return indices;
                }
            }
        }
        return indices;
    }

    public static void main1w(String[] args) {
//        ListNode listNode=new ListNode(2);
//        listNode.next=new ListNode(4);
//        listNode.next.next=new ListNode(3);
//        ListNode listNode2=new ListNode(5);
//        listNode2.next=new ListNode(0);
//        listNode2.next.next=new ListNode(32);
//        addTwoNumbers(listNode,listNode2);
        System.out.println(removeAdjacentCharacter("bbbbb"));
    }
    /**
     * derddrtrrf derdrtrf
     * @param string
     * @return
     */
    private static String removeAdjacentCharacter(String string){
        StringBuilder stringBuilder=new StringBuilder();
        for(int i=0;i<string.length()-1;i++){
            if(!stringBuilder.isEmpty() && stringBuilder.charAt(stringBuilder.length()-1)==string.charAt(i+1)){
                stringBuilder.append(string.charAt(i));
                i++;
            }else
                stringBuilder.append(string.charAt(i));
        }
        if(!stringBuilder.isEmpty() && stringBuilder.toString().charAt(stringBuilder.length()-1)!=string.charAt(string.length()-1))
            stringBuilder.append(string.charAt(string.length()-1));
        return stringBuilder.toString();
    }

    /**
     * Given a string s, find the length of the longest
     * substring
     * without repeating characters.
     * Input: s = "abcabcbb"
     * Output: 3
     * Explanation: The answer is "abc", with the length of 3.
     * Example 2:
     * <p>
     * Input: s = "bbbbb"
     * Output: 1
     * Explanation: The answer is "b", with the length of 1.
     * Example 3:
     * <p>
     * Input: s = "pwwkew"
     * Output: 3
     * Explanation: The answer is "wke", with the length of 3.
     * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        String removedAdjacentString=removeAdjacentCharacter(s);
        return 0;
    }



    /**
     * Input: l1 = [2,4,3], l2 = [5,6,4]
     * Output: [7,0,8]
     * Explanation: 342 + 465 = 807.
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        BigInteger bigInteger1=new BigInteger(readElements(l1));
        BigInteger bigInteger2=new BigInteger(readElements(l2));
        BigInteger sum=bigInteger1.add(bigInteger2);
        String[] result=new StringBuilder(""+sum).reverse().toString().split("");
        ListNode listNode=new ListNode(Integer.parseInt(result[0]));
        for(int i=1;i<result.length;i++)
            insert(listNode,Integer.parseInt(result[i]));
        return listNode;
    }

    private static void insert(ListNode root,int value){
        ListNode temp=root;
        while (temp.next!=null){
            temp=temp.next;
        }
        temp.next=new ListNode(value);
    }
    private static String readElements(ListNode listNode){
        ListNode temp=listNode;
        StringBuilder stringBuilder=new StringBuilder();
        while(temp!=null){
            System.out.println(temp.val);
            stringBuilder.append(temp.val);
            temp=temp.next;
        }
        return stringBuilder.reverse().toString();
    }

    /**
     * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
     *
     * The overall run time complexity should be O(log (m+n)).
     *
     *
     *
     * Example 1:
     *
     * Input: nums1 = [1,3], nums2 = [2,4,5]
     * Output: 2.00000
     * Explanation: merged array = [1,2,3] and median is 2.
     *
     * Example 2:
     *
     * Input: nums1 = [1,2], nums2 = [3,4]
     * Output: 2.50000
     * Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        List<Integer> list=new ArrayList<>();
        Arrays.stream(nums1).forEach(list::add);
        Arrays.stream(nums2).forEach(list::add);
        Collections.sort(list);
        int sumOfLength=nums1.length+nums2.length;
        int index=sumOfLength/2;
        if(sumOfLength%2!=0){
            return list.get(index);
        }else {
            double lastValue=list.get(index);
            double initValue=list.get(index-1);
            return (initValue+lastValue)/2;
        }
    }
    public static int countPrimes(int n) {
        if(n<2)
            return 0;
        int primeCount=0;
        for(int i=2;i<n;i++){
            if(isPrimeNumber(i))
                primeCount++;
        }
        return primeCount;
    }

    private static boolean isPrimeNumber(int n){
        for(int i=n;i>1;i--){
            if(n!=i && n%i==0)
                return false;
        }
        return true;
    }




    /**
     *  1 2 3
     *  3 2 1
     *
     *       1 2 3
     *     2 4 6
     *   3 6 9
     *
     *
     *   3 9 4 8 3
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        List<Integer> list=new ArrayList<>();
        list.sort(Comparator.comparingInt(Integer::intValue).reversed());

//        CountDownLatch countDownLatch=new CountDownLatch(1);
//        countDownLatch.await(3,TimeUnit.SECONDS);
//        AtomicLong atomicLong=new AtomicLong();
//
        FileSystem fileSystem=new FileSystem(true,new ArrayList<>(),0);

        FileSystem fileSystem1=new FileSystem(true,null,0);
        FileSystem fileSystem2=new FileSystem(false,null,5);
        FileSystem fileSystem3=new FileSystem(false,null,8);
        FileSystem fileSystem4=new FileSystem(true,null,0);
        List<FileSystem> fileSystems=new ArrayList<>();
        fileSystems.add(fileSystem1);
        fileSystems.add(fileSystem2);
        fileSystems.add(fileSystem3);
        fileSystems.add(fileSystem4);

        fileSystem.setContents(fileSystems);

        List<FileSystem> fileSystems1=new ArrayList<>();
        fileSystem1.setContents(fileSystems1);
        FileSystem fileSystem5=new FileSystem(false,null,4);
        FileSystem fileSystem6=new FileSystem(false,null,48);
        fileSystems1.add(fileSystem5);
        fileSystems1.add(fileSystem6);
        int size= getSize(fileSystem);
        System.out.println("Sum of files size - "+size);
        System.out.println("Sum of files size test case - "+(size==65));


//        System.out.println(countPrimes(10));
//        if(System.out.printf("Hello world")==null){}
//        long current=System.currentTimeMillis();
//        System.out.println(findMedianSortedArrays(new int[]{1,3,3,4,5,5,6},new int[]{}));
//        long end=System.currentTimeMillis();
//        System.out.println("ms-"+(end-current));
//        List<ListNode> list=new ArrayList<>();
//        list.add(new ListNode(2));
//        list.add(new ListNode(3));
//        list.add(new ListNode(4));
//        list.parallelStream().map(listNode -> listNode.val=listNode.val*3)
//                .forEach(System.out::println);
//        list.parallelStream().map(listNode -> listNode.val=listNode.val*3).forEach(System.out::println);
//        list.parallelStream().map(listNode -> listNode.val=listNode.val*3).forEach(System.out::println);
//        list.parallelStream().map(listNode -> listNode.val=listNode.val*3).forEach(System.out::println);
//        ListNode listNode=new ListNode(1);
//        listNode.next=new ListNode(2);
//        listNode.next.next=new ListNode(3);
//        listNode.next.next.next=new ListNode(4);
//        listNode.next.next.next.next=new ListNode(5);
//        System.out.println(removeNthFromEnd(listNode,2));
    }

    /**
     *
     * @param head [1,2], 2 -> len=2,n=2  -> nth=len-n
     *                                      -> sub=2-2 -> nth=2-0=2th element should be deleted
     * @param n len-n
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        int length=getLength(head);
        if(length==1 && n==1)
            return null;
        if(length==n)
            head=head.next;
        length-=n;
        int count=0;
        ListNode tempNode=head;
        while (tempNode!=null){
            if(count==length-1){
                tempNode.next=tempNode.next.next;
                break;
            }
            tempNode=tempNode.next;
            count++;
        }
        return head;
    }

    private static int getLength(ListNode listNode){
        ListNode tempNode=listNode;
        int length=0;
        while (tempNode!=null){
            length++;
            tempNode=tempNode.next;
        }
        return length;
    }

    public static void getSizeOfAllFileSystems (final FileSystem fileSystem,
            final AtomicLong atomicLong) {
        if (!fileSystem.isDirectory())
            atomicLong.set(atomicLong.get() + fileSystem.getSize());
        if (fileSystem.isDirectory() && fileSystem.getContents() != null) {
            fileSystem.getContents()
                    .forEach(content -> {
                        if (content.isDirectory())
                            getSizeOfAllFileSystems(content, atomicLong);
                        else
                            atomicLong.set(atomicLong.get() + content.getSize());
                    });
        }
    }

    public static int getSize (FileSystem fileSystem) {
        if (!fileSystem.isDirectory)
            return fileSystem.size;
        int size = 0;
        if (fileSystem.contents != null)
            for (FileSystem fileSystem1 : fileSystem.contents) {
                size += getSize(fileSystem1);
            }
        return size;
    }

}

@Data
@AllArgsConstructor
@NoArgsConstructor
class FileSystem{
    boolean isDirectory;
    List<FileSystem> contents;
    int size;  // if isDirectory = true then size will be 0
}

class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) {
         this.val = val;
     }
     ListNode(int val, ListNode next) {
         this.val = val;
         this.next = next;
     }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}
