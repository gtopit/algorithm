package com.gtop.work.demo.algorithm.training.b017;

import org.junit.Test;

/**
 * 给定一个有序数组用于表示一条线上的各个点
 * 给定k表示线段长度
 * 求k能盖住线上的最多点数量，相等也算盖住
 * @author hongzw@citycloud.com.cn
 * @Date 2022/2/21 15:58
 */
public class Cover {

    @Test
    public void test() {
        int nTest = 1000;
        for (int i = 0; i < nTest; i++) {
            int[] arr = randomArr();
            int k = (int) (Math.random() * 10) + 1;
            int r1 = maxCoverPoint1(arr, k);
            int r2 = maxCoverPoint2(arr, k);
            if (r1 != r2) {
                System.out.println("Oops!!!");
                for (int a : arr) {
                    System.out.printf("%d, ", a);
                }
                break;
            }

        }
    }

    /**
     * O(n*n) 暴力
     * @author hongzw
     * @date 2022/2/21 17:43
     * @return
     *
     */
    public int maxCoverPoint1(int[] arr, int k) {

        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            int count = 0;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] <= arr[i] + k) {
                    count++;
                }
            }
            max = Math.max(max, count);
        }

        return max;

    }

    /**
     * O(n*logn) 二分
     * @author hongzw
     * @date 2022/2/21 17:43
     * @return
     *
     */
    public int maxCoverPoint2(int[] arr, int k) {

        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            int ni = nearestIndex(arr, i, arr[i] - k);
            max = Math.max(max, i - ni + 1);
        }
        return max;
    }

    private int nearestIndex(int[] arr, int rightIndex, int value) {

        int bestIndex = rightIndex;
        int leftIndex = 0;
        while (leftIndex <= rightIndex) {
            int midIndex = leftIndex + ((rightIndex - leftIndex) >> 1);
            if (arr[midIndex] >= value) {
                bestIndex = midIndex;
                rightIndex = midIndex - 1;
            } else {
                leftIndex = midIndex + 1;
            }
        }
        return bestIndex;

    }

    /**
     * O(n) 窗口内最大值
     * @author hongzw
     * @date 2022/2/21 17:53
     * @return
     *
     */
    public int maxCoverPoint3(int[] arr, int k) {

        int left = 0;
        int right = 0;
        int n = arr.length;
        int max = 0;
        while (right < n) {

        }

        return 1;
    }

    /**
     * 用于测试
     * @author hongzw
     * @date 2022/2/21 17:44
     * @return
     *
     */
    private int[] randomArr() {
        int n = (int) (Math.random() * 20) + 1;
        int[] arr = new int[n];
        arr[0] = (int) (Math.random() * 6);
        for (int i = 1; i < n; i++) {
            arr[i] = arr[i - 1] + (int) (Math.random() * 3);
        }
        return arr;
    }

}
