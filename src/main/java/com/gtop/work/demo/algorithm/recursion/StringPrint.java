package com.gtop.work.demo.algorithm.recursion;

import java.util.*;

/**
 * @author hongzw@citycloud.com.cn
 * @Date 2021/11/22 8:42
 */
public class StringPrint {

    public static void main(String[] args) {
        String s = "aabc";
        char[] chars = s.toCharArray();
        List<String> ans = new ArrayList<>();
        allSort(chars, 0, ans);

        for (String an : ans) {
            System.out.println(an);
        }

        System.out.println("-------------------------------");
        Set<String> ans2 = new HashSet<>();
        allSubSort(chars, 0, ans2, "");
        for (String an : ans2) {
            System.out.println(an);
        }
    }

    /**
     * 打印一个字符串的全部子序列，并且去重
     *
     * @param chars
     * @param index
     * @param ans
     * @param path
     */
    private static void allSubSort(char[] chars, int index, Set<String> ans, String path) {
        if (index == chars.length) {
            ans.add(path);
            return;
        }
        allSubSort(chars, index + 1, ans, path);
        allSubSort(chars, index + 1, ans, path + String.valueOf(chars[index]));
    }

    /**
     * 打印一个字符串的全部排列，并且去重
     *
     * @param chars
     * @param index
     * @param ans
     */
    private static void allSort(char[] chars, int index, List<String> ans) {

        if (index == chars.length) {
            ans.add(String.valueOf(chars));
            return;
        }
        // 去重（剪枝）
        boolean[] visited = new boolean[256];
        for (int i = index; i < chars.length; i++) {
            if (!visited[chars[i]]) {
                visited[chars[i]] = true;
                swap(chars, index, i);
                allSort(chars, index + 1, ans);
                swap(chars, index, i);
            }
        }
    }

    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

}
