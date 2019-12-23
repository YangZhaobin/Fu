package com.yzb.test.loadbalance.轮询;

import com.yzb.test.loadbalance.LoadBalancer;
import com.yzb.test.loadbalance.Server;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 平滑加权轮询算法
 */
public class SmoothWeightRoundRobinLoadBalancer implements LoadBalancer {

    public Map<String, Weight> weightMap;
    public int totalWeight = 0;

    public SmoothWeightRoundRobinLoadBalancer() {
        weightMap = new LinkedHashMap<>();
        Server.IP_LIST_WEIGHT.forEach((ip, weight) -> {
            totalWeight += weight;
            weightMap.put(ip, new Weight(ip, weight, 0));
        });
    }

    @Override
    public String getServerIp() {
        // currentWeight += weight
        for (Weight weight : weightMap.values()) {
            weight.currentWeight += weight.weight;
        }

        // get max(currentWeight)
        Weight maxCurrentWeight = null;
        for (Weight weight : weightMap.values()) {
            if (maxCurrentWeight == null || weight.currentWeight > maxCurrentWeight.currentWeight) {
                maxCurrentWeight = weight;
            }
        }

        // max(currentWeight) -= totalWeight
        maxCurrentWeight.currentWeight -= totalWeight;

        return maxCurrentWeight.ip;
    }

    @Override
    public String getServerIp(String clientIp) {
        return null;
    }
}
