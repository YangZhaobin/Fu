package com.yzb.test.reflect;

public class Robot {

    static {
        System.out.println("Robot Class Init");
    }

    String name;

    public void sayName() {
        System.out.println(name);
    }

    private void sayHi(String string) {
        System.out.println("Hi, " + string);
    }
}
