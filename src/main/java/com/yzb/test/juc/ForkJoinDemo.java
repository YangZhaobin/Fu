package com.yzb.test.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import lombok.AllArgsConstructor;

public class ForkJoinDemo {
    private static int THRESHOLD = 10000;
    private static int TASK_COUNT = 100;

    @AllArgsConstructor
    static class CountTask extends RecursiveTask<Long> {
        private long start;
        private long end;

        protected Long compute() {
            long sum = 0;
            if ((end - start) < THRESHOLD) {
                for (long i = start; i <= end; i++) {
                    sum += i;
                }
            } else {
                long step = (start + end) / TASK_COUNT;
                List<CountTask> subTasks = new ArrayList<>();
                long pos = start;
                for (int i = 0; i < TASK_COUNT; i++) {
                    long last = pos + step;
                    if (last >  end) last = end;
                    CountTask subTask = new CountTask(pos, last);
                    pos += step + 1;
                    subTasks.add(subTask);
                    subTask.fork();
                }
                for (CountTask subTask : subTasks) {
                    sum += subTask.join();
                }
            }
            return sum;
        }
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountTask task = new CountTask(0, 200000L);
        ForkJoinTask<Long> ret = forkJoinPool.submit(task);

        try {
            Long aLong = ret.get();
            System.out.println(aLong);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
