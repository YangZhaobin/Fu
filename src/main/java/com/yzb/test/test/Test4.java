package com.yzb.test.test;

import java.util.*;

public class Test4 {
    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        List<String> list = new ArrayList<>();

        System.out.println(happyNumber(1 ));
    }

    static Map<Integer, Integer> m = new HashMap<>();
    public static boolean happyNumber(int number) {
        if (m.get(number) != null) {
            return false;
        }
        else {
            int count = getCount(number);
            if (isSuccess(count)) return true;
            else {
                m.put(number, count);
                return happyNumber(count);
            }
        }
    }

    public static int getCount(int number) {
        int count = 0;
        char[] cs = (number + "").toCharArray();
        for (char c : cs) {
            count += Math.pow(Integer.parseInt(c + ""), 2);
        }
        return count;
    }

    public static boolean isSuccess(int count) {
        double v = Math.log10(count);
        return (v % 1 == 0);
    }
}
