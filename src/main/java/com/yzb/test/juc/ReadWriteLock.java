package com.yzb.test.juc;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLock {
    private int value;
    private static Lock normalLock = new ReentrantLock();
    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private static Lock readLock =  reentrantReadWriteLock.readLock();
    private static Lock writeLock =  reentrantReadWriteLock.writeLock();

    public Object handleRead(Lock lock) {
        lock.lock();
        try {
            TimeUnit.SECONDS.sleep(2);
            System.out.println("read value " + value);
            return value;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }

    public Object handleWrite(Lock lock, int newVal) {
        lock.lock();
        try {
            TimeUnit.SECONDS.sleep(1);
            value = newVal;
            System.out.println("write value " + value);
            return value;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }

    static class ReadRunnable implements Runnable {
        private ReadWriteLock lock;

        public ReadRunnable(ReadWriteLock lock) {
            this.lock = lock;
        }

        public void run() {
            lock.handleRead(readLock);
//            lock.handleRead(normalLock);
        }
    }

    static class WriteRunnable implements Runnable {
        private ReadWriteLock lock;

        public WriteRunnable(ReadWriteLock lock) {
            this.lock = lock;
        }

        public void run() {
            lock.handleWrite(writeLock, new Random().nextInt());
        }
    }

    public static void main(String[] args) {
        ReadWriteLock readWriteLock = new ReadWriteLock();
        ReadRunnable readRunnable = new ReadRunnable(readWriteLock);
        WriteRunnable writeRunnable = new WriteRunnable(readWriteLock);

        for (int i = 0; i < 18; i++) {
            new Thread(readRunnable).start();
        }
        for (int i = 18; i < 20; i++) {
            new Thread(writeRunnable).start();
        }
    }
}
