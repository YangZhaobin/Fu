package com.yzb.test.ds.UnionFind;

import java.util.HashMap;
import java.util.Map;

/**
 * 并查集
 */
public class UnionFind6<E> implements UF1<E> {

    public Map<E, E> parents;

    private Map<E, Integer> ranks;

    public UnionFind6() {
        parents = new HashMap<>();
        ranks = new HashMap<>();
    }

    public void add(E e) {
        parents.put(e, e);
        ranks.put(e, 1);
    }

    /**
     * 合并两个集合时，将rank低的集合合并到rank高的集合中，即将rank低的集合的根指向rank高的集合的根
     */
    @Override
    public void unionElements(E p, E q) {
        E pRoot = find(p);
        E qRoot = find(q);

        if (pRoot == qRoot || pRoot.equals(qRoot))
            return;

        if (ranks.get(pRoot) < ranks.get(qRoot)) {
            parents.put(pRoot, qRoot);
        }
        else if (ranks.get(pRoot) > ranks.get(qRoot)) {
            parents.put(qRoot, pRoot);
        }
        else {
            parents.put(pRoot, qRoot);
            ranks.put(qRoot, ranks.get(qRoot) + 1);
        }
    }

    /**
     * 通过判断两个元素的根节点是否相同 来判断两个元素是否在同一集合中
     *
     * 时间复杂度：O(h) h为树的高度
     */
    @Override
    public boolean isConnected(E p, E q) {
        return find(p) == find(q);
    }

    /**
     * 查找元素P所对应的集合编号
     */
    public E find(E p) {
        if (!parents.get(p).equals(p)) {
            parents.put(p, find(parents.get(p)));
        }
        return parents.get(p);
    }
}
