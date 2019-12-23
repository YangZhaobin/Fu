package com.yzb.test.ds.UnionFind;

/**
 * 并查集 简略版3
 *
 * 使用孩子指向父亲节点的方式 构造一种特殊的树结构
 *
 * 基于size进行优化： 在合并元素时判断两个树的元素个数，
 *                  将元素个数较少的树的根指向元素个数较多树的根，
 *                  避免树的高度快速增长
 */
public class UnionFind3 implements UF {

    /**
     * parents[i] 表示i指向的元素 即其父元素
     */
    private int[] parents;

    /**
     * sz[i] 表示以i为根的集合中元素个数
     */
    private int[] sz;

    public UnionFind3(int size) {
        parents = new int[size];
        sz = new int[size];
        for (int i = 0; i < size; i++) {
            parents[i] = i;
            sz[i] = 1;
        }
    }

    @Override
    public int getSize() {
        return parents.length;
    }

    /**
     * 合并两个集合时，将sz低的集合合并到sz高的集合中，即将sz低的集合的根指向sz高的集合的根
     */
    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot)
            return;

        if (sz[pRoot] <= sz[qRoot]) {
            parents[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        }
        else {
            parents[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
    }

    /**
     * 通过判断两个元素的根节点是否相同 来判断两个元素是否在同一集合中
     *
     * 时间复杂度：O(h) h为树的高度
     */
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * 查找元素P所对应的集合编号
     *
     * 时间复杂度：O(h) h为树的高度
     */
    private int find(int p) {
        if (p < 0 || p >= parents.length)
            throw new IllegalArgumentException();
        int parent = parents[p];
        return parent == p ? parent : find(parent);
    }
}
