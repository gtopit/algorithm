package com.gtop.work.demo.algorithm.training.t2022_2_3;

import java.util.*;

/**
 * https://leetcode.com/problems/cheapest-flights-within-k-stops/
 * @author hongzw@citycloud.com.cn
 * @Date 2022/2/19 11:54
 */
public class CheapestFlights {

    public static void main(String[] args) {
        CheapestFlights test = new CheapestFlights();
        int price = test.findCheapestPrice(4, new int[][]{{0,1,1},{0,2,5},{1,2,1},{2,3,1}}, 0, 3, 1);
        System.out.println(price);
    }

    /**
     * 求：从src市出发到dst市，最多不超过k次中转的情况下，最少的费用
     * 解题思路：
     * 1、使用一个n长度数组costArr记录经过k次中转后，从src到dst的最少费用，初始值都设为Integer.MAX_VALUE，
     * 如果k次中转后，costArr[dst] == Integer.MAX_VALUE，则表示没有从src到达dst的航班，否则为最少费用
     * 2、使用一个map（curMap）记录第i次中转后，在最少费用的情况下，从src市能到达的市
     * 3、使用一个临时map（nextMap）记录第i次中转之后，如果i < k时，i+1次中转时，需要重新计算在最少费用的情况下，从src市能到达的市
     * 4、为了方便操作，我们使用map（toList）将flights转换成从src市出发，能到达的市，
     * @author hongzw
     * @date 2022/2/19 12:18
     * @param n 城市
     * @param flights 航班从出发点到目的点的飞行价格数组， flights[i] = [fromi, toi, pricei]
     * @param src 出发地
     * @param dst 目的地
     * @param k 可以中转的次数
     * @return
     *
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

        // flights = [[0,1, 100], [0,2, 500], [1,2, 100]] => toList = [[[1, 100], [2, 500]], [[2, 100]]]
        ArrayList<ArrayList<int[]>> toList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            toList.add(new ArrayList<>());
        }
        for (int[] flight : flights) {
            toList.get(flight[0]).add(new int[] {flight[1], flight[2]});
        }
        int[] costArr = new int[n];
        Arrays.fill(costArr, Integer.MAX_VALUE);
        Map<Integer, Integer> curMap = new HashMap<>();
        curMap.put(src, 0);
        for (int i = 0; i <= k; i++) {
            Map<Integer, Integer> nextMap = new HashMap<>();
            Set<Map.Entry<Integer, Integer>> entrySet = curMap.entrySet();
            for (Map.Entry<Integer, Integer> entry : entrySet) {
                int from = entry.getKey();
                int preCost = entry.getValue();
                ArrayList<int[]> lines = toList.get(from);
                for (int[] line : lines) {
                    int to = line[0];
                    int cost = line[1];
                    costArr[to] = Math.min(costArr[to], cost + preCost);
                    nextMap.put(to, Math.min(nextMap.getOrDefault(to, Integer.MAX_VALUE), cost + preCost));
                }
            }
            curMap = nextMap;
        }

        return costArr[dst] == Integer.MAX_VALUE ? -1 : costArr[dst];
    }

    /**
     * Bellman Ford 算法
     * @author hongzw
     * @date 2022/2/19 19:34
     * @return
     *
     */
    public int findCheapestPrice2(int n, int[][] flights, int src, int dst, int k) {
        int[] costArr = new int[n];
        Arrays.fill(costArr, Integer.MAX_VALUE);
        costArr[src] = 0;
        for (int i = 0; i <= k; i++) {
            int[] nextArr = Arrays.copyOf(costArr, n);
            for (int[] flight : flights) {
                // 只有当前出发点到目的点费用有效，下一次的目的点才能作为本次的出发点进行累加比较
                if (costArr[flight[0]] != Integer.MAX_VALUE) {
                    // 比如：航班信息：[[0, 1, 100],[0, 2, 500],[1, 2, 100]]，
                    // 共有4个城市，从0出发，本次费用 [MAX, 100, 500, MAX]，
                    // 那么下次到来时，只有从1和2出发的航班才有意义，因为0和3航班已经是最大值了，再加上一个值，肯定会更大
                    // 比如：下次目的地的2 的费用要么是本次能到达的 目的地2，也就是500，要么就是以本次出发点为目的地计算的费用（100） + 本次出发点到目的地2的费用（100） = 200，两者取较小的。
                    nextArr[flight[1]] = Math.min(nextArr[flight[1]], costArr[flight[0]] + flight[2]);
                }
            }
            costArr = nextArr;
        }
        return costArr[dst] == Integer.MAX_VALUE ? -1 : costArr[dst];
    }

}
