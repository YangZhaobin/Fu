package com.yzb.test.test;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Test19 {

    public static void main(String[] args) {
        int[] nums = {2,7,9,3,1};

        System.out.println();
        System.out.println(robD(nums));
    }

    public static int robD(int[] nums) {
        return robDD(nums, nums.length - 1);
    }

    public static int robDD(int[] nums, int index) {
        if (index < 0) return 0;
        if (index == 0) return nums[0];
        if (index == 1) return Math.max(nums[0], nums[1]);

        return Math.max(robDD(nums, index - 2) + nums[index], robDD(nums, index - 1));
    }


    public static int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];
        if (n == 2) return Math.max(nums[0], nums[1]);

        int max1 = nums[0];
        int max2 = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            int max = Math.max(nums[i] + max1, max2);
            max1 = max2;
            max2 = max;
        }

        return max2;
    }

}
