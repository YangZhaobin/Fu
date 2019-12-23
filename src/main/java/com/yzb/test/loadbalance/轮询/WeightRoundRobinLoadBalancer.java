package com.yzb.test.loadbalance.轮询;

import com.yzb.test.loadbalance.LoadBalancer;
import com.yzb.test.loadbalance.Server;

/**
 * 加权轮询算法
 *
 * 加权轮询调度会生成不均匀的实例序列，这种不平滑的负载可能会使某些实例出现瞬时高负载的现象，导致系统存在宕机的风险
 */
public class WeightRoundRobinLoadBalancer implements LoadBalancer {

    private int totalWeight = 0;
    private Integer pos = 0;

    public WeightRoundRobinLoadBalancer() {
        for (Integer weight : Server.IP_LIST_WEIGHT.values()) {
            totalWeight += weight;
        }
    }

    @Override
    public String getServerIp() {
        int requestId = RequestId.get();
        int offset = requestId % totalWeight;
        for (String ip : Server.IP_LIST_WEIGHT.keySet()) {
            Integer weight = Server.IP_LIST_WEIGHT.get(ip);
            if (offset < weight)
                return ip;
            offset -= weight;
        }
        return null;
    }

    @Override
    public String getServerIp(String clientIp) {
        return null;
    }
}
