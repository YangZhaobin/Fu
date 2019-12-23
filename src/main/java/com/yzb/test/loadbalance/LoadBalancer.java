package com.yzb.test.loadbalance;

public interface LoadBalancer {

    String getServerIp();

    String getServerIp(String clientIp);

}
