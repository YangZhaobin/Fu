package com.yzb.test.juc;

import java.util.concurrent.locks.ReentrantLock;

public class IntLock extends Thread {
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();
    int lock;

    public IntLock(int lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            if (lock == 1) {
                lock1.lockInterruptibly();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {}
                lock2.lockInterruptibly();
            }
            else{
                lock2.lockInterruptibly();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {}
                lock1.lockInterruptibly();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock1.isHeldByCurrentThread())
                lock1.unlock();
            if (lock2.isHeldByCurrentThread())
                lock2.unlock();

            System.out.println(Thread.currentThread().getId() + " 线程退出。");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        IntLock t1 = new IntLock(1);
        IntLock t2 = new IntLock(0);

        t1.start();
        t2.start();
        Thread.sleep(2000);
        t2.interrupt();
    }
}
