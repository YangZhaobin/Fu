package com.yzb.test.ds.tree;

import org.omg.CORBA.Object;

/**
 * 线段树
 */
public class SegmentTree<E> {

    /**
     * 源数据
     */
    transient E[] data;
    /**
     * 线段树数据存储，非叶子节点记录统计数据， size为源数据size的4倍
     */
    transient E[] tree;
    private Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> merger) {
        data = (E[])new Object[arr.length];
        for (int i = 0; i < arr.length; i++)
            data[i] = arr[i];

        this.merger = merger;
        tree = (E[])new Object[4 * arr.length];
        buildSegmentTree(0, 0, data.length - 1);
    }

    public int getSize() {
        return data.length;
    }

    public E get(int index) {
        if (index < 0 || index >= data.length)
            throw new IllegalArgumentException();
        return data[index];
    }

    public void set(int index, E e) {
        if (index < 0 || index >= data.length)
            throw new IllegalArgumentException();
        data[index] = e;
        set(0, 0, data.length - 1, index, e);
    }

    public E query(int queryL, int queryR) {
        if (queryL < 0 || queryL >= data.length || queryR < 0 || queryR >= data.length || queryL > queryR)
            throw new IllegalArgumentException();
        return query(0, 0, data.length - 1, queryL, queryR);
    }

    /**
     * 在treeIndex的位置创建表示区间 [l, r] 的线段树
     */
    private void buildSegmentTree(int treeIndex, int l, int r) {
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }

        int leftTreeIndex = getLeftChild(treeIndex);
        int rightTreeIndex = getRightChild(treeIndex);
        int mid = l + (r - l) / 2;

        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    public void set(int treeIndex, int l, int r, int index, E e) {
        if (l == r) {
            tree[treeIndex] = e;
            return;
        }

        int leftTreeIndex = getLeftChild(treeIndex);
        int rightTreeIndex = getRightChild(treeIndex);
        int mid = l + (r - l) / 2;

        if (index >= mid + 1)
            set(rightTreeIndex, mid + 1, r, index, e);
        else
            set(leftTreeIndex, l, mid, index, e);

        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    /**
     * 在treeIndex为根的线段树中[l, r]的范围里  搜索区间[queryL, queryR]的值
     */
    public E query(int treeIndex, int l, int r, int queryL, int queryR) {
        if (l == queryL && r == queryR)
            return tree[treeIndex];

        int leftTreeIndex = getLeftChild(treeIndex);
        int rightTreeIndex = getRightChild(treeIndex);
        int mid = l + (r - l) / 2;

        if (queryL >= mid + 1) {
            return query(rightTreeIndex, mid + 1, r, queryL, queryR);
        }
        else if (queryR <= mid) {
            return query(leftTreeIndex, l, mid, queryL, queryR);
        }
        else {
            return merger.merge(
                query(leftTreeIndex, l, mid, queryL, mid),
                query(rightTreeIndex, mid + 1, r, mid + 1, queryR)
            );
        }
    }

    private int getParent(int index) {
        if (index == 0)
            throw new IllegalArgumentException();
        return (index - 1) / 2;
    }

    private int getLeftChild(int index) {
        return index * 2 + 1;
    }

    private int getRightChild(int index) {
        return index * 2 + 2;
    }
}
