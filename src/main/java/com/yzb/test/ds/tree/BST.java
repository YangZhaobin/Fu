package com.yzb.test.ds.tree;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉搜索树
 */
public class BST<E extends Comparable<E>> {
    private TreeNode<E> root;
    private int size;

    public BST() {
        this.root = null;
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E e) {
        root = add(root, e);
    }

    /**
     * 向以node为根的树插入数据e
     * @return 插入数据后的根节点
     */
    private TreeNode add(TreeNode node, E e) {
        if (node == null) {
            size++;
            return new TreeNode(e);
        }

        if (node.e.compareTo(e) > 0) {
            node.left = add(node.left, e);
        }
        else if (node.e.compareTo(e) < 0) {
            node.right = add(node.right, e);
        }

        return node;
    }

    public boolean contains(E e) {
        return contains(root, e);
    }

    /**
     * 向以node为根的树是否包含e
     */
    private boolean contains(TreeNode node, E e) {
        if (node == null) {
            return false;
        }

        if (node.e.compareTo(e) > 0) {
            return contains(node.left, e);
        }
        else if (node.e.compareTo(e) < 0) {
            return contains(node.right, e);
        }
        else {
            return true;
        }
    }

    private void visit(TreeNode node) {
        System.out.println(node.e);
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(TreeNode node) {
        if (node != null) {
            visit(node);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public void preOrderNR() {
        preOrderNR(root);
    }

    private void preOrderNR(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            TreeNode n = stack.pop();
            if (n != null) {
                visit(n);
                if (n.right != null)
                    stack.push(n.right);
                if (n.left != null)
                    stack.push(n.left);
            }
        }
    }

    /**
     * 中序遍历
     */
    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(TreeNode node) {
        if (node != null) {
            inOrder(node.left);
            visit(node);
            inOrder(node.right);
        }
    }

    public void inOrderNR() {
        inOrderNR(root);
    }

    private void inOrderNR(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = node;
        while (!stack.isEmpty() || cur != null) {
            // 每次先找到当前节点的最左节点并进行访问
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            if (!stack.isEmpty()) {
                cur = stack.pop();
                visit(cur);
                cur = cur.right;
            }
        }
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(TreeNode node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            visit(node);
        }
    }

    /**
     * 层次遍历
     */
    public void levelOrder() {
        levelOrder(root);
    }

    private void levelOrder(TreeNode node) {
        if (node == null) return;

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(node);
        while(!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            visit(cur);
            if (cur.left != null)
                queue.add(cur.left);
            if (cur.right != null)
                queue.add(cur.right);
        }
    }

    /**
     * 获取最值
     */
    public E min() {
        if (size == 0) return null;
        return (E) min(root).e;
    }

    private TreeNode min(TreeNode node) {
        if (node.left == null) return node;
        return min(node.left);
    }

    public E max() {
        if (size == 0) return null;
        return (E) max(root).e;
    }

    private TreeNode max(TreeNode node) {
        if (node.right == null) return node;
        return max(node.right);
    }

    /**
     * 删除操作
     */
    public E removeMin() {
        E ret = min();
        root = removeMin(root);
        return ret;
    }

    private TreeNode removeMin(TreeNode node) {
        if (node.left == null) {
            TreeNode right = node.right;
            node.right = null;
            size--;
            return right;
        }
        node.left = removeMin(node.left);
        return node;
    }

    public E removeMax() {
        E ret = max();
        root = removeMax(root);
        return ret;
    }

    private TreeNode removeMax(TreeNode node) {
        if (node.right == null) {
            TreeNode left = node.left;
            node.left = null;
            size--;
            return left;
        }

        node.right = removeMax(node.right);
        return node;
    }

    public void remove(E e) {
        remove(root, e);
    }

    private TreeNode remove(TreeNode node, E e) {
        if (node == null) return null;

        if (node.e.compareTo(e) == 0) {
            TreeNode successor;
            if (node.left == null) {
                successor = node.right; // GC
                node.right = null;
                size--;
            }
            else if (node.right == null) {
                successor = node.left;
                node.left = null;  // GC
                size--;
            } else {
                // 选取右子树中最小节点为后继节点
                // 前驱： 左子树的最大值
                // 后继： 右子树的最小值
                successor = min(node.right);
                successor.right = removeMin(node.right);
                successor.left = node.left;
                node.left = node.right = null; // GC
            }
            return successor;
        }
        else if (node.e.compareTo(e) > 0) {
            node.left = remove(node.left, e);
        }
        else {
            node.right = remove(node.right, e);
        }
        return node;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        generateBSTString(root, 0, sb);
        return sb.toString();
    }

    private void generateBSTString(TreeNode node, int depth, StringBuilder sb) {
        if (node == null) {
            sb.append(generateDepthString(depth) + "null\n");
            return;
        }

        sb.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, sb);
        generateBSTString(node.right, depth + 1, sb);
    }

    private String generateDepthString(int depth) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            sb.append("--");
        }
        return sb.toString();
    }
}
