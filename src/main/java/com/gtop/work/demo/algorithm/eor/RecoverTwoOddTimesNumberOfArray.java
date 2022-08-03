package com.gtop.work.demo.algorithm.eor;

/**
 * 一个数组中有两种数（a和b）出现了奇数次，其他数都出现了偶数次，找出这两种数并打印
 * 要求空间复杂度O(1)
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/10/22 8:57
 */
public class RecoverTwoOddTimesNumberOfArray {

    public static void main(String[] args) {

        int[] arr = randomArray(3);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ",");
        }
        System.out.println();
        int[] two = findAndRecoverTwo(arr);
        System.out.println(two[0] + ", " + two[1]);

    }

    public static int[] findAndRecoverTwo(int[] arr) {

        if (null == arr || arr.length < 4) {
            return arr;
        }

        int eor = 0;
        int _eor = 0;
        int a = 0;
        int b = 0;

        // for循环结束后，eor结果为a^b
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }

        // 最右边的数为1，其他位为0，这样做的目的是，因为a^b不可能为空，因此_eor不可能为空，
        // 而得到的_eor用于判断，要么a的最右边的数为1，要么b的最右边的数为1
        _eor = eor & (-eor);

        // 提取出a
        for (int i = 0; i < arr.length; i++) {
            if ((_eor & arr[i]) > 0) {
                a ^= arr[i];
            }
        }

        b = eor ^ a;

        return new int[]{a, b};

    }

    private static int[] randomArray(int oddTimes) {

        if (oddTimes < 1) {
            throw new RuntimeException("请输入两种数出现的奇数次数");
        }

        int evenTimes = (int) (Math.random() * 10);

        int totalOddTime = oddTimes << 1;

        int totalEventTime = evenTimes << 1;

        int length = totalOddTime + totalEventTime;

        int[] arr = new int[length];

        int a = (int) (Math.random() * 10) + 1;
        int b = (int) (Math.random() * 10) + 11;

        for (int i = 0; i < oddTimes; i++) {
            arr[i] = a;
        }

        for (int i = oddTimes; i < totalOddTime; i++) {
            arr[i] = b;
        }

        int[] subArray = subArray(evenTimes);

        for (int i = 0; i < evenTimes; i++) {
            arr[i + totalOddTime] = subArray[i];
        }

        for (int i = 0; i < evenTimes; i++) {
            arr[i + totalOddTime + evenTimes] = subArray[i];
        }

        /*for (int i = 0; i < length; i++) {

            int index = (int ) (Math.random() * length);

            arr[index] = arr[index] ^ arr[i];
            arr[i] = arr[index] ^ arr[i];
            arr[index] = arr[index] ^ arr[i];

        }*/

        return arr;

    }

    private static int[] subArray(int len) {

        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * 10) + 21;
        }

        return arr;

    }

}
