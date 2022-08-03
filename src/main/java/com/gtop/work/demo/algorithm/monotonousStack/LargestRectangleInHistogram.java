package com.gtop.work.demo.algorithm.monotonousStack;

/**
 * 给定一个非负数组arr，代表直方图
 * 返回直方图的最大长方形面积
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/12/7 17:30
 */
public class LargestRectangleInHistogram {

    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 1, 3, 2};
        System.out.println(way(arr));
    }

    public static int way(int[] arr) {
        if (null == arr || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        int[] stack = new int[n];
        int si = -1;
        int max = 0;
        for (int i = 0; i < n; i++) {
            while (si != -1 && arr[stack[si]] >= arr[i]) {
                int outVal = arr[stack[si--]];
                int left = si != -1 ? stack[si] + 1 : 0;
                int right = i - 1;
                max = Math.max(max, (right - left + 1) * outVal);
            }
            stack[++si] = i;
        }
        while (si != -1) {
            int outVal = arr[stack[si--]];
            int left = si != -1 ? stack[si] + 1 : 0;
            int right = n - 1;
            max = Math.max(max, (right - left + 1) * outVal);
        }
        return max;
    }

}
