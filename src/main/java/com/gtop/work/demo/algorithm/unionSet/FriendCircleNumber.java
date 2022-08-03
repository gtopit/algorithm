package com.gtop.work.demo.algorithm.unionSet;

/**
 * 朋友圈个数
 * 给定一个长度为n的矩阵，1代表互为朋友，0代表不是
 * eg., 0  1  2
 * ```````````
 * 0 ` 1  0  1 `
 * 1 ` 0  1  0 `  对角线肯定为1，因为自己肯定认识自己，这里假设如果a认识b，那么b也认识a，2和0认识，1和0不认识，1和2不认识
 * 2 ` 1  0  1 `
 * ``````````
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/12/31 10:56
 */
public class FriendCircleNumber {

    public static void main(String[] args) {
        int[][] arr = {{1, 0, 1}, {0, 1, 0}, {1, 0, 1}};
        System.out.println(getCircle(arr));
    }

    private static int[] parents;
    private static int[] setSize;

    public static int getCircle(int[][] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int num = 0;
        init(arr);
        for (int i = 0; i < arr.length; i++) {
            // 因为互为朋友，因此只需要填写对角线一半的数据即可
            for (int j = i; j < arr[i].length; j++) {
                if (arr[i][j] == 1) {
                    union(i, j);
                }
            }
        }
        for (int i = 0; i < setSize.length; i++) {
            if (setSize[i] != 0) {
                num++;
            }
        }
        return num;
    }

    private static void init(int[][] arr) {
        int n = arr.length;
        parents = new int[n];
        setSize = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
            setSize[i] = 1;
        }
    }

    private static void union(int e1, int e2) {
        int p1 = findParent(e1);
        int p2 = findParent(e2);
        if (p1 != p2) {
            int s1 = setSize[p1];
            int s2 = setSize[p2];
            if (s2 > s1) {
                s2 += s1;
                setSize[p1] = 0;
                setSize[p2] = s2;
            } else {
                s1 += s2;
                setSize[p2] = 0;
                setSize[p1] = s1;
            }
        }
    }

    private static boolean isSameSet(int e1, int e2) {
        return findParent(e1) == findParent(e2);
    }

    private static int findParent(int e) {
        int p = parents[e];
        while (p != parents[p]) {
            p = parents[p];
        }

        int c = e;
        while (c != parents[c]) {
            c = parents[c];
            parents[c] = p;
        }

        return p;
    }

}
