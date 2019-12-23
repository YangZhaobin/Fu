package com.yzb.test.test;

import java.util.Arrays;

public class Test21 {
    public static void main(String[] args) {
        System.out.println(Class3.val);
        Class1[] class1s = new Class1[2];

        int[] is  = new int[] {6, 4, 7, 3, 2};
        Arrays.sort(is, 2, 5);
        for (int i = 0; i < 5; i++) {
            System.out.print(is[i] + "\f");
        }
    }

    public static class Class1 {
        static {
            System.out.println("Class1 init");
        }
    }

    public static class Class2 extends Class1 {
        static {
            System.out.println("Class2 init");
        }

        static final int val = 1;
    }

    public static class Class3 extends Class2 {
        static {
            System.out.println("Class3 init");
        }
    }
}
