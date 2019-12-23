package com.yzb.test.common;

public interface InterfaceTest {

    final String t = "aaa";

    default void test() {
        System.out.println(t);
    }

    static void ta() {
        System.out.println(t);
    }

}
