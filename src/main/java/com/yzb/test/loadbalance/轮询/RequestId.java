package com.yzb.test.loadbalance.轮询;

import java.util.concurrent.atomic.AtomicInteger;

public class RequestId {

    private static AtomicInteger num = new AtomicInteger(0);

    public static int get() {
        return num.getAndIncrement();
    }
}
