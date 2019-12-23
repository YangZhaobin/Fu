package com.yzb.test.juc.thread;

public class SleepInterruptedThread {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            while (true) {
                System.out.println("sub thread running. " + System.currentTimeMillis());
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("sub thread isInterrupted.");
                    break;
                }
                try {
                    System.out.println("sub thread sleep. " + System.currentTimeMillis());
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.out.println("sub thread sleep isInterrupted. " + System.currentTimeMillis());
                    Thread.currentThread().interrupt();
                }

                Thread.yield();
            }
        });

        thread.start();
        System.out.println("main thread. " + System.currentTimeMillis());
        Thread.sleep(1000);
        System.out.println("main thread. " + System.currentTimeMillis());
        thread.interrupt();
        System.out.println("main thread interrupt sub thread. " + System.currentTimeMillis());
        Thread.sleep(2000);
    }

}
