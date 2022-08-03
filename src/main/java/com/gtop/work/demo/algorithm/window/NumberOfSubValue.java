package com.gtop.work.demo.algorithm.window;

import java.util.LinkedList;

/**
 * 给定一个整形数组arr，和一个整形num
 * 某个arr中的子数组sub，如果想要达标，必须满足
 * sub中最大值-sub中最小值 <= num
 * 返回arr中达标子数组的数量
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/12/7 8:45
 */
public class NumberOfSubValue {

    public static void main(String[] args) {

        int[] arr = new int[]{5, 2, 1, 3, 4, 4, 6, 7};
        System.out.println(right(arr, 3));
        System.out.println(way(arr, 3));

    }

    /**
     * 暴力解O(n^2)
     *
     * @param arr
     * @param sum
     * @return
     */
    public static int right(int[] arr, int sum) {
        if (arr == null || arr.length == 0 || sum < 0) {
            return 0;
        }
        int N = arr.length;
        int count = 0;
        for (int L = 0; L < N; L++) {
            int max = arr[L];
            int min = arr[L];
            for (int R = L; R < N; R++) {
                max = Math.max(max, arr[R]);
                min = Math.min(min, arr[R]);
                if (max - min <= sum) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 最优解O(n)
     * 思路：如果子数组[i...j]达标，那么以i开头的每个子数组都达标，达标数即为i-j+1
     * 如果子数组[i...j+1]不达标，那么以i开头，j+1结尾及之后的子数组都不达标
     * 因此可以使用两个双端链表，一个从大到小的入队顺序，一个从小到大的入队顺序
     * 处理arr中的每个元素时同步更新两个队列，比较两个队列里的最大最小值即可
     *
     * @param arr
     * @param num
     * @return
     */
    public static int way(int[] arr, int num) {

        if (null == arr || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        int right = 0;
        // max双端队列用数组表示
        LinkedList<Integer> maxQueue = new LinkedList<>();
        // min双端队列用Java提供的
        LinkedList<Integer> minQueue = new LinkedList<>();
        int count = 0;
        for (int left = 0; left < n; left++) {

            while (right < n) {
                while (!minQueue.isEmpty() && arr[minQueue.peekLast()] >= arr[right]) {
                    minQueue.pollLast();
                }
                minQueue.addLast(right);

                while (!maxQueue.isEmpty() && arr[maxQueue.peekLast()] <= arr[right]) {
                    maxQueue.pollLast();
                }
                maxQueue.addLast(right);

                if (arr[maxQueue.peekFirst()] - arr[minQueue.peekFirst()] <= num) {
                    right++;
                } else {
                    break;
                }

            }

            count += right - left;

            if (maxQueue.peekFirst() <= left) {
                maxQueue.pollFirst();
            }
            if (minQueue.peekFirst() <= left) {
                minQueue.pollFirst();
            }

        }

        return count;
    }
}
