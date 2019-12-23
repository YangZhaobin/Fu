package com.yzb.test.ds.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * Trie字典树
 *
 * 查找时间复杂度与存储元素个数N无关，与字符串长度有关
 * - 假设所有字符串长度之和为n，构建字典树的时间复杂度为O(n)
 * - 假设要查找的字符串长度为k，查找的时间复杂度为O(k)
 */
public class Trie {
    private class Node {
        // 标识该节点是否是一个单词的结束
        boolean isWord;
        Map<Character, Node> next;

        public Node() {
            isWord = false;
            next = new HashMap<>();
        }
    }

    private Node root;
    private int size;

    public Trie() {
        this.root = new Node();
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public void add(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        if (!cur.isWord) {
            size++;
        }
        cur.isWord = true;
    }

    public boolean contains(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }

    public boolean match(String word) {
        return match(root, word, 0);
    }

    private boolean match(Node node, String word, int index) {
        if (index == word.length()) {
            return node.isWord;
        }
        char c = word.charAt(index);
        if (c != '.') {
            if (node.next.get(c) == null)
                return false;
            return match(node.next.get(c), word, index + 1);
        }
        for (char nextChar : node.next.keySet()) {
            if (match(node.next.get(nextChar), word, index + 1))
                return true;
        }
        return false;
    }

    /**
     * 查询字典树中是否存在单词以prefix为前缀
     */
    public boolean isPrefix(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        return true;
    }
}
