package com.yzb.test.jvm;

public class StringCP {
    public static void main(String[] args) {
        String s = new String("abc") + new String("d");

        String s3 = new String("a") + new String("b");

        // 此时ab没有加入常量池  所以s3 == s4
        String s2 = new String("ab" + "cd");

        String s1 = s.intern();
        String s4 = s3.intern();

        System.out.println(s == s1);  // false
        System.out.println(s3 == s4); // true
    }
}
