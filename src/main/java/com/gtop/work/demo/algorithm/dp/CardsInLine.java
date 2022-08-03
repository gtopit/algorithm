package com.gtop.work.demo.algorithm.dp;

/**
 * 给定一个整形数组，代表数值不同的纸牌排成一条线
 * 玩家A和玩家B依次拿走每张纸牌
 * 规定玩家A先拿，玩家B后拿
 * 但是每个玩家每次只能拿走最左或最右变得纸牌
 * 玩家A和玩家B都绝顶聪明
 * 请返回最后获胜者的分数
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/11/23 21:37
 */
public class CardsInLine {

    public static void main(String[] args) {

        int[] arr = new int[]{20, 30, 40, 30, 50, 40};
        int way1 = way1(arr);
        System.out.println(way1);
        int way2 = way2(arr);
        System.out.println(way2);

    }

    /**
     * 动态规划
     *
     * @param arr
     * @return
     */
    public static int way2(int[] arr) {

        int n = arr.length;

        int[][] fdp = new int[n][n];
        int[][] sdp = new int[n][n];

        for (int i = 0; i < n; i++) {
            fdp[i][i] = arr[i];
            sdp[i][i] = 0;
        }

        for (int start = 0; start < n; start++) {

            int left = 0;
            for (int right = start + 1; right < n; right++) {
                int p1 = arr[left] + sdp[left + 1][right];
                int p2 = arr[right] + sdp[left][right - 1];
                fdp[left][right] = Math.max(p1, p2);

                int p3 = fdp[left + 1][right];
                int p4 = fdp[left][right - 1];

                sdp[left][right] = Math.min(p3, p4);
                left++;
            }
        }

        return fdp[0][n - 1];

    }

    /**
     * 递归方式
     *
     * @param arr
     * @return
     */
    public static int way1(int[] arr) {
        return first1(arr, 0, arr.length - 1);
    }

    /**
     * 先手拿牌
     *
     * @param arr   纸牌数值数组
     * @param left  拿左边的
     * @param right 拿右边的
     * @return
     */
    private static int first1(int[] arr, int left, int right) {

        if (left == right) {
            return arr[left];
        }

        int p1 = arr[left] + second1(arr, left + 1, right);
        int p2 = arr[right] + second1(arr, left, right - 1);

        return Math.max(p1, p2);

    }

    /**
     * 后手拿牌
     *
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private static int second1(int[] arr, int left, int right) {

        // 如果只剩下一张牌，会被先手拿走
        if (left == right) {
            return 0;
        }

        // 以后手的姿态在先手拿完剩余的牌中尽力拿最大值
        int p1 = first1(arr, left + 1, right);
        int p2 = first1(arr, left, right - 1);

        // 但是无论怎么拿，得到的只能是被动接受先手剩余的最小的一种情况
        return Math.min(p1, p2);

    }

}
