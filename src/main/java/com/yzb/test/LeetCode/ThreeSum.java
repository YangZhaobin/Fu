package com.yzb.test.LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

    public static void main(String[] args) {
        System.out.println(threeSum(new int[]{-1,0,1,2,-1,-4}));
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        int len = nums.length;
        if (len == 0) return ret;

        Arrays.sort(nums);
        int left = 0;
        int right = len - 1;
        int mid = left + 1;
        while (left < right) {
            int num = nums[mid] + nums[right] + nums[left];
            if (mid == right) {
                while (left + 1 < len && nums[left] == nums[left + 1]) {
                    left++;
                }
                mid = ++left + 1;
                continue;
            }
            if (num == 0) {
                List<Integer> list = new ArrayList<>();
                list.add(nums[left]);
                list.add(nums[mid]);
                list.add(nums[right]);
                ret.add(list);

                while (left + 1 < len && nums[left] == nums[left + 1]) {
                    left++;
                }
                mid = ++left + 1;
                right = len - 1;
                continue;
            }
            else if (num > 0) {
                right--;
            }
            else {
                mid++;
            }
        }

        return ret;
    }
}
