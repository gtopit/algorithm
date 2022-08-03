package com.gtop.work.demo.algorithm.tree;

import com.gtop.work.demo.algorithm.common.Node;

/**
 * 返回最大搜索子树节点数
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/12/29 17:16
 */
public class MaxSubSBT {

    public static void main(String[] args) {
        Node<Integer> root = new Node<>(9);
        root.left = new Node<>(6);
        root.right = new Node<>(8);
        root.left.left = new Node<>(3);
        root.left.right = new Node<>(7);
        root.left.left.left = new Node<>(1);
        root.left.left.right = new Node<>(5);
        System.out.println(getMaxSubSBT(root));
    }

    public static int getMaxSubSBT(Node<Integer> root) {
        return getMaxSubSBT0(root).size;
    }

    private static Info getMaxSubSBT0(Node<Integer> root) {
        Info info = new Info();
        info.size = 0;
        info.isSub = true;
        if (root == null) {
            return info;
        }
        if (root.left != null && root.left.data > root.data) {
            info.isSub = false;
        }
        if (root.right != null && root.right.data < root.data) {
            info.isSub = false;
        }
        Info li = getMaxSubSBT0(root.left);
        Info ri = getMaxSubSBT0(root.right);

        info.size = info.isSub ? li.size + ri.size + 1 : Math.max(li.size, ri.size);

        return info;
    }

    static class Info {
        // 当前节点收集到的最大搜索二叉树大小
        int size;
        // 表示当前节点是否为搜索二叉树
        boolean isSub;
    }

}
