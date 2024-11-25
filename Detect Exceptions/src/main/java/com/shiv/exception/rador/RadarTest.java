package com.shiv.exception.rador;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RadarTest {
    public static void main(String[] args) {
        IntStream.range(1, 4)
                .takeWhile(counter -> counter != 3)
                .forEach(System.out::println);
//        int[] nums = {0,1,0,3,0,2,12};
//        System.out.println("Before "+ Arrays.toString(nums));
//        moveZeroes(nums);
//        System.out.println("After "+ Arrays.toString(nums));
    }

    /**
     * Input: nums = [0,1,0,3,12]
     * Output: [1,3,12,0,0]
     * Example 2:
     *
     * Input: nums = [0]
     * Output: [0]
     * @param nums
     */
    public static void moveZeroes(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] == 0) {
                    swap(nums, j, j + 1);
                }
            }
        }
    }

    public static int missingNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int finalI = i;
            if (Arrays.stream(nums).asDoubleStream().noneMatch(data -> data == finalI)) {
                return i;
            }
        }
        return nums.length;
    }

    public static void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }
}
