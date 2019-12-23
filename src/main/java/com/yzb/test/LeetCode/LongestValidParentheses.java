package com.yzb.test.LeetCode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LongestValidParentheses {

    public static void main(String[] args) {

        System.out.println(1 / 2 * 2);
    }

    public static int longestValidParentheses(String s) {
        int len = s.length();
        if (len < 2) return 0;

        // dp[i] 存储以第i个字符结尾的最长有效括号的长度
        int[] dp = new int[len];
        int max = 0;
        for (int i = 1; i < len; i++) {
            if (s.charAt(i) == ')') {
                int pre = i - 1 - dp[i - 1];
                if (pre >= 0 && s.charAt(pre) == '(') {
                    dp[i] = dp[i - 1] + 2;

                    // 处理独立的括号对的情形 类似()()、()(())
                    if (pre > 0) {
                        dp[i] += dp[pre - 1];
                    }
                    max = Math.max(max, dp[i]);
                }
            }
        }
        return max;
    }
}
