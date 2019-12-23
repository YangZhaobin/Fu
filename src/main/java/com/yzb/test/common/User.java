package com.yzb.test.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Collections;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    String name;
    Integer age;

    public static void main(String[] args) {
//        Integer[] integers = {1, 2, 3, 4, 5};
//        Integer[] integers1 = {0, 0, 0, 0, 0};
//
//        System.arraycopy(integers, 1, integers1, 2, 2);
//        System.out.println(Arrays.asList(integers1));
//        System.out.println(Arrays.asList(Arrays.copyOf(integers1, 10)));

        final User user = new User();
        user.setName("y");
        user.setAge(12);

        System.out.println(user);

        user.setName("yang");
        System.out.println(user);
    }

    public static void foo(User user) {
        user = new User("hh", 22);
    }
    public static void foo(Integer i) {
        i = 300;
    }
}
