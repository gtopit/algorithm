package com.gtop.work.demo.algorithm.training.b017;

import org.junit.Test;

/**
 * 有效括号判断 eg.,
 * 有效： (()())
 * 无效： ())()(
 * 问题1：判断字符串中括号是否有效
 * 问题2：如果字符串括号无效，返回至少填几个字符能让其整体有效
 * 问题3：求字符串中嵌套最多层的括号
 * @author hongzw@citycloud.com.cn
 * @Date 2022/2/21 18:14
 */
public class ValidateParentheses {

    @Test
    public void test1() {
        String str = "()()";
        System.out.println(question1(str));
        str = "(()))(";
        System.out.println(question1(str));
    }

    @Test
    public void test2() {
        String str = "()()";
        System.out.println(question2(str));
        str = "(()))(";
        System.out.println(question2(str));
    }

    @Test
    public void test3() {
        String str = "((()))()((((()))))";
        System.out.println(question3(str));
    }

    public boolean question1(String str) {
        char[] chars = str.toCharArray();
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            if ('(' == chars[i]) {
                count++;
            } else if (')' == chars[i]) {
                count--;
            }
            if (count < 0) {
                return false;
            }
        }
        return count == 0;
    }

    public int question2(String str) {

        char[] chars = str.toCharArray();
        int right = 0;
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            if ('(' == chars[i]) {
                count++;
            } else if (')' == chars[i]) {
                count--;
            }
            // 如果出现右括号了，那么怎么也消不掉了，因此需要添加对应的左括号来抵消，right++表示的就是需要的左括号
            // 此时重新计算，因此count=0
            if (count < 0) {
                right++;
                count = 0;
            }
        }
        // 最后，如果count > 0，说明原字符串有count个抵消不了的左括号，
        // 加上需要抵消的右括号
        return right + count;

    }

    public int question3(String str) {
        char[] chars = str.toCharArray();
        int max = 0;
        int count = 0;
        for (char aChar : chars) {
            if ('(' == aChar) {
                count++;
            } else if (')' == aChar) {
                max = Math.max(max, count);
                count--;
            }
            if (count < 0) {
                count = 0;
            }
        }
        return max;
    }

}
