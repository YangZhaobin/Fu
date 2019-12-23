package com.yzb.test.test;

import java.util.*;

public class Test9 {
    public static void main(String[] args){
        List<String> addPotList = new ArrayList<>();
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("ab");
        list.add("v");


        Collections.copy(addPotList, list);
        System.out.println(addPotList);

//        List<String> dict = new ArrayList<>();
//        dict.add("cat");
//        dict.add("bat");
//        dict.add("rat");
//        System.out.println(replaceWords(dict, "the cattle was rattled by the battery"));
    }


    public static class Trie {
        private class Node {
            boolean isWord;
            String word;
            Map<Character, Node> next;

            public Node() {
                isWord = false;
                word = "";
                next = new HashMap<>();
            }
        }

        private Node root;

        public Trie() {
            this.root = new Node();
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
            cur.word = word;
        }

        /**
         * 查询字典树中单词的前缀 没有则直接返回单词
         */
        public String prefix(String word) {
            Node cur = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (cur.next.get(c) == null)
                    return word;
                cur = cur.next.get(c);
                if (cur.isWord)
                    return cur.word;
            }

            return word;
        }
    }

    public static String replaceWords(List<String> dict, String sentence) {
        String[] words = sentence.split(" ");

        Trie trie = new Trie();
        for (String d : dict) {
            trie.add(d);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            words[i] = trie.prefix(words[i]);
            sb.append(words[i]);
            if (i != words.length - 1) {
                sb.append(" ");
            }
        }

        return sb.toString();
    }

}
