package com.yzb.test.ds.avl;

import lombok.Data;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class AVLTree<K extends Comparable<K>, V> {
    @Data
    private class Node {
        K key;
        V value;
        Node left, right;

        int height;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.height = 1;
        }
    }

    private Node root;
    private int size;

    public AVLTree() {
        this.root = null;
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getHeight(Node node) {
        return node == null ? 0 : node.height;
    }

    public int getBalanceFactor(Node node) {
        return node == null ? 0 : getHeight(node.left) - getHeight(node.right);
    }

    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node node) {
        if (node == null)
            return true;

        if (Math.abs(getBalanceFactor(node)) > 1)
            return false;
        else
            return isBalanced(node.left) && isBalanced(node.right);
    }

    public void add(K key, V value) {
        root = add(root, key, value);
    }

    /**
     * 向以node为根的树插入数据e
     * @return 插入数据后的根节点
     */
    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        if (node.key.compareTo(key) > 0) {
            node.left = add(node.left, key, value);
        }
        else if (node.key.compareTo(key) < 0) {
            node.right = add(node.right, key, value);
        }
        else {
            node.value = value;
        }

        // 更新height
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;

        // 计算平衡因子 平衡维护
        return maintainBalance(node);
    }

    private Node maintainBalance(Node node) {
        if (node == null) return null;
        int balanceFactor = getBalanceFactor(node);
        // 左倾斜
        if (balanceFactor > 1) {
            if (getBalanceFactor(node.left) >= 0)
                // LL
                return rightRotate(node);
            else
                // LR
//                node.left = leftRotate(node.left);
//                return rightRotate(node);
                return leftRightRotate(node);
        }
        // 右倾斜
        if (balanceFactor < -1) {
            if (getBalanceFactor(node.right) <= 0)
                // RR
                return leftRotate(node);
            else
                // RL
//                node.right = rightRotate(node.right);
//                return leftRotate(node);
                return rightLeftRotate(node);
        }
        return node;
    }

    /**
     * 对节点y进行右旋转，返回旋转后新的根节点x
     *
     *          y                                x
     *        /  \        右旋转                /   \
     *       x   T4    --------->            z      y
     *     / \                              / \    /  \
     *    z  T3                            T1 T2  T3  T4
     *   / \
     *  T1  T2
     */
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T3 = x.right;

        // 旋转
        x.right = y;
        y.left = T3;

        // 更新height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        // 返回新的根节点x
        return x;
    }

    /**
     * 对节点y进行左旋转，返回旋转后新的根节点x
     *
     *          y                                x
     *        /  \        左旋转                /   \
     *       T4   x    --------->             y    z
     *           / \                        / \   / \
     *         T3  z                       T4 T3 T1 T2
     *            / \
     *          T1  T2
     */
    private Node leftRotate(Node y) {
        Node x = y.right;
        Node T3 = x.left;

        // 旋转
        x.left = y;
        y.right = T3;

        // 更新height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        // 返回新的根节点x
        return x;
    }

    /**
     *  左右旋转  先对x进行左旋转， 再对y进行右旋转
     *
     *          y                         y                             z
     *        /  \                      /  \                          /   \
     *       x   T4    --------->      z   T4    --------->          x     y
     *     / \                       /  \                          / \   /  \
     *    T3  z                     x   T2                        T3 T1 T2  T4
     *       / \                  / \
     *     T1  T2               T3  T1
     */
    private Node leftRightRotate(Node y) {
        Node x = y.left;
        Node z = x.right;
        Node T1 = z.left;
        Node T2 = z.right;

        // 旋转
        z.right = y;
        z.left = x;
        x.right = T1;
        y.left = T2;

        // 更新height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        z.height = Math.max(getHeight(z.left), getHeight(z.right)) + 1;

        // 返回新的根节点z
        return z;
    }

    /**
     *  右左旋转  先对x进行右旋转， 再对y进行左旋转
     *
     *          y                           y                                z
     *        /  \                        /  \                             /   \
     *       T4   x    --------->       T4    z        --------->         y    x
     *           / \                         / \                         / \   / \
     *          z  T3                       T1  x                       T4 T1 T2 T3
     *         / \                             / \
     *       T1  T2                          T2  T3
     */
    private Node rightLeftRotate(Node y) {
        Node x = y.right;
        Node z = x.left;
        Node T1 = z.left;
        Node T2 = z.right;

        // 旋转
        z.right = y;
        z.left = x;
        y.right = T1;
        x.left = T2;

        // 更新height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        z.height = Math.max(getHeight(z.left), getHeight(z.right)) + 1;

        // 返回新的根节点z
        return z;
    }

    public boolean contains(K key) {
        return contains(root, key);
    }

    /**
     * 向以node为根的树是否包含e
     */
    private boolean contains(Node node, K key) {
        if (node == null)
            return false;

        if (node.key.compareTo(key) > 0)
            return contains(node.left, key);
        else if (node.key.compareTo(key) < 0)
            return contains(node.right, key);
        return true;
    }

    private void visit(Node node) {
        System.out.println(node.key + " - " + node.value);
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node node) {
        if (node != null) {
            visit(node);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public void preOrderNR() {
        preOrderNR(root);
    }

    private void preOrderNR(Node node) {
        Stack<Node> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            Node n = stack.pop();
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

    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            visit(node);
            inOrder(node.right);
        }
    }

    public void inOrderNR() {
        inOrderNR(root);
    }

    private void inOrderNR(Node node) {
        Stack<Node> stack = new Stack<>();
        Node cur = node;
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

    private void postOrder(Node node) {
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

    private void levelOrder(Node node) {
        if (node == null) return;

        Queue<Node> queue = new ArrayDeque<>();
        queue.add(node);
        while(!queue.isEmpty()) {
            Node cur = queue.poll();
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
    public K min() {
        if (size == 0) return null;
        return (K) min(root).key;
    }

    private Node min(Node node) {
        if (node.left == null) return node;
        return min(node.left);
    }

    public K max() {
        if (size == 0) return null;
        return (K) max(root).key;
    }

    private Node max(Node node) {
        if (node.right == null) return node;
        return max(node.right);
    }

    /**
     * 删除操作
     */
    public K removeMin() {
        K ret = min();
        root = removeMin(root);
        return ret;
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            Node right = node.right;
            node.right = null;
            size--;
            return right;
        }
        node.left = removeMin(node.left);
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        return maintainBalance(node);
    }

    public K removeMax() {
        K ret = max();
        root = removeMax(root);
        return ret;
    }

    private Node removeMax(Node node) {
        if (node.right == null) {
            Node left = node.left;
            node.left = null;
            size--;
            return left;
        }

        node.right = removeMax(node.right);
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        return maintainBalance(node);
    }

    public void remove(K key) {
        remove(root, key);
    }

    private Node remove(Node node, K key) {
        if (node == null) return null;

        if (node.key.compareTo(key) == 0) {
            Node successor;
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

            successor.height = Math.max(getHeight(successor.left), getHeight(successor.right)) + 1;
            return maintainBalance(successor);
        }
        else if (node.key.compareTo(key) > 0)
            node.left = remove(node.left, key);
        else
            node.right = remove(node.right, key);

        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        return maintainBalance(node);
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        generateBSTString(root, 0, sb);
        return sb.toString();
    }

    private void generateBSTString(Node node, int depth, StringBuilder sb) {
        if (node == null) {
            sb.append(generateDepthString(depth) + "null\n");
            return;
        }

        sb.append(generateDepthString(depth) + node.key + "\n");
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

    public static void main(String[] args) {
        AVLTree<Integer, Integer> avl = new AVLTree<>();
        avl.add(1, 1);
        avl.add(2, 1);
        avl.add(3, 1);
        avl.add(4, 1);
        avl.add(5, 1);
        avl.add(6, 1);
        avl.add(7, 1);

        System.out.println(avl.toString());

        avl.preOrder();
    }
}
