package com.yzb.test.test;


public class Test22 {
    public static void main(String[] args) {

        String str1 = new String("abc");
        str1 = str1.intern();
        String str2 = "abc";
        System.out.println(str1 == str2);

        String str4 = new String("s") + new String("tr");
        String str3 = "str" + "ing";
        String str5 = str4.intern();
        System.out.println(str4 == str5);
    }
}
