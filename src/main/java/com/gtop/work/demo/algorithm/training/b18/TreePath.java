package com.gtop.work.demo.algorithm.training.b18;

import com.gtop.work.demo.algorithm.common.Node;
import org.junit.Test;

/**
 * 给定一个二叉树得头节点head，路径得规定有以下三种不同的规定：
 * 1）路径必须是头节点出发，到叶节点为止，返回最大路径和
 * 2）路径可以从任意节点出发，但必须往下走到达任何节点，返回最大路径和
 * 3）路径可以从任意节点出发，到任何节点，返回最大路径和
 * @author hongzw@citycloud.com.cn
 * @Date 2022/2/23 18:54
 */
public class TreePath {

    @Test
    public void test() {
        Node<Integer> head = new Node<>(1);
        head.left = new Node<>(-3);
        head.right = new Node<>(-5);
        System.out.println(ans1(head));
    }

    @Test
    public void test2() {
        Node<Integer> head = new Node<>(2);
        head.left = new Node<>(2);
        head.right = new Node<>(1);
        System.out.println(ans2(head));
    }

    @Test
    public void test3() {
        Node<Integer> head = new Node<>(-2);
        head.left = new Node<>(-2);
        head.right = new Node<>(-1);
        System.out.println(ans3(head));
    }


    public int ans3(Node<Integer> head) {
        Info3 info3 = process3(head);
        return info3 == null ? 0 : info3.max;
    }

    private Info3 process3(Node<Integer> head) {
        if (head == null) {
            return null;
        }
        int p1 = head.data;
        int p2 = Integer.MIN_VALUE;
        int p3 = Integer.MIN_VALUE;
        if (head.left != null) {
            p2 = process3(head.left).max;
            p3 = p2 + p1;
        }
        int p4 = Integer.MIN_VALUE;
        int p5 = Integer.MIN_VALUE;
        if (head.right != null) {
            p4 = process3(head.right).max;
            p5 = p4 + p1;
        }
        int p6 = p1 + p2 + p4;
        Info3 info = new Info3();
        info.max = Math.max(p1, Math.max(p2, Math.max(p3, Math.max(p4, Math.max(p5, p6)))));
        return info;
    }

    private static class Info3 {
        int max;
    }

    public int ans2(Node<Integer> head) {
        Info2 info2 = process2(head);
        return info2 == null ? 0 : info2.max;
    }

    private Info2 process2(Node<Integer> head) {

        if (head == null) {
            return null;
        }
        int p1 = head.data;
        int p2 = Integer.MIN_VALUE;
        int p3 = Integer.MIN_VALUE;
        if (head.left != null) {
            p2 = process2(head.left).max;
            p3 = p2 + p1;
        }
        int p4 = Integer.MIN_VALUE;
        int p5 = Integer.MIN_VALUE;
        if (head.right != null) {
            p4 = process2(head.right).max;
            p5 = p4 + p1;
        }
        Info2 info = new Info2();
        info.max = Math.max(p1, Math.max(p2, Math.max(p3, Math.max(p4, p5))));
        return info;
    }

    private static class Info2 {
        int max;
    }

    public int ans1(Node<Integer> head) {
        Info1 info1 = process1(head);
        return info1 == null ? 0 : info1.max;
    }

    private Info1 process1(Node<Integer> head) {
        if (head == null) {
            return null;
        }
        Info1 info = new Info1();
        info.max = head.data;
        Info1 p2 = process1(head.left);
        Info1 p3 = process1(head.right);

        if (p2 != null) {
            info.max = Math.max(info.max, p2.max + head.data);
        }
        if (p3 != null) {
            info.max = Math.max(info.max, p3.max + head.data);
        }
        return info;
    }

    private static class Info1 {
        int max;
    }

}
