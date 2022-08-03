package com.gtop.work.demo.algorithm.list;

/**
 * 回文链表检测
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/11/5 16:24
 */
public class SingleList {

    private Node head;
    private int size;

    public SingleList() {
        head = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public Node delete(int index) {
        if (index >= size) {
            throw new RuntimeException("out of array bounds");
        }
        Node cur = head;
        if (index == 0) {
            head = cur.next;
        }

        for (int i = 0; i < index - 1; i++) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        size--;

        return head;
    }

    public void add(int data) {
        Node node = new Node(data);
        Node cur = head;
        if (null == cur) {
            head = node;
        } else {
            while (null != cur.next) {
                cur = cur.next;
            }
            cur.next = node;
        }
        size++;
    }

    public int get(int index) {
        if (index >= size) {
            throw new RuntimeException("out of array bounds");
        }
        Node cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.data;
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
        SingleList list = new SingleList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);

        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();
        list.delete(0);

        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }

        System.out.println();

        list.delete(1);

        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }

        System.out.println();

    }

}
