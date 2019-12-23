package com.yzb.test.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReenterLockCondition implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();

    public void run() {
        try {
            lock.lock();
            condition.await();
            System.out.println("Thread is going on");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReenterLockCondition t = new ReenterLockCondition();
        Thread thread = new Thread(t);
        thread.start();

        TimeUnit.SECONDS.sleep(2);

        lock.lock();
        condition.signal();
        System.out.println("let another thread go");
        lock.unlock();
    }
}
