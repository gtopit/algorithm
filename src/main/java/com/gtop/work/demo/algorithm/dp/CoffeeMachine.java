package com.gtop.work.demo.algorithm.dp;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * // 题目
 * // 数组arr代表每一个咖啡机制造一杯咖啡的时间，咖啡机可以并行制造咖啡
 * // 现在有n个人需要喝咖啡，只能选择其中的一个咖啡机来制造咖啡。
 * // 认为每个人喝咖啡的时间非常短，制造好的时间即是喝完的时间。
 * // 每个人喝完之后咖啡杯可以选择洗或者自然挥发干净，只有一台洗咖啡杯的机器。
 * // 洗杯子的机器洗完一个杯子时间为a，任何一个杯子自然挥发干净的时间为b。
 * // 四个参数：arr, n, a, b
 * // 假设时间点从0开始，返回所有人喝完咖啡并洗完咖啡杯的全部过程结束后，至少来到什么时间点。
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/11/30 11:38
 */
public class CoffeeMachine {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 7};
        int way1 = way1(arr, 4, 3, 10);
        System.out.println(way1);
        int way2 = way2(arr, 4, 3, 10);
        System.out.println(way2);
    }

    public static int way1(int[] arr, int n, int a, int b) {

        int[] drinks = getMinQueue(arr, n);

        return process1(drinks, a, b, 0, 0);

    }

    private static int[] getMinQueue(int[] arr, int n) {
        PriorityQueue<Machine> queue = new PriorityQueue<>(Comparator.comparingInt(o -> (o.m + o.w)));
        for (int i = 0; i < arr.length; i++) {
            queue.add(new Machine(0, arr[i]));
        }
        // 第n个喝完咖啡的人相对第一个喝完咖啡的人花费的最少时间
        int[] drinks = new int[n];
        for (int i = 0; i < n; i++) {
            Machine cur = queue.poll();
            cur.w += cur.m;
            drinks[i] = cur.w;
            queue.add(cur);
        }
        return drinks;
    }

    private static int process1(int[] drinks, int a, int b, int index, int canWashTime) {

        if (index == drinks.length) {
            return 0;
        }

        // 选择挥发
        int p1 = Math.max(drinks[index] + b, process1(drinks, a, b, index + 1, canWashTime));
        // 选择洗
        int p2 = Math.max(Math.max(drinks[index], canWashTime) + a, process1(drinks, a, b, index + 1, canWashTime + a));

        return Math.min(p1, p2);

    }

    public static int way2(int[] arr, int n, int a, int b) {

        int[] drinks = getMinQueue(arr, n);

        int costMax = 0;
        for (int i = 0; i < n; i++) {
            costMax = Math.max(costMax, drinks[i]);
            costMax += a;
        }
        int[][] dp = new int[n + 1][costMax + 1];

        for (int index = n - 1; index >= 0; index--) {
            for (int canWashTime = 0; canWashTime <= costMax; canWashTime++) {
                if ((Math.max(drinks[index], canWashTime) + a) >= costMax) {
                    break;
                }
                // 选择挥发
                int p1 = Math.max(drinks[index] + b, dp[index + 1][canWashTime]);
                // 选择洗
                int p2 = Math.max(Math.max(drinks[index], canWashTime) + a, dp[index + 1][canWashTime + a]);

                dp[index][canWashTime] = Math.min(p1, p2);

            }
        }

        return dp[0][0];
    }

    private static class Machine {
        int w;
        int m;

        public Machine(int waiteTime, int machineTime) {
            w = waiteTime;
            m = machineTime;
        }
    }

}
