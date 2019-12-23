package com.yzb.test.ds.UnionFind;

/**
 * 并查集 简略版2
 *
 * 使用孩子指向父亲节点的方式 构造一种特殊的树结构
 */
public class UnionFind2 implements UF {

    /**
     * 元素的父元素
     */
    private int[] parents;

    public UnionFind2(int size) {
        parents = new int[size];
        for (int i = 0; i < size; i++)
            parents[i] = i;
    }

    @Override
    public int getSize() {
        return parents.length;
    }

    /**
     * 合并两个集合时，将p的根节点指向q的根节点
     */
    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot)
            return;

        parents[pRoot] = qRoot;
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
