package com.yzb.test.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Demo {
    public static void main(String[] args) throws Exception {
        System.out.println("Main Begin");

        Class rc = Class.forName("com.yzb.test.reflect.Robot");

        Robot robot = (Robot) rc.newInstance();
        robot.name = "y";
        robot.sayName();

        Constructor constructor = rc.getConstructor();
        Robot robot2 = (Robot) constructor.newInstance();
        robot2.name = "y2";
        robot2.sayName();

        /**
         * getDeclared...  该方法是获取本类中的所有方法/字段，包括私有的(private、protected、默认以及public)。
         * get... 该方法是获取本类以及父类或者父接口中所有的公共方法/字段(public修饰符修饰的)
         */
        Method sayHi = rc.getDeclaredMethod("sayHi", String.class);
        sayHi.setAccessible(true);
        sayHi.invoke(robot, "yyy");

        Field[] fields = rc.getFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }
    }
}
