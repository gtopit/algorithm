package com.gtop.work.demo.algorithm.sortTable;

/**
 * AVL树：任意一个节点的左子树和右子树的高度差不能超过1
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/12/21 10:16
 */
public class AVLTree<K extends Comparable<K>, V> {

    private Node<K, V> root;
    private int size;

    public Node<K, V> put(K key, V value) {

        Node<K, V> old = null;
        Node<K, V> index = findLastIndex(key);
        if (index != null && key.compareTo(index.k) == 0) {
            // 存在则替换
            old = new Node<>(index.k, index.v);
            index.v = value;
        } else {
            // 不存在则递归创建
            size++;
            root = add(root, key, value);
        }

        return old;
    }

    public void remove(K key) {
        if (containsKey(key)) {
            size--;
            root = delete(root, key);
        }
    }

    public int size() {
        return size;
    }

    public boolean containsKey(K key) {
        if (key == null) {
            return false;
        }
        Node<K, V> index = findLastIndex(key);
        return index != null && key.compareTo(index.k) == 0 ? true : false;
    }

    /**
     * 查找和key最接近的节点，可以相等蛋是不能小于key
     *
     * @param key
     * @return
     */
    public Node<K, V> findLastNoSmallIndex(K key) {
        if (key == null) {
            return null;
        }
        Node<K, V> ans = null;
        Node<K, V> cur = root;
        while (cur != null) {
            if (key.compareTo(cur.k) == 0) {
                ans = cur;
                break;
            } else if (key.compareTo(cur.k) < 0) {
                ans = cur;
                cur = cur.l;
            } else {
                cur = cur.r;
            }
        }
        return ans;
    }

    /**
     * 查找和key最接近的节点，可以相等蛋是不能大于key
     *
     * @param key
     * @return
     */
    public Node<K, V> findLastNoBigIndex(K key) {
        if (key == null) {
            return null;
        }
        Node<K, V> ans = null;
        Node<K, V> cur = root;
        while (cur != null) {
            if (key.compareTo(cur.k) == 0) {
                ans = cur;
                break;
            } else if (key.compareTo(cur.k) > 0) {
                ans = cur;
                cur = cur.r;
            } else {
                cur = cur.l;
            }
        }
        return ans;
    }

    /**
     * 查找key，如果不存在，则返回最后一个和key最接近的node
     *
     * @param key
     * @return
     */
    public Node<K, V> findLastIndex(K key) {
        Node<K, V> cur = root;
        Node<K, V> pre = root;
        while (cur != null) {
            pre = cur;
            if (key.compareTo(cur.k) == 0) {
                break;
            } else if (key.compareTo(cur.k) < 0) {
                cur = cur.l;
            } else {
                cur = cur.r;
            }
        }
        return pre;
    }

    /**
     * 递归添加节点
     *
     * @param cur
     * @param key
     * @param value
     * @return
     */
    private Node<K, V> add(Node<K, V> cur, K key, V value) {
        if (cur == null) {
            return new Node<>(key, value);
        }
        if (key.compareTo(cur.k) < 0) {
            cur.l = add(cur.l, key, value);
        } else {
            cur.r = add(cur.r, key, value);
        }
        cur.h = getHigh(cur);
        return maintain(cur);
    }

    /**
     * 递归删除，删除之前已经保证删除的key一定存在
     *
     * @param cur
     * @param key
     * @return
     */
    private Node<K, V> delete(Node<K, V> cur, K key) {
        if (key.compareTo(cur.k) < 0) {
            cur.l = delete(cur.l, key);
        } else if (key.compareTo(cur.k) > 0) {
            cur.r = delete(cur.r, key);
        } else {
            // 找到要删除的节点cur

            if (cur.l == null && cur.r == null) {
                // 既没有左子树，也没有右子树，干掉自己
                cur = null;
            } else if (cur.l != null && cur.r == null) {
                // 存在左子树，蛋是不存在右子树，让左子树根节点替代自己
                cur = cur.l;
            } else if (cur.l == null && cur.r != null) {
                //不存在左子树，蛋是存在右子树，让右子树根节点替代自己
                cur = cur.r;
            } else {
                // 既存在左子树，又存在右子树，有两种替代方案，
                // 1、找到左子树最右节点替代自己
                // 2、找到右子树最左节点替代自己
                // 这里选择2替代方案
                Node<K, V> last = cur.r;
                while (last != null) {
                    last = last.l;
                }
                cur.r = delete(cur.r, last.k);
                last.l = cur.l;
                last.r = cur.r;
                cur = last;
            }
        }
        if (cur != null) {
            cur.h = getHigh(cur);
        }
        return maintain(cur);
    }

    private Node<K, V> maintain(Node<K, V> cur) {
        if (cur == null) {
            return null;
        }
        int lh = cur.l == null ? 0 : cur.l.h;
        int rh = cur.r == null ? 0 : cur.r.h;
        if (lh - rh > 1) {
            // 左子树比右子树高
            int llh = cur.l.l == null ? 0 : cur.l.l.h;
            int lrh = cur.l.r == null ? 0 : cur.l.r.h;
            // 如果是lr型，左旋一次，再右旋一次
            if (llh - lrh < 0) {
                // lr型
                leftRotate(cur);
            }
            // 如果是ll型 或 ll型和lr型同时命中，右旋一次
            rightRotate(cur);
        } else if (rh - lh > 1) {
            // 右子树比左子树高
            int rrh = cur.r.r == null ? 0 : cur.r.r.h;
            int rlh = cur.r.l == null ? 0 : cur.r.l.h;
            // 如果是rl型，右旋一次，再左旋一次
            if (rrh - rlh < 0) {
                // rl型
                rightRotate(cur);
            }
            // 如果是rr型 或 rr型和rl型同时命中，左旋一次
            leftRotate(cur);
        }
        return cur;
    }

    /**
     * 左旋，并调整相应节点树高
     *
     * @param cur
     * @return
     */
    private Node<K, V> leftRotate(Node<K, V> cur) {
        Node<K, V> temp = cur.r;
        cur.r = cur.r.l;
        temp.l = cur;
        temp.h = getHigh(temp);
        cur.h = getHigh(cur);
        return temp;
    }

    /**
     * 右旋，并调整相应节点树高
     *
     * @param cur
     * @return
     */
    private Node<K, V> rightRotate(Node<K, V> cur) {
        Node<K, V> temp = cur.l;
        cur.l = cur.l.r;
        temp.r = cur;
        temp.h = getHigh(temp);
        cur.h = getHigh(cur);
        return temp;
    }

    private int getHigh(Node<K, V> cur) {
        return Math.max(cur.l == null ? 0 : cur.l.h, cur.r == null ? 0 : cur.r.h) + 1;
    }

    static class Node<K extends Comparable<K>, V> {
        K k;
        V v;
        Node<K, V> l;
        Node<K, V> r;
        int h;

        public Node(K k, V v) {
            this.k = k;
            this.v = v;
            h = 1;
        }
    }

}
