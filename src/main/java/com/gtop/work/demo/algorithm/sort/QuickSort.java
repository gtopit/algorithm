package com.gtop.work.demo.algorithm.sort;

import java.util.Map;

/**
 * 快排
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/11/4 20:38
 */
public class QuickSort {

    /**
     * 要求：将数组分成两部分， <= X 的放左边，> X 的放右边
     * 实现思路：
     * 规划出一个区域，起始位置为lessEqual=-1，
     * 0、以当前数组的最右一个数作为X
     * 1、如当前数<= X ，当前数[index]和区域lessEqual的下一个位置交换，区域往右+1，指向当前数位置index++
     * 2、如果当前数> X，指向当前数位置index++
     * 3、当前数index > 数组长度退出
     * 4、将数组最后一个数和lessEqual交换
     *
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private static int partition(int[] arr, int left, int right) {

        int lessEqual = left - 1;
        int index = left;
        while (index < right) {
            if (arr[index] <= arr[right]) {
                swap(arr, ++lessEqual, index);
            }
            index++;
        }
        swap(arr, ++lessEqual, right);
        return lessEqual;
    }

    /**
     * 荷兰国旗问题，要求：将数组分成三部分， < X 的放左边，== X 的放中间，> X 的放右边
     * 实现思路：
     * 规划出两个区域，小于区域S和大于区域B
     * 0、以当前数组的最右一个数作为X
     * 1、如果当前数< X， 当前数和S区域下一个位置交换，区域往右+1，指向当前数位置index++
     * 2、如果当前数== X，指向当前数位置index++
     * 3、如果当前数> X，当前数和B区前一个位置交换，区域往左+1，当前数位置index不动
     * 4、index == B区域边界下标退出
     * 5、将数组最后一个数和B区最左一个数交换
     *
     * @param arr
     * @param left
     * @param right
     * @return
     */
    public static int[] netherlandsFlag(int[] arr, int left, int right) {

        if (left > right) {
            return new int[]{-1, -1};
        }
        if (left == right) {
            return new int[]{left, right};
        }

        int smallIndex = left - 1;
        int bigIndex = right;
        int index = left;
        while (index < bigIndex) {
            if (arr[index] < arr[right]) {
                swap(arr, ++smallIndex, index++);
            } else if (arr[index] > arr[right]) {
                swap(arr, index, --bigIndex);
            } else {
                index++;
            }
        }

        swap(arr, bigIndex, right);
        return new int[]{smallIndex + 1, bigIndex};
    }

    /**
     * V1版本一次只搞定一个数（借助partition）
     *
     * @param arr
     */
    public static void quickSortV1(int[] arr) {
        processV1(arr, 0, arr.length - 1);
    }

    private static void processV1(int[] arr, int left, int right) {

        if (left >= right) {
            return;
        }

        int ready = partition(arr, left, right);
        processV1(arr, left, ready - 1);
        processV1(arr, ready + 1, right);

    }

    /**
     * V2版本一次搞定一批数（借助netherlandsFlag）
     *
     * @param arr
     */
    public static void quickSortV2(int[] arr) {

        processV2(arr, 0, arr.length - 1);

    }

    private static void processV2(int[] arr, int left, int right) {

        if (left >= right) {
            return;
        }

        int[] equalArea = netherlandsFlag(arr, left, right);

        processV2(arr, left, equalArea[0] - 1);
        processV2(arr, equalArea[1] + 1, right);

    }

    /**
     * V3版本在V2基础上加入随机数，经数学证明，其时间复杂度收敛于O(N*logN)
     *
     * @param arr
     */
    public static void quickSortV3(int[] arr) {
        processV3(arr, 0, arr.length - 1);
    }

    private static void processV3(int[] arr, int left, int right) {

        if (left >= right) {
            return;
        }

        swap(arr, (int) (left + Math.random() * (right - left + 1)), right);
        int[] equalArea = netherlandsFlag(arr, left, right);
        processV3(arr, left, equalArea[0] - 1);
        processV3(arr, equalArea[1] + 1, right);

    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {

        int[] arr = new int[]{1, 2, 5, 4, 3, 67, 98, 5, 79, 4, 2, 7, 1, 2, 34, 4};

        // quickSortV1(arr);
        // quickSortV2(arr);
        quickSortV3(arr);

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

}
