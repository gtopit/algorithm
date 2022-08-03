package com.gtop.work.demo.algorithm.dp;

/**
 * 给定一个数字，裂开形式是后面的值不能比前面的小，
 * 例如：3  裂开的情况：1 1 1、1 2、3
 * 问共有多少种裂开方式
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/12/2 13:36
 */
public class MaxSplitNumber {

    public static void main(String[] args) {
        System.out.println(way1(5));
        System.out.println(way2(5));
        System.out.println(way3(5));
    }

    public static int way1(int num) {

        if (num < 0) {
            return 0;
        }
        if (num == 0) {
            return 1;
        }

        return process1(1, num);

    }

    private static int process1(int split, int restNum) {

        if (restNum == 0) {
            return 1;
        }

        if (restNum < split) {
            return 0;
        }

        if (restNum == split) {
            return 1;
        }

        int count = 0;
        for (int i = split; i <= restNum; i++) {
            count += process1(i, restNum - i);
        }
        return count;

    }

    public static int way2(int num) {
        int[][] dp = new int[num + 1][num + 1];
        for (int i = 0; i <= num; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= num; i++) {
            dp[i][i] = 1;
        }
        // 从下往上，从左往右
        for (int split = num - 1; split > 0; split--) {
            for (int restNum = 1; restNum <= num; restNum++) {
                int count = 0;
                for (int i = split; i <= restNum; i++) {
                    if (restNum - i >= 0) {
                        count += dp[i][restNum - i];
                    }
                }
                dp[split][restNum] = count;
            }
        }

        return dp[1][num];
    }

    public static int way3(int num) {
        int[][] dp = new int[num + 1][num + 1];
        for (int i = 0; i <= num; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= num; i++) {
            dp[i][i] = 1;
        }
        // 从下往上，从左往右
        for (int split = num - 1; split > 0; split--) {
            for (int restNum = 1; restNum <= num; restNum++) {
                if (restNum - split >= 0) {
                    dp[split][restNum] = dp[split + 1][restNum] + dp[split][restNum - split];
                }
            }
        }

        return dp[1][num];
    }

}
