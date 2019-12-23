package com.yzb.test.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Test7 {
    public static void main(String[] args) {
        System.out.println(numDecodings("226"));
    }

    public static int numDecodings(String s) {
        int len = s.length();
        if (len == 0) return len;

        int[] counts = new int[len + 1];
        counts[0] = 0;
        for (int i = 1; i <= len; i++) {
            char c = s.charAt(i - 1);
            if (i == 1) {
                if (c == '0')
                    return 0;
                else
                    counts[i] = 1;
                continue;
            }

            char c1 = s.charAt(i - 2);
            int n = Integer.parseInt(c1 + "" + c);
            counts[i] = counts[i - 1];

            if (c == '0') {
                if (c1 == '0') return 0;
                if (n >= 1 && n <= 26)
                    if (counts[i - 2] != 0)
                        counts[i] = counts[i - 2];
                    else
                        counts[i] = 1;
                else
                    return 0;
                continue;
            }
            if (c1 == '0') {
                counts[i] = counts[i - 1];
            }
            else {
                if (n >= 1 && n <= 26) {
                    if (counts[i - 2] != 0)
                        counts[i] += counts[i - 2];
                    else
                        counts[i] += 1;
                }
            }
        }

        return counts[len];
    }
}
