package com.gtop.work.demo.algorithm.dp;

/**
 * arr是面值数组，其中的值都是整数且没有重复
 * 给定一个正数aim
 * 每个值都认为是一种面值，且认为张数是无限的
 * 返回组成aim的最少货币数
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/12/2 8:46
 */
public class MinCoinsNoLimit {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 4, 5, 7};
        System.out.println(way1(arr, 9));
        System.out.println(way2(arr, 9));
        System.out.println(way3(arr, 9));
    }

    public static int way1(int[] arr, int aim) {

        if (arr == null || arr.length == 0 || aim <= 0) {
            return -1;
        }

        return process1(arr, 0, aim);

    }

    private static int process1(int[] arr, int index, int restAim) {

        if (index == arr.length) {
            return restAim == 0 ? 0 : -1;
        }

        int next;
        int min = process1(arr, index + 1, restAim);
        for (int i = 1; i * arr[index] <= restAim; i++) {
            next = process1(arr, index + 1, restAim - i * arr[index]);
            if (next != -1) {
                if (min != -1) {
                    min = Math.min(min, next + i);
                } else {
                    min = next + i;
                }
            }
        }

        return min;
    }

    public static int way2(int[] arr, int aim) {

        if (arr == null || arr.length == 0 || aim <= 0) {
            return -1;
        }

        int n = arr.length;
        int[][] dp = new int[n + 1][aim + 1];

        dp[n][0] = 0;
        for (int i = 1; i <= aim; i++) {
            dp[n][i] = -1;
        }

        for (int index = n - 1; index >= 0; index--) {
            for (int restAim = 0; restAim <= aim; restAim++) {
                int next;
                int min = dp[index + 1][restAim];
                for (int i = 1; i * arr[index] <= restAim; i++) {
                    next = dp[index + 1][restAim - i * arr[index]];
                    if (next != -1) {
                        if (min != -1) {
                            min = Math.min(min, next + i);
                        } else {
                            min = next + i;
                        }
                    }
                }
                dp[index][restAim] = min;
            }
        }

        return dp[0][aim];
    }

    public static int way3(int[] arr, int aim) {

        if (arr == null || arr.length == 0 || aim <= 0) {
            return -1;
        }

        int n = arr.length;
        int[][] dp = new int[n + 1][aim + 1];

        dp[n][0] = 0;
        for (int i = 1; i <= aim; i++) {
            dp[n][i] = -1;
        }

        for (int index = n - 1; index >= 0; index--) {
            for (int restAim = 0; restAim <= aim; restAim++) {
                int min = dp[index + 1][restAim];
                int next;
                // 减去一张当前面值，后续的决策中会选出最小值，如果存在，则+1和当前比较，得到最小值
                if (restAim - arr[index] >= 0 && (next = dp[index + 1][restAim - arr[index]]) > -1) {
                    if (min != -1) {
                        min = Math.min(min, next + 1);
                    } else {
                        min = next + 1;
                    }
                }

                dp[index][restAim] = min;
            }
        }

        return dp[0][aim];
    }

}
