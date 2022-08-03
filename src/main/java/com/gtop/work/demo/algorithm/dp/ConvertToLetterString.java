package com.gtop.work.demo.algorithm.dp;

/**
 * 给出一串由数字组成的字符串，求可以转换成由26个大写英文字母转换出的最多可能数
 * 如 111 可以拆成1 11 那么对应的就是A 和 K 组合成AK，也可以拆成11 1 那么对应的就是K A 组合成KA，
 * 还可以拆成 1 1 1 那么对应的就是A A A，组合成AAA，因此最多能有3中拆法
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/11/25 16:36
 */
public class ConvertToLetterString {

    public static void main(String[] args) {
        String arr = "0";
        int way1 = way1(arr);
        System.out.println(way1);
        int way2 = way2(arr);
        System.out.println(way2);
    }

    public static int way1(String s) {
        char[] chars = s.toCharArray();
        return process1(chars, 0);
    }

    /**
     * 递归
     *
     * @param arr
     * @param index
     * @return
     */
    private static int process1(char[] arr, int index) {

        if (index == arr.length) {
            return 1;
        }

        // 如果单独面对0，说明转不了了
        if (arr[index] == '0') {
            return 0;
        }

        int p1 = process1(arr, index + 1);
        int p2 = 0;
        if (index + 1 < arr.length && ((arr[index] - '0') * 10 + (arr[index + 1] - '0') < 27)) {
            p2 = process1(arr, index + 2);
        }

        return p1 + p2;
    }

    public static int way2(String str) {
        char[] arr = str.toCharArray();
        int[] dp = new int[arr.length + 1];
        int length = arr.length;
        dp[length] = 1;

        for (int index = length - 1; index >= 0; index--) {

            if (arr[index] != '0') {
                int p1 = dp[index + 1];
                int p2 = 0;
                if (index + 1 < arr.length && ((arr[index] - '0') * 10 + (arr[index + 1] - '0') < 27)) {
                    p2 = dp[index + 2];
                }
                dp[index] = p1 + p2;
            }
        }

        return dp[0];

    }

}
