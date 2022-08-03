package com.gtop.work.demo.algorithm.sortTable;

/**
 * Size Balance Tree
 * 任意叔叔节点总数不能小于侄子节点（意味着兄弟节点之间的数据量不会超过两倍）
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/12/23 18:15
 */
public class SBTree<K extends Comparable<K>, V> {

    public static void main(String[] args) {
        SBTree<String, String> map = new SBTree<>();
        map.put("1", "A");
        map.put("10", "B");
        map.put("15", "C");
        map.put("8", "D");
        map.put("11", "D");
        map.put("12", "D");
        System.out.println(map.findLastNoBigIndex("14").k);
        map.remove("12");
        System.out.println(map.findLastNoBigIndex("14").k);

    }

    private Node<K, V> root;

    public Node<K, V> put(K key, V value) {
        if (key == null) {
            return null;
        }
        Node<K, V> last = finLastIndex(key);
        Node<K, V> old = null;
        if (last != null && key.compareTo(last.k) == 0) {
            old = new Node<>(last.k, last.v);
            last.v = value;
        } else {
            root = add(root, key, value);
        }

        return old;
    }

    public void remove(K key) {
        if (key == null) {
            return;
        }
        if (containsKey(key)) {
            root = delete(root, key);
        }
    }

    public boolean containsKey(K key) {
        Node<K, V> last = finLastIndex(key);
        return last != null && last.k.compareTo(key) == 0 ? true : false;
    }

    public Node<K, V> findLastNoSmallIndex(K key) {
        Node<K, V> cur = root;
        Node<K, V> ans = root;
        while (cur != null) {
            if (cur.k.compareTo(key) == 0) {
                ans = cur;
                break;
            } else if (cur.k.compareTo(key) > 0) {
                ans = cur;
                cur = cur.l;
            } else {
                cur = cur.r;
            }
        }
        return ans;
    }

    public Node<K, V> findLastNoBigIndex(K key) {
        Node<K, V> cur = root;
        Node<K, V> ans = root;
        while (cur != null) {
            if (cur.k.compareTo(key) == 0) {
                ans = cur;
                break;
            } else if (cur.k.compareTo(key) < 0) {
                ans = cur;
                cur = cur.r;
            } else {
                cur = cur.l;
            }
        }
        return ans;
    }

    public Node<K, V> finLastIndex(K key) {
        Node<K, V> cur = root;
        Node<K, V> pre = root;
        while (cur != null) {
            pre = cur;
            if (cur.k.compareTo(key) == 0) {
                break;
            } else if (cur.k.compareTo(key) < 0) {
                cur = cur.r;
            } else {
                cur = cur.l;
            }
        }
        return pre;
    }

    public int size() {
        return root == null ? 0 : root.size;
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
        cur.size++;
        if (cur.k.compareTo(key) < 0) {
            cur.r = add(cur.r, key, value);
        } else {
            // else分支是大于，等于情况已经被前面的步骤排除
            cur.l = add(cur.l, key, value);
        }
        return maintain(cur);

    }

    /**
     * 递归删除
     *
     * @param cur
     * @param key
     * @return
     */
    private Node<K, V> delete(Node<K, V> cur, K key) {
        // 因为是递归删除，因此在命中的沿途节点上都需要size--
        cur.size--;
        if (cur.k.compareTo(key) < 0) {
            cur.r = delete(cur.r, key);
        } else if (cur.k.compareTo(key) > 0) {
            cur.l = delete(cur.l, key);
        } else {
            // 找到要删除的节点了
            if (cur.l == null && cur.r == null) {
                cur = null;
            } else if (cur.l != null && cur.r == null) {
                cur = cur.l;
            } else if (cur.l == null && cur.r != null) {
                cur = cur.r;
            } else {
                // 既存在左子树，又存在右子树
                Node<K, V> last = cur.r;
                int cs = cur.size;
                while (last != null) {
                    last = last.l;
                }
                cur = delete(cur, last.k);
                last.l = cur.l;
                last.r = cur.r;
                cur = last;
                cur.size = cs - 1;
            }
        }
        return cur;
    }

    private Node<K, V> maintain(Node<K, V> cur) {

        if (cur == null) {
            return null;
        }

        int ls = cur.l == null ? 0 : cur.l.size;
        int rs = cur.r == null ? 0 : cur.r.size;
        int lls = ls != 0 && cur.l.l != null ? cur.l.l.size : 0;
        int lrs = ls != 0 && cur.l.r != null ? cur.l.r.size : 0;
        int rls = rs != 0 && cur.r.l != null ? cur.r.l.size : 0;
        int rrs = rs != 0 && cur.r.r != null ? cur.r.r.size : 0;
        if (rs < lls) {
            // 包括了LL型或 LL&&LR型
            cur = rightRotate(cur);
            cur.r = maintain(cur.r);
            cur = maintain(cur);
        } else if (rs < lrs) {
            // LR型
            cur.l = leftRotate(cur.l);
            cur = rightRotate(cur);
            cur.l = maintain(cur.l);
            cur.r = maintain(cur.r);
            cur = maintain(cur);
        } else if (ls < rrs) {
            // 包括了RR型和 RR&&RL型
            cur = leftRotate(cur);
            cur.l = maintain(cur.l);
            cur = maintain(cur);
        } else if (ls < rls) {
            // RL型
            cur.r = rightRotate(cur.r);
            cur = leftRotate(cur);
            cur.r = maintain(cur.r);
            cur.l = maintain(cur.l);
            cur = maintain(cur);
        }
        return cur;

    }

    private Node<K, V> leftRotate(Node<K, V> cur) {
        Node<K, V> temp = cur.r;
        cur.r = cur.r.l;
        temp.l = cur;
        temp.size = cur.size;
        cur.size = getSize(cur);
        return temp;
    }

    private Node<K, V> rightRotate(Node<K, V> cur) {
        Node<K, V> temp = cur.l;
        cur.l = cur.l.r;
        temp.l = cur;
        temp.size = cur.size;
        cur.size = getSize(cur);
        return temp;
    }

    private int getSize(Node<K, V> cur) {
        return (cur.l == null ? 0 : cur.l.size) + (cur.r == null ? 0 : cur.r.size) + 1;
    }

    static class Node<K, V> {
        K k;
        V v;
        Node<K, V> l;
        Node<K, V> r;
        int size;

        public Node(K key, V value) {
            k = key;
            v = value;
            size = 1;
        }
    }

}
