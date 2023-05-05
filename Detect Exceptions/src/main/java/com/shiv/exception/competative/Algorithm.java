package com.shiv.exception.competative;

public class Algorithm {
    public static void main(String[] args) {

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
        if(target<nums[0])
            return 0;
        if(target>nums[nums.length-1])
            return nums.length;
        for(int i=0;i<nums.length-1;i++){
            if(nums[i]==target)
                return i;
            else{
                if(target>nums[i] && target<nums[i+1])
                    return i+1;
            }
        }
        return 0;
    }
}
