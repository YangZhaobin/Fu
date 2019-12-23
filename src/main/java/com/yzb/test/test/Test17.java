package com.yzb.test.test;

import java.util.ArrayList;
import java.util.List;

/**
 * 青蛙 🐸 跳台阶的问题：一只青蛙一次可以跳上1级台阶，也可以跳上 2 级。求该青蛙跳上一个 n 级的台阶总共有多少种跳法？
 */
public class Test17 {

    static List<List<String>> ret;
    static Test17 test = new Test17();
    static Test17 test2 = new Test17();

    public Test17() {
        System.out.println("构造");
    }

    static {
        System.out.println("静态");
    }
    public static void main(String[] args) {
        Test17 test3 = new Test17();
//        System.out.println(solveNQueens(4));
    }

    public static List<List<String>> solveNQueens(int n) {
        ret = new ArrayList<>();
        if (n != 0)
            backtrace(n, 0, new ArrayList<>());
        return ret;
    }


    public static void backtrace(int n, int start, List<String> list) {
        if (list.size() == n) {
            ret.add(new ArrayList<>(list));
            return;
        }

        if (list.size() < start) return;

        for (int index = 0; index < n; index++) {
            boolean f = true;
            for (int i = 0; i < start; i++) {
                int j = list.get(i).indexOf('Q');

                // 不能在同一行
                if (index == j) {
                    f = false;
                    break;
                }

                // 不能在同一对角线
                if (start + index == i + j) {
                    f = false;
                    break;
                }
                if ((start - index) == (i - j)) {
                    f = false;
                    break;
                }
            }

            if (!f) continue;
            String str = "";
            for (int ii = 0; ii < n; ii++) {
                str += (ii == index) ? "Q" : ".";
            }
            list.add(str);
            backtrace(n, start + 1, list);
            list.remove(list.size() - 1);
        }
    }
}
