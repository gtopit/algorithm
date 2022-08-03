package com.gtop.work.demo.algorithm.schedule;

/**
 * 打印星号
 * 给定 n * n 的矩阵，打印的星号格式如下（n = 12）
 * <p>
 * ````````````````````````````
 * `  * * * * * * * * * * * * `
 * `                        * `
 * `    * * * * * * * * *   * `
 * `    *               *   * `
 * `    *   * * * * *   *   * `
 * `    *   *       *   *   * `
 * `    *   *   *   *   *   * `
 * `    *   *   * * *   *   * `
 * `    *   *           *   * `
 * `    *   * * * * * * *   * `
 * `    *                   * `
 * `    * * * * * * * * * * * `
 * ````````````````````````````
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/12/27 15:50
 */
public class PrintStar {

    public static void main(String[] args) {
        print(1);
        print(2);
        print(3);
        print(4);
        print(5);
        print(6);
        print(7);
        print(8);
        print(9);
        print(10);
    }

    public static void print(int n) {
        if (n < 1) {
            return;
        }
        char[][] arr = new char[n][n];
        int r1 = 0;
        int c1 = 0;
        int r2 = n - 1;
        int c2 = n - 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = ' ';
            }
        }
        while (r1 <= r2) {
            populate(arr, r1, c1, r2, c2);
            r1 += 2;
            c1 += 2;
            r2 -= 2;
            c2 -= 2;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%c ", arr[i][j]);
            }
            System.out.println();
        }

    }

    private static void populate(char[][] arr, int r1, int c1, int r2, int c2) {
        // 填充第r1行
        int cur = c1;
        while (cur <= c2) {
            arr[r1][cur++] = '*';
        }
        // 填充第c2列
        cur = r1;
        while (cur <= r2) {
            arr[cur++][c2] = '*';
        }
        // 填充第r2行
        cur = c2;
        while (cur > c1) {
            arr[r2][cur--] = '*';
        }
        // 填充第c1列
        cur = r2;
        while (cur > r1 + 1) {
            arr[cur--][c1 + 1] = '*';
        }
    }

}
