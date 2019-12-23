package com.yzb.test.loadbalance.随机;

import com.yzb.test.loadbalance.LoadBalancer;
import com.yzb.test.loadbalance.Server;

import java.util.Random;

public class RandomLoadBalancer implements LoadBalancer {
    @Override
    public String getServerIp() {
        Random random = new Random();
        return Server.IP_LIST.get(random.nextInt(Server.IP_LIST.size()));
    }

    @Override
    public String getServerIp(String clientIp) {
        return null;
    }
}
