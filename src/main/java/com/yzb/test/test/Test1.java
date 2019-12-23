package com.yzb.test.test;

import java.util.*;

public class Test1 {
    public static void main(String[] args) {
        int[][] int1s = {{1, 2}, {3, 8}, {4, 9}, {2, 10}};
        Arrays.sort(int1s, Comparator.comparingInt(ints -> ints[0]));

        System.out.println(int1s[1][0]);

        List<int[]> list = new ArrayList<>();

        list.add(new int[]{1, 2});
        list.add(new int[]{3, 8});
        list.add(new int[]{2, 9});

        list.sort(Comparator.comparingInt(ints -> ints[0]));

        list.get(1)[0] = 3;
        System.out.println(list.get(1)[0]);

        int[][] ints = list.toArray(new int[0][0]);
    }

    public static int testTry() {
        try {
            System.out.println("try");
            int i = 1 / 0;
            return 1;
        } catch (Exception e) {
            System.out.println("catch");
            return 2;
        } finally {
            System.out.println("finally");
            return 3;
        }
    }

    private String t;

    public void tt() {
        Test1 test1 = new Test1();
        String t = test1.t;
    }

    /**
     *  递归先报StackOverflowError错误
     */
    public static void test() {
        test();
    }
}
