package com.gtop.work.demo.algorithm.monotonousStack;

/**
 * 给定一个二维数组matrix，其中的值不是0就是1，
 * 返回全部由1组成的最大子矩形，内部有多少个1
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/12/7 17:53
 */
public class MaxSubRectangle {

    public static void main(String[] args) {

        // [["0","0","1","0"],["0","0","1","0"],["0","0","1","0"],["0","0","1","1"],["0","1","1","1"],["0","1","1","1"],["1","1","1","1"]]
        char[][] arr = new char[][]{{'0', '0', '1', '0'}, {'0', '0', '1', '0'}, {'0', '0', '1', '0'}, {'0', '0', '1', '1'}, {'0', '1', '1', '1'}, {'0', '1', '1', '1'}, {'1', '1', '1', '1'}};

        System.out.println(way(arr));

    }

    /**
     * [
     * [1, 1, 0, 1],
     * [1, 1, 1, 0],
     * [1, 1, 1, 0]
     * ]
     * 思路：一次处理一行数据，但是处理前先累加上一行的数据
     * 例如：上一行为[1, 1, 0, 1]，那么此行数据累加后为[2, 2, 1, 0]，下一行累加为[3, 3, 2, 0]
     * 这样就可以使用单调栈的思路求解
     *
     * @param srcArr
     * @return
     */
    public static int way(char[][] srcArr) {

        if (null == srcArr || srcArr.length == 0 || srcArr[0].length == 0) {
            return 0;
        }
        int n = srcArr.length;
        int m = srcArr[0].length;
        int[][] arr = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = srcArr[i][j] - '0';
            }
        }

        int[] cur = new int[m];
        int[] stack = new int[m];
        int si = -1;
        int max = 0;
        for (int i = 0; i < m; i++) {
            cur[i] = arr[0][i];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                while (si != -1 && cur[stack[si]] >= cur[j]) {
                    int outVal = cur[stack[si--]];
                    int left = si != -1 ? stack[si] + 1 : 0;
                    int right = j - 1;
                    max = Math.max(max, (right - left + 1) * outVal);
                }
                stack[++si] = j;
            }

            while (si != -1) {
                int outVal = cur[stack[si--]];
                int left = si != -1 ? stack[si] + 1 : 0;
                int right = m - 1;
                max = Math.max(max, (right - left + 1) * outVal);
            }

            for (int j = 0; j < m; j++) {
                cur[j] = (arr[i - 1][j] == 0 || arr[i][j] == 0) ? arr[i][j] : cur[j] + 1;
            }
        }

        for (int j = 0; j < m; j++) {
            while (si != -1 && cur[stack[si]] >= cur[j]) {
                int outVal = cur[stack[si--]];
                int left = si != -1 ? stack[si] + 1 : 0;
                int right = j - 1;
                max = Math.max(max, (right - left + 1) * outVal);
            }
            stack[++si] = j;
        }

        while (si != -1) {
            int outVal = cur[stack[si--]];
            int left = si != -1 ? stack[si] + 1 : 0;
            int right = m - 1;
            max = Math.max(max, (right - left + 1) * outVal);
        }

        return max;

    }

}
