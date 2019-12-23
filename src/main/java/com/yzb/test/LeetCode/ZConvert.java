package com.yzb.test.LeetCode;

public class ZConvert {
    public static void main(String[] args) {
        System.out.println(convert("PAYPALISHIRING", 3));
    }
    public static String convert(String s, int numRows) {
        if (numRows == 0) return "";
        if (numRows == 1) return s;

        int len = s.length();
        if (len == 0) return "";

        String ret = "";
        int step = (2 * (numRows - 1));
        for (int i = 0; i < numRows; i++) {
            int subStep = step - i * 2;
            if (subStep == 0) subStep = step;
            for (int j = i; j < len;) {
                System.out.println("-- " + j + "   " + s.charAt(j));
                ret += s.charAt(j);
                j += subStep;

                if (subStep != step) subStep = step - subStep;
            }
        }
        return ret;
    }
}
