package com.gtop.work.demo.algorithm.fibonacci;

import org.junit.Test;

/**
 * @author hongzw@citycloud.com.cn
 * @Date 2022/7/30 8:14
 */
public class Fibonacci {

    public static int getN(int n) {
        if (n < 3) {
            return 1;
        }
        int[][] base = {{1, 1},
                        {1, 0}};
        int[][] res = matrixPower(base, n - 2);
        return res[0][0] + res[1][0];
    }

    private static int[][] matrixPower(int[][] base, int p) {

        // 单位矩阵
        int[][] res = new int[base.length][base[0].length];
        for (int i = 0; i < base.length; i++) {
            for (int j = 0; j < base[0].length; j++) {
                res[i][j] = i == j ? 1 : 0;
            }
        }

        while (p > 0) {
            if ((p & 1) != 0) {
                res = mulMatrix(res, base);
            }
            base = mulMatrix(base, base);
            p >>= 1;

        }
        return res;
    }

    private static int[][] mulMatrix(int[][] m1, int[][] m2) {

        int[][] res = new int[m1.length][m2.length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m1[i].length; j++) {
                int tmp = 0;
                for (int k = 0; k < m2.length; k++) {
                    tmp += m1[i][k] * m2[k][j];
                }
                res[i][j] = tmp;
            }
        }
        return res;
    }

    @Test
    public void test() {
        int n = getN(6);
        System.out.println(n);
    }

}
