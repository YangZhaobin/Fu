package com.yzb.test.test;

import java.util.*;

public class Test5 {
    public static void main(String[] args) {
        Integer i1 = 1;
        Integer i2 = 2;
        Integer i3 = 3;
        Integer i4 = 3;
        Integer i5 = 321;
        Integer i6 = 321;
        Long l = 3L;
        System.out.println(i3 == i4);   // true
        System.out.println(i5 == i6);   // false
        System.out.println(i3 == (i1 + i2));    // true
        System.out.println(i3.equals(i1 + i2)); // true
        System.out.println(l == (i1 + i2));    // true
        System.out.println(l.equals(i1 + i2)); // false

    }
}
