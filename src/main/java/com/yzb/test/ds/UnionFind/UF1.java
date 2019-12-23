package com.yzb.test.ds.UnionFind;

/**
 * 并查集接口
 */
public interface UF1<E> {

    /**
     * 合并元素p和元素q所属的集合
     */
    void unionElements(E p, E q);

    /**
     * 两点是否相连
     */
    boolean isConnected(E p, E q);
}
