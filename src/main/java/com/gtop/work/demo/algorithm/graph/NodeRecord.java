package com.gtop.work.demo.algorithm.graph;

/**
 * @author hongzw@citycloud.com.cn
 * @Date 2022/2/20 11:15
 */
public class NodeRecord {

    public Node node;
    public int distance;
    public NodeRecord(Node node, Integer distance) {
        this.node = node;
        this.distance = distance;
    }

}
