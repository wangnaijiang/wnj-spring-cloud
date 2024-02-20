package com.wnj.util;

import java.util.Arrays;

public class SortTest {
    public int[] sortArray(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len -1; i++) {
            int mixIndex = i;
            for (int j = i+1 ; j < len; j++) {
                if(nums[mixIndex] > nums[j]){
                    mixIndex = j;
                }
            }
            if(mixIndex != i){
                System.out.println("swap " + i + ", mixIndex="+mixIndex);
                swapValue(nums, i, mixIndex);
            }else{
                System.out.println("no swap "+ i);
            }
        }
        return nums;
    }

    private void swapValue(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    public static void main(String[] args) {
        int[] nums = {0, 3, 5, 2, 1};
        SortTest test = new SortTest();
        int[] results = test.sortArray(nums);
        System.out.println(Arrays.toString(nums));
        System.out.println(Arrays.toString(results));
    }
}
