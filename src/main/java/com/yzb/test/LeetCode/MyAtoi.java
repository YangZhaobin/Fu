package com.yzb.test.LeetCode;

/**
 * 8 将字符串转换成整数。
 */
public class MyAtoi {
    public static void main(String[] args) {
        System.out.println(myAtoi("9223372036854775808"));
    }
    public static int myAtoi(String str) {
        int n = str.length();
        if (n == 0) return 0;
        boolean less0 = false;
        boolean isBegin = false;

        char[] cs = str.toCharArray();
        long ret = 0;
        for (int i = 0; i < n; i++) {
            if (cs[i] > '9' || cs[i] < '0') {
                if (cs[i] == ' ') {
                    if (!isBegin)
                        continue;
                    else
                        break;
                }
                else if (cs[i] == '+') {
                    if (!isBegin) {
                        isBegin = true;
                        continue;
                    }
                    else
                        break;
                }
                else if (cs[i] == '-') {
                    if (!isBegin) {
                        isBegin = less0 = true;
                        continue;
                    }
                    else
                        break;
                }
                else {
                    if (isBegin)
                        break;
                    else
                        return 0;
                }
            }

            isBegin = true;
            ret = ret * 10 + (cs[i] - '0');
            if (ret > Integer.MAX_VALUE) break;
        }

        ret = less0 ? -ret : ret;
        if (ret > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        if (ret < Integer.MIN_VALUE) return Integer.MIN_VALUE;
        return (int)ret;
    }
}
