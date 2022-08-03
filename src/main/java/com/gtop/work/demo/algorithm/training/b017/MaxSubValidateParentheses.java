package com.gtop.work.demo.algorithm.training.b017;

import org.junit.Test;

/**
 * https://leetcode.com/problems/longest-valid-parentheses/
 * 求一串字符串中最长有效括号，返回长度
 * @author hongzw@citycloud.com.cn
 * @Date 2022/2/21 18:36
 */
public class MaxSubValidateParentheses {

    @Test
    public void test() {

        String str = "(()))())(";
        System.out.println(longestValidParentheses(str));

    }

    public int longestValidParentheses(String arr) {

        if (arr == null || arr.length() < 2) {
            return 0;
        }
        int ans = 0;
        char[] chars = arr.toCharArray();
        int n = chars.length;
        int[] dp = new int[n];

        // dp[i]：子串必须以i结尾的情况下，往左最远能扩出多长的有效区域
        for (int i = 1; i < n; i++) {
            if (chars[i] == ')') {
                int look = i - dp[i - 1] - 1;
                if (look >= 0 && chars[look] == '(') {
                    dp[i] = dp[i - 1] + 2 + (look > 0 ? dp[look - 1] : 0);
                }
            }
            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }

}
