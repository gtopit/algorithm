package com.gtop.work.demo.algorithm.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 找出二叉树拥有节点最多的那层（二叉树最大宽度）
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/12/29 10:05
 */
public class FindMaxNodeLevel {

    public static void main(String[] args) {
        Node root = new Node('a');
        root.left = new Node('b');
        root.right = new Node('c');
        root.left.left = new Node('d');
        root.left.right = new Node('e');
        root.right.left = new Node('f');
        root.right.right = new Node('g');
        root.left.right.left = new Node('h');
        root.left.right.right = new Node('i');
        root.right.left.right = new Node('j');
        root.right.right.left = new Node('k');
        root.right.right.right = new Node('l');
        System.out.println(findMax(root));
    }

    /**
     * 思路：1、按层遍历，遍历本层时找到下一层第一个出现的节点nf，
     * 2、如果遍历到节点等于nf时，则说明来到下一层了，继续1步骤
     * 3、直至结束
     *
     * @param root
     * @return
     */
    public static int findMax(Node root) {
        if (root == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        Node cur = root;
        queue.add(cur);
        Node nf = null;
        int max = 0;
        int count = 0;
        while (!queue.isEmpty()) {
            cur = queue.poll();
            if (cur == nf) {
                max = Math.max(max, count);
                count = 0;
                nf = null;
            }
            count++;
            if (cur.left != null) {
                queue.add(cur.left);
                if (nf == null) {
                    nf = cur.left;
                }
            }
            if (cur.right != null) {
                queue.add(cur.right);
                if (nf == null) {
                    nf = cur.right;
                }
            }
        }
        max = Math.max(max, count);
        return max;
    }

    static class Node {
        char data;
        Node left;
        Node right;

        public Node(char data) {
            this.data = data;
        }
    }

}
