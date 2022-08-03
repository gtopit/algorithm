package com.gtop.work.demo.algorithm.tree;

import com.gtop.work.demo.algorithm.common.Node;

/**
 * 完全二叉树判断
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/12/29 15:11
 */
public class IsTotalBinaryTree {

    public static void main(String[] args) {
        Node<Integer> root = new Node<>(1);
        root.left = new Node<>(2);
        root.right = new Node<>(3);
        root.left.left = new Node<>(4);
        root.left.right = new Node<>(5);
        root.right.right = new Node<>(7);
        System.out.println(isTotal(root));
    }

    public static boolean isTotal(Node<Integer> root) {
        if (root == null) {
            return true;
        }
        if (root.right != null && root.left == null) {
            return false;
        }
        boolean pl = isTotal(root.left);
        boolean pr = isTotal(root.right);
        return pl && pr;
    }

}
