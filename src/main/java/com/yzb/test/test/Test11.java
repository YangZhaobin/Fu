package com.yzb.test.test;

import java.util.*;

public class Test11 {
    public static void main(String[] args) {
        System.out.println(permute(new int[]{3, 3, 0, 3}));
    }

    public static List<List<Integer>> ret;
    public static List<List<Integer>> permute(int[] nums) {
        ret = new ArrayList<>();

        Arrays.sort(nums);

        int[] used = new int[nums.length];
        if (0 != nums.length)
            backtrace(nums, used, new ArrayList<>());

        return ret;
    }

    public static void backtrace(int[] nums, int[] used, List<Integer> list) {
        if (list.size() == nums.length) {
            ret.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i] == 1) continue;

            String u = "[" + used[0] + ", " + used[1] + ", " + used[2] + "]";

            if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == 1) {
                System.out.println("=== i : " + i + "  nums[i]:  " + nums[i] + "  used " + u + "  list:  " + list);
                continue;
            }
//            boolean f = false;
//            for (int j = 0; j < i; j++) {
//                if (nums[i] == nums[j] && used[j] == 1) {
//                    System.out.println("dup == i : " + i + "  nums[i]:  " + nums[i] + "  nums[j]:  " + nums[j]);
//                    f = true;
//                    break;
//                }
//            }
//            if (f) {
//                continue;
//            }

            list.add(nums[i]);
            System.out.println("i : " + i + "  nums[i]:  " + nums[i] + "  used " + u + "  list:  " + list);
            used[i] = 1;
            backtrace(nums, used, list);
            list.remove(list.size() - 1);
            used[i] = 0;
        }
    }
}
