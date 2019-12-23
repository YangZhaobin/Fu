package com.yzb.test.juc;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountDownLatchDemo implements Runnable {
    static CountDownLatch countDownLatch = new CountDownLatch(10);
    static CountDownLatchDemo countDownLatchDemo = new CountDownLatchDemo();

    static boolean flag = true;

    public void run() {
        try {
            TimeUnit.SECONDS.sleep(new Random().nextInt(10));
            System.out.println("check complete " + countDownLatch.getCount());
            countDownLatch.countDown();

            if (0 == countDownLatch.getCount()) {
//                TimeUnit.SECONDS.sleep(2);
                System.out.println("check over");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static class SubWait extends Thread {
        @Override
        public void run() {
            try {
                System.out.println("Sub Wait");

                countDownLatch.await();

                System.out.println("Sub Fire");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            exec.submit(countDownLatchDemo);
        }
        Thread subWait = new SubWait();
        subWait.start();
        try {
            // 主线程等待倒计时结束
            System.out.println("Main Wait");
            countDownLatch.await();

            // countDownLatch释放锁资源后 执行主线任务
            System.out.println("Main Fire");
            exec.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
