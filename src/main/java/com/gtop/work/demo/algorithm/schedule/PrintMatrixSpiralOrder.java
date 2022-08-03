package com.gtop.work.demo.algorithm.schedule;

/**
 * 螺旋打印矩阵
 * 例如：
 * a b c d
 * e f g h  => a b c d h l p o n m i e f g k j
 * i j k l
 * m n o p
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/12/27 16:18
 */
public class PrintMatrixSpiralOrder {

    public static void main(String[] args) {

        char[][] arr1 = new char[][]{{'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'}};
        print(arr1);
        char[][] arr2 = new char[][]{{'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'}, {'j', 'k', 'l'}};
        print(arr2);
        char[][] arr3 = new char[][]{{'a', 'b', 'c', 'd'}, {'e', 'f', 'g', 'h'}, {'i', 'j', 'k', 'l'}};
        print(arr3);
    }

    public static void print(char[][] arr) {

        if (arr == null || arr.length == 0 || arr[0].length == 0) {
            return;
        }

        int r1 = 0;
        int c1 = 0;
        int r2 = arr.length - 1;
        int c2 = arr[0].length - 1;
        while (r1 <= r2 && c1 <= c2) {
            doPrint(arr, r1++, c1++, r2--, c2--);
        }
        System.out.println();

    }

    private static void doPrint(char[][] arr, int r1, int c1, int r2, int c2) {
        if (c1 == c2) {
            // 如果行多于列
            for (int i = 0; i <= r2 - r1; i++) {
                System.out.printf("%c ", arr[r1 + i][c1]);
            }
        } else if (r1 == r2) {
            // 如果列多于行
            for (int i = 0; i <= c2 - c1; i++) {
                System.out.printf("%c ", arr[r1][c1 + i]);
            }
        } else {
            // 行列一样多
            // 1、打印r1行
            int cur = c1;
            while (cur < c2) {
                System.out.printf("%c ", arr[r1][cur++]);
            }
            // 2、打印c2列
            cur = r1;
            while (cur < r2) {
                System.out.printf("%c ", arr[cur++][c2]);
            }
            // 3、打印r2行
            cur = c2;
            while (cur > c1) {
                System.out.printf("%c ", arr[r2][cur--]);
            }
            // 4、打印c1列
            cur = r2;
            while (cur > r1) {
                System.out.printf("%c ", arr[cur--][c1]);
            }
        }
    }

}
