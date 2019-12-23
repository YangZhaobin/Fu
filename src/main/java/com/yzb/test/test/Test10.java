package com.yzb.test.test;

import java.util.HashMap;
import java.util.Map;

public class Test10 {
    public static void main(String[] args){

        MagicDictionary dictionary = new MagicDictionary();
        dictionary.add("hello");
//        dictionary.add("hallo");
        dictionary.add("leetcode");

        System.out.println(dictionary.search("hello"));
    }

    static class MagicDictionary {

        private class Node {
            boolean isWord;
            Map<Character, Node> next;

            public Node() {
                isWord = false;
                next = new HashMap<>();
            }
        }

        private Node root;

        /** Initialize your data structure here. */
        public MagicDictionary() {
            this.root = new Node();
        }

        /** Build a dictionary through a list of words */
        public void buildDict(String[] dict) {
            for (String d : dict) {
                add(d);
            }
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
            cur.isWord = true;
        }

        /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
        public boolean search(String word) {
            return match(root, word, 0, false);
        }

        private boolean match(Node node, String word, int index, boolean changed) {
            if (index == word.length()) {
                return node.isWord && changed;
            }
            char c = word.charAt(index);
            if (node.next.get(c) == null) {
                if (changed) return false;
                for (char nextChar : node.next.keySet()) {
                    if (match(node.next.get(nextChar), word, index + 1, true))
                        return true;
                }
                return false;
            }
            else if (!changed) {
                for (char nextChar : node.next.keySet()) {
                    boolean b = c != nextChar;
                    if (match(node.next.get(nextChar), word, index + 1, b))
                        return true;
                }
                return false;
            }
            return match(node.next.get(c), word, index + 1, changed);
        }
    }

}
