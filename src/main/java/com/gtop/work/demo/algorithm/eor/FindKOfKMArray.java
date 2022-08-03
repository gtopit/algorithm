package com.gtop.work.demo.algorithm.eor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 一个数组中有一种数出现K次，其他数都出现了M次，M > 1,K < M
 * 找到 K 并打印
 * 要求：额外空间复杂度O(1)，时间复杂度O(n)
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/10/22 11:09
 */
public class FindKOfKMArray {

    public static void main(String[] args) {

        for (int i = 0; i < 1000_000; i++) {
            int kk = 3, mm = 4;
            int[] arr = randomArray(kk, mm);
            int k = findK(arr, kk, mm);
            int _k = findKCompare(arr, kk, mm);
            if (!equal(k, _k)) {
                System.out.println("出错啦");
                printArr(arr);
            }
        }

    }

    private static void printArr(int[] arr) {

        for (int a : arr) {
            System.out.print(a + ",");
        }

        System.out.println();

        Arrays.sort(arr);

        for (int a : arr) {
            System.out.print(a + ",");
        }

        System.out.println();

    }

    private static boolean equal(int k, int k1) {
        return k == k1;
    }

    private static int findK(int[] arr, int k, int m) {

        if (null == arr || arr.length < 3) {
            throw new RuntimeException("数组长度不符合要求");
        }

        // 用于记录数组中每个数字在int类型转换成二进制相应位置出现的次数
        int[] totalTimes = new int[32];

        for (int i = 0; i < arr.length; i++) {

            // 累计次数
            for (int j = 0; j < totalTimes.length; j++) {
                totalTimes[j] += (arr[i] >> j) & 1;
            }

        }

        int target = 0;
        int realTime;

        for (int i = 0; i < totalTimes.length; i++) {
            if ((realTime = totalTimes[i] % m) != 0) {
                if (realTime != k) {
                    return -1;
                }
                target |= (1 << i);
            }
        }

        return target;
    }

    private static int findKCompare(int[] arr, int k, int m) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int a : arr) {
            Integer num = map.get(a);
            if (null != num) {
                map.put(a, ++num);
            } else {
                map.put(a, 1);
            }
        }

        for (Integer num : map.keySet()) {
            if (map.get(num).equals(k)) {
                return num;
            }
        }
        return -1;
    }

    private static int[] randomArray(int k, int m) {

        if (m < 1 || k < 1 || (m <= k)) {
            throw new RuntimeException("k、m必须大于1，并且k < m");
        }

        int len = calArrayLength(k, m);

        int[] arr = createArr(len);

        populateK(arr, k);

        populateM(arr, k, m);

        disorderArr(arr);

        return arr;

    }

    private static void disorderArr(int[] arr) {
        int length = arr.length;
        int temp = 0;
        for (int i = 0; i < length; i++) {
            int swapIndex = (int) (Math.random() * length);
            temp = arr[i];
            arr[i] = arr[swapIndex];
            arr[swapIndex] = temp;
        }
    }

    private static void populateM(int[] arr, int k, int m) {

        int mKind = (arr.length - k) / m;

        int[] arrKind = new int[mKind];
        for (int i = 0; i < mKind; i++) {
            arrKind[i] = (int) (Math.random() * ((i + 1) * 10)) + (i + 1) * 10;
        }

        int subArrLen = arrKind.length;
        int index = 0;
        for (int i = k; i < arr.length; i++) {
            if (index < subArrLen) {
                arr[i] = arrKind[index];
                index++;
            } else {
                index = 0;
                i--;
            }
        }


    }

    private static void populateK(int[] arr, int k) {
        int theK = (int) (Math.random() * 9) + 1;

        for (int i = 0; i < k; i++) {
            arr[i] = theK;
        }
    }

    private static int[] createArr(int len) {
        return new int[len];
    }

    private static int calArrayLength(int k, int m) {

        return ((int) (Math.random() * 5 + 1)) * m + k;

    }

}
