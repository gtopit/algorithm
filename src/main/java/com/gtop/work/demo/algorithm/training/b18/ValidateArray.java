package com.gtop.work.demo.algorithm.training.b18;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给定一个整数N，请做出一个数组，使其满足：
 * i + k != 2 * j  (限制条件： 0 < i < j < k )
 * @author hongzw@citycloud.com.cn
 * @Date 2022/2/21 22:30
 */
public class ValidateArray {

    @Test
    public void test() {
        int[] arr = generateArr(7);
        for (int i : arr) {
            System.out.printf("%d ", i);
        }
        System.out.println();
    }

    public int[] generateArr(int n) {
        int size = tableSizeFor(n);
        int[] arr = new int[] {1};
        int length = arr.length;
        while (length < size) {
            arr = gen(arr);
            length = arr.length;
        }
        return Arrays.copyOf(arr, n);
    }

    private int tableSizeFor(int cap) {
        int n = cap;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n;
    }

    private int[] gen(int[] arr) {
        int n = arr.length;
        int[] arr2 = new int[n * 2];
        for (int i = 0; i < n; i++) {
            arr2[i] = arr[i] * 2 - 1;
            arr2[n + i] = arr[i] * 2;
        }
        return arr2;
    }

}
