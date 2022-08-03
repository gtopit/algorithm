package com.gtop.work.demo.algorithm.unionSet;

/**
 * 给定m行n列的二维数组，初始时数组中所有位置值都为0，
 * 现每给一个(i,j)的一维数组，在二维数组中对应位置变为1，同时返回组成的岛群数
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2022/1/6 8:53
 */
public class Island3 {

    public static void main(String[] args) {
        init(10, 9);
        /*int i = way1(new int[]{0, 0});
        System.out.println(i);
        i = way1(new int[]{1, 0});
        System.out.println(i);
        i = way1(new int[]{2, 0});
        System.out.println(i);
        i = way1(new int[]{2, 2});
        System.out.println(i);
        i = way1(new int[]{4, 2});
        System.out.println(i);
        i = way1(new int[]{3, 2});
        System.out.println(i);
        i = way1(new int[]{2, 1});
        System.out.println(i);*/
    }

    private static int M;
    private static int N;
    private static int[][] map;

    private static int[] parents;
    private static int[] setSize;

    public static void init(int m, int n) {
        M = m + 2;
        N = n + 2;
        // 为了方便处理边界值，给原始二维数组最外层加一层
        map = new int[M][N];
        parents = new int[m * n];
        setSize = new int[m * n];
    }

    public static int way1(int[] arr) {
        if (arr == null || arr.length != 2 || arr[0] > M - 2 || arr[1] > N - 2) {
            throw new RuntimeException("illegal parameter");
        }
        int i = arr[0];
        int j = arr[1];
        int mi = i + 1;
        int mj = j + 1;
        int n = N - 2;
        if (map[mi][mj] != 1) {
            map[mi][mj] = 1;
            add(i * n + j);
            if (map[mi - 1][mj] == 1) {
                // 上
                union(i * n + j, (i - 1) * n + j);
            }
            if (map[mi][mj + 1] == 1) {
                // 右
                union(i * n + j, i * n + j + 1);
            }
            if (map[mi + 1][mj] == 1) {
                // 下
                union(i * n + j, (i + 1) * n + j);
            }
            if (map[mi][mj - 1] == 1) {
                // 左
                union(i * n + j, i * n + j - 1);
            }
        }
        return getTotal();
    }

    private static int getTotal() {
        int total = 0;
        for (int i = 0; i < setSize.length; i++) {
            if (setSize[i] > 0) {
                total++;
            }
        }
        return total;
    }

    private static void add(int e) {
        parents[e] = e;
        setSize[e] = 1;
    }

    private static void union(int e1, int e2) {
        int p1 = findParent(e1);
        int p2 = findParent(e2);
        int s1 = setSize[p1];
        int s2 = setSize[p2];
        if (s2 > s1) {
            s2 += Math.abs(s1);
            setSize[p2] = s2;
            parents[p1] = p2;
            setSize[p1] = -1;
        } else {
            s1 += Math.abs(s2);
            setSize[p1] = s1;
            parents[p2] = p1;
            setSize[p2] = -1;
        }
    }

    private static int findParent(int e) {
        int p = e;
        while (p != parents[p]) {
            p = parents[p];
        }
        int c = e;
        while (c != p) {
            parents[e] = p;
            c = parents[c];
            e = c;
        }
        return p;
    }

}