package com.gtop.work.demo.algorithm.dp;

/**
 * 机器人走路
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/11/23 12:29
 */
public class RobotWalk {

    public static void main(String[] args) {
        int way1 = way1(5, 2, 3, 5);
        System.out.println(way1);
        int way2 = way2(5, 2, 3, 5);
        System.out.println(way2);
        int way3 = way3(5, 2, 3, 5);
        System.out.println(way3);
    }

    /**
     * 机器人开始在start位置，可以向左向右走，但是如果start来到了1位置，只能向右走，如果start来到了n位置，只能向左走
     * 要求机器人走完k步能来到target位置的所有走法
     *
     * @param n      表示总共有 1 ~ n个位置
     * @param start  表示机器人所在的开始位置
     * @param target 表示机器人要走到的位置
     * @param k      表示机器人一共要走多少步
     * @return 返回总共有多少种走法
     */
    public static int way1(int n, int start, int target, int k) {

        if (n < 2 || start < 1 || start > n || target > n || target < 1 || k < 0) {
            return -1;
        }

        return process1(k, start, target, n);

    }

    /**
     * @param rest   还剩下的步数
     * @param cur    当前所在位置
     * @param target 目标位置
     * @param n      走路的范围
     * @return
     */
    private static int process1(int rest, int cur, int target, int n) {
        int ans;
        if (rest == 0) {
            ans = cur == target ? 1 : 0;
            return ans;
        }
        if (cur == 1) {
            ans = process1(rest - 1, cur + 1, target, n);
        } else if (cur == n) {
            ans = process1(rest - 1, cur - 1, target, n);
        } else {
            ans = process1(rest - 1, cur - 1, target, n) + process1(rest - 1, cur + 1, target, n);
        }
        return ans;
    }

    /**
     * 使用缓存
     *
     * @param n
     * @param start
     * @param target
     * @param k
     * @return
     */
    public static int way2(int n, int start, int target, int k) {

        if (n < 2 || start < 1 || start > n || target > n || target < 1 || k < 0) {
            return -1;
        }

        int[][] dp = new int[n + 1][k + 1];

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }

        return process2(k, start, target, n, dp);
    }

    /**
     * 分析走路的过程，发现存在重复的步骤，因此可以使用缓存，这样再下一次碰到相同情况，直接从缓存拿结果
     *
     * @param rest
     * @param cur
     * @param target
     * @param n
     * @param dp
     * @return
     */
    private static int process2(int rest, int cur, int target, int n, int[][] dp) {
        if (dp[cur][rest] != -1) {
            return dp[cur][rest];
        }
        int ans;
        if (rest == 0) {
            ans = cur == target ? 1 : 0;
        } else if (cur == 1) {
            ans = process2(rest - 1, cur + 1, target, n, dp);
        } else if (cur == n) {
            ans = process2(rest - 1, cur - 1, target, n, dp);
        } else {
            ans = process2(rest - 1, cur - 1, target, n, dp) + process2(rest - 1, cur + 1, target, n, dp);
        }
        dp[cur][rest] = ans;
        return ans;
    }

    /**
     * 动态规划
     *
     * @param n
     * @param start
     * @param target
     * @param k
     * @return
     */
    public static int way3(int n, int start, int target, int k) {

        if (n < 2 || start < 1 || start > n || target > n || target < 1 || k < 0) {
            return -1;
        }

        int[][] dp = new int[n + 1][k + 1];

        dp[target][0] = 1;
        for (int rest = 1; rest < k + 1; rest++) {
            dp[1][rest] = dp[2][rest - 1];
            for (int cur = 2; cur < n; cur++) {
                dp[cur][rest] = dp[cur - 1][rest - 1] + dp[cur + 1][rest - 1];
            }
            dp[n][rest] = dp[n - 1][rest - 1];
        }
        return dp[start][k];
    }

}
