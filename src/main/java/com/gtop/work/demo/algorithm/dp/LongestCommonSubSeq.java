package com.gtop.work.demo.algorithm.dp;

/**
 * 最长公共子序列
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/11/28 10:45
 */
public class LongestCommonSubSeq {

    public static void main(String[] args) {
        String text1 = "abcde";
        String text2 = "ace";
        int count = longestCommonSubsequence3(text1, text2);
        System.out.println(count);
    }

    public static int longestCommonSubsequence1(String text1, String text2) {

        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
            return 0;
        }

        char[] arr1 = text1.toCharArray();
        char[] arr2 = text2.toCharArray();

        return process1(arr1, arr2, arr1.length - 1, arr2.length - 1);

    }

    /**
     * 递归 比较0-i，和0-j的最长子序列
     *
     * @param arr1
     * @param arr2
     * @param i    str1来到的位置
     * @param j    str2来到的位置
     * @return
     */
    private static int process1(char[] arr1, char[] arr2, int i, int j) {

        if (i == 0 && j == 0) {
            return arr1[i] == arr2[j] ? 1 : 0;
        } else if (i == 0) {
            return arr1[i] == arr2[j] ? 1 : process1(arr1, arr2, i, j - 1);
        } else if (j == 0) {
            return arr1[i] == arr2[j] ? 1 : process1(arr1, arr2, i - 1, j);
        } else {
            int p1 = process1(arr1, arr2, i, j - 1);
            int p2 = process1(arr1, arr2, i - 1, j);
            int p3 = (arr1[i] == arr2[j] ? 1 : 0) + process1(arr1, arr2, i - 1, j - 1);
            return Math.max(Math.max(p1, p2), p3);
        }

    }

    public static int longestCommonSubsequence2(String text1, String text2) {

        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
            return 0;
        }

        char[] arr1 = text1.toCharArray();
        char[] arr2 = text2.toCharArray();

        return process2(arr1, arr2, 0, 0);

    }

    /**
     * 最长公共子序列
     * 实现思路，只需要判断arr1[i]是否等于arr2[j]，从而决定i、j往后移动的情况
     *
     * @param arr1
     * @param arr2
     * @param i
     * @param j
     * @return
     */
    private static int process2(char[] arr1, char[] arr2, int i, int j) {

        if (i == arr1.length || j == arr2.length) {
            // 表示二者中有任何一串字符串到达结尾，则结束
            return 0;
        }
        if (arr1[i] == arr2[j]) { // 相等则同时移动，比较下个位置
            return 1 + process2(arr1, arr2, i + 1, j + 1);
        } else { // 不相等，要不就i移动，要不就j移动
            int p1 = process2(arr1, arr2, i + 1, j);
            int p2 = process2(arr1, arr2, i, j + 1);
            return Math.max(p1, p2);
        }

    }

    public static int longestCommonSubsequence3(String text1, String text2) {

        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
            return 0;
        }

        char[] arr1 = text1.toCharArray();
        char[] arr2 = text2.toCharArray();

        int[][] dp = new int[arr1.length][arr2.length];

        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2.length; j++) {
                dp[i][j] = -1;
            }
        }
        return process3(arr1, arr2, 0, 0, dp);

    }

    /**
     * 缓存
     *
     * @param arr1
     * @param arr2
     * @param i
     * @param j
     * @param dp
     * @return
     */
    private static int process3(char[] arr1, char[] arr2, int i, int j, int[][] dp) {

        if (i == arr1.length || j == arr2.length) {
            // 表示二者中有任何一串字符串到达结尾，则结束
            return 0;
        }
        if ((i < arr1.length && j < arr2.length) && dp[i][j] != -1) {
            return dp[i][j];
        }
        int ans;
        if (arr1[i] == arr2[j]) {
            ans = 1 + process3(arr1, arr2, i + 1, j + 1, dp);
        } else {
            int p1 = process3(arr1, arr2, i + 1, j, dp);
            int p2 = process3(arr1, arr2, i, j + 1, dp);
            ans = Math.max(p1, p2);
        }
        dp[i][j] = ans;
        return ans;
    }


    // 基于你的code，改严格位置依赖的动态规划
    // 傻缓存就是动态规划！但是既然你要这种形式，给你！
    public static int longestCommonSubsequence(String s1, String s2) {
        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();
        int n = arr1.length;
        int m = arr2.length;
        int[][] dp = new int[n + 1][m + 1];
        // 状态(i,j)，根据你写的，依赖(i+1,j+1)、(i+1,j)和(i,j+1)
        // 所以，填表的时候，整体顺序从下往上
        // 每一行的顺序，从右往左
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (arr1[i] == arr2[j]) {
                    dp[i][j] = 1 + dp[i + 1][j + 1];
                } else {
                    int p1 = dp[i + 1][j];
                    int p2 = dp[i][j + 1];
                    dp[i][j] = Math.max(p1, p2);
                }

            }
        }
        return dp[0][0];
    }

}
