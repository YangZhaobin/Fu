package com.yzb.test.test;

import java.util.ArrayList;
import java.util.List;

/**
 * 青蛙 🐸 跳台阶的问题：一只青蛙一次可以跳上1级台阶，也可以跳上 2 级。求该青蛙跳上一个 n 级的台阶总共有多少种跳法？
 */
public class Test16 {
    public static void main(String[] args) {
        System.out.println(jump(4));
        System.out.println(ret);
    }

    public static int count;
    public static List<List<Integer>> ret = new ArrayList<>();
    public static int jump(int n) {
        count = 0;
//        backtraceing(n);

        backtraceing(0, n, new ArrayList<>());

//        count = f(n);
        return count;
    }

    /**
     * 分治法
     */
    public static int f(int n) {
        // 边界条件（解决）
        // 当台阶数目小于1时，那么就返回0种方案数量
        if (n < 1) return 0;
        // 当台阶数目为1时，问题的规模已经足够小，我们可以直接想出他的方案数量—— 即1种：1步
        if (n == 1) return 1;
        // 当台阶数目为2时，他的方案数量为2种，即—— 1步+1步，2步
        if (n == 2) return 2;

        // 分解并合并
        return f(n - 1) + f(n - 2);
    }

    /**
     * 回溯法
     */
    public static void backtraceing(int target) {
        if (target <= 2) {
            count += target; // 当剩余一个台阶是即累加一种方案，剩余两个台阶时累加两种方案
            return;
        }

        // 下面是两个基本点选择一步和选择两步
        // 选择一步
        backtraceing(target - 1);
        // 选择两步
        backtraceing(target - 2);
    }

    /**
     * 回溯法 计算所有路径
     */
    public static void backtraceing(int start, int n, List<Integer> list) {
        if (start > n) return;
        if (start == n) {
            count++;
            ret.add(list);
            return;
        }

        for (int i = 1; i <= 2; i++) {
            list.add(i);
            backtraceing(start + i, n, new ArrayList<>(list));

            list.remove(list.size() - 1);
        }
    }
}
