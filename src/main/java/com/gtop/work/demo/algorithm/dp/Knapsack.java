package com.gtop.work.demo.algorithm.dp;

/**
 * 背包问题
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/11/24 19:30
 */
public class Knapsack {

    public static void main(String[] args) {
        int[] w = new int[]{5, 4, 1, 0};
        int[] v = new int[]{9, 8, 2, 2};
        int way1 = way1(w, v, 6);
        System.out.println(way1);
        int way2 = way2(w, v, 6);
        System.out.println(way2);
    }

    /**
     * 背包问题，w数组和v数组等长，求bag能装下货物的最大价值
     *
     * @param w   货物重量
     * @param v   货物价值
     * @param bag 背包能装下的重量
     * @return
     */
    public static int way1(int[] w, int[] v, int bag) {

        if (null == w || w.length == 0 || w.length != v.length || bag < 0) {
            return -1;
        }

        return process1(w, v, 0, bag);

    }

    /**
     * 递归方式
     *
     * @param w     货物重量
     * @param v     货物价值
     * @param index 来到的位置
     * @param rest  背包剩余重量
     * @return
     */
    private static int process1(int[] w, int[] v, int index, int rest) {

        if (rest < 0) {
            return -1;
        }

        // 表示已经没有获取选了
        if (w.length == index) {
            return 0;
        }

        // 要index位置的货物
        int preRest = process1(w, v, index + 1, rest - w[index]);

        // preRest表示前一次剩余的背包容量，如果已经为负数，则此次也没得选，此次价值+0
        int p1 = preRest < 0 ? 0 : v[index] + preRest;
        int p2 = process1(w, v, index + 1, rest);

        return Math.max(p1, p2);
    }

    /**
     * 动态规划
     *
     * @param w
     * @param v
     * @param bag
     * @return
     */
    public static int way2(int[] w, int[] v, int bag) {
        if (null == w || w.length == 0 || w.length != v.length || bag < 0) {
            return -1;
        }

        int[][] dp = new int[w.length + 1][bag + 1];

        for (int index = w.length - 1; index >= 0; index--) {
            for (int rest = 0; rest <= bag; rest++) {
                int p1;
                if (rest - w[index] < 0) {
                    p1 = 0;
                } else {
                    p1 = v[index] + dp[index + 1][rest - w[index]];
                }
                int p2 = dp[index + 1][rest];
                dp[index][rest] = Math.max(p1, p2);
            }
        }

        return dp[0][bag];
    }

}
