package com.yzb.test.ds.tree;

import lombok.Data;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉搜索树
 */
@Data
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
        // 递归方式
//        root = add(root, e);

        // 非递归方式
        addNR(e);
    }

    public void addNR(E e) {
        int cmp = 0;
        TreeNode node = root;
        TreeNode parent = null;
        while (node != null) {
            cmp = node.e.compareTo(e);
            parent = node;
            if (cmp > 0) {
                node = node.left;
            } else if (cmp < 0) {
                node = node.right;
            } else {
                node.e = e;
                return;
            }
        }

        size++;
        if (parent == null) {
            root = new TreeNode(e);
        } else {
            if (cmp > 0) {
                parent.left = new TreeNode(e, parent);
            } else {
                parent.right = new TreeNode(e, parent);
            }
        }
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
            node.left.parent = node;
        } else if (node.e.compareTo(e) < 0) {
            node.right = add(node.right, e);
            node.right.parent = node;
        }
        return node;
    }

    public boolean contains(E e) {
        return contains(root, e);
    }

    public boolean containsNR(E e) {
        TreeNode node = root;
        while (node != null) {
            if (node.e.compareTo(e) > 0) {
                node = node.left;
            } else if (node.e.compareTo(e) < 0) {
                node = node.right;
            } else {
                return true;
            }
        }
        return false;
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
        } else if (node.e.compareTo(e) < 0) {
            return contains(node.right, e);
        } else {
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

    // 递归前序遍历
    private void preOrder(TreeNode node) {
        if (node != null) {
            visit(node);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    // 非递归前序遍历
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
                successor = node.right; // Help for GC
                node.right = null;
                size--;
            } else if (node.right == null) {
                successor = node.left;
                node.left = null;  // Help for GC
                size--;
            } else {
                // 选取右子树中最小节点为后继节点 (or 前驱节点)
                // 前驱： 左子树的最大值  中序遍历时的前一个节点
                // 后继： 右子树的最小值  中序遍历时的后一个节点
                successor = min(node.right);
                successor.right = removeMin(node.right);
                successor.left = node.left;
                node.left = node.right = null; // help for GC
            }
            return successor;
        } else if (node.e.compareTo(e) > 0) {
            node.left = remove(node.left, e);
        } else {
            node.right = remove(node.right, e);
        }
        return node;
    }


    /**
     * 计算二叉树高度 （层序遍历）
     */
    public int height() {
        return height(root);
    }

    public int heightNR() {
        return heightNR(root);
    }

    public int height(TreeNode node) {
        if (node == null) return 0;
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    public int heightNR(TreeNode node) {
        if (node == null) return 0;
        int height = 0;
        int levelSize = 1; // 每一层的个数
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur.left != null)
                queue.add(cur.left);
            if (cur.right != null)
                queue.add(cur.right);

            levelSize--;
            if (levelSize == 0) {
                levelSize = queue.size();
                height++;
            }
        }
        return height;
    }


    /**
     * 判断是否为完全二叉树  (使用层次遍历)
     */
    public boolean isComplete() {
        return isComplete(root);
    }

    public boolean isComplete(TreeNode node) {
        if (node == null) return false;

        boolean leaf = false;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (leaf && (cur.left != null || cur.right != null)) {
                return false;
            }

            if (cur.left != null && cur.right != null) {
                queue.add(cur.left);
                queue.add(cur.right);
            } else if (cur.left == null && cur.right != null) {
                return false;
            } else { // 后面遍历的节点必需是叶子节点
                leaf = true;
                if (cur.left != null) {
                    queue.add(cur.left);
                }
            }
        }
        return true;
    }

    public boolean isComplete1(TreeNode node) {
        if (node == null) return false;

        boolean leaf = false;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (leaf && (cur.left != null || cur.right != null)) {
                return false;
            }

            // 减少重复判断
            if (cur.left != null) {
                queue.add(cur.left);
            } else if (cur.right != null) {
                return false;
            }

            if (cur.right != null) {
                queue.add(cur.right);
            } else {
                leaf = true;
            }
        }
        return true;
    }

    /**
     * 翻转二叉树
     */
    public void invert() {
        invert(root);
    }

    public void invert(TreeNode node) {
        if (node == null) return;

        TreeNode tmp = node.left;
        node.left = node.right;
        node.right = tmp;

        invert(node.left);
        invert(node.right);
    }

    /**
     * 获取前驱节点
     */
    public TreeNode predecessor(TreeNode node) {
        if (node == null) return null;
        TreeNode p = node.left;
        // 前驱节点在左子树中 (left.right.right.right...)
        if (p != null) {
            while (p.right != null) {
                p = p.right;
            }
            return p;
        }

        // 从父节点、祖父节点中寻找前驱
        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }
        return node.parent;
    }

    /**
     * 获取后继节点
     */
    public TreeNode successor(TreeNode node) {
        if (node == null) return null;
        TreeNode p = node.right;
        // 前驱节点在左子树中 (right.left.left.left...)
        if (p != null) {
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }

        // 从父节点、祖父节点中寻找前驱
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }
        return node.parent;
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
            sb.append("-----");
        }
        return sb.toString();
    }
}
