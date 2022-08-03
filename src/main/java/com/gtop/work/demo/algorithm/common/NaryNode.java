package com.gtop.work.demo.algorithm.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hongzw@citycloud.com.cn
 * @Date 2021/12/30 9:30
 */
public class NaryNode<T extends Comparable<T>> {
    public T data;
    public List<NaryNode<T>> children;

    public NaryNode(T data) {
        this.data = data;
        this.children = new ArrayList<>();
    }

    public NaryNode(T data, List<NaryNode<T>> children) {
        this.data = data;
        this.children = children;
    }

    @Override
    public String toString() {
        return String.valueOf(data);
    }
}
