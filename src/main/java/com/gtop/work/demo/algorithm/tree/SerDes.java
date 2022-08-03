package com.gtop.work.demo.algorithm.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉树序列化，反序列化（按层）
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/11/19 23:25
 */
public class SerDes {

    public static void main(String[] args) {

        Node root = new Node('a');
        root.left = new Node('b');
        root.right = new Node('c');
        root.left.left = new Node('d');
        root.left.right = new Node('e');
        root.right.left = new Node('f');
        root.right.right = new Node('g');
        pre(root);
        System.out.println();
        Queue<Character> ser = levelSer(root);
        Node des = levelDes(ser);
        pre(des);
        System.out.println();
        Queue<Character> queue = deepSer(root);
        Node node = deepDes(queue);
        pre(node);
        System.out.println();
        Queue<Character> queue2 = deepSer2(root);
        Node node2 = deepDes2(queue2);
        pre(node2);
        System.out.println();
    }

    /**
     * 非递归前序遍历反序列化
     *
     * @param queue2
     * @return
     */
    private static Node deepDes2(Queue<Character> queue2) {

        if (queue2 == null || queue2.isEmpty()) {
            return null;
        }
        Stack<Node> stack = new Stack<>();
        Node cur = generateNode(queue2.poll());
        Node root = cur;
        if (cur != null) {
            stack.push(cur);
        }
        while (!stack.isEmpty()) {
            cur = stack.pop();
            cur.right = generateNode(queue2.poll());
            cur.left = generateNode(queue2.poll());
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }

        return root;
    }

    public static void pre(Node cur) {
        if (cur == null) {
            return;
        }
        System.out.printf("%c ", cur.data);
        pre(cur.left);
        pre(cur.right);
    }

    /**
     * 非递归前序遍历序列化
     *
     * @param root
     * @return
     */
    public static Queue<Character> deepSer2(Node root) {
        Queue<Character> ser = new LinkedList<>();
        if (root == null) {
            ser.add(null);
            return ser;
        }
        Stack<Node> stack = new Stack<>();
        Node cur = root;
        stack.push(cur);
        ser.add(cur.data);
        while (!stack.isEmpty()) {
            cur = stack.pop();
            if (cur.right != null) {
                stack.push(cur.right);
                ser.add(cur.right.data);
            } else {
                ser.add(null);
            }

            if (cur.left != null) {
                stack.push(cur.left);
                ser.add(cur.left.data);
            } else {
                ser.add(null);
            }
        }

        return ser;
    }

    /**
     * 前序遍历递归序列化
     *
     * @param root
     * @return
     */
    public static Queue<Character> deepSer(Node root) {
        Queue<Character> ser = new LinkedList<>();
        preSer(ser, root);
        return ser;
    }

    /**
     * 前序遍历递归反序列化
     *
     * @param ser
     * @return
     */
    public static Node deepDes(Queue<Character> ser) {
        if (ser == null || ser.isEmpty()) {
            return null;
        }
        return preDes(ser);
    }

    private static Node preDes(Queue<Character> ser) {
        Node root = generateNode(ser.poll());
        if (root == null) {
            return null;
        }
        root.left = preDes(ser);
        root.right = preDes(ser);
        return root;
    }

    private static void preSer(Queue<Character> ser, Node cur) {
        if (cur == null) {
            ser.add(null);
            return;
        }
        ser.add(cur.data);
        preSer(ser, cur.left);
        preSer(ser, cur.right);
    }

    /**
     * 按层反序列化
     *
     * @param src
     * @return
     */
    public static Node levelDes(Queue<Character> src) {
        if (src == null || src.isEmpty()) {
            return null;
        }
        Node root = generateNode(src.poll());
        Node cur = root;
        Queue<Node> queue = new LinkedList<>();
        if (cur != null) {
            queue.add(cur);
        }
        while (!queue.isEmpty()) {
            cur = queue.poll();
            cur.left = generateNode(src.poll());
            if (cur.left != null) {
                queue.add(cur.left);
            }
            cur.right = generateNode(src.poll());
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }

        return root;
    }

    private static Node generateNode(Character data) {
        if (data != null) {
            return new Node(data);
        }
        return null;
    }

    /**
     * 按层序列化
     *
     * @param root
     * @return
     */
    public static Queue<Character> levelSer(Node root) {
        Queue<Character> ser = new LinkedList<>();
        if (root == null) {
            ser.add(null);
            return ser;
        }
        Node cur = root;
        Queue<Node> queue = new LinkedList<>();
        queue.add(cur);
        ser.add(cur.data);
        while (!queue.isEmpty()) {
            cur = queue.poll();
            if (cur.left != null) {
                queue.add(cur.left);
                ser.add(cur.left.data);
            } else {
                ser.add(null);
            }
            if (cur.right != null) {
                queue.add(cur.right);
                ser.add(cur.right.data);
            } else {
                ser.add(null);
            }
        }
        return ser;
    }

    private static class Node {
        char data;
        Node left;
        Node right;

        public Node(char data) {
            this.data = data;
        }
    }

}
