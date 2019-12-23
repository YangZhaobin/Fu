package com.yzb.test.common;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Test  {

    public static int test () {
        System.out.println("test!");
        return 3;
    }

    public static int test1 () {
        try {
            return test();
        } catch (Exception e) {

        } finally {
            System.out.println("final");
        }
        return 1;
    }

    public static void main(String[] args) {
        System.out.println(test1());
    }

}
