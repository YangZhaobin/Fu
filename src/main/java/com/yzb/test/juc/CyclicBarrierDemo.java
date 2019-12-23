package com.yzb.test.juc;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import lombok.AllArgsConstructor;

public class CyclicBarrierDemo {
    @AllArgsConstructor
    static class Soldier implements Runnable {
        private String soldier;
        private CyclicBarrier cyclicBarrier;

        public void doWork() {
            try {
                TimeUnit.SECONDS.sleep(new Random().nextInt(10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(soldier + " work complete ");
        }

        public void run() {
            try {
                // 等待所有士兵
                cyclicBarrier.await();

                doWork();

                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    @AllArgsConstructor
    static class BarrierRun implements Runnable {
        int i;
        int N;

        public void run() {
            System.out.println(N + "个士兵 " + i++ + "次任务开始");
        }
    }

    public static void main(String[] args) {
        int N = 10;
        ExecutorService exec = Executors.newFixedThreadPool(N);
        CyclicBarrier cyclic = new CyclicBarrier(N, new BarrierRun(1, N));

        System.out.println("集合");

        for (int i = 0; i < N; i++) {
            System.out.println("士兵" + i + "报道");
            exec.submit(new Soldier("士兵" + i, cyclic));
        }
        exec.shutdown();
    }
}
