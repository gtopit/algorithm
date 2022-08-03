package com.gtop.work.demo.algorithm.tree;

import com.gtop.work.demo.algorithm.common.Node;

/**
 * 平衡二叉树判断（左子树是平衡二叉树，右子树是平衡二叉树，左子树和右子树最大高度差不超过1）
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/12/29 15:57
 */
public class IsAVLTree {

    public static void main(String[] args) {
        Node<Integer> root = new Node<>(1);
        root.left = new Node<>(2);
        root.right = new Node<>(3);
        root.left.left = new Node<>(4);
        root.left.left.left = new Node<>(5);
        System.out.println(isAVLTree(root));
    }

    public static boolean isAVLTree(Node<Integer> root) {
        Info info = doIsAVLTree(root);
        return info.isOk;
    }

    private static Info doIsAVLTree(Node<Integer> root) {

        Info info = new Info();
        info.isOk = true;
        info.high = 0;
        if (root == null) {
            return info;
        }

        Info li = doIsAVLTree(root.left);
        Info ri = doIsAVLTree(root.right);

        info.isOk = Math.abs(li.high - ri.high) > 1 ? false : true;
        info.high = Math.max(li.high, ri.high) + 1;

        return info;

    }

    static class Info {
        int high;
        boolean isOk;
    }

}
