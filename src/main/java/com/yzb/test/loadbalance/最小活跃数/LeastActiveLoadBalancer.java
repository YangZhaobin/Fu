package com.yzb.test.loadbalance.最小活跃数;

import com.yzb.test.loadbalance.LoadBalancer;

/**
 * 最小活跃数
 */
public class LeastActiveLoadBalancer implements LoadBalancer {
    @Override
    public String getServerIp() {
        return null;
    }

    @Override
    public String getServerIp(String clientIp) {
        return null;
    }
}
