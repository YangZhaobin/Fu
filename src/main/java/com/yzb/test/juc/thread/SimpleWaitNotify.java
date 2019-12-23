package com.yzb.test.juc.thread;

public class SimpleWaitNotify {

    final static Object lock = new Object();

    public static class T1 extends Thread {
        @Override
        public void run() {
            synchronized (lock) {
                System.out.println("t1 running. " + System.currentTimeMillis());
                try {
                    System.out.println("t1 lock wait. " + System.currentTimeMillis());
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t1 end. " + System.currentTimeMillis());
            }
        }
    }

    public static class T2 extends Thread {
        @Override
        public void run() {
            synchronized (lock) {
                System.out.println("t2 running. lock notify. " + System.currentTimeMillis());
//                lock.notify();
                try {
                    System.out.println("t2 sleep. " + System.currentTimeMillis());
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t2 end. " + System.currentTimeMillis());
            }
        }
    }

    public static class T3 extends Thread {
        @Override
        public void run() {
            System.out.println("t3 running. " + System.currentTimeMillis());
            try {
                System.out.println("t3 sleep. " + System.currentTimeMillis());
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t3 end. " + System.currentTimeMillis());
        }
    }

    public static class T4 extends Thread {
        @Override
        public void run() {
            System.out.println("t4 running.  " + System.currentTimeMillis());
            try {
                System.out.println("t4 sleep. " + System.currentTimeMillis());
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t4 end. " + System.currentTimeMillis());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T1 t1 = new T1();
        T2 t2 = new T2();
        t1.start();
        t2.start();
        Thread.sleep(1000);
        t1.interrupt();

//        T3 t3 = new T3();
//        T4 t4 = new T4();
//        t3.start();
//        t4.start();
    }
}
