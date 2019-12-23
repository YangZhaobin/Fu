package com.yzb.test.LeetCode;

/**
 * 01背包问题
 *
 * 背包容量为c  n个物品的价值为vals[n]， 重量为weights[n]
 * 背包能状态的最大价值是多少
 */
public class Package {
    public static void main(String[] args) {
//        System.out.println(maxValue2(
//            new int[]{6, 10, 12},
//            new int[]{1, 2, 3},
//            5
//        ));
    }

    public static int maxValue(int[] vals, int[] weights, int c) {
        int n = vals.length;
        if (n == 0) return 0;

        // values[i][j] 表示前i个元素在容量j下能够装载的最大价值
        int[][] values = new int[n][c + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < c + 1; j++) {
                if (j == 0) {
                    values[i][j] = 0;
                }
                else if (i == 0) {
                    values[i][j] = weights[i] <= j ? vals[i] : 0;
                }
                else {
                    values[i][j] = values[i - 1][j];
                    if (j - weights[i] >= 0)
                        values[i][j] = Math.max(
                            values[i][j],
                            vals[i] + values[i - 1][j - weights[i]]
                        );
                }
            }
        }

        return values[n - 1][c];
    }

    public static int maxValue2(int[] vals, int[] weights, int c) {
        int n = vals.length;
        if (n == 0) return 0;

        // values[j] 表示前i个元素在容量j下能够装载的最大价值
        int[] values = new int[c + 1];
        values[0] = 0;
        for (int j = 0; j <= c; j++)
            // 初始化第0个物品的信息
            values[j] = weights[0] <= j ? vals[0] : 0;

        /**
         * 真TM难懂 随缘吧
         */
        for (int i = 1; i < n; i++) {
            for (int j = c; j >= weights[i]; j--) {
                values[j] = Math.max(values[j], vals[i] + values[j - weights[i]]);
            }
        }

        return values[c];
    }
}
