package com.yzb.test.thread;


public class OEPrint {

    public static volatile int i = 0;
    public static volatile boolean f = true;
    public static void main(String[] args) {
        Thread oPrintThread = new OPrintThread();
        Thread ePrintThread = new EPrintThread();
        oPrintThread.start();
        ePrintThread.start();
    }

    public static class OPrintThread extends Thread {
        @Override
        public void run() {
            while (i < 100) {
                if (f) {
                    System.out.println("OPrintThread print: " + i++);
                    f = !f;
                }
            }
        }
    }

    public static class EPrintThread extends Thread {
        @Override
        public void run() {
            while (i < 100) {
                if (!f) {
                    System.out.println("EPrintThread print: " + i++);
                    f = !f;
                }
            }
        }
    }
}
