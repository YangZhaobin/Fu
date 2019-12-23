package com.yzb.test.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReenterLockTest implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();

    public void run() {
        try {
            lock.tryLock(20, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println("Sub Thread Interrupted");
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReenterLockTest t = new ReenterLockTest();
        Thread thread = new Thread(t);

        lock.lock();
        System.out.println("Main Thread has lock");
        TimeUnit.SECONDS.sleep(2);

        thread.start();

        TimeUnit.SECONDS.sleep(2);
        int i = 1 / 0;
//        thread.interrupt();
    }
}
