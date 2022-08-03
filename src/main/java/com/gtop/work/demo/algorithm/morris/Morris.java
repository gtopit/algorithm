package com.gtop.work.demo.algorithm.morris;

/**
 * Morris遍历
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/12/17 10:56
 */
public class Morris {

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);
        morrisPre(head);
        morrisMid(head);
        morrisPost(head);
    }

    /**
     * 使用cur表示当前来到的节点，遍历规则：
     * 1、cur不存在左节点，cur右移
     * 2、cur存在左节点，找到左子树的最右子节点
     * 2.1、如果左子树的最右子节点为空，则让其指向cur，然后cur左移
     * 2.2、如果左子树的最右子节点等于cur，则让左子树的最右子节点变为空，cur右移
     * 3、直到cur为空结束
     *
     * @param head
     */
    public static void morris(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            cur = cur.right;
        }

    }

    /**
     * 先序遍历
     * morris遍历的顺序举例：
     * 1
     * /   \
     * 2     3
     * / \   / \
     * 4  5  6   7
     * morris序：1 2 4 2 5 1 3 6 3 7
     * 先序遍历：1 2 4 5 3 6 7
     * 观察morris序可知，一个节点最多有两次机会到达自己，只在第一次到达时处理（打印），就可以变得和先序遍历一样
     *
     * @param head
     */
    public static void morrisPre(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    System.out.printf("%d ", cur.data);
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            } else {
                System.out.printf("%d ", cur.data);
            }
            cur = cur.right;
        }
        System.out.println();
    }

    /**
     * 中序遍历
     * morris遍历的顺序举例：
     * 1
     * /   \
     * 2     3
     * / \   / \
     * 4  5  6   7
     * morris序：1 2 4 2 5 1 3 6 3 7
     * 中序遍历：4 2 5 1 6 3 7
     * 观察morris序可知，一个节点最多有两次机会到达自己，只在第二次时到达时处理（打印），就可以变得和先序遍历一样
     *
     * @param head
     */
    public static void morrisMid(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    System.out.printf("%d ", cur.data);
                    mostRight.right = null;
                }
            } else {
                System.out.printf("%d ", cur.data);
            }
            cur = cur.right;
        }

        System.out.println();

    }

    /**
     * 后序遍历
     * morris遍历的顺序举例：
     * 1
     * /   \
     * 2     3
     * / \   / \
     * 4  5  6   7
     * morris序：1 2 4 2 5 1 3 6 3 7
     * 后序遍历：4 5 2 6 7 3 1
     * 观察morris序可知，一个节点最多有两次机会到达自己，
     * 在第二次到达时，逆序处理（打印）自己的左子树右节点，
     * 最后再逆序处理（打印）一次以head为首的全部右节点
     * 由于使用Morris的目的就是在遍历时间为O(n)的情况下空间复杂度为O(1)，
     * 因此处理逆序不能使用栈等额外空间，此时，可以使用链表反转的技巧打印
     *
     * @param head
     */
    public static void morrisPost(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                    // 逆序打印左子树右节点
                    printEdge(cur.left);
                }
            }
            cur = cur.right;
        }
        // 逆序打印一次以head为首的全部右节点
        printEdge(head);
        System.out.println();
    }

    private static void printEdge(Node cur) {
        // 反转右子节点
        Node reverse = reverseRight(cur);
        Node temp = reverse;
        while (temp != null) {
            System.out.printf("%d ", temp.data);
            temp = temp.right;
        }
        // 打印完恢复原来顺序
        reverseRight(reverse);
    }

    private static Node reverseRight(Node reverse) {
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

    private static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }

}
