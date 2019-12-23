package com.yzb.test.test;

public class Test2 {
    public static void main(String[] args) {
//        String s = new String("1");
//        s.intern();
//        String s2 = "1";
//        System.out.println(s == s2); // jdk1.6: false  jdk1.7: false
//
//        String s3 = new String("1") + new String("1");
//        String s4 = "11";
//        s3.intern();
//        System.out.println(s3 == s4); // jdk1.6: false  jdk1.7: true

        String a = "1";
//        String s = new String("a") + new String("b");  // 在堆中创建"ab"对象
//        String s1 = a + "a" + "b";  // jvm进行优化，将在堆中再次创建"ab"并将其地址放入常量池
//        String s2 = s.intern(); // s2为s1中创建的"ab"的引用
        // 如果s1不在常量池中添加"ab"的话，s2为s在堆中的引用  此时应该为true
        // 否则s2为在s1中创建的"ab"的引用 不与s相等
//        System.out.println(s == s2);  // false


        String s = new String("a") + new String("b");

        String s1 = new String("ab" + "cd");
        String s2 = s.intern();
        String s3 = "ab";

        System.out.println(s == s2);
        System.out.println(s3 == s2);
    }
}
