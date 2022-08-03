package com.gtop.work.demo.algorithm.common;

/**
 * @author hongzw@citycloud.com.cn
 * @Date 2021/12/29 13:50
 */
public class Node<T extends Comparable<T>> {
    public T data;
    public Node<T> left;
    public Node<T> right;
    public Node<T> parent;

    public Node(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        if (data == null) {
            return "null";
        }
        return String.valueOf(data);
    }
}
