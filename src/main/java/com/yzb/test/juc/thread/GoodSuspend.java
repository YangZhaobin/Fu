package com.yzb.test.juc.thread;

public class GoodSuspend {
    final static Object lock = new Object();

    public static class ChangeObjectThread extends Thread {
        volatile boolean suspendMe = false;

        public void suspendMe() {
            suspendMe = true;
        }

        public void resumeMe() {
            suspendMe = false;
            synchronized (this) {
                notify();
            }
        }

        @Override
        public void run() {
            while (true) {
                synchronized (this) {
                    while (suspendMe) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

                synchronized (lock) {
                    System.out.println(" in ChangeObjectThread. ");
                }
                Thread.yield();
            }
        }
    }

    public static class ReadObjectThread extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    System.out.println("in ReadObjectThread.");
                }
                Thread.yield();
            }
        }
    }
}
