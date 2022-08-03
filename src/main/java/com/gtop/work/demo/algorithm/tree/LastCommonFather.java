package com.gtop.work.demo.algorithm.tree;

import com.gtop.work.demo.algorithm.common.Node;

/**
 * 二叉树任意两个节点的最低公共祖先
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/12/29 18:13
 */
public class LastCommonFather {

    public static void main(String[] args) {
        Node<Integer> root = new Node<>(9);
        root.left = new Node<>(6);
        root.right = new Node<>(10);
        root.left.left = new Node<>(3);
        root.left.right = new Node<>(7);
        root.left.left.left = new Node<>(1);
        root.left.left.right = new Node<>(5);
        System.out.println(findAncestor(root, 3, 7));
    }

    public static Node<Integer> findAncestor(Node<Integer> root, Integer a, Integer b) {
        if (a == null || b == null) {
            return null;
        }
        return findAncestor0(root, a, b).ans;
    }

    private static Info findAncestor0(Node<Integer> cur, Integer a, Integer b) {
        Info info = new Info();
        info.ans = null;
        if (cur == null) {
            return info;
        }

        if ((find(cur.left, a) && find(cur.right, b)) || (find(cur.left, b) && find(cur.right, a))) {
            info.isOnBothSide = true;
            info.ans = cur;
        } else {
            Info li = findAncestor0(cur.left, a, b);
            Info ri = findAncestor0(cur.right, a, b);
            info.ans = li.isOnBothSide ? li.ans : ri.ans;
        }
        return info;
    }

    private static boolean find(Node<Integer> cur, Integer a) {

        Node next = cur;
        while (next != null) {
            if (next.data.compareTo(a) == 0) {
                return true;
            } else if (next.data.compareTo(a) < 0) {
                next = next.right;
            } else {
                next = next.left;
            }
        }

        return false;
    }

    static class Info {
        // 表示要查找的节点分别在当前节点的左右子树上
        boolean isOnBothSide;
        // 答案
        Node<Integer> ans;
    }

}
