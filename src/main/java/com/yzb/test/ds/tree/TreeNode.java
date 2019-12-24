package com.yzb.test.ds.tree;

import lombok.Data;

@Data
public class TreeNode<E extends Comparable<E>> {
    E e;
    TreeNode left;
    TreeNode right;
    TreeNode parent;

    public TreeNode(E e) {
        this.e = e;
        this.left = null;
        this.right = null;
        this.parent = null;
    }

    public TreeNode(E e, TreeNode parent) {
        this.e = e;
        this.left = null;
        this.right = null;
        this.parent = parent;
    }
}
