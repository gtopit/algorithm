package com.gtop.work.demo.algorithm.dp;

/**
 * 给定一个数组arr和一个正数M
 * 返回在arr的子数组长度不超过M的情况下，最大的累加和
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2022/1/4 16:31
 */
public class MaxSum {

    public static int getMax(int[] arr, int m) {
        int max = Integer.MIN_VALUE;
        if (arr == null) {
            return max;
        }
        int[] stack = new int[m];
        int si = -1;
        int[] sums = new int[arr.length + 1];
        for (int i = 1; i < sums.length; i++) {
            sums[i] += arr[i - 1];
        }
        for (int i = 0; i < sums.length; i++) {

        }
        return max;
    }

}
