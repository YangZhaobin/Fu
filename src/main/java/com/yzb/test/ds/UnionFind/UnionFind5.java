package com.yzb.test.ds.UnionFind;

/**
 * 并查集
 *
 * 路径压缩：在查找元素时 将树进行压缩 高度压低
 *
 */
public class UnionFind5 implements UF {

    /**
     * parents[i] 表示i指向的元素 即其父元素
     */
    private int[] parents;

    /**
     * ranks[i] 表示以i为根的树的高度
     */
    private int[] ranks;

    public UnionFind5(int size) {
        parents = new int[size];
        ranks = new int[size];
        for (int i = 0; i < size; i++) {
            parents[i] = i;
            ranks[i] = 1;
        }
    }

    @Override
    public int getSize() {
        return parents.length;
    }

    /**
     * 合并两个集合时，将rank低的集合合并到rank高的集合中，即将rank低的集合的根指向rank高的集合的根
     */
    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot)
            return;

        if (ranks[pRoot] <= ranks[qRoot]) {
            parents[pRoot] = qRoot;
        }
        else if (ranks[pRoot] > ranks[qRoot]) {
            parents[qRoot] = pRoot;
        }
        else {
            parents[pRoot] = qRoot;
            ranks[qRoot] = ranks[qRoot] + 1;
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
     */
    private int find(int p) {
        if (p < 0 || p >= parents.length)
            throw new IllegalArgumentException();
//        while (parents[p] != p) {
//            parents[p] = parents[parents[p]];
//            p = parents[p];
//        }
        if (parents[p] != p) {
            parents[p] = find(parents[p]);
        }
        return parents[p];
    }
}
