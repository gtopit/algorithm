package com.gtop.work.demo.algorithm.sort;

/**
 * 合并排序
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/10/23 10:11
 */
public class MergeSort {

    public static void main(String[] args) {

        /*int[] arr = new int[] {1, 5, 4, 6, 7, 2, 8, 3, 9};
        int[] arr2 = new int[] {1, 5, 4, 6, 7, 2, 8, 3, 9};

        recurseSort(arr);
        iterateSort(arr2);

        for (int i : arr) {
            System.out.print(i);
        }

        System.out.println();

        for (int i : arr2) {
            System.out.print(i);
        }*/

        System.out.println("----------------------");

        int[] arr3 = new int[]{6, 5, 3, 1, 2, 3, 8, 9, 4, 4, 6, 0, 9, 1, 2};
        for (int i : arr3) {
            System.out.print(i + " ");
        }
        System.out.println();
        mergeSort2(arr3);

    }

    /**
     * 递归排序
     *
     * @param arr
     */
    private static void recurseSort(int[] arr) {

        if (null == arr || arr.length < 2) {
            return;
        }

        recurseProcess(arr, 0, arr.length - 1);

    }

    private static void recurseProcess(int[] arr, int left, int right) {

        if (left >= right) {
            return;
        }

        int mid = left + ((right - left) >> 1);

        recurseProcess(arr, left, mid);

        recurseProcess(arr, mid + 1, right);

        merge(arr, left, mid, right);

    }

    private static void merge(int[] arr, int left, int mid, int right) {

        int pLeft = left;
        int pRight = mid + 1;

        int[] help = new int[right - left + 1];
        int i = 0;


        while ((pLeft <= mid) && (pRight <= right)) {
            help[i++] = arr[pLeft] < arr[pRight] ? arr[pLeft++] : arr[pRight++];
        }

        while (pLeft <= mid) {
            help[i++] = arr[pLeft++];
        }

        while (pRight <= right) {
            help[i++] = arr[pRight++];
        }

        i = left;
        for (int e : help) {
            arr[i++] = e;
        }

    }

    /**
     * 递归排序可用迭代方式实现
     *
     * @param arr
     */
    private static void iterateSort(int[] arr) {

        int stepSize = 1;
        int length = arr.length;
        for (int i = 0; i < length; i++) {

            if (stepSize < length) {

                int left = 0;

                while (left < length) {

                    int mid = left + stepSize - 1;
                    if (mid >= length) {
                        break;
                    }
                    int right = Math.min(mid + stepSize, length - 1);

                    merge(arr, left, mid, right);

                    left = right + 1;

                }

                if (stepSize > (Integer.MAX_VALUE >> 1)) {
                    break;
                }

                stepSize <<= 1;

            }

        }

    }

    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        int mergeSize = 1;// 当前有序的，左组长度
        while (mergeSize < N) { // log N
            int L = 0;
            // 0....
            while (L < N) {
                // L...M  左组（mergeSize）
                int M = L + mergeSize - 1;
                if (M >= N) {
                    break;
                }
                //  L...M   M+1...R(mergeSize)
                int R = Math.min(M + mergeSize, N - 1);
                merge(arr, L, M, R);
                L = R + 1;
            }

            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();

            if (mergeSize > N / 2) {
                break;
            }
            mergeSize <<= 1;
        }
    }


}
