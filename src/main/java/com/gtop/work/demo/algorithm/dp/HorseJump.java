package com.gtop.work.demo.algorithm.dp;

/**
 * 跳马问题
 * 象棋盘（10行9列）
 * 马一开始在(0,0)，求k步之后来到(x,y)位置的所有方式之和（马走日）
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/12/1 10:13
 */
public class HorseJump {

    public static void main(String[] args) {

        System.out.println(way1(13, 9, 8));
        System.out.println(way2(13, 9, 8));

    }

    public static int way1(int k, int x, int y) {

        return process1(k, x, y);

    }

    /**
     * (x,y)必须在(0,0)-(10,9)范围内
     *
     * @param restK
     * @param nextX
     * @param nextY
     * @return
     */
    private static int process1(int restK, int nextX, int nextY) {
        if (nextX < 0 || nextX > 9 || nextY < 0 || nextY > 8) {
            return 0;
        }
        if (restK == 0) {
            return 0 == nextX && 0 == nextY ? 1 : 0;
        }

        // 马下一步可以往上下左右，左上左下右上右下 8个方向跳
        int p = process1(restK - 1, nextX + 1, nextY + 2);
        p += process1(restK - 1, nextX + 2, nextY + 1);
        p += process1(restK - 1, nextX + 2, nextY - 1);
        p += process1(restK - 1, nextX + 1, nextY - 2);
        p += process1(restK - 1, nextX - 1, nextY - 2);
        p += process1(restK - 1, nextX - 2, nextY - 1);
        p += process1(restK - 1, nextX - 2, nextY + 1);
        p += process1(restK - 1, nextX - 1, nextY + 2);

        return p;

    }

    public static int way2(int k, int x, int y) {

        int[][][] dp = new int[k + 1][10][9];
        dp[0][0][0] = 1;

        for (int i = 1; i <= k; i++) {
            for (int nextX = 0; nextX < 10; nextX++) {
                for (int nextY = 0; nextY < 9; nextY++) {
                    int p = getValue(dp, i - 1, nextX + 1, nextY + 2);
                    p += getValue(dp, i - 1, nextX + 2, nextY + 1);
                    p += getValue(dp, i - 1, nextX + 2, nextY - 1);
                    p += getValue(dp, i - 1, nextX + 1, nextY - 2);
                    p += getValue(dp, i - 1, nextX - 1, nextY - 2);
                    p += getValue(dp, i - 1, nextX - 2, nextY - 1);
                    p += getValue(dp, i - 1, nextX - 2, nextY + 1);
                    p += getValue(dp, i - 1, nextX - 1, nextY + 2);
                    dp[i][nextX][nextY] = p;
                }
            }
        }

        return dp[k][x][y];

    }

    private static int getValue(int[][][] dp, int k, int x, int y) {
        if (x < 0 || x > 9 || y < 0 || y > 8) {
            return 0;
        }
        return dp[k][x][y];
    }

}
