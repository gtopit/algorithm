package com.gtop.work.demo.algorithm.schedule;

/**
 * 正方形位置变换（顺时针）：
 * a b c d     m i e a
 * e f g h     n j f b
 * i j k l  => o k g c
 * m n o p     p l h d
 * 正方形位置变换（逆时针）：
 * a b c d     d h l p
 * e f g h     c g k o
 * i j k l  => b f j n
 * m n o p     a e i m
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/12/27 9:22
 */
public class RotateMatrix {

    public static void main(String[] args) {
        char[][] arr = new char[][]{{'a', 'b', 'c', 'd'}, {'e', 'f', 'g', 'h'}, {'i', 'j', 'k', 'l'}, {'m', 'n', 'o', 'p'}};
        printArr(arr);
        change(arr);
        printArr(arr);
        change2(arr);
        printArr(arr);
    }

    private static void printArr(char[][] arr) {
        System.out.println("---------------->");
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.printf("%c ", arr[i][j]);
            }
            System.out.println();
        }
    }

    public static void change(char[][] arr) {
        if (arr == null || arr.length == 0 || arr[0].length == 0) {
            return;
        }
        int row1 = 0;
        int col1 = 0;
        int row2 = arr.length - 1;
        int col2 = arr[0].length - 1;
        while (row1 < row2) {
            rotateMatrix(arr, row1++, col1++, row2--, col2--);
        }
    }

    public static void change2(char[][] arr) {
        if (arr == null || arr.length == 0 || arr[0].length == 0) {
            return;
        }
        int row1 = 0;
        int col1 = 0;
        int row2 = arr.length - 1;
        int col2 = arr[0].length - 1;
        while (row1 < row2) {
            rotateMatrix2(arr, row1++, col1++, row2--, col2--);
        }
    }

    /**
     * 顺时针
     *
     * @param arr
     * @param row1
     * @param col1
     * @param row2
     * @param col2
     */
    private static void rotateMatrix(char[][] arr, int row1, int col1, int row2, int col2) {

        for (int i = 0; i < col2 - col1; i++) {
            // 缓存第i组第一个字符
            char tmp = arr[row1][col1 + i];
            // 第i组第四个字符给第一个位置
            arr[row1][col1 + i] = arr[row2 - i][col1];
            // 第i组第三个字符给第四个位置
            arr[row2 - i][col1] = arr[row2][col2 - i];
            // 第i组第二个字符给第三个位置
            arr[row2][col2 - i] = arr[row1 + i][col2];
            // 第i组第一个字符给第四个位置
            arr[row1 + i][col2] = tmp;
        }

    }

    /**
     * 逆时针
     *
     * @param arr
     * @param row1
     * @param col1
     * @param row2
     * @param col2
     */
    private static void rotateMatrix2(char[][] arr, int row1, int col1, int row2, int col2) {

        for (int i = 0; i < col2 - col1; i++) {
            // 缓存第i组第一个字符
            char tmp = arr[row1][col1 + i];
            // 第i组第二个字符给第一个位置
            arr[row1][col1 + i] = arr[row1 + i][col2];
            // 第i组第三个字符给第二个位置
            arr[row1 + i][col2] = arr[row2][col2 - i];
            // 第i组第四个字符给第三个位置
            arr[row2][col2 - i] = arr[row2 - i][col1];
            // 第i组第一个字符给第四个位置
            arr[row2 - i][col1] = tmp;
        }

    }

}
