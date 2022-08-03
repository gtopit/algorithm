package com.gtop.work.demo.algorithm.list;

import java.util.Stack;

/**
 * 回文链表特点，从头部开始和从尾部开始对比，如果数据能一一对应，则为回文链表，例：
 * 链表为奇数时：1 -> 2 -> 3 -> 2 -> 1
 * 链表为偶数时：1 -> 2 -> 3 -> 3 -> 2 -> 1
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/11/5 19:41
 */
public class PalindromeList {

    /**
     * 判断链表是否为回文链表
     * 实现：
     * 定义一个栈，然后定义两个指针变量，分别为right和cur，
     * 一开始，cur指向链表头，right指向链表头的下一个
     * 通过循环，每次cur向下指向两个位置，right向下指向一个位置
     * cur遍历结束，根据right可以确定链表一半的长度位置，
     * 然后将right之后的每个node入栈，拿链表头和栈弹出的每个元素比较，如果存在不一样的，
     * 直接返回false，
     * 否则为true
     *
     * @param head
     */
    public static boolean isPalindromeList(Node head) {
        Node cur = head;
        Node right = head.next;

        Stack<Node> stack = new Stack<>();

        while (null != cur.next && null != cur.next.next) {
            right = right.next;
            cur = cur.next.next;
        }

        while (null != right) {
            stack.push(right);
            right = right.next;
        }

        cur = head;
        while (!stack.isEmpty()) {
            if (cur.data != stack.pop().data) {
                return false;
            } else {
                cur = cur.next;
            }
        }

        return true;

    }

    public static boolean isPalindromeWithoutStack(Node head) {

        Node right = head;
        Node cur = head;

        while (null != cur.next && null != cur.next.next) {
            right = right.next;
            cur = cur.next.next;
        }

        cur = right.next;
        right.next = null;
        Node temp;
        while (null != cur) {
            temp = cur.next;
            cur.next = right;
            right = cur;
            cur = temp;
        }
        cur = head;
        temp = right;
        boolean isOk = true;
        while (null != cur && null != right) {
            if (cur.data != right.data) {
                isOk = false;
                break;
            }
            cur = cur.next;
            right = right.next;
        }

        right = temp;
        cur = temp.next;
        right.next = null;
        while (null != cur) {
            temp = cur.next;
            cur.next = right;
            right = cur;
            cur = temp;
        }

        return isOk;
    }

    private static class Node {
        int data;
        Node next;

        public Node(int e) {
            data = e;
            next = null;
        }
    }

    public static void main(String[] args) {

        Node node = new Node(1);
        Node head = node;
        node = new Node(2);
        head.next = node;
        node = new Node(3);
        head.next.next = node;
        node = new Node(4);
        head.next.next.next = node;
        node = new Node(3);
        head.next.next.next.next = node;
        node = new Node(2);
        head.next.next.next.next.next = node;
        node = new Node(1);
        head.next.next.next.next.next.next = node;

        System.out.println(isPalindromeList(head));

        System.out.println(isPalindromeWithoutStack(head));

        while (null != head) {
            System.out.println(head.data);
            head = head.next;
        }

    }

}
