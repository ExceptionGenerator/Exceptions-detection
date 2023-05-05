package com.shiv.exception.competative;

public class Algorithm {
    public static void main(String[] args) {
        int[] array={1,3,5,6};
        var algo=new Algorithm();
        long startTime=System.currentTimeMillis();
        System.out.println(algo.searchInsert(array,7));
        System.out.println("Time - "+(System.currentTimeMillis()-startTime)+" ms");
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
}
