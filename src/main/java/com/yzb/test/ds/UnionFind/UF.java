package com.yzb.test.ds.UnionFind;

/**
 * 并查集接口
 */
public interface UF {

    int getSize();

    /**
     * 合并元素p和元素q所属的集合
     */
    void unionElements(int p, int q);

    /**
     * 两点是否相连
     */
    boolean isConnected(int p, int q);
}
