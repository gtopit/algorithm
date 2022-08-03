package com.gtop.work.demo.algorithm.unionSet;

import com.gtop.work.demo.algorithm.common.Node;

import java.util.HashMap;
import java.util.Map;

/**
 * 并查集：并查集的查询效率可以做到当 查询的次数大于等于集合时，时间均摊为O(1)
 * 并查集主要包含两个功能
 * 1) 查找两个元素是否位于同一个集合
 * 2) 将两个元素所在的集合合并成一个（如果不在一个集合）
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/12/30 19:06
 */
public class UnionSet<V> {

    public static void main(String[] args) {
        String[] arr = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n"};
        UnionSet set = new UnionSet(arr);
        set.union("a", "b");
        set.union("b", "c");
        set.union("c", "d");
        System.out.println(set.isSameSet("a", "d"));
        set.union("e", "g");
        set.union("f", "g");
        set.union("g", "h");
        set.union("h", "j");
        set.union("k", "g");
        set.union("l", "g");
        System.out.println(set.isSameSet("a", "l"));
        set.union("d", "e");
        System.out.println(set.isSameSet("a", "l"));
    }

    /**
     * 给原始数据包装一层
     */
    private Map<V, Node<V>> nodes;
    /**
     * 当前节点与代表节点之间的关系
     */
    private Map<Node<V>, Node<V>> parents;
    /**
     * 只保存代表节点的大小
     */
    private Map<Node<V>, Integer> setSize;

    static class Node<V> {
        V data;

        public Node(V data) {
            this.data = data;
        }
    }

    public UnionSet(V[] src) {
        if (src == null || src.length == 0) {
            throw new RuntimeException("null or empty array is not allow");
        }
        nodes = new HashMap<>(src.length);
        parents = new HashMap<>(src.length);
        setSize = new HashMap<>(src.length);
        for (V v : src) {
            Node<V> e = new Node<>(v);
            nodes.put(v, e);
            parents.put(e, e);
            setSize.put(e, 1);
        }
    }

    public boolean isSameSet(V e1, V e2) {
        return findParent(nodes.get(e1)) == findParent(nodes.get(e2));
    }

    /**
     * 合并两个集合，小的集合挂到大的集合上
     *
     * @param e1
     * @param e2
     */
    public void union(V e1, V e2) {
        Node<V> n1 = nodes.get(e1);
        Node<V> n2 = nodes.get(e2);
        if (n1 == null || n2 == null) {
            throw new RuntimeException("null element is not allow");
        }
        Node<V> p1 = findParent(n1);
        Node<V> p2 = findParent(n2);
        if (p1 != p2) {
            Integer s1 = setSize.get(p1);
            Integer s2 = setSize.get(p2);
            if (s2 > s1) {
                s2 += s1;
                setSize.put(p2, s2);
                parents.put(p1, p2);
                setSize.remove(p1);
            } else {
                s1 += s2;
                setSize.put(p1, s1);
                parents.put(p2, p1);
                setSize.remove(p2);
            }
        }
    }

    public void add(V e) {
        if (nodes.get(e) == null) {
            nodes.put(e, new Node<>(e));
        }
    }

    /**
     * 查找节点e的父节点
     * 同时，这里有个重要的优化，就是找到父节点后，将沿途的子节点直接挂到父节点
     * eg.,  a → b → c → d   =>   a → d ← b
     * ↑
     * c
     *
     * @param e
     * @return
     */
    private Node<V> findParent(Node<V> e) {
        Node<V> parent = e;
        while (parent != parents.get(parent)) {
            parent = parents.get(parent);
        }
        Node<V> cur = e;
        while (parent != parents.get(cur)) {
            parents.put(cur, parent);
            cur = parents.get(cur);
        }
        return parent;
    }

}
