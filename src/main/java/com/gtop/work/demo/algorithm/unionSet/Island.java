package com.gtop.work.demo.algorithm.unionSet;

import java.util.Map;

/**
 * 岛群问题：
 * 有一个二维数组，
 * '1'代表一个岛，'0'代表不是岛，
 * 岛的相邻位置（上下左右）如果也存在岛，那么就归属为一个岛群，
 * 统计总共有多少个岛（用并查集和感染两种方式实现）
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/12/31 14:45
 */
public class Island {

    public static void main(String[] args) {
        char[][] arr = new char[][]{{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}};
        System.out.println(getIsland(arr));
    }

    private static int[] setSize;
    private static int[] parents;

    public static int getIsland(char[][] arr) {
        if (arr == null || arr.length == 0 || arr[0].length == 0) {
            return 0;
        }
        init(arr);
        int n = arr.length;
        int m = arr[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m - 1; j++) {
                if (arr[i][j] == '1' && arr[i][j + 1] == '1') {
                    union(i * m + j, i * m + j + 1);
                }
            }
        }
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == '1' && arr[i + 1][j] == '1') {
                    union(i * m + j, (i + 1) * m + j);
                }
            }
        }
        int sum = 0;
        for (int i = 0; i < setSize.length; i++) {
            if (setSize[i] > 0) {
                sum += 1;
            }
        }
        return sum;
    }

    private static void init(char[][] arr) {
        int n = arr.length;
        int m = arr[0].length;
        parents = new int[n * m];
        setSize = new int[n * m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == '1') {
                    parents[i * m + j] = i * m + j;
                    setSize[i * m + j] = 1;
                }
            }
        }
    }

    private static boolean isSameSet(int e1, int e2) {
        return findParent(e1) == findParent(e2);
    }

    private static void union(int e1, int e2) {
        int p1 = findParent(e1);
        int p2 = findParent(e2);
        if (p1 != p2) {
            int s1 = setSize[p1];
            int s2 = setSize[p2];
            if (s2 > s1) {
                s2 += Math.abs(s1);
                setSize[p1] = -1;
                setSize[p2] = s2;
                parents[p1] = p2;
            } else {
                s1 += Math.abs(s2);
                setSize[p2] = -1;
                setSize[p1] = s1;
                parents[p2] = p1;
            }
        }
    }

    private static int findParent(int e) {
        int p = e;
        while (p != parents[p]) {
            p = parents[p];
        }
        int c = e;
        while (parents[c] != p) {
            e = c;
            parents[e] = p;
            c = parents[e];
        }
        return p;
    }

}
