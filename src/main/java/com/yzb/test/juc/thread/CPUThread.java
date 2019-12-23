package com.yzb.test.juc.thread;

public class CPUThread {

    public static volatile boolean flag = false;

    public static class RunThead extends Thread {
        @Override
        public void run() {
            while (true) {
                if (flag) break;
            }
            System.out.println("t1 end.");
            System.out.println(System.currentTimeMillis());

        }
    }

    public static class RunThead2 extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            flag = true;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new RunThead();
        Thread t2 = new RunThead2();
        System.out.println(System.currentTimeMillis());
        t1.start();
        Thread.sleep(1000);
        t2.start();
    }

}
