package com.yzb.test.LeetCode;

/**
 * 正则表达式匹配
 *
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 *
 *      - '.' 匹配任意单个字符
 *      - '*' 匹配零个或多个前面的那一个元素
 */
public class IsMatch {
    public static void main(String[] args) {
        System.out.println(isMatch("aab", "c*a*b"));
    }

    public static boolean isMatch(String s, String p) {
        return dp(s.toCharArray(),  p.toCharArray());
    }

    public static boolean dp(char[] ss, char[] ps) {
        int lenS = ss.length;
        int lenP = ps.length;

        // dp[i][j] 表示ss的前i位和ps的前j位是否匹配
        boolean[][] dp = new boolean[lenS + 1][lenP + 1];
        for (int j = 1; j <= lenP; j++) {
            dp[0][j] = ps[j - 1] == '*' && dp[0][j - 1];
        }
        dp[0][0] = true;

        for (int i = 1; i <= lenS; i++) {
            for (int j = 1; j <= lenP; j++) {
                if (ps[j - 1] == '*') {
                    dp[i][j] = dp[i][j - 2]
                            || ((ss[i - 1] == ps[j - 2] || ps[j - 2] == '.')
                                && dp[i - 1][j]);
                }
                else {
                    dp[i][j] = (ss[i - 1] == ps[j - 1] || ps[j - 1] == '.')
                            && dp[i - 1][j - 1];
                }
            }
        }
        return dp[lenS][lenP];
    }
}
