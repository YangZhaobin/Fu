package com.yzb.test.loadbalance;

import com.yzb.test.loadbalance.轮询.RoundRobinLoadBalancer;
import com.yzb.test.loadbalance.轮询.SmoothWeightRoundRobinLoadBalancer;
import com.yzb.test.loadbalance.轮询.Weight;
import com.yzb.test.loadbalance.轮询.WeightRoundRobinLoadBalancer;
import com.yzb.test.loadbalance.随机.WeightRandomLoadBalancer;

import java.util.*;

public class Server {

    public static List<String> IP_LIST = Arrays.asList(
        "192.168.0.1",
        "192.168.0.2",
        "192.168.0.3",
        "192.168.0.4",
        "192.168.0.5",
        "192.168.0.6",
        "192.168.0.7"
    );

    public static Map<String, Integer> IP_LIST_WEIGHT = new LinkedHashMap<>();
    static {
        IP_LIST_WEIGHT.put("192.168.0.1", 2);
        IP_LIST_WEIGHT.put("192.168.0.2", 9);
        IP_LIST_WEIGHT.put("192.168.0.3", 1);
        IP_LIST_WEIGHT.put("192.168.0.4", 7);
        IP_LIST_WEIGHT.put("192.168.0.5", 1);
        IP_LIST_WEIGHT.put("192.168.0.6", 2);
        IP_LIST_WEIGHT.put("192.168.0.7", 8);
    }



    public static String getServer(LoadBalancer loadBalancer) {
        return loadBalancer.getServerIp();
    }

    public static void main(String[] args) {
//        LoadBalancer loadBalancer = new RandomLoadBalancer();
//        LoadBalancer loadBalancer = new WeightRandomLoadBalancer();
//        LoadBalancer loadBalancer = new RoundRobinLoadBalancer();
//        LoadBalancer loadBalancer = new WeightRoundRobinLoadBalancer();
        LoadBalancer loadBalancer = new SmoothWeightRoundRobinLoadBalancer();
        for (int i = 0; i < 10; i++) {
            System.out.println(getServer(loadBalancer));
        }
    }
}
