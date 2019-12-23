package com.yzb.test.thread;

public class ThreadDispatcher {

    static class Sched implements Runnable {
        // 总票数
        final int TICKET_MAXNUM = 500;
        // 线程（售票窗口）数量
        final int THREAD_NUM = 10;
        // 已售出票数
        public int count = 0;
        // 第i张票的线程的序号是TicketThreadNo[i]，-1表示未售出
        private int[] ThreadNo;
        // 记录每个线程售出的票数,说明每个线程占用的CPU时间
        private int[] TicketNum;
        // ThreadId[i]存放第i个线程的Id号
        private int[] ThreadId;
        // 将要创建的线程的序号，初值为0
        private int NewThreadNo;
        // 工作变量,仅用于消耗线程的CPU时间
        double d;

        public Sched() {
            int i;
            ThreadNo = new int[TICKET_MAXNUM];
            for (i = 0; i < TICKET_MAXNUM; i++)
                ThreadNo[i] = -1;
            TicketNum = new int[THREAD_NUM];
            for (i = 0; i < THREAD_NUM; i++)
                TicketNum[i] = 0;
            NewThreadNo = 0;
            ThreadId = new int[THREAD_NUM];
        }

        public synchronized void sell() {
            if (count < TICKET_MAXNUM) {
                ThreadNo[count] = getNo((int)Thread.currentThread().getId());
                //System.out.println(sold[count]+ "号线程售第" + count + "张票");
                count++;
                delay();
            }
        }

        // 从线程的id号得到线程的序号
        private int getNo(int id) {
            int i;
            for (i = 0; i < THREAD_NUM; i++)
                if (ThreadId[i] == id)
                    return i;
            return -1;
        }

        // 仅用于消耗CPU时间，表示售票所用时间
        private void delay(){
            d = 5000000;
            while (d > 0)
                d = d - 1;
        }

        // 累计并打印每个线程卖的票数
        public void accumulate() {
            int i;
            for (i = 0; i < TICKET_MAXNUM; i++)
                TicketNum[ThreadNo[i]]++;
            for (i = 0; i < THREAD_NUM; i++)
                System.out.printf("%3d号线程卖：%-4d张票\n", i, TicketNum[i]);
        }

        @Override
        public void run() {
            //Thread.currentThread().setPriority(NewThreadNo+1);
            ThreadId[NewThreadNo++] = (int)Thread.currentThread().getId();
            while (count < TICKET_MAXNUM) // 只要有票，线程（窗口）就不停售票
                sell();
        }
    }

    public static void main(String[] args) {
        int i;
        Sched t = new Sched();
        for (i = 0; i < t.THREAD_NUM; i++) {
            new Thread(t).start();
        }
        while (t.count < t.TICKET_MAXNUM) //等待票都卖完
            Thread.yield();
        t.accumulate();

//        0号线程卖：41  张票
//        1号线程卖：22  张票
//        2号线程卖：63  张票
//        3号线程卖：45  张票
//        4号线程卖：92  张票
//        5号线程卖：105 张票
//        6号线程卖：29  张票
//        7号线程卖：31  张票
//        8号线程卖：44  张票
//        9号线程卖：28  张票
    }
}
