package com.gtop.work.demo.algorithm.training.b017;

import org.junit.Test;

/**
 * https://leetcode.com/problems/largest-1-bordered-square/
 * 给出一个二维数组代表的矩形，数组中的值不是0就是1，求出由1围城的最大正方形的面积，正方形围成的内部可以为0
 * @author hongzw@citycloud.com.cn
 * @Date 2022/2/21 20:51
 */
public class MaxSquare {

    @Test
    public void test() {
        int[][] arr = new int[][] {{1,1,1},{1,0,1},{1,1,1}};
        int s = largest1BorderedSquare(arr);
        System.out.println(s);
    }

    public int largest1BorderedSquare(int[][] arr) {

        int n = arr.length;
        int m = arr[0].length;
        int[][] rowArr = new int[n][m];
        int[][] colArr = new int[n][m];
        setMaxOneArr(arr, rowArr, colArr);
        int size = Math.min(n, m);
        // 从最大可能的边长开始试，逐渐减小，找到即返回
        for (int k = size; k > 0; k--) {
            if (hasMaxSize(k, rowArr, colArr)) {
                return k * k;
            }
        }
        return 0;
    }

    private boolean hasMaxSize(int k, int[][] rowArr, int[][] colArr) {
        int n = rowArr.length;
        int m = rowArr[0].length;
        for (int i = 0; i < n - k + 1; i++) {
            for (int j = 0; j < m - k + 1; j++) {
                // 举例说明下面判断条件
                // 原始数组： 1  1  1    rowArr数组：  1  1  1    colArr数组：  1  2  3
                //          1  0  1                 2  0  2                 1  0  1
                //          1  1  1                 3  1  3                 1  2  3
                // 需要取rowArr[2][0]、rowArr[2][2]、colArr[0][2]、colArr[2][2] 来判断四条边的1数量是否满足条件
                // 血的教训，没进行具象化分析，这个条件判断卡了7个小时
                if (rowArr[i][j + k - 1] >= k && rowArr[i + k - 1][j + k - 1] >= k && colArr[i + k - 1][j] >= k && colArr[i + k - 1][j + k - 1] >= k) {
                    return true;
                }
            }
        }
        return false;
    }

    private void setMaxOneArr(int[][] arr, int[][] rowArr, int[][] colArr) {

        int n = arr.length;
        int m = arr[0].length;
        rowArr[0][0] = arr[0][0];
        for (int i = 1; i < m; i++) {
            colArr[0][i] = arr[0][i];
            // 将第0行的第1列到第m列填完整
            rowArr[0][i] = arr[0][i] == 1 ? rowArr[0][i - 1] + 1 : 0;
        }
        colArr[0][0] = arr[0][0];
        for (int i = 1; i < n; i++) {
            rowArr[i][0] = arr[i][0];
            // 将第0列第1到第n列填完整
            colArr[i][0] = arr[i][0] == 1 ? colArr[i - 1][0] + 1 : 0;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (arr[i][j] == 1) {
                    // 第i行
                    rowArr[i][j] = rowArr[i][j - 1] + 1;
                    // 第j列
                    colArr[i][j] = colArr[i - 1][j] + 1;
                }
            }
        }

    }

}
