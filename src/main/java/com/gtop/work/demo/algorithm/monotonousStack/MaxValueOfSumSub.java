package com.gtop.work.demo.algorithm.monotonousStack;

import java.util.LinkedList;

/**
 * 给定一个只包含正整数的数组arr，arr中任何一个子数组sub，
 * 一定都可以算出sub的累加和*sub中的最小值是什么，
 * 那么所有子数组中，这个值最大是多少
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/12/7 14:10
 */
public class MaxValueOfSumSub {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 2};
        System.out.println(test(arr));
        System.out.println(way(arr));
    }

    /**
     * 对数器，暴力解
     *
     * @param arr
     * @return
     */
    public static int test(int[] arr) {

        if (arr == null || arr.length == 0) {
            return 0;
        }

        int n = arr.length;
        int[] sum = new int[n + 1];
        int max = Integer.MIN_VALUE;
        int min;
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + arr[i];
        }
        for (int i = 0; i < n; i++) {
            min = Integer.MAX_VALUE;
            for (int j = i; j < n; j++) {
                min = Math.min(min, arr[j]);
            }
            max = Math.max(max, (sum[n] - sum[i]) * min);
        }

        return max;
    }

    /**
     * 单调栈实现
     *
     * @param arr
     * @return
     */
    public static int way(int[] arr) {

        if (arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        int[] sum = new int[n];
        sum[0] = arr[0];
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = 1; i < n; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peekLast()] >= arr[i]) {
                stack.pollLast();
            }
            stack.addLast(i);
        }
        int max = sum[n - 1] * arr[stack.peekFirst()];
        for (int i = 1; i < n - 1; i++) {
            if (stack.peekFirst() == i) {
                stack.pollFirst();
            }
            max = Math.max(max, (sum[n - 1] - sum[i]) * arr[stack.peekFirst()]);
        }
        return max;

    }

}
