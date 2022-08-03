package com.gtop.work.demo.algorithm.dp;

/**
 * 给定3个参数，n、m、k
 * 怪兽有n滴血，等着英雄来砍自己
 * 英雄每打击一次，都会让怪兽在[0-m]的血量上等概率的获得一个值
 * 求k次打击之后，英雄把怪兽砍死的概率
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/11/30 20:22
 */
public class KillMonster {

    public static void main(String[] args) {

        System.out.println(way1(3, 2, 3));
        System.out.println(way2(3, 2, 3));
        System.out.println(way3(3, 2, 3));

    }

    public static double way1(int n, int m, int k) {

        // 打击k次共有的损失血量的可能
        double total = Math.pow(m + 1, k);

        int kill = process1(m, k, n);

        return kill / total;

    }

    /**
     * @param m
     * @param restK 剩余次数
     * @param restN 剩余血量
     * @return
     */
    private static int process1(int m, int restK, int restN) {
        if (restK == 0) {
            return restN <= 0 ? 1 : 0;
        }

        int kill = 0;
        for (int i = 0; i <= m; i++) {
            kill += process1(m, restK - 1, restN - i);
        }
        return kill;
    }

    /**
     * 动态规划
     *
     * @param n
     * @param m
     * @param k
     * @return
     */
    public static double way2(int n, int m, int k) {

        // 打击k次共有的损失血量的可能
        double total = Math.pow(m + 1, k);

        long[][] dp = new long[n + 1][k + 1];
        dp[0][0] = 1;
        for (int restK = 1; restK <= k; restK++) {
            dp[0][restK] = (long) Math.pow(m + 1, restK);
            for (int restN = 1; restN <= n; restN++) {
                long kill = 0;
                for (int i = 0; i <= m; i++) {
                    if (restN - i < 0) {
                        // 如果剩余血量少于0，则后续的所有次数都是杀死怪兽的次数
                        kill += dp[0][restK - 1];
                    } else {
                        kill += dp[restN - i][restK - 1];
                    }
                }
                dp[restN][restK] = kill;
            }
        }

        return dp[n][k] / total;

    }

    /**
     * 动态规划省略枚举类型
     *
     * @param n
     * @param m
     * @param k
     * @return
     */
    public static double way3(int n, int m, int k) {

        // 打击k次共有的损失血量的可能
        double total = Math.pow(m + 1, k);

        long[][] dp = new long[n + 1][k + 1];
        dp[0][0] = 1;
        // 根据way2，可知：
        // dp[n][k] = dp[n-0][k-1]+dp[n-1][k-1]+...+dp[n-m][k-1]           (A)
        // dp[n-1][k] = dp[n-1][k-1]+dp[n-2][k-1]+...+dp[n-m-1][k-1]       (B)
        // A - B = dp[n][k] - dp[n-1][k] = dp[n-0][k-1] - dp[n-m-1][k-1]   (C)
        // C公式转化下:dp[n][k] = dp[n-1][k] + dp[n][k-1] - dp[n-m-1][k-1]   (D)
        for (int restK = 1; restK <= k; restK++) {
            dp[0][restK] = (long) Math.pow(m + 1, restK);
            for (int restN = 1; restN <= n; restN++) {
                dp[restN][restK] = dp[restN - 1][restK] + dp[restN][restK - 1];
                // 根据D公式，省略枚举行为(for的累加)
                if (restN - m - 1 < 0) {
                    dp[restN][restK] -= (long) Math.pow(m + 1, restK - 1);
                } else {
                    dp[restN][restK] -= dp[restN - m - 1][restK - 1];
                }
            }
        }

        return dp[n][k] / total;

    }

}
