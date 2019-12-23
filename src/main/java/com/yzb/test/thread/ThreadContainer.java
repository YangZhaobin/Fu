package com.yzb.test.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现一个容器，提供两个方法 add， size
 * 写两个线程，线程1添加元素，线程2监控元素的个数
 * 当个数到指定数目时，线程2给出提示并结束
 *
 *
 *    CountDownLatch
 *
 */
public class ThreadContainer {
    private static volatile List<Integer> list = new ArrayList<>();
    private static Object lock = new Object();

    public static void add(Integer i) {
        list.add(i);
    }

    public static int size() {
        return list.size();
    }

    public static class AddThread implements Runnable {
        Integer num;
        Integer target;

        public AddThread(Integer i, Integer target) {
            this.num = i;
            this.target = target;
        }

        @Override
        public void run() {
            synchronized (lock) {
                for (int i = 1; i <= num; i++) {
                    add(i);
                    System.out.println("add + " + i);

                    if (list.size() == target) {
                        lock.notify();

                        // notify不会释放锁 所以还需要等待
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static class WaitThread implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                try {
                    lock.wait();

                    lock.notify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("wait thread end.");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread addThread = new Thread(new AddThread(10, 5));
        Thread waitThread = new Thread(new WaitThread());
        waitThread.start();
        Thread.sleep(100);
        addThread.start();
    }

}
