package com.gtop.work.demo.algorithm.kmp;

/**
 * KMP算法
 * 比较str1是否包含str2
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/12/15 18:25
 */
public class KMP {

    public static void main(String[] args) {

        String str1 = "abaabdaaacdbsbcdabcdzybcdabcdaxkdseetsacdsdsasdvvdcsdaacdb";
        String str2 = "bcdabcdzybcdabcdax";
        System.out.println(findFirst(str1, str2));
    }

    /**
     * 查找str1是否包含str2
     *
     * @param str1
     * @param str2
     * @return 返回第一次匹配的首位置，找不到返回-1
     */
    public static int findFirst(String str1, String str2) {

        if (str1 == null || str2 == null || str2.length() < 1 || str1.length() < str2.length()) {
            return -1;
        }

        // 记录了str2每个字符最长前缀和后缀相等时候的下标
        int[] nexts = nextArray(str2);

        char[] arr1 = str1.toCharArray();
        char[] arr2 = str2.toCharArray();
        int i1 = 0;
        int i2 = 0;
        while (i1 < arr1.length && i2 < arr2.length) {
            if (arr1[i1] == arr2[i2]) {
                // 字符串中字符一样，同时++
                i1++;
                i2++;
            } else if (nexts[i2] < 0) {
                // next数组来到第一个了，还不相等，那只能源字符串+1进行比较了
                i1++;
            } else {
                // 看前一个最大前后缀能不能匹配上，也就是从0到i2-1是否和源字符串的0到i1-1个字符是否相等
                // 因此从i1 和 i2开始比较
                i2 = nexts[i2];
            }
        }

        return i2 == arr2.length ? i1 - i2 : -1;
    }

    /**
     * next数组包含了每个位置字符 前缀子串等于后缀子串的最大长度，eg.,
     * a b c d a b c k:
     * 0 1 2 3 4 5 6 7
     * 0位置规定为-1
     * 1位置规定为0
     * 2位置由于a != b，因此为0
     * 3位置 a != c，ab != bc，因此为0
     * 4位置 a != d，ab != cd，abc != bcd，因此为0
     * 5位置 a == a，ab != da，abc != cda，abcd != bcda，因此为1
     * 6位置 a != b，ab == ab，abc != dab，abcd != cdab，abcda != bcdab，因此为2
     * 7位置 a != c，ab != bc，abc == abc，abcd != dbac，abcda != cdabc，abcdab != bcdabc，因此为3
     *
     * @param str2
     * @return
     */
    private static int[] nextArray(String str2) {

        char[] array = str2.toCharArray();
        int[] nexts = new int[array.length];
        nexts[0] = -1;
        int cn;
        for (int i = 2; i < nexts.length; i++) {
            cn = nexts[i - 1];
            while (cn > -1) {
                if (array[cn] == array[i - 1]) {
                    nexts[i] = cn + 1;
                    break;
                } else {
                    cn = nexts[cn];
                }
            }

        }
        return nexts;
    }

}
