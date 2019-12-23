package com.yzb.test.ds.tree;

import lombok.Data;

@Data
public class TreeNode<E extends Comparable<E>> {
    E e;
    TreeNode left;
    TreeNode right;

    public TreeNode(E e) {
        this.e = e;
        this.left = null;
        this.right = null;
    }
}
