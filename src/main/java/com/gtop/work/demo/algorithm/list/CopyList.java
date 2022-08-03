package com.gtop.work.demo.algorithm.list;

/**
 * 克隆链表
 * 问题描述：
 * 有一个链表，链表的节点除了有指向下一节点的引用外，还有另外一个任意指向的节点引用，
 * 现需要将此链表进行拷贝，生成一个和原链表一样的复制链表
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/11/8 15:48
 */
public class CopyList {

    private static class Node {
        int data;
        Node next;
        Node random;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {

        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.random = head.next.next;
        head.next.random = head;
        head.next.next.random = head.next.next.next;

        Node copyHead = copy(head);

        Node cur = head;
        while (null != cur) {
            System.out.print("data=" + cur.data + ",random=" + (null != cur.random ? cur.random.data : "NULL") + "    ");
            cur = cur.next;
        }
        System.out.println();
        cur = copyHead;
        while (null != cur) {
            System.out.print("data=" + cur.data + ",random=" + (null != cur.random ? cur.random.data : "NULL") + "    ");
            cur = cur.next;
        }

    }

    /**
     * @param head 返回新拷贝链表的头
     *             实现思路
     *             1、新建和原来一样多的节点，然后让原节点的next分别指向一个，新节点的next指向原节点next指向的那一个
     *             也就是在原节点相邻的两个节点之间插入一个节点
     *             2、将random引用赋值
     *             3、分离出复制链表
     * @return
     */
    public static Node copy(Node head) {
        Node newHead = null;
        Node cur = head;
        Node temp = null;
        while (null != cur) {
            temp = cur.next;
            cur.next = new Node(cur.data);
            cur.next.next = temp;
            cur = temp;
        }

        cur = head;
        while (null != cur) {
            temp = cur.random;
            if (null != temp) {
                cur.next.random = temp.next;
            } else {
                cur.next.random = null;
            }
            cur = cur.next.next;
        }

        Node old = head;
        newHead = head.next;
        Node copy = newHead;
        while (null != old && null != old.next) {
            temp = old.next.next;
            old.next = temp;
            if (null != copy.next) {
                copy.next = copy.next.next;
                copy = copy.next.next;
            }
            old = temp;
        }

        return newHead;
    }

}
