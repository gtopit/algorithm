package com.gtop.work.demo.algorithm.greedy;

import java.util.PriorityQueue;

/**
 * 给定一块金条，分成指定大小的小金条（不对分的顺序做要求），每分一次代价是当时大金条的值，
 * eg,. 金条为10，分成1、4、5的小金条，
 * 1）可以先分成1和9，此时花费的代价为10，然后再把9分成4和5，花费的总代价为19
 * 2）可以先分成5和5，此时花费的代价为10，然后再把5分成1和4，花费的总代价为15
 * 要求返回切分的最小代价
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/12/30 10:49
 */
public class SplitGold {

    public static void main(String[] args) {
        int[] size = new int[]{1, 4, 5, 10, 19};

        System.out.println(getMin(size));
        System.out.println(getMin2(size));
    }

    public static int getMin(int[] size) {

        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int i : size) {
            heap.add(i);
        }
        int sum = 0;
        int cur;
        while (heap.size() > 1) {
            cur = heap.poll() + heap.poll();
            sum += cur;
            heap.add(cur);
        }
        return sum;
    }

    /**
     * @param arr
     * @return
     */
    public static int getMin2(int[] arr) {
        int ans = Integer.MAX_VALUE;

        return ans;
    }


    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static int[] removeIndex(int[] arr, int index) {
        int[] target = new int[arr.length - 1];
        int pos = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i != index) {
                target[pos++] = arr[i];
            }
        }
        return target;
    }
}
