package com.gtop.work.demo.algorithm.greedy;

/**
 * 点灯问题，用X表示墙，.表示街道组成的数组，
 * X可以放灯，但可照亮可不照亮，
 * .可以放灯，但一定要照亮，
 * 一盏灯可以照亮街道i-1，i，i+1的位置，问：给定一个数组，至少需要点多少盏灯才能照亮全部街道
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/12/30 17:00
 */
public class Light {

    public static int getMin(char[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 'X') {
                i++;
            } else {
                ans++;
                if (i + 1 == arr.length) {
                    break;
                } else {
                    if (arr[i + 1] == 'X') {
                        i += 2;
                    } else {
                        i += 3;
                    }
                }
            }
        }
        return ans;
    }

}
