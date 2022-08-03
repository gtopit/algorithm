package com.gtop.work.demo.algorithm.dp;

/**
 * @author hongzw@citycloud.com.cn
 * @Date 2021/12/3 20:29
 */
public class MaxValue {

    public static void main(String[] args) {

        System.out.println(way1(1779824555, 3));

    }

    /**
     * 给定一个数，共取走k个，问剩余的最小情况
     *
     * @param num
     * @param k
     * @return
     */
    public static int way1(int num, int k) {

        int finalNum = num;
        for (int i = 0; i < k; i++) {
            finalNum = process1(finalNum);
        }

        return finalNum;
    }

    public static int process1(int num) {

        if (num < 1) {
            return 0;
        }

        char[] arr = String.valueOf(num).toCharArray();
        int n = arr.length;
        if (n < 2) {
            return 0;
        }
        if (n < 3) {
            return Integer.valueOf(arr[0] < arr[1] ? String.valueOf(arr[0]) : String.valueOf(arr[1]));
        }

        int index = 0;

        while ((index + 1 < n) && arr[index] <= arr[index + 1]) {
            index++;
        }
        char[] newArr = removeChar(arr, index);
        return Integer.valueOf(String.valueOf(newArr));

    }

    private static char[] removeChar(char[] arr, int index) {

        char[] newArr = new char[arr.length - 1];

        int p = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i != index) {
                newArr[p++] = arr[i];
            }
        }

        return newArr;

    }

}
