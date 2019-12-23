package com.yzb.test.ds.UnionFind;

/**
 * 并查集 简略版
 */
public class UnionFind1 implements UF {

    /**
     * 元素所属集合编号ID
     */
    private int[] ids;

    public UnionFind1(int size) {
        ids = new int[size];
        for (int i = 0; i < size; i++)
            ids[i] = i;
    }

    @Override
    public int getSize() {
        return ids.length;
    }

    @Override
    public void unionElements(int p, int q) {
        int pId = find(p);
        int qId = find(q);

        if (pId == qId)
            return;

        for (int i = 0; i < ids.length; i++) {
            if (ids[i] == pId)
                ids[i] = qId;
        }
    }

    /**
     * 时间复杂度：O(1)
     */
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * 查找元素P所对应的集合编号
     */
    private int find(int p) {
        if (p < 0 || p >= ids.length)
            throw new IllegalArgumentException();
        return ids[p];
    }
}
