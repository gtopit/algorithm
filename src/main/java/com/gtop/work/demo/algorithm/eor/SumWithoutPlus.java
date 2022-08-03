package com.gtop.work.demo.algorithm.eor;

/**
 * 求两个整数之和，不能使用 + 操作符
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/10/22 13:45
 */
public class SumWithoutPlus {

    public static void main(String[] args) {

        System.out.println(sum(3, 7));

    }

    private static int sum(int a, int b) {

        int needAdd;

        // 以a为基准，循环b，进行一次异或，进位在下一次判断进入循环后累加
        while (b != 0) {

            // 按位与得到需要的进位
            needAdd = a & b;
            // 异或为无进位相加
            a = a ^ b;

            // 此时将需要的进位右移一位，若不为空，则在下次循环时候经过异或运算累加到a
            b = needAdd << 1;

        }

        return a;

    }

}
