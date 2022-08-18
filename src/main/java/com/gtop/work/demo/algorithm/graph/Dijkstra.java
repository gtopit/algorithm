package com.gtop.work.demo.algorithm.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 迪杰斯特拉算法（计算从给定的点出发到其他点的最短路径集合）
 * @author hongzw@citycloud.com.cn
 * @Date 2022/2/19 21:24
 */
public class Dijkstra {

    public void test() {
        Node head = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");
        Node e = new Node("E");
        Edge ab = new Edge(4, head, b);
        head.edges.add(ab);
        Edge ad = new Edge(2, head, d);
        head.edges.add(ad);
        Edge ac = new Edge(3, head, c);
        head.edges.add(ac);
        Edge be = new Edge(5, b, e);
        b.edges.add(be);
        Edge db = new Edge(1, d, b);
        d.edges.add(db);
        Edge  de = new Edge(8, d, e);
        d.edges.add(de);
        Edge cd = new Edge(1, c, d);
        c.edges.add(cd);
        Map<Node, Integer> distanceMap = dijkstra(head, 5);
        distanceMap.forEach((k, v) -> System.out.println(k.value + "  " + v));
    }

    public Map<Node, Integer> dijkstra(Node from, int size) {
        Map<Node, Integer> distanceMap = new HashMap<>();
        distanceMap.put(from, 0);
        HeapEx ex = new HeapEx(size);
        ex.addOrUpdateOrIgnore(from, 0);

        while (!ex.isEmpty()) {
            NodeRecord minRecord = ex.pop();
            int distance = minRecord.distance;
            Node minNode = minRecord.node;
            distanceMap.put(minNode, distance);
            ArrayList<Edge> edges = minNode.edges;
            for (Edge edge : edges) {
                ex.addOrUpdateOrIgnore(edge.to, distance + edge.weight);
            }
        }

        return distanceMap;
    }

    /**
     * 加强堆
     * @author hongzw
     * @date 2022/2/20 14:40
     *
     */
    static class HeapEx {

        private Node[] nodes;
        private Map<Node, Integer> indexMap;
        private Map<Node, Integer> distanceMap;
        private int size;

        public NodeRecord pop() {

            NodeRecord record = null;
            if (size > 0) {
                Node node = nodes[0];
                record = new NodeRecord(node, distanceMap.get(node));
                distanceMap.put(node, -1);
                indexMap.remove(node);
                swap(0, --size);
                heapDown(nodes[size], 0);
            }

            return record;
        }

        public boolean isEmpty() {
            return this.size == 0;
        }

        public void addOrUpdateOrIgnore(Node node, Integer distance) {
            Integer target = distanceMap.get(node);
            if (!Objects.isNull(target)) { // update or ignore
                if (!target.equals(-1)) { // update
                    if (target.compareTo(distance) > 0) {
                        distanceMap.put(node, distance);
                        int index = indexMap.get(node);
                        heapDown(node, index);
                        heapUp(node, index);
                    }
                }
            } else { // add
                indexMap.put(node, size);
                distanceMap.put(node, distance);
                nodes[size] = node;
                heapUp(node, size++);
            }
        }

        private void heapDown(Node node, int index) {
            // 候选index
            int ci = index * 2 + 1;
            while (ci < size) {
                ci = ci + 1 < size && distanceMap.get(nodes[ci + 1]) < distanceMap.get(nodes[ci]) ?  ci + 1 : ci;
                if (ci == index || distanceMap.get(nodes[ci]) >= distanceMap.get(node)) {
                    break;
                }
                swap(ci, index);
                index = ci;
                ci = index * 2 + 1;
            }
        }

        private void heapUp(Node node, int index) {
            while (distanceMap.get(node) < distanceMap.get(nodes[index / 2])) {
                swap(index, index / 2);
                index = index / 2;
            }
        }

        private void swap(int i, int j) {
            indexMap.put(nodes[i], j);
            indexMap.put(nodes[j], i);
            Node node = nodes[i];
            nodes[i] = nodes[j];
            nodes[j] = node;
        }

        public HeapEx(int size) {
            this.nodes = new Node[size];
            this.indexMap = new HashMap<>();
            this.distanceMap = new HashMap<>(size);
            this.size = 0;
        }
    }

}
