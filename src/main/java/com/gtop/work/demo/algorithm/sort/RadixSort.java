package com.gtop.work.demo.algorithm.sort;

/**
 * 基数排序
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/11/7 19:43
 */
public class RadixSort {

    public static void main(String[] args) {

        int[] arr = new int[]{123, 45, 235, 4567, 356, 123, 12, 19, 16, 16, 123, 768855, 21333, 45734, 56234, 10};
        sort(arr);

        for (int i = 0; i < arr.length; i++) {
            System.out.printf(arr[i] + " ");
        }
        System.out.println();
    }

    public static void sort(int[] arr) {
        int digit = getMaxBits(arr);
        sort(arr, 0, arr.length - 1, digit);
    }

    private static int getMaxBits(int[] arr) {

        int maxOr = 0;
        for (int i = 0; i < arr.length; i++) {
            maxOr |= arr[i];
        }
        return String.valueOf(maxOr).length();
    }

    public static void sort(int[] arr, int left, int right, int digit) {


        int[] help = new int[right - left + 1];
        for (int i = 0; i < digit; i++) {

            int[] count = new int[10];
            int[] countSum = new int[10];

            for (int j = left; j <= right; j++) {
                int bitNum = getBitNum(arr[j], i);
                count[bitNum]++;
            }

            countSum[0] = count[0];
            for (int j = 1; j < count.length; j++) {
                countSum[j] = countSum[j - 1] + count[j];
            }

            for (int j = right; j >= left; j--) {
                int bitNum = getBitNum(arr[j], i);
                int rightIndex = --countSum[bitNum];
                help[rightIndex] = arr[j];
            }

            int index = 0;
            for (int j = left; j <= right; j++) {
                arr[j] = help[index++];
            }

        }
    }

    private static int getBitNum(int num, int bit) {
        for (int i = 0; i < bit; i++) {
            num /= 10;
        }
        return num % 10;
    }

}
