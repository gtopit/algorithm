package com.gtop.work.demo.algorithm.tree;

import com.gtop.work.demo.algorithm.common.Node;

/**
 * 搜索二叉树判断（每一棵子二叉树的头节点都比左边值大，比右节点值小）
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/12/29 15:33
 */
public class IsSBT2 {

    public static void main(String[] args) {
        Node<Integer> root = new Node<>(5);
        root.left = new Node<>(3);
        root.right = new Node<>(8);
        root.right.left = new Node<>(7);
        root.right.right = new Node<>(9);
        System.out.println(isSBT(root));
    }

    public static boolean isSBT(Node<Integer> root) {
        if (root == null) {
            return true;
        }
        if (root.left != null && root.left.data > root.data) {
            return false;
        }
        if (root.right != null && root.right.data < root.data) {
            return false;
        }
        boolean pl = isSBT(root.left);
        boolean pr = isSBT(root.right);
        return pl && pr;
    }

}
