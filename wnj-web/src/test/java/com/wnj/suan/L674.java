package com.wnj.suan;

class L674 {
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,5,7,4,9};
        System.out.println(findLengthOfLCIS(arr));
    }
    public static int findLengthOfLCIS(int[] nums) {
        if(nums.length <= 1)
            return nums.length;
        int ans = 1;
        int count = 1;
        for(int i=0;i<nums.length-1;i++) {
            if(nums[i+1] > nums[i]) {
                count++;
            } else {  
                count = 1;
            }
            ans = count > ans ? count : ans;
        }
        return ans;
    }
}