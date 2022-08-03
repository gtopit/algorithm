package com.gtop.work.demo.algorithm.dp;

/**
 * 最小路径和
 * 给定一个二维数组，一个人必须从左上角出发，最后到达右下角，
 * 沿途只可以向下或向右走，沿途的数字都累加就是距离累加和，返回最小距离累加和
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/11/30 8:19
 */
public class MinPathSum {

    public static void main(String[] args) {
        int[][] arr = {
                {1, 1, 1, 2, 3, 5, 6},
                {2, 2, 3, 2, 1, 7, 1},
                {2, 1, 3, 1, 4, 5, 2},
                {2, 1, 3, 2, 3, 1, 4}
        };
        System.out.println(way1(arr));
        System.out.println(way2(arr));
    }

    public static int way1(int[][] arr) {

        if (arr == null || arr.length == 0) {
            return 0;
        }

        int n = arr.length;
        int m = arr[0].length;
        int[][] dp = new int[n][m];
        dp[0][0] = arr[0][0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + arr[i][0];
        }
        for (int i = 1; i < m; i++) {
            dp[0][i] = dp[0][i - 1] + arr[0][i];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = arr[i][j] + Math.min(dp[i][j - 1], dp[i - 1][j]);
            }
        }
        return dp[n - 1][m - 1];
    }

    /**
     * 空间压缩
     *
     * @param arr
     * @return
     */
    public static int way2(int[][] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int row = arr.length;
        int col = arr[0].length;

        int n = Math.min(row, col);
        // dp表选二维数组中维度最小的作为一维数组
        int[] dp = new int[n];
        dp[0] = arr[0][0];
        if (row > col) {
            // 填好第一行
            for (int i = 1; i < col; i++) {
                dp[i] = dp[i - 1] + arr[0][i];
            }
            // 填剩余的行
            for (int i = 1; i < row; i++) {
                dp[0] = dp[0] + arr[i][0];
                for (int j = 1; j < col; j++) {
                    dp[j] = Math.min(dp[j - 1], dp[j]) + arr[i][j];
                }
            }
        } else {
            // 填好第一列
            for (int i = 1; i < row; i++) {
                dp[i] = dp[i - 1] + arr[i][0];
            }
            // 填剩余列
            for (int i = 1; i < col; i++) {
                dp[0] = dp[0] + arr[0][i];
                for (int j = 1; j < row; j++) {
                    dp[j] = Math.min(dp[j - 1], dp[j]) + arr[j][i];
                }
            }
        }
        return dp[n - 1];
    }

}
