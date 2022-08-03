package com.gtop.work.demo.algorithm.tree;

import com.gtop.work.demo.algorithm.common.Node;

/**
 * 满二叉树判断
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/12/29 17:05
 */
public class IsFullBinaryTree {

    public static void main(String[] args) {
        Node<Integer> root = new Node<>(10);
        root.left = new Node<>(1);
        root.right = new Node<>(2);
        root.left.right = new Node<>(3);
        System.out.println(isFull(root));
    }

    public static boolean isFull(Node<Integer> root) {
        if (root == null) {
            return true;
        }
        if ((root.left == null && root.right == null)) {
            return true;
        }
        if (root.left != null && isFull(root.left) && root.right != null && isFull(root.right)) {
            return true;
        }
        return false;
    }

}
