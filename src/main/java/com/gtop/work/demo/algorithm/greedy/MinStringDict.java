package com.gtop.work.demo.algorithm.greedy;

import java.util.Arrays;

/**
 * 给定一个由字符串组成的数组strs，必须把所有字符串拼接起来，
 * 返回所有可能的拼接结果中，字典序最小的结果
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/12/30 10:20
 */
public class MinStringDict {

    public static void main(String[] args) {
        String[] strs = new String[]{"dcf", "aba", "a"};
        System.out.println(getMinStringDist(strs));
    }

    public static String getMinStringDist(String[] strs) {
        Arrays.sort(strs, (a, b) -> (a + b).compareTo(b + a));
        StringBuilder builder = new StringBuilder();
        for (String str : strs) {
            builder.append(str);
        }
        return builder.toString();
    }

}
