package com.gtop.work.demo.algorithm.training.b18;

/**
 * 有n个打包机器人从左到右依次排开，上方有一个自动装置会抓取一批物品放到每个打包机器上，
 * 放到每个机器上的物品有多有少，由于物品不同，需要工人将每个机器上的物品进行移动从而达到
 * 每个机器上的物品相等才能打包。每个物品的重量太大，每次只能搬一个物品进行移动，为了省力，
 * 只在相邻的机器上移动。请计算在搬动最少轮次数下，使每个机器上数量相等。如果不能使每个机器
 * 上的物品相等，则返回-1。
 * eg.,
 * [1,0,5]表示有3个机器，每个机器上分别有1、0、5个物品，经过下面的轮次后：
 * 第一轮：0 <- 5 => 1 1 4  表示3号机器上向2号机器上搬一个物品
 * 第二轮：1 <- 1 <- 4 => 2 1 3  表示2号机器上向1号机器上搬一个物品，3号机器上向2号机器上搬一个物品
 * 第三轮：1 <- 3 => 2 2 2  表示3号机器上向2号机器上搬一个物品
 * 共移动了三轮，因此返回3
 * eg.,
 * [2,2,3]表示有三个机器，每个机器上分别有2、2、3个物品，无论怎么搬动，都不能使三个机器上的物品相等，
 * 因此，返回-1。
 * @author hongzw@citycloud.com.cn
 * @Date 2022/2/23 19:00
 */
public class PackageMachine {

    public int minMove(int[] arr) {

        if (arr == null || arr.length == 0) {
            return -1;
        }
        int n = arr.length;
        int total = arr[0];
        int[] sums = new int[n];
        sums[0] = arr[0];
        for (int i = 1; i < n; i++) {
            total += arr[i];
            sums[i] = sums[i - 1] + arr[i];
        }
        if (total % n != 0) {
            return -1;
        }

        int avg = total / n;
        int ans = 0;
        for (int i = 1; i < n; i++) {
            int left = sums[i - 1] - (i - 1) * avg;
            int right = sums[n - 1] - sums[i] - (n - i - 1) * avg;
            // 考虑i位置时，左边实际拥有的数-左边需要的数 和 右边实际拥有的数-右边需要的数 的情况
            if (left < 0 && right < 0) {
                ans = Math.max(ans, Math.abs(left) + Math.abs(right));
            } else {
                ans = Math.max(ans, Math.max(Math.abs(left), Math.abs(right)));
            }
        }
        return ans;
    }

}
