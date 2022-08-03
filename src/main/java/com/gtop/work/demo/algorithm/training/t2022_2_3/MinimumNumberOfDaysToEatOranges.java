package com.gtop.work.demo.algorithm.training.t2022_2_3;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/minimum-number-of-days-to-eat-n-oranges/
 * @author hongzw@citycloud.com.cn
 * @Date 2022/2/20 14:46
 */
public class MinimumNumberOfDaysToEatOranges {

    @Test
    public void test() {
        int days = minDays(10);
        System.out.println(days);
    }

    public int minDays(int n) {
        Map<Integer, Integer> dp = new HashMap<>();
        return process(n, dp);
    }

    private int process(int n, Map<Integer, Integer> dp) {

        if (n <= 1) {
            return 1;
        }
        int days;
        if (dp.containsKey(n)) {
            return dp.get(n);
        }
        // divisible by 2
        int p1 = n % 2 + 1 + process(n/2, dp);
        // divisible by 3
        int p2 = n % 3 + 1 + process(n/3, dp);
        days = Math.min(p1, p2);
        dp.put(n, days);
        return days;
    }

}
