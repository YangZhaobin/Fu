package com.yzb.test.LeetCode;

public class CountAndSay {

    public static void main(String[] args) {
//        System.out.println(countAndSay(2));
        System.out.println(say("1211"));
    }

    public static String countAndSay(int n) {

        if (n < 1) return "";

        String ret = "1";
        for (int i = 1; i < n; i++) {
            ret = say(ret);
        }
        return ret;
    }

    public static String say(String str) {
        char[] cs = str.toCharArray();
        int len = cs.length;
        String ret = "";

        int count = 1;
        for (int i = 1; i < len; i++) {
            if (cs[i] == cs[i - 1]) {
                count++;
            }
            else {
                ret += count + "" +  cs[i - 1];
                count = 1;
            }
        }
        ret += count + "" + cs[len - 1];
        return ret;
    }
}
