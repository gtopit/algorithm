package com.gtop.work.demo.algorithm.sort;

/**
 * 计算最大乘积
 * 给定一个元素类型为小写字符串额数组，请计算两个没有相同字符的元素长度乘积的最大值
 * 如果没有符合条件的两个元素，则返回0
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/10/27 12:27
 */
public class MaximumProduct {

    public static void main(String[] args) {

        String[] arr = randomArray(100000);
        //String[] arr = new String[] {"a", "bce", "ef", "gh"};

        long begin = System.currentTimeMillis();
        int max = mergeFindMaxLen(arr, 0, 0, arr.length - 1);
        long end = System.currentTimeMillis();
        System.out.println("合并算法时间 = " + (end - begin));

        begin = System.currentTimeMillis();
        int max2 = findMaxLen(arr);
        end = System.currentTimeMillis();
        System.out.println("冒泡算法时间 = " + (end - begin));

        System.out.println(max + "  " + max2);

    }

    private static String[] randomArray(int len) {

        String[] arr = new String[len];

        String[] template = new String[]{"bacdd", "id", "et", "oo", "putr", "pyueopsid", "bwl", "irebfca", "iusdjkgadslkasd", "ghr"};
        int length = template.length;
        for (int i = 0; i < len; i++) {
            int randomIndex = (int) (Math.random() * length);
            arr[i] = template[randomIndex];
        }

        return arr;
    }

    /**
     * 算法复杂度 O(nlogn)
     *
     * @param arr
     * @param maxLen
     * @param left
     * @param right
     * @return
     */
    public static int mergeFindMaxLen(String[] arr, int maxLen, int left, int right) {

        if (left == right) {
            return maxLen;
        }

        int mid = left + ((right - left) >> 1);

        maxLen = Math.max(mergeFindMaxLen(arr, maxLen, left, mid), mergeFindMaxLen(arr, maxLen, mid + 1, right));

        return mergeCompare(arr, maxLen, left, mid, right);
    }

    private static int mergeCompare(String[] arr, int changeableMaxLen, int left, int mid, int right) {

        int pLeft = left;
        int pRight = mid + 1;
        int innerMaxLen;
        while ((pLeft <= mid) && pRight <= right) {

            if (!hasSameElement(arr[pLeft], arr[pRight])) {
                innerMaxLen = arr[pLeft].length() * arr[pRight++].length();
                if (changeableMaxLen < innerMaxLen) {
                    changeableMaxLen = innerMaxLen;
                }
            } else {
                pLeft++;
            }

        }

        while (pLeft <= mid) {
            if (!hasSameElement(arr[pLeft++], arr[pRight - 1])) {
                innerMaxLen = arr[pLeft - 1].length() * arr[pRight - 1].length();
                if (changeableMaxLen < innerMaxLen) {
                    changeableMaxLen = innerMaxLen;
                }
            }
        }

        while (pRight <= right) {
            if (!hasSameElement(arr[pLeft - 1], arr[pRight++])) {
                innerMaxLen = arr[pLeft - 1].length() * arr[pRight - 1].length();
                if (changeableMaxLen < innerMaxLen) {
                    changeableMaxLen = innerMaxLen;
                }
            }
        }

        return changeableMaxLen;

    }

    /**
     * 算法复杂度O(n^2)
     *
     * @param arr
     * @return
     */
    public static int findMaxLen(String[] arr) {

        int length = arr.length;
        int changeableMaxLen = 0;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if (!hasSameElement(arr[i], arr[j])) {
                    int tempLen = arr[i].length() * arr[j].length();
                    if (changeableMaxLen < tempLen) {
                        changeableMaxLen = tempLen;
                    }
                }
            }
        }

        return changeableMaxLen;

    }

    private static boolean hasSameElement(String str1, String str2) {
        char[] charArray = str2.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (str1.indexOf(charArray[i]) > -1) {
                return true;
            }
        }

        return false;
    }

}
