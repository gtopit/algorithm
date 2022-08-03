package com.gtop.work.demo.algorithm.graph;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 图
 * @author hongzw@citycloud.com.cn
 * @Date 2022/2/19 21:40
 */
public class Graph {
    public HashMap<Integer, Node> nodes;
    public HashSet<Edge> edges;
    public Graph() {
        this.nodes = new HashMap<>();
        this.edges = new HashSet<>();
    }
}
