package com.yzb.test.thread;

import java.util.concurrent.locks.ReentrantLock;

public class ThreadTest {

    public static void main(String[] args) {

        ReentrantLock lock = new ReentrantLock(true);
        lock.tryLock();

    }
}
