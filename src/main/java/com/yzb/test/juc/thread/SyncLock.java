package com.yzb.test.juc.thread;

public class SyncLock extends Thread {
    public static Object lock1 = new Object();
    public static Object lock2 = new Object();
    int lock;

    public SyncLock(int lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            if (lock == 1) {
                synchronized (lock1) {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        System.out.println("1 sleep InterruptedException");
                    }
                    /**
                     * 出现异常，线程退出，锁释放
                     */
                    int i = 1 / 0;
                    synchronized (lock2) {}
                }
            }
            else{
                synchronized (lock2) {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        System.out.println("2 sleep InterruptedException");
                    }
                    synchronized (lock1) {}
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getId() + " 线程退出。");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SyncLock t1 = new SyncLock(1);
        SyncLock t2 = new SyncLock(0);

        t1.start();
        t2.start();
//        Thread.sleep(2000);
//        t2.interrupt();
    }
}
