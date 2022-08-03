package com.gtop.work.demo.algorithm.graph;

import java.util.ArrayList;

/**
 * 点
 * @author hongzw@citycloud.com.cn
 * @Date 2022/2/19 21:31
 */
public class Node {
    public String value;
    public int in;
    public int out;
    public ArrayList<Node> nexts;
    public ArrayList<Edge> edges;
    public Node(String value) {
        this.value = value;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
