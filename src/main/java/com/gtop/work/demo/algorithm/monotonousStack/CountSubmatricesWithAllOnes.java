package com.gtop.work.demo.algorithm.monotonousStack;

/**
 * 给定一个二维数组matrix，其中值不是1就是0，
 * 返回全部由1组成的子矩阵数量
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/12/7 20:07
 */
public class CountSubmatricesWithAllOnes {

    public static void main(String[] args) {
        int[][] arr = new int[][]{{1, 0, 1}, {1, 1, 0}, {1, 1, 0}};
        System.out.println(way1(arr));
    }

    /**
     * [
     * [1, 1, 0, 1],
     * [1, 1, 1, 0],
     * [1, 1, 1, 0]
     * ]
     * 思路：一次处理一行数据，但是处理前先累加上一行的数据
     * 例如：上一行为[1, 1, 0, 1]，那么此行数据累加后为[2, 2, 1, 0]，下一行累加为[3, 3, 2, 0]
     * 这样就可以使用单调栈的思路求解
     * 每一行得到当前元素，当大于或等于前一个栈里的元素时出栈，
     * 但是出栈元素只有比当前入栈元素大时才处理，否则交给相等情况下最后一个处理，这样避免重复计算
     * 只处理当前元素及往下到起左右第一个比自己小的元素中较大一个为止
     * 例如 [3, 3, 2, 0]中的第一个元素3，则其左边元素没有，设置为0，右边第一个比自己小的元素为2，则自己只处理到右边比左边大，
     * 因此只处理到3-2这么多，后面更低层的会由后面底层的处理
     *
     * @param arr
     * @return
     */
    public static int way1(int[][] arr) {
        if (arr == null || arr.length == 0 || arr[0].length == 0) {
            return 0;
        }
        int n = arr.length;
        int m = arr[0].length;
        int[] cur = new int[m];
        int[] stack = new int[m];
        int si = -1;
        int outVal;
        int left;
        int down;
        int k;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                cur[j] = arr[i][j] == 0 ? 0 : cur[j] + 1;
            }
            for (int j = 0; j < m; j++) {
                while (si != -1 && cur[stack[si]] >= cur[j]) {
                    outVal = cur[stack[si--]];
                    // 相同大小留到最后一次处理
                    if (outVal > cur[j]) {
                        left = si != -1 ? stack[si] : -1;
                        k = j - left - 1;
                        down = Math.max(si != -1 ? cur[stack[si]] : 0, cur[j]);
                        // 处理左边和右边中值比较大的那个那层开始到当前层高度
                        count += (outVal - down) * (1 + k) * k / 2;
                    }
                }
                stack[++si] = j;
            }

            while (si != -1) {
                outVal = cur[stack[si--]];
                left = si != -1 ? stack[si] : -1;
                k = m - left - 1;
                down = si != -1 ? cur[stack[si]] : 0;
                count += (outVal - down) * (1 + k) * k / 2;
            }

        }
        return count;
    }
}
