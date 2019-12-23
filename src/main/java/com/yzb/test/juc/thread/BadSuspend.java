package com.yzb.test.juc.thread;

public class BadSuspend {
    final static Object lock = new Object();

    public static class T1 extends Thread {

        public T1(String name) {
            super.setName(name);
        }

        @Override
        public void run() {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " running. " + System.currentTimeMillis());
                Thread.currentThread().suspend();
                System.out.println(Thread.currentThread().getName() + " end. " + System.currentTimeMillis());
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new T1("t1");
        Thread t2 = new T1("t2");
        t1.start();
        Thread.sleep(1000);
        t2.start();

        System.out.println("t1 resume. " + System.currentTimeMillis());
        t1.resume();
        System.out.println("t2 resume. " + System.currentTimeMillis());
        t2.resume();
        System.out.println("t1 join. " + System.currentTimeMillis());
        t1.join();
        System.out.println("t2 join. " + System.currentTimeMillis());
        t2.join();
    }

}
