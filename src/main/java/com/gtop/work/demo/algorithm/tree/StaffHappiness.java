package com.gtop.work.demo.algorithm.tree;

import com.gtop.work.demo.algorithm.common.NaryNode;

import java.util.ArrayList;

/**
 * 多叉树代表公司员工层级，每个节点有个happy值，
 * 现在公司举行一次聚会，需要邀请公司内部员工参加，
 * 但是有个要求，就是直接上下级员工不能同时邀请，现在求出邀请员工最大的happy值之和
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/12/30 9:22
 */
public class StaffHappiness {

    public static void main(String[] args) {
        NaryNode<Integer> root = new NaryNode<>(10, new ArrayList<>());
        root.children.add(new NaryNode<>(1));
        root.children.add(new NaryNode<>(3));
        root.children.add(new NaryNode<>(50));
        root.children.get(0).children.add(new NaryNode<>(10));
        root.children.get(0).children.add(new NaryNode<>(40));
        System.out.println(getMaxHappiness(root));
    }

    public static int getMaxHappiness(NaryNode<Integer> root) {

        Info i1 = getMaxHappiness0(root, true);
        Info i2 = getMaxHappiness0(root, false);

        return Math.max(i1.total, i2.total);
    }

    private static Info getMaxHappiness0(NaryNode<Integer> cur, boolean isJoin) {
        Info info = new Info();
        if (cur == null) {
            return info;
        }
        int p1 = 0, p2 = 0;
        if (isJoin) {
            int ct = 0;
            if (cur.children != null) {
                for (NaryNode<Integer> child : cur.children) {
                    ct += getMaxHappiness0(child, false).total;
                }
            }
            p1 = cur.data + ct;
        } else {
            int ct = 0;
            if (cur.children != null) {
                for (NaryNode<Integer> child : cur.children) {
                    ct += getMaxHappiness0(child, true).total;
                }
            }
            p2 = ct;
        }
        info.total = Math.max(p1, p2);
        return info;
    }

    static class Info {
        int total;
    }

}
