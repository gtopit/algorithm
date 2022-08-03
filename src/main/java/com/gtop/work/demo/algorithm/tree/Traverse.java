package com.gtop.work.demo.algorithm.tree;

import java.util.Stack;

/**
 * 遍历
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/11/8 19:59
 */
public class Traverse {

    public static void main(String[] args) {

        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);

        System.out.println("----------------");
        pre(head);
        System.out.println();
        preUseStack(head);
        System.out.println();
        System.out.println("----------------");
        in(head);
        System.out.println();
        inUseStack(head);
        System.out.println();
        System.out.println("----------------");
        post(head);
        System.out.println();
        postUseStack(head);
        System.out.println();

    }

    /**
     * 先序遍历：根-左-右
     *
     * @param head
     */
    public static void pre(Node head) {

        if (null == head) {
            return;
        }
        System.out.printf("%d ", head.data);
        pre(head.left);
        pre(head.right);
    }

    /**
     * 中序遍历：左-根-右
     *
     * @param head
     */
    public static void in(Node head) {
        if (null == head) {
            return;
        }
        in(head.left);
        System.out.printf("%d ", head.data);
        in(head.right);
    }

    /**
     * 后续遍历：左-右-根
     *
     * @param head
     */
    public static void post(Node head) {
        if (null == head) {
            return;
        }
        post(head.left);
        post(head.right);
        System.out.printf("%d ", head.data);
    }

    /**
     * 先序遍历：根-左-右
     * 不用递归
     *
     * @param head
     */
    public static void preUseStack(Node head) {

        if (null == head) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        Node cur = head;
        stack.push(cur);

        while (!stack.isEmpty() && null != cur) {
            cur = stack.pop();
            System.out.printf("%d ", cur.data);
            if (null != cur.right) {
                stack.push(cur.right);
            }
            if (null != cur.left) {
                stack.push(cur.left);
            }
        }

    }

    /**
     * 中序遍历：左-根-右
     * 不用递归
     *
     * @param head
     */
    public static void inUseStack(Node head) {

        if (null == head) {
            return;
        }

        Node cur = head;
        Stack<Node> stack = new Stack<>();

        while (!stack.isEmpty() || null != cur) {

            if (null != cur) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                System.out.printf("%d ", cur.data);
                cur = cur.right;
            }

        }

    }

    /**
     * 后序遍历：左-右-根
     * 不用递归
     *
     * @param head
     */
    public static void postUseStack(Node head) {

        if (null == head) {
            return;
        }

        Node cur = head;

        Stack<Node> stack = new Stack<>();
        Stack<Node> help = new Stack<>();

        stack.push(cur);
        while (!stack.isEmpty()) {

            cur = stack.pop();
            help.push(cur);

            if (null != cur.left) {
                stack.push(cur.left);
            }

            if (null != cur.right) {
                stack.push(cur.right);
            }

        }

        while (!help.isEmpty()) {
            System.out.printf("%d ", help.pop().data);
        }

    }

    private static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }

}
