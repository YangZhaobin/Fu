package com.yzb.test.loadbalance.轮询;

import com.yzb.test.loadbalance.LoadBalancer;
import com.yzb.test.loadbalance.Server;

/**
 * 基础轮询算法
 */
public class RoundRobinLoadBalancer implements LoadBalancer {

    private Integer pos = 0;

    @Override
    public String getServerIp() {
        if (pos >= Server.IP_LIST.size())
            pos = 0;
        return Server.IP_LIST.get(pos++);
    }

    @Override
    public String getServerIp(String clientIp) {
        return null;
    }
}
