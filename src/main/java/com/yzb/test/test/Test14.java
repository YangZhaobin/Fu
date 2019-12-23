package com.yzb.test.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test14 {
    public static void main(String[] args) {
        System.out.println(partition("aab"));
    }

    static List<List<String>> res;
    public static List<List<String>> partition(String s) {
        res = new ArrayList<>();

        backtrack(s, 0, new ArrayList<>());

        return res;
    }

    static void backtrack(String string, int start, List<String> path) {
        int len = string.length();
        if (start == len) {
            if (path.size() > 0)
                res.add(path);
            return;
        }

        for (int i = start; i < len; i++) {
            String t = string.substring(start, i + 1);
            if (isPalindrome(t)) {
                path.add(t);
                backtrack(string, i + 1, new ArrayList<>(path));
                path.remove(path.size() - 1);
            }
        }
    }

    static boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) return false;
        };
        return true;
    }

}
