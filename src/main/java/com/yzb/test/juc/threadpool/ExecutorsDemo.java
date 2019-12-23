package com.yzb.test.juc.threadpool;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

public class ExecutorsDemo {

    public static class DivTask implements Runnable {
        int a, b;

        public DivTask(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {
            int c = a / b;
            System.out.println(c);
        }
    }

    public static class Task implements Runnable {
        int a;
        public Task(int a) {
            this.a = a;
        }

        @Override
        public void run() {
            System.out.println(a);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(a + "  end.");
        }
    }

    public static class TraceThreadPoolExecutor extends ThreadPoolExecutor {

        public TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
                                       long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

//        @Override
//        public Future<?> submit(Runnable task) {
//            return super.submit(wrap(task, new Exception("Client Stack Here"), Thread.currentThread().getName()));
//        }
//
//        @Override
//        public void execute(Runnable command) {
//            super.execute(wrap(command, new Exception("Client Stack Here"), Thread.currentThread().getName()));
//        }
//
//        public Runnable wrap(Runnable task, Exception clientStack, String clientThreadName) {
//            return () -> {
//                try {
//                    task.run();
//                } catch (Exception e) {
//                    clientStack.printStackTrace();
//                    throw e;
//                }
//            };
//        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            super.afterExecute(r, t);
            if (t != null) {
                t.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
//        ExecutorService exec = Executors.newFixedThreadPool(5);
//        TraceThreadPoolExecutor exec = new TraceThreadPoolExecutor(0, Integer.MAX_VALUE,
//                0L, TimeUnit.SECONDS, new SynchronousQueue<>());
//        for (int i = 0; i < 5; i++) {
//            exec.execute(new DivTask(100, i));
//        }
//        exec.shutdown();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2, 4, 6L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2));

        for (int i = 0; i < 5; i++) {
            executor.submit(new Task(i));
        }
        executor.shutdown();
    }
}
