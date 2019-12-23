package com.yzb.test.loadbalance.一致性哈希;

import com.yzb.test.loadbalance.LoadBalancer;
import com.yzb.test.loadbalance.Server;

import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistentHashLoadBalancer implements LoadBalancer {

    /**
     * Hash 环
     * key：    hash值
     * value：  ip地址
     */
    private TreeMap<Integer, String> virtualNodes = new TreeMap<>();
    private static final int VIRTUAL_NODES = 160;

    public ConsistentHashLoadBalancer(TreeMap<Integer, String> virtualNodes) {
        // 初始化哈希环
        for (String ip : Server.IP_LIST) {
            for (int i = 0; i < VIRTUAL_NODES; i++) {
                virtualNodes.put(getHash(ip + i), ip);
            }
        }
    }

    @Override
    public String getServerIp() {
        return null;
    }

    @Override
    public String getServerIp(String clientIp) {
        int hash = getHash(clientIp);
        // 找到大于hash的virtualNodes的子树的firstKey
        SortedMap<Integer, String> subMap = virtualNodes.tailMap(hash);
        if (subMap == null) {
            return virtualNodes.get(virtualNodes.firstKey());
        }
        return virtualNodes.get(subMap.firstKey());
    }

    private int getHash(String str) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < str.length(); i++)
            hash = (hash ^ str.charAt(i)) * p;
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        // 如果算出来的值为负数则取其绝对值
        if (hash < 0)
            hash = Math.abs(hash);
        return hash;
    }
}
