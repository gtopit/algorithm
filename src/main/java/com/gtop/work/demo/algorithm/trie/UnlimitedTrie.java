package com.gtop.work.demo.algorithm.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * 无限前缀树
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/11/1 16:36
 */
public class UnlimitedTrie {

    public static void main(String[] args) {
        UnlimitedTrie trie = new UnlimitedTrie();
        trie.insert("中国");
        trie.insert("中心");
        trie.insert("中国");
        trie.insert("中华人民共和国");
        trie.insert("中央国务院");

        System.out.println(trie.search("中国"));
        System.out.println(trie.countPrefix("中"));

        trie.delete("中心");
        System.out.println(trie.search("中心"));

    }

    public void delete(String word) {
        if (null == word || search(word) < 1) {
            return;
        }

        char[] chars = word.toCharArray();
        Node node = root;
        int path;
        for (int i = 0; i < chars.length; i++) {
            path = getIndexOf(chars[i]);
            node = node.nexts.get(path);
            node.pass--;
        }
        node.end--;
        if (node.end < 1) {
            node = null;
        }
    }

    public int countPrefix(String word) {

        if (null == word) {
            return 0;
        }

        char[] chars = word.toCharArray();
        int path;
        Node node = root;
        for (int i = 0; i < chars.length; i++) {
            path = getIndexOf(chars[i]);
            node = node.nexts.get(path);
            if (null == node) {
                return 0;
            }
        }
        return node.pass;

    }

    public int search(String word) {

        if (null == word) {
            return 0;
        }

        char[] chars = word.toCharArray();
        int path;
        Node node = root;
        for (int i = 0; i < chars.length; i++) {
            path = getIndexOf(chars[i]);
            node = node.nexts.get(path);
            if (null == node) {
                return 0;
            }
        }

        return node.end;

    }

    public void insert(String word) {

        if (null == word) {
            return;
        }

        char[] chars = word.toCharArray();
        Node node = root;
        int path;
        Node nextNode;
        for (int i = 0; i < chars.length; i++) {
            path = getIndexOf(chars[i]);
            nextNode = node.nexts.get(path);
            if (null == nextNode) {
                nextNode = new Node();
                node.nexts.put(path, nextNode);
            }
            node = nextNode;
            node.pass++;
        }
        node.end++;

    }

    private int getIndexOf(char aChar) {
        return aChar;
    }

    public UnlimitedTrie() {
        root = new Node();
    }

    private Node root;

    private static class Node {
        int pass;
        int end;

        Map<Integer, Node> nexts;

        public Node() {
            pass = 0;
            end = 0;
            nexts = new HashMap<>();
        }

    }

}
