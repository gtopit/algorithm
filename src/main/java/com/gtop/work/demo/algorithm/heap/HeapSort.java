package com.gtop.work.demo.algorithm.heap;

/**
 * 堆排序
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/10/31 12:43
 */
public class HeapSort {

    public static void main(String[] args) {

        int[] arr = new int[]{1, 3, 2, 5, 4, 3, 6, 5, 5, 5, 3, 9, 8, 12, 45, 78, 7, 6, 76, 8, 8, 8, 4, 3, 4, 9, 0, 21, 1, 3, 18, 16, 16};

        /*for (int i = 0; i < arr.length; i++) {
            heapsert(arr, i);
        }*/
        // O(n)复杂度
        // 计算 1/2*n*1 + 1/4*n*2 + 1/8*n*3 + 1/16*n*4 ... (A)
        //  (A) * 2 - (A)  = n + n/2 + n/4 + n/8 + n/16 ... 收敛于O(n)
        for (int i = arr.length / 2; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }

        System.out.println();

        int heapSize = arr.length;
        while (heapSize > 0) {
            swap(arr, 0, --heapSize);
            heapify(arr, 0, heapSize);
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }

    }

    public static void heapsert(int[] arr, int index) {

        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }

    }

    public static void heapify(int[] arr, int index, int heapSize) {

        int left = index * 2 + 1;

        while (left < heapSize) {
            int largestIndex = ((left + 1) < heapSize) && (arr[left + 1] > arr[left]) ? left + 1 : left;
            if (arr[largestIndex] <= arr[index]) {
                break;
            }
            swap(arr, index, largestIndex);
            index = largestIndex;
            left = index * 2 + 1;
        }

    }

    private static void swap(int[] arr, int index, int bestIndex) {
        int temp = arr[index];
        arr[index] = arr[bestIndex];
        arr[bestIndex] = temp;
    }

}
