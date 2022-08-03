package com.gtop.work.demo.algorithm.window;

import java.util.LinkedList;

/**
 * 假设一个固定大小为w的窗口，依次划过arr
 * 每次滑动后求出滑动窗口里面的最大值
 * 例如：arr=[4,3,5,4,3,3,6,7]  w=3
 * 返回：[5,5,5,4,6,7]
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/12/4 11:15
 */
public class WindowSlice {

    public static void main(String[] args) {

        int[] arr = new int[]{4, 3, 5, 4, 3, 3, 6, 7};
        int[] way = way(arr, 3);
        for (int w : way) {
            System.out.printf("%d,", w);
        }

    }

    public static int[] way(int[] arr, int w) {

        if (arr == null || w < 1 || w < 1) {
            return new int[0];
        }

        // 用一个双端队列，保存的是arr的下标值
        LinkedList<Integer> dequeue = new LinkedList<>();
        int n = arr.length;
        int retIndex = 0;
        int rightIndex = 0;
        int[] retArr = new int[n + 1 - w];

        while (rightIndex < n) {

            while (!dequeue.isEmpty() && arr[dequeue.peekLast()] <= arr[rightIndex]) {
                dequeue.pollLast();
            }
            dequeue.addLast(rightIndex);

            if (dequeue.peekFirst() + w - 1 < rightIndex) {
                dequeue.pollFirst();
            }

            if (rightIndex >= w - 1) {
                retArr[retIndex++] = arr[dequeue.peekFirst()];
            }
            rightIndex++;
        }

        return retArr;

    }

}
