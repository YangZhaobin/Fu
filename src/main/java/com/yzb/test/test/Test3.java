package com.yzb.test.test;

public class Test3 {
    public static void main(String[] args) {

        String str1 = new StringBuilder().append("ja").append("va").toString();
        String str2 = new StringBuilder().append("yang").append("zhaobin").toString();
        String str3 = new StringBuilder().append("lo").append("ng").toString();
        String str4 = new StringBuilder().append("i").append("nt").toString();
        String str5 = new String("yangzhaobin");

        System.out.println(str1.intern() == str1);  // false
        System.out.println(str2.intern() == str2);  // true
        System.out.println(str3.intern() == str3);  // false
        System.out.println(str4.intern() == str4);  // false
        System.out.println(str2.intern() == str5);  // false
    }
}
