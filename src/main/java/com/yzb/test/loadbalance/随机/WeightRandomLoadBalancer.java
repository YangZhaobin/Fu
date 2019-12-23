package com.yzb.test.loadbalance.随机;

import com.yzb.test.loadbalance.LoadBalancer;
import com.yzb.test.loadbalance.Server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 加权随机
 *
 *
 */
public class WeightRandomLoadBalancer implements LoadBalancer {

    private List<String> ips;
    private int totalWeight = 0;

    public WeightRandomLoadBalancer() {
        //
        ips = new ArrayList<>();
        Server.IP_LIST_WEIGHT.forEach((ip, count) -> {
            for (int i = 0; i < count; i++) {
                ips.add(ip);
            }
        });


        //
        for (Integer weight : Server.IP_LIST_WEIGHT.values()) {
            totalWeight += weight;
        }
    }

    @Override
    public String getServerIp() {
        return func2();
    }

    @Override
    public String getServerIp(String clientIp) {
        return null;
    }

    /**
     * 方法1： 将所有ip按权重数放入ips中，然后正常随机
     */
    public String func1() {
        Random random = new Random();
        return ips.get(random.nextInt(ips.size()));
    }

    /**
     * 方法2：
     */
    public String func2() {
        int offset = new Random().nextInt(totalWeight);
        for (String ip : Server.IP_LIST_WEIGHT.keySet()) {
            Integer weight = Server.IP_LIST_WEIGHT.get(ip);
            if (offset < weight)
                return ip;
            offset -= weight;
        }
        return func1();
    }
}
