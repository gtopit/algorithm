package com.gtop.work.demo.algorithm.training.t2022_2_3;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 来自微软
 * 给定一个数组arr，一个正数num，一个正数k
 * 可以把arr中的某些数字拿出来组成一组，要求该组中的最大值减去最小值<=num
 * 且该组数字的个数一定要正好等于k
 * 每个数字只能选择进某一组，不能进多个组
 * 返回arr中最多有多少组
 * @author hongzw@citycloud.com.cn
 * @Date 2022/2/20 16:57
 */
public class MaxTeamNumber {

    @Test
    public void test() {
        int n = 18;
        int v = 50;
        int testTimes = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int len = (int) (Math.random() * n) + 1;
            int[] arr = randomArray(len, v);
            int num = (int) (Math.random() * v) + 1;
            int k = (int) (Math.random() * len) + 1;
            int ans1 = way1(arr, num, k);
            int ans2 = way2(arr, num, k);
            if (ans1 != ans2) {
                for (int number : arr) {
                    System.out.print(number + " ");
                }
                System.out.println();
                System.out.println("num : " + num);
                System.out.println("k : " + k);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");
    }

    @Test
    public void test2() {
        int[] arr = new int[] {3, 17};
        int num = 29;
        int k = 2;
        int ans1 = way1(arr, num, k);
        int ans2 = way2(arr, num, k);
        System.out.println(ans1 == ans2);
    }

    public int way2(int[] arr, int num, int k) {
        Arrays.sort(arr);
        return process1(arr, 0, new int[arr.length], 0, num, k);
    }


    public int way1(int[] arr, int num, int k) {
        Arrays.sort(arr);
        return process(arr, num, k, arr.length - 1);
    }

    /**
     *
     * 做不成动态规划？？？？
     * @author hongzw
     * @date 2022/2/20 17:10
     * @param arr
     * @param num
     * @param k
     * @param i 表示前i个数
     * @return
     *
     */
    private int process(int[] arr, int num, int k, int i) {

        if (i < k) {
            return i + 1 == k && arr[k - 1] - arr[0] <= num ? 1 : 0;
        }
        int p1;
        int p2;
        int max = 0;
        for (int j = i; j >= k; j--) {
            p1 = process(arr, num, k, j - 1);
            p2 = process(arr, num, k, j - k) + (arr[j] - arr[j - k + 1] > num ? 0 : 1);
            max = Math.max(max, Math.max(p1, p2));
        }

        return max;
    }


    private int way4(int[] arr, int num, int k) {
        int n = arr.length;
        if (k > n) {
            return 0;
        }
        Arrays.sort(arr);
        int[] dp = new int[n];
        int p1;
        int p2;
        int max = 0;
        dp[k - 1] = arr[k - 1] -arr[0] > num ? 0 : 1;
        for (int i = k; i < n; i++) {
            p1 = dp[i - 1];
            p2 = dp[i - k] + (arr[i] - arr[i - k + 1] > num ? 0 : 1);
            dp[i] = Math.max(max, Math.max(p1, p2));
        }
        return dp[n - 1];
    }

    private int cache(int[] arr, int num, int k, int i, Map<Integer, Integer> dp) {
        if (i < k) {
            return i + 1 == k && arr[k - 1] - arr[0] <= num ? 1 : 0;
        }
        if (dp.get(i) != null) {
            return dp.get(i);
        }
        int p1;
        int p2;
        int max = 0;
        for (int j = i; j >= k; j--) {
            p1 = cache(arr, num, k, j - 1, dp);
            p2 = cache(arr, num, k, j - k, dp) + (arr[i] - arr[i - k + 1] > num ? 0 : 1);
            max = Math.max(max, Math.max(p1, p2));
        }
        dp.put(i, max);
        return max;
    }

    public int way3(int[] arr, int num, int k) {
        Arrays.sort(arr);
        return cache(arr, num, k, arr.length - 1, new HashMap<>());
    }

    private int process1(int[] arr, int index, int[] path, int size, int num, int k) {
        if (index == arr.length) {
            if (size % k != 0) {
                return 0;
            } else {
                for (int start = 0; start < size; start += k) {
                    if (path[start + k - 1] - path[start] > num) {
                        return 0;
                    }
                }
                return size / k;
            }
        } else {
            int p1 = process1(arr, index + 1, path, size, num, k);
            path[size] = arr[index];
            int p2 = process1(arr, index + 1, path, size + 1, num, k);
            return Math.max(p1, p2);
        }
    }

    // 用于测试
    public int[] randomArray(int len, int value) {
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            ans[i] = (int) (Math.random() * value);
        }
        return ans;
    }

}
