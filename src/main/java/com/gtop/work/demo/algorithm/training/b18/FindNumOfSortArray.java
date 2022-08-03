package com.gtop.work.demo.algorithm.training.b18;

import org.junit.Test;

/**
 * 在行列都有序的二维数组中，找num，找到返回true，否则返回false
 * @author hongzw@citycloud.com.cn
 * @Date 2022/2/23 18:58
 */
public class FindNumOfSortArray {

    @Test
    public void test() {
        int[][] arr = new int[][] {{1,2,3}, {4,5,6}, {7,8,9}};
        System.out.println(findNum(arr, 10));
    }

    public boolean findNum(int[][] arr, int num) {
        int n = arr.length;
        int m = arr[0].length;
        int i = 0;
        int j = m - 1;
        while (i < n && j >= 0) {
            if (arr[i][j] == num) {
                return true;
            }
            if (arr[i][j] < num) {
                i++;
            } else {
                j--;
            }
        }
        return false;
    }

}
