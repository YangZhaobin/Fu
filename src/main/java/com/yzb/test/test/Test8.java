package com.yzb.test.test;

import java.util.*;

public class Test8 {
    public static void main(String[] args) {

//        Scanner sc = new Scanner(System.in);
//        String tt = sc.nextLine();
//        String[] ttArray = tt.split(" ");
//
//        int n = Integer.valueOf(ttArray[0]);
//        int s = Integer.valueOf(ttArray[1]);
//
//        int[] nums = new int[n];
//        String tt2 = sc.nextLine();
//        String[] ttArray2 = tt2.split(" ");
//        for(int i=0;i<n;i++){
//            nums[i] = Integer.valueOf(ttArray2[i]);
//        }
//
//        System.out.println();
//        System.out.println(getMaxCount(s,nums));

        System.out.println(getMaxCount(7, new int[]{2,3,1,2,4,3}));
        System.out.println(getMaxCount(5, new int[]{5, 1, 1, 1, 2, 3}));
    }

    public static int getMaxCount(int s, int[] nums) {
        int len = nums.length;

        int begin = 0;
        int end = -1;

        int sum = 0;
        int res = -1;

        while (begin < len) {
            if (sum <= s) {
                end++;
                if (end >= len) break;
                sum += nums[end];
            }
            else {
                res = Math.max(res, end - begin);
                sum -= nums[begin++];
            }
        }
        if (res == -1) return 0;
        return res;
    }

    public static int findMaxNum(int[] nums,int s){
        int max = 0;
        List<Integer> queue = new LinkedList<Integer>();
        int count = 0;
        int sum = 0;
        for(int i=0;i<nums.length;i++){
            while(sum+nums[i]>s){
                int removed = queue.remove(0);
                sum-=removed;
                count--;
            }
            queue.add(nums[i]);
            sum+=nums[i];
            count++;
            max = Math.max(max,count);
        }
        return max;
    }

}
