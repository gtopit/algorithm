package com.gtop.work.demo.algorithm.list;

/**
 * 将一条链表上的数据按照给定的值分成三部分，小于pivot的放在左边，等于的放在中间，大于pivot的放在右边
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/11/19 22:18
 */
public class SmallerEqualBigger {

    public static void main(String[] args) {
        Node head = new Node(5);
        head.next = new Node(8);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(9);
        head.next.next.next.next.next = new Node(1);
        head.next.next.next.next.next.next = new Node(7);
        head.next.next.next.next.next.next.next = new Node(2);
        head.next.next.next.next.next.next.next.next = new Node(6);
        head.next.next.next.next.next.next.next.next.next = new Node(8);
        head.next.next.next.next.next.next.next.next.next.next = new Node(6);

        Node newHead = listPartition(head, 6);

        while (null != newHead) {
            System.out.printf("%d,", newHead.value);
            newHead = newHead.next;
        }

    }

    public static Node listPartition(Node head, int pivot) {
        Node sh = null; // smaller head
        Node st = null; // smaller tail
        Node eh = null; // equal head
        Node et = null; // equal tail
        Node bh = null; // bigger head
        Node bt = null; // bigger tail

        if (null == head) {
            return null;
        }

        Node cur = null;
        while (null != head) {
            cur = head.next;
            head.next = null;
            if (head.value < pivot) {
                if (null == sh) {
                    sh = head;
                    st = head;
                } else {
                    st.next = head;
                    st = head;
                }
            } else if (head.value > pivot) {
                if (null == bh) {
                    bh = head;
                    bt = head;
                } else {
                    bt.next = head;
                    bt = head;
                }
            } else {
                if (null == eh) {
                    eh = head;
                    et = head;
                } else {
                    et.next = head;
                    et = head;
                }
            }
            head = cur;
        }

        if (null == sh) {
            if (null == eh) {
                cur = bt;
            }
        } else {
            cur = sh;
            if (null == eh) {
                st.next = bh;
            } else {
                st.next = eh;
                et.next = bh;
            }
        }

        return cur;
    }

    public static class Node {
        int value;
        Node next;

        public Node(int v) {
            value = v;
        }
    }

}
