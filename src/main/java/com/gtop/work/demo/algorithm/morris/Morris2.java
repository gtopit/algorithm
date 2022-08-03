package com.gtop.work.demo.algorithm.morris;

import org.junit.Test;

/**
 * @author hongzw@citycloud.com.cn
 * @Date 2022/7/31 7:56
 */
public class Morris2 {

    public static void morris(Node head) {
        Node cur = head;
        Node mostRight;
        while (cur != null) {
            if (cur.left == null) {
                cur = cur.right;
            } else {
                mostRight = cur.left;
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                } else {
                    mostRight.right = null;
                    cur = cur.right;
                }
            }
        }
    }

    /**
     * morris遍历改后续遍历
     * @param head
     */
    public static void postMorris(Node head) {
        Node cur = head;
        Node mostRight;
        while (cur != null) {
            if (cur.left == null) {
                cur = cur.right;
            } else {
                mostRight = cur.left;
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                } else {
                    mostRight.right = null;
                    // 打印左子树
                    printPost(cur.left);
                    cur = cur.right;
                }
            }
        }
        // 打印head为头的右子树
        printPost(head);
    }

    private static void printPost(Node cur) {
        Node head = reverseList(cur);
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.right;
        }
        reverseList(head);
    }

    private static Node reverseList(Node reverse) {
        Node temp;
        Node next = reverse.right;
        reverse.right = null;
        while (next != null) {
            temp = next.right;
            next.right = reverse;
            reverse = next;
            next = temp;
        }
        return reverse;
    }

    @Test
    public void test() {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);
        postMorris(head);
    }

    @Test
    public void  test2() {
        byte[] bs = new byte[] {0x65, 0x66};
        System.out.println(new String(bs));
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
