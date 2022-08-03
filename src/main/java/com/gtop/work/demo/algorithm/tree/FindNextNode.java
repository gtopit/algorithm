package com.gtop.work.demo.algorithm.tree;

import com.gtop.work.demo.algorithm.common.Node;

/**
 * 查找后继节点
 * <p>
 * 给你二叉树的某个节点，
 * 返回该节点的后继节点
 * （后继节点：一棵二叉树按照中序遍历，前一节点的下一节点即为后继节点），
 * 现在节点不但有左右子节点，还有父节点，通过二叉树结构特征找到后继节点
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/12/29 11:40
 */
public class FindNextNode {

    public static void main(String[] args) {
        // 测试树
        //                     1
        //                    /
        //                   2
        //                    \
        //                     3
        //                     /
        //                     4
        //                      \
        //                       5
        //                        \
        //                         6
        Node<Integer> root = new Node<>(1);
        root.left = new Node<>(2);
        root.left.parent = root;
        root.left.right = new Node<>(3);
        root.left.right.parent = root.left;
        System.out.println(findNext(root.left.right));
        root.left.right.left = new Node<>(4);
        root.left.right.left.parent = root.left.right;
        root.left.right.left.right = new Node<>(5);
        root.left.right.left.right.parent = root.left.right.left;
        root.left.right.left.right.right = new Node<>(6);
        root.left.right.left.right.right.parent = root.left.right.left.right;
        System.out.println(findNext(root.left.right.left.right.right));
        System.out.println("---------------------------");
        root = new Node<>(1);
        root.left = new Node<>(2);
        root.left.parent = root;
        System.out.println(findNext(root.left));
    }

    public static Node<Integer> findNext(Node<Integer> cur) {
        if (cur == null || cur.parent == null) {
            return null;
        }
        if (cur.right != null) {
            // 1)当前节点存在右子节点
            return cur.right;
        } else if (cur.parent.left == cur) {
            // 2)当前节点是其父节点的左子节点
            return cur.parent;
        } else if (cur.parent.right == cur) {
            // 3)当前节点是其父节点的右子节点
            Node next = cur;
            while (next != null) {
                if (next.parent != null && next.parent.left == next) {
                    return next.parent;
                }
                next = next.parent;
            }
        }
        return null;
    }

}
