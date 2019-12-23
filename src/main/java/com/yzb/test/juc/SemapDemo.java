package com.yzb.test.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemapDemo implements Runnable {
    final Semaphore semaphore = new Semaphore(5);

    @Override
    public void run() {
        try {
            semaphore.acquire();
            TimeUnit.SECONDS.sleep(2);
            int i = 1 / 0;

        } catch (Exception e) {
//            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getId() + ": done!");
            semaphore.release();
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(20);
        SemapDemo semapDemo = new SemapDemo();
        for (int i = 0; i < 20; i++) {
            exec.submit(semapDemo);
        }
    }
}
