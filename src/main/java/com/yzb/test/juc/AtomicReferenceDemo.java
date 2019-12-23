package com.yzb.test.juc;

import com.yzb.test.common.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceDemo {
    static User user = new User("yang", 0);
    static AtomicReference<User> ar = new AtomicReference(user);
    static AtomicReference<Integer> ar1 = new AtomicReference(0);
    static AtomicInteger ai = new AtomicInteger(0);

    public static class ChangeAgeThread implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                ar.getAndUpdate((t) -> {
                    User user = new User();
                    user.setName(t.getName());
                    user.setAge(t.getAge() + 1);
                    return user;
                });
                ar1.getAndUpdate((t) -> {
                    t++;
                    return t;
                });

                ai.getAndIncrement();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] ts = new Thread[10];
        for (int i = 0; i < 10; i++) {
            ts[i] = new Thread(new ChangeAgeThread());
        }

        for (int i = 0; i < 10; i++) {
            ts[i].start();
        }
        for (int i = 0; i < 10; i++) {
            ts[i].join();
        }

        System.out.println(ar.get());
        System.out.println(ar1.get());
        System.out.println(ai.get());

        User user = new User("yang", 0);
        User user3 = new User("yang1", 0);
        AtomicReference<User> aUser = new AtomicReference(user);
        User user1 = user;
        user1.setAge(2);
        aUser.compareAndSet(user1, user3);
        System.out.println(aUser);
    }
}
