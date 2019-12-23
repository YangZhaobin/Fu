package com.yzb.test.test;

import java.util.Arrays;

public class Test6 {
    public static void main(String[] args) {
        // 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。
        // 找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。

        // 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
        // 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
        System.out.println(threeSumClosest(new int[]{1,-3,3,5,4,1}, 1));
    }

    public static int threeSumClosest(int[] nums, int target) {
        int len = nums.length;
        if (len == 3) {
            return nums[0] + nums[1] + nums[2];
        }
        Arrays.sort(nums);
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < len - 2; i++) {
            int left = i + 1;
            int right = len - 1;

            while (left < right) {
                int count = nums[left] + nums[right] + nums[i];
                if (count == target) {
                    return target;
                }
                int r = count - target;
                if (Math.abs(res) > Math.abs(r)) {
                    res = r;
                }
                if (count < target) {
                    left++;
                }
                else {
                    right--;
                }
            }
        }
        return res + target;
    }
}
