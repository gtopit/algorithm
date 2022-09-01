package com.gtop.work.demo.algorithm.manacher;

/**
 * 最大回文字串
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/12/16 9:23
 */
public class Manacher {
    
    /**
     * 获取最大的回文串大小
     *
     * @param s
     * @return
     */
    public int getMax(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int[] lr = manacher(s);
        return lr[1] - lr[0];
    }

    /**
     * 获取最大回文子串
     *
     * @param s
     * @return
     */
    public String getMaxPalindrome(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }
        int[] lr = manacher(s);
        return s.substring(lr[0], lr[1]);
    }

    /**
     * 返回获得到的最长回文串的开始和结束位置
     *
     * @param s
     * @return
     */
    public int[] manacher(String s) {
        if (s == null || s.isEmpty()) {
            return new int[2];
        }
        int[] lrIndices = new int[2];
        char[] arr = wrapString(s);
        int r = -1;
        int c = -1;
        int n = arr.length;
        int[] pArr = new int[n];
        int max = 0;
        int ml = 0;
        int mr = 0;
        for (int i = 0; i < n; i++) {
            // 不需要比较的最小范围
            // 只有i < r时才存在加速过程，即pArr能做为指导
            // 并且经过证明，当i<r时，只有当以c为中心，做i的对称点_i（即2 * c - i），取对称点的回文半径左边等于c回文半径左边时才需要对比 ①
            pArr[i] = i < r ? Math.min(pArr[2 * c - i], r - i) : 1;
            while (i - pArr[i] >= 0 && i + pArr[i] < n) {
                // 只有①情况下或 i>=r才有可能出现下面==的情况，否则直接跳出循环
                if (arr[i + pArr[i]] == arr[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            if (pArr[i] + i > r) {
                r = pArr[i] + i;
                c = i;
            }
            if (max < pArr[i]) {
                max = pArr[i];
                ml = i - pArr[i];
                mr = i + pArr[i];
            }
        }
        lrIndices[0] = (ml + 1) / 2;
        lrIndices[1] = mr / 2;
        return lrIndices;
    }

    /**
     * 将原始串进行处理，在每个位置前后加上一个默认的字符（新建char数组的默认值）
     * eg., [a,b,c] => [,a,,b,,c,]
     *
     * @param s
     * @return
     */
    private char[] wrapString(String s) {
        char[] src = s.toCharArray();
        int n = src.length * 2 + 1;
        char[] arr = new char[n];
        for (int i = 0; i < src.length; i++) {
            arr[i * 2 + 1] = src[i];
        }
        return arr;
    }

}
