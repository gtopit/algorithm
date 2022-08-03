package com.gtop.work.demo.algorithm.tree;

import com.gtop.work.demo.algorithm.common.Node;

/**
 * 给定一个二叉树的头节点head，任何两个节点之间都存在距离，返回整棵二叉树的最大距离。
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/12/29 16:12
 */
public class BinaryTreeMaxDistance {

    public static void main(String[] args) {
        Node<Integer> root = new Node<>(1);
        root.left = new Node<>(2);
        root.right = new Node<>(3);
        root.left.left = new Node<>(4);
        root.left.right = new Node<>(5);
        root.left.right.left = new Node<>(6);
        System.out.println(getMaxInstance(root));
    }

    public static int getMaxInstance(Node<Integer> root) {
        Info info = doGetMaxInstance(root);
        return info.distance;
    }

    /**
     * 两种情况，1）经过自己的最大距离；2）不经过自己的最大距离
     *
     * @param root
     * @return
     */
    private static Info doGetMaxInstance(Node<Integer> root) {
        Info info = new Info();
        info.distance = 0;
        info.high = 0;
        if (root == null) {
            return info;
        }
        // 不经过自己时，要么在左子树上获得最大，要么在右子树上获得最大
        Info li = doGetMaxInstance(root.left);
        Info ri = doGetMaxInstance(root.right);

        // 本节点树高
        info.high = Math.max(li.high, ri.high) + 1;

        int p1 = li.distance;
        int p2 = ri.distance;
        // 经过自己时，左边最长路径就是左子树的高度，右边最长路径就是右子树高度
        int p3 = li.high + ri.high + 1;
        info.distance = Math.max(Math.max(p1, p2), p3);
        return info;
    }

    static class Info {
        int distance;
        int high;
    }

}
