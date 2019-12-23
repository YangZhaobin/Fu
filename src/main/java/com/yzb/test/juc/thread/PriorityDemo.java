package com.yzb.test.juc.thread;

public class PriorityDemo {
    public static class HighPriorityThread extends Thread {
        public static int count = 0;
        @Override
        public void run() {
            System.out.println("HighPriorityThread start.");
            while (true) {
                synchronized (PriorityDemo.class) {
                    count++;
                    if (count > 100000) {
                        System.out.println("HighPriorityThread complete!");
                        break;
                    }
                }
            }
        }
    }

    public static class LowPriorityThread extends Thread {
        public static int count = 0;
        @Override
        public void run() {
            System.out.println("LowPriorityThread start.");
            while (true) {
                synchronized (PriorityDemo.class) {
                    count++;
                    if (count > 100000) {
                        System.out.println("LowPriorityThread complete!");
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread high = new HighPriorityThread();
        Thread low = new LowPriorityThread();
        high.setPriority(Thread.MAX_PRIORITY);
        low.setPriority(Thread.MIN_PRIORITY);
        low.start();
        high.start();
        System.out.println("MainThread end.");
    }

}
