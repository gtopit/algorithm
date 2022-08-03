package com.gtop.work.demo.algorithm.training.b18;

import org.junit.Test;

/**
 * 给定一个一维数组arr，长度为n，你可以把任意长度大于0且小于n的前缀作为左部分，
 * 剩下的作为右部分。
 * 但是每种划分下都有左部分最大值和右部分最大值，请返回最大的 左部分值减去右部分值的绝对值
 * @author hongzw@citycloud.com.cn
 * @Date 2022/2/23 19:16
 */
public class MaxValueOfLeftAndRest {

    @Test
    public void test() {
        int[] arr = new int[] {1,2,3,4,5};
        System.out.println(getMaxValue(arr));
    }

    public int getMaxValue(int[] arr) {
        if (arr == null) {
            return 0;
        }
        int n = arr.length;
        if (n < 2) {
            return arr[0];
        }
        int max = arr[0];
        for (int i = 1; i < n; i++) {
            max = Math.max(max, arr[i]);
        }
        return Math.max(max - arr[0], max - arr[n - 1]);
    }

}
