package com.yzb.test.ds.heap;

import java.util.ArrayList;
import java.util.List;

/**
 * 最大堆
 */
public class MaxHeap<E extends Comparable> {

    public MaxHeap() {
        list = new ArrayList();
    }

    public MaxHeap(List<E> list) {
        heapify(list);
    }

    transient List<E> list;

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int getSize() {
        return list.size();
    }

    public void add(E e) {
        list.add(e);
        siftUp(list.size() - 1);
    }

    public E extractMax() {
        E max = findMax();
        swap(0, list.size() - 1);
        list.remove(list.size() - 1);
        siftDown(0);
        return max;
    }

    /**
     * 替换堆首元素
     */
    public E replace(E e) {
        E max = findMax();
        list.set(0, e);
        siftDown(0);
        return max;
    }

    public void heapify(List<E> list) {
        this.list = list;

        int index = getParent(list.size() - 1);
        for (int i = index; i >= 0; i--) {
            siftDown(i);
        }
    }

    public void heapify() {
        int index = getParent(list.size() - 1);
        for (int i = index; i >= 0; i--) {
            siftDown(i);
        }
    }

    private void siftUp(int k) {
//        if (k <= 0) return;
//
//        int parent = getParent(k);
//        if (list.get(parent).compareTo(list.get(k)) >= 0) return;
//
//        swap(parent, k);
//        siftUp(parent);

        while (k > 0) {
            int parent = getParent(k);
            if (list.get(parent).compareTo(list.get(k)) >= 0) return;
            swap(parent, k);
            k = parent;
        }
    }

    private E findMax() {
        if (list.size() == 0)
            throw new IllegalArgumentException();
        return list.get(0);
    }

    private void siftDown(int k) {
        int left = getLeftChild(k);
        int right = getRightChild(k);
        int size = list.size();

        if (left >= size) return;

        int index = left;
        if (right < size) {
            index = list.get(left).compareTo(list.get(right)) > 0 ? left : right;
        }

        if (list.get(index).compareTo(list.get(k)) <= 0) {
            return;
        }
        else {
            swap(index, k);
            siftDown(index);
        }
    }

    private void swap(int i, int j) {
        E eleI = list.get(i);
        E eleJ = list.get(j);
        list.set(i, eleJ);
        list.set(j, eleI);
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

    public static void main(String[] args) {
        MaxHeap<Integer> heap = new MaxHeap<>();
        heap.add(16);
        heap.add(35);
        heap.add(11);
        heap.add(27);
        heap.add(44);

        System.out.println(heap.list);

        heap.extractMax();
        System.out.println(heap.list);
    }
}
