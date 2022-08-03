package com.gtop.work.demo.algorithm.dp;

/**
 * 最长回文子序列
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/11/25 20:50
 */
public class LongestPalindromeSubseq {

    public static void main(String[] args) {

        String str = "bbbab";

        int i = longestPalindromeSubseq3(str);
        System.out.println(i);
    }

    public static int longestPalindromeSubseq(String s) {
        if (null == s || s.length() == 0) {
            return 0;
        }
        char[] arr = s.toCharArray();
        return process(arr, 0, arr.length - 1);
    }

    /**
     * 最长回文子序列，
     * 思路，只需要判断arr1[i]是否等于arr[j]，从而决定i、j往后移动的情况
     *
     * @param arr
     * @param i
     * @param j
     * @return
     */
    private static int process(char[] arr, int i, int j) {
        if (i > j) {
            return 0;
        } else if (i == j) {
            return 1;
        } else {
            if (arr[i] == arr[j]) {
                return 2 + process(arr, i + 1, j - 1);
            } else {
                int p1 = process(arr, i + 1, j);
                int p2 = process(arr, i, j - 1);
                return Math.max(p1, p2);
            }
        }
    }

    public static int longestPalindromeSubseq2(String s) {
        if (null == s || s.length() == 0) {
            return 0;
        }
        char[] arr = s.toCharArray();
        int[][] dp = new int[arr.length][arr.length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                dp[i][j] = -1;
            }
        }
        return process2(arr, 0, arr.length - 1, dp);
    }

    private static int process2(char[] arr, int i, int j, int[][] dp) {
        if (i <= j && dp[i][j] != -1) {
            return dp[i][j];
        }
        int ans;
        if (i > j) {
            ans = 0;
        } else if (i == j) {
            ans = 1;
        } else {
            if (arr[i] == arr[j]) {
                ans = 2 + process2(arr, i + 1, j - 1, dp);
            } else {
                int p1 = process2(arr, i + 1, j, dp);
                int p2 = process2(arr, i, j - 1, dp);
                ans = Math.max(p1, p2);
            }
        }
        dp[i][j] = ans;
        return ans;
    }

    public static int longestPalindromeSubseq3(String s) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        int[][] dp = new int[n][n];
        // 一个值的时候，对角线已经填好
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        // 两个值的时候，由于(i,j)依赖左，左下，下，已经填完i == j的值了，
        // 当依赖左下时，会发现可能会得到0，此时的0可能不是我们想要的值，因此将对角线的下一个对角线填好
        // 后续就不要判断处理0值问题了。（画图一下子就清晰了）
        for (int i = 0; i < n - 1; i++) {
            // 如果i和i+1位置值一样，则回文+1变成2，否则维持原来的1
            dp[i][i + 1] = arr[i] == arr[i + 1] ? 2 : 1;
        }
        // (i,j)依赖(i+1,j-1) (i+1,j) (i,j-1)
        // 从下往上，从左往右填
        for (int i = n - 3; i >= 0; i--) {
            for (int j = i + 2; j < n; j++) {
                if (arr[i] == arr[j]) {
                    dp[i][j] = 2 + dp[i + 1][j - 1];
                } else {
                    int p1 = dp[i + 1][j];
                    int p2 = dp[i][j - 1];
                    dp[i][j] = Math.max(p1, p2);
                }
            }
        }
        return dp[0][n - 1];
    }

}
