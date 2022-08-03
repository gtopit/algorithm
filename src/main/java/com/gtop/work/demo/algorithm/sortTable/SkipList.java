package com.gtop.work.demo.algorithm.sortTable;

import java.util.ArrayList;
import java.util.List;

/**
 * 跳表：使用概率替代传统搜索二叉树中数据在新增、删除后导致的倾斜需要调整（左旋右旋）
 * 跳表具有的属性：
 * size：跳表中的元素数量
 * maxLevel：最大层数
 * head：表头，包含了层数及每层元素
 * 初始化时，
 * head = new Node<>(null, null); // key = null 用来表示比加入的任何key都小的值
 * head.nextNodes.add(null);  // 表示头节点此时只有一层，并且指向null
 * maxLevel = 0; // 表示只有一层，而非没有层数
 * size = 0; // 拥有0个元素
 * 新建节点时，以 0.5 的概率决定新建节点能到达的最大层数
 * 如果新建节点的最大层数比maxLevel大，则maxLevel增大到新增节点的层数，并且head.nextNodes加入对应层数，先指向null，代码：head.nextNodes.add(null)
 * 新建节点一样，先在其nextNodes加入对应层数，while(newLevel-- >= 0) { newNode.nextNodes.add(null); }
 * 然后在跳表中个个层对应位置改变nextNodes指向
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/12/24 10:00
 */
public class SkipList<K extends Comparable<K>, V> {

    private int size;
    private int maxLevel;
    private Node<K, V> head;
    /**
     * 决定新建节点层数，< 0.5时层数+1，继续，直到 >= 0.5时停止
     */
    private static final double PROBABILITY = 0.5;

    public Node<K, V> put(K key, V value) {
        if (key == null) {
            return null;
        }
        Node<K, V> cur = mostRightLessNodeInTree(key);
        Node<K, V> old = null;
        Node<K, V> nextNode = cur.nextNodes.get(0);
        if (nextNode != null && nextNode.isKeyEqual(key)) {
            old = new Node<>(cur.k, cur.v);
            cur.v = value;
        } else {
            // 新增节点
            size++;
            Node<K, V> newNode = new Node(key, value);
            int newNodeLevel = 0;
            while (Math.random() < PROBABILITY) {
                newNodeLevel++;
            }
            while (newNodeLevel > maxLevel) {
                head.nextNodes.add(null);
                maxLevel++;
            }
            for (int i = 0; i <= newNodeLevel; i++) {
                newNode.nextNodes.add(null);
            }
            int curLevel = maxLevel;
            cur = head;
            while (curLevel >= 0) {
                cur = mostRightLessNodeInLevel(cur, key, curLevel);
                if (curLevel <= newNodeLevel) {
                    newNode.nextNodes.set(curLevel, cur.nextNodes.get(curLevel));
                    cur.nextNodes.set(curLevel, newNode);
                }
                curLevel--;
            }
        }

        return old;
    }

    public void remove(K key) {
        if (key == null) {
            return;
        }
        if (containsKey(key)) {
            size--;
            int curLevel = maxLevel;
            Node<K, V> cur = head;
            Node<K, V> nextNode;
            while (curLevel >= 0) {
                cur = mostRightLessNodeInLevel(cur, key, curLevel);
                nextNode = cur.nextNodes.get(0);
                // 找到key所在层的位置，前一个node指向其下一个node即删除
                if (nextNode != null && nextNode.isKeyEqual(key)) {
                    cur.nextNodes.set(curLevel, nextNode.nextNodes.get(curLevel));
                }
                // 防止内存泄露，如果删除的节点在最高那层只有自己，最高层数需要减一
                if (curLevel != 0 && cur.nextNodes.get(curLevel) == null && cur == head) {
                    head.nextNodes.remove(curLevel);
                    maxLevel--;
                }
                curLevel--;
            }
        }
    }

    public int size() {
        return size;
    }

    public K fistKey() {
        return head.nextNodes.get(0) != null ? head.nextNodes.get(0).k : null;
    }

    public K lastKey() {

        int curLevel = maxLevel;
        Node<K, V> cur = head;
        Node<K, V> next = head;
        while (curLevel >= 0) {
            while (next != null) {
                cur = next;
                next = next.nextNodes.get(curLevel);
            }
            curLevel--;
        }
        return cur.k;
    }

    public boolean containsKey(K key) {
        if (key == null) {
            return false;
        }
        Node<K, V> cur = mostRightLessNodeInTree(key);
        Node<K, V> nextNode = cur.nextNodes.get(0);
        return nextNode != null && nextNode.isKeyEqual(key);
    }

    /**
     * 查找跳表中比key小的最右节点
     * 从顶层开始查找，然后逐层递减，
     * 最终在第0层找到小于key的最右节点
     *
     * @param key
     * @return
     */
    public Node<K, V> mostRightLessNodeInTree(K key) {
        if (key == null) {
            return null;
        }
        int level = maxLevel;
        Node<K, V> cur = head;
        while (level >= 0) {
            cur = mostRightLessNodeInLevel(cur, key, level--);
        }
        return cur;
    }

    /**
     * 查找当前层中比key小的最右节点
     *
     * @param cur
     * @param key
     * @param level
     * @return
     */
    private Node<K, V> mostRightLessNodeInLevel(Node<K, V> cur, K key, int level) {
        Node<K, V> next = cur;
        while (next != null && next.isKeyLess(key)) {
            cur = next;
            next = next.nextNodes.get(level);
        }
        return cur;
    }

    public SkipList() {
        // 开始时的跳表头
        head = new Node<>(null, null);
        head.nextNodes.add(null);
        size = 0;
        maxLevel = 0;
    }

    static class Node<K extends Comparable<K>, V> {
        K k;
        V v;
        // 表示层数
        List<Node<K, V>> nextNodes;

        public Node(K key, V value) {
            k = key;
            v = value;
            nextNodes = new ArrayList<>();
        }

        /**
         * 当前节点的key是否小于其他节点的key
         * 1、其他节点不为空，当前节点为空，成立
         * 2、其他节点不为空，当前节点不为空并且比较器比较后比其他节点key小，成立
         * 3、其他情况都不成立
         *
         * @param otherKey
         * @return
         */
        public boolean isKeyLess(K otherKey) {
            return otherKey != null && (k == null || k.compareTo(otherKey) < 0);
        }

        /**
         * 当前节点是否和其他节点相等
         * 1、当前节点为空并且其他节点为空，成立
         * 2、当前节点不为空并且其他节点不为空，比较器比较后相等，成立
         * 3、其他情况都不成立
         *
         * @param otherKey
         * @return
         */
        public boolean isKeyEqual(K otherKey) {
            return (k == null && otherKey == null) || (k != null && otherKey != null && k.compareTo(otherKey) == 0);
        }
    }

}
