package com.yzb.test.classloader;

import com.yzb.test.reflect.Robot;

public class MyClassLoaderTest {
    public static void main(String[] args) throws Exception {

//        Class rc1 = Class.forName("com.yzb.test.reflect.Robot");
//        Class rc2 = ClassLoader.getSystemClassLoader().loadClass("com.yzb.test.reflect.Robot");
//        System.out.println(rc1 == rc2);

        MyClassLoader myClassLoader = new MyClassLoader("C:\\Users\\41905\\Desktop\\", "myClassLoader");
        Class<?> test = myClassLoader.loadClass("Test");

        test.newInstance();
    }
}
