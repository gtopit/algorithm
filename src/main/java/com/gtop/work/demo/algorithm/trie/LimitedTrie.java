package com.gtop.work.demo.algorithm.trie;

/**
 * 有限前缀树（记录26个英文字母的前缀树）
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/11/1 12:33
 */
public class LimitedTrie {

    public static void main(String[] args) {
        LimitedTrie trie = new LimitedTrie();
        trie.insert("abc");
        trie.insert("abd");
        trie.insert("aef");
        trie.insert("aefe");

        trie.delete("abe");
        System.out.println(trie.search("abc"));

    }

    public int countPrefix(String pre) {
        if (null == pre) {
            return 0;
        }

        char[] chars = pre.toLowerCase().toCharArray();
        Node node = root;
        int path;
        for (int i = 0; i < chars.length; i++) {
            path = chars[i] - BASE_CHAR;
            node = node.nexts[path];
            if (null == node) {
                return 0;
            }
        }

        return node.pass;
    }

    public int search(String word) {

        int count = 0;
        if (null == word) {
            return count;
        }
        char[] chars = word.toCharArray();

        Node node = root;
        int path;
        for (int i = 0; i < chars.length; i++) {
            path = chars[i] - BASE_CHAR;
            if (null == (node = node.nexts[path])) {
                break;
            }
        }

        if (null != node) {
            count = node.end;
        }

        return count;
    }

    public void insert(String e) {
        char[] chars = e.toLowerCase().toCharArray();
        int path = 0;
        Node node = root;
        Node nextNode = null;
        for (int i = 0; i < chars.length; i++) {
            path = chars[i] - BASE_CHAR;
            nextNode = node.nexts[path];
            if (null == nextNode) {
                nextNode = new Node();
                node.nexts[path] = nextNode;
            }
            node = nextNode;
            nextNode.pass++;
        }
        nextNode.end++;
    }

    public void delete(String word) {

        if (null == word || search(word) < 1) {
            return;
        }

        char[] chars = word.toLowerCase().toCharArray();
        Node node = root;
        int path;
        for (int i = 0; i < chars.length; i++) {
            path = chars[i] - BASE_CHAR;
            node = node.nexts[path];
            node.pass--;
        }
        node.end--;
        if (node.end < 1) {
            node = null;
        }

    }

    public LimitedTrie() {
        root = new Node();
    }

    private Node root;

    private static final char BASE_CHAR = 'a';

    private static class Node {
        int pass;
        int end;
        Node[] nexts;

        public Node() {
            this.pass = 0;
            this.end = 0;
            nexts = new Node[26];
        }

    }

}
