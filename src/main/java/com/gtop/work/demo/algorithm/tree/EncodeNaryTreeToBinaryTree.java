package com.gtop.work.demo.algorithm.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * N-ary tree 序列化成 bin tree ， bin tree 反序列化 成 N-ary tree
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/12/28 19:24
 */
public class EncodeNaryTreeToBinaryTree {


    public static TreeNode nt2bt(Node root) {
        TreeNode head = new TreeNode(root.val);
        head.left = nt2bt0(root.children);
        return head;
    }

    public static Node bt2nt(TreeNode root) {
        Node head = new Node(root.val, bt2nt0(root.left));
        return head;
    }

    private static List<Node> bt2nt0(TreeNode cur) {
        List<Node> children = new ArrayList<>();
        Node node;
        while (cur != null) {
            node = new Node(cur.val, bt2nt0(cur.left));
            children.add(node);
            cur = cur.right;
        }
        return children;
    }

    private static TreeNode nt2bt0(List<Node> children) {
        TreeNode head = null;
        TreeNode cur = null;
        TreeNode node;
        for (Node child : children) {
            node = new TreeNode(child.val);
            if (head == null) {
                head = node;
            } else {
                cur.right = node;
            }
            cur = node;
            cur.left = nt2bt0(child.children);
        }
        return head;
    }

    // 提交时不要提交这个类
    public static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    ;

    // 提交时不要提交这个类
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}

