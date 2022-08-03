package com.gtop.work.demo.algorithm.graph;

/**
 * 边
 * @author hongzw@citycloud.com.cn
 * @Date 2022/2/19 21:30
 */
public class Edge {

    public int weight;
    public Node from;
    public Node to;
    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }

}
