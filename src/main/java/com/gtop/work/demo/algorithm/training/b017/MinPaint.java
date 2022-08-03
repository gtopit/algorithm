package com.gtop.work.demo.algorithm.training.b017;

import org.junit.Test;

/**
 * 给出一组有n个格子的正方形，每个格子不是红色就是绿色，
 * 现在需要将格子进行涂色，规则是，从左边开始，最后一个红色格子一定要在最前一个绿色格子的前面 ，eg:.,
 * 涂前：RGRRGGRG   涂后：RRRRGGGG  最少涂2次
 * @author hongzw@citycloud.com.cn
 * @Date 2022/2/21 20:16
 */
public class MinPaint {

    @Test
    public void test() {
        String str = "RRRGR";
        System.out.println(getMin(str));
    }

    public int getMin(String str) {
        if (str == null && str.length() == 0) {
            return 0;
        }
        char[] arr = str.toCharArray();
        int n = arr.length;
        // 统计数组中R的数量
        int rNum = 0;
        for (int i = 0; i < n; i++) {
            rNum += arr[i] == 'R' ? 1 : 0;
        }
        int ans = rNum;
        int gNum = 0;
        // 以i为分界线，枚举左边G的数量和右边R的数量，两者之和最小值即为涂抹次数
        for (int i = 0; i < n; i++) {
            gNum += arr[i] == 'G' ? 1 : 0;
            rNum -= arr[i] == 'R' ? 1 : 0;
            ans = Math.min(ans, gNum + rNum);
        }
        return ans;
    }

}
