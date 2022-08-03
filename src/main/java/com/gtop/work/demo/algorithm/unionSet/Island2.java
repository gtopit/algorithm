package com.gtop.work.demo.algorithm.unionSet;

/**
 * 岛群问题：
 * 有一个二维数组，
 * '1'代表一个岛，'0'代表不是岛，
 * 岛的相邻位置（上下左右）如果也存在岛，那么就归属为一个岛群，
 * 统计总共有多少个岛（用并查集和感染两种方式实现）
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/12/31 19:08
 */
public class Island2 {

    public static int getIsland(char[][] arr) {
        if (arr == null || arr.length == 0 || arr[0].length == 0) {
            return 0;
        }
        int island = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == '1') {
                    island++;
                    infect(arr, i, j);
                }
            }
        }

        return island;
    }

    private static void infect(char[][] arr, int i, int j) {
        if (i >= arr.length || j >= arr[0].length || i < 0 || j < 0 || arr[i][j] != '1') {
            return;
        }
        arr[i][j] = '0';
        infect(arr, i - 1, j);
        infect(arr, i + 1, j);
        infect(arr, i, j - 1);
        infect(arr, i, j + 1);
    }

}
