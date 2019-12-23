package com.yzb.test.juc.thread;

public class JoinMain {
    public static volatile int i = 0;

    public static class AddThread extends Thread {
        @Override
        public void run() {
            for (; i < 10000; i++);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new AddThread();
        thread.start();

        thread.join();
        System.out.println(i);
    }

}
