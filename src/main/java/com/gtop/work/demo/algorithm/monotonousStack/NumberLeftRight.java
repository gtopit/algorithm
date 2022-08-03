package com.gtop.work.demo.algorithm.monotonousStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个整形数组arr，返回每个数左边和右边距离自己最近的比自己小的数，如果没有，则返回-1
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/12/6 16:05
 */
public class NumberLeftRight {

    public static void main(String[] args) {
        int[] arr = new int[]{2, 5, 4, 1, 3};
        int[][] smallNotRepeat = getSmallNotRepeat2(arr);
        for (int i = 0; i < smallNotRepeat.length; i++) {
            System.out.printf("%d -> (%d, %d)", arr[i], smallNotRepeat[i][0] != -1 ? arr[smallNotRepeat[i][0]] : -1, smallNotRepeat[i][1] != -1 ? arr[smallNotRepeat[i][1]] : -1);
            System.out.println();
        }
        System.out.println("------------------");
        arr = new int[]{2, 5, 4, 1, 3, 2, 4, 5, 5};
        int[][] small = getSmall(arr);
        for (int i = 0; i < small.length; i++) {
            System.out.printf("%d -> (%d, %d)", arr[i], small[i][0] != -1 ? arr[small[i][0]] : -1, small[i][1] != -1 ? arr[small[i][1]] : -1);
            System.out.println();
        }
    }

    /**
     * 数组没有重复数，使用Java提供的栈
     *
     * @param arr
     * @return
     */
    public static int[][] getSmallNotRepeat(int[] arr) {

        if (null == arr || arr.length == 0) {
            return new int[0][0];
        }

        int n = arr.length;
        int[][] retArr = new int[n][2];

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                Integer out = stack.pop();
                retArr[out][0] = stack.isEmpty() ? -1 : stack.peek();
                retArr[out][1] = i;
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            Integer out = stack.pop();
            retArr[out][0] = stack.isEmpty() ? -1 : stack.peek();
            retArr[out][1] = -1;
        }

        return retArr;
    }

    /**
     * 数组没有重复数，使用数组代替Java提供的栈
     *
     * @param arr
     * @return
     */
    public static int[][] getSmallNotRepeat2(int[] arr) {

        if (null == arr || arr.length == 0) {
            return new int[0][0];
        }

        int n = arr.length;
        int[][] retArr = new int[n][2];

        int[] stack = new int[n];
        int si = -1;

        for (int i = 0; i < n; i++) {
            while (si != -1 && arr[stack[si]] > arr[i]) {
                Integer out = stack[si--];
                retArr[out][0] = si == -1 ? -1 : stack[si];
                retArr[out][1] = i;
            }
            stack[++si] = i;
        }

        while (si != -1) {
            Integer out = stack[si--];
            retArr[out][0] = si == -1 ? -1 : stack[si];
            retArr[out][1] = -1;
        }

        return retArr;
    }

    /**
     * 允许数组出现相同值
     *
     * @param arr
     * @return
     */
    public static int[][] getSmall(int[] arr) {

        int n = arr.length;
        int[][] retArr = new int[n][2];
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek().get(stack.peek().size() - 1)] > arr[i]) {
                List<Integer> outList = stack.pop();
                for (Integer out : outList) {
                    retArr[out][0] = !stack.isEmpty() ? stack.peek().get(stack.peek().size() - 1) : -1;
                    retArr[out][1] = i;
                }
            }

            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
                stack.peek().add(i);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                stack.add(list);
            }
        }

        while (!stack.isEmpty()) {
            List<Integer> outList = stack.pop();
            for (Integer out : outList) {
                retArr[out][0] = !stack.isEmpty() ? stack.peek().get(stack.peek().size() - 1) : -1;
                retArr[out][1] = -1;
            }
        }

        return retArr;
    }

}
