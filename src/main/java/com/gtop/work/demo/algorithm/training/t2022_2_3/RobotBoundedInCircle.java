package com.gtop.work.demo.algorithm.training.t2022_2_3;

import org.junit.Test;

/**
 * https://leetcode.com/problems/robot-bounded-in-circle/
 * @author hongzw@citycloud.com.cn
 * @Date 2022/2/20 15:16
 */
public class RobotBoundedInCircle {

    @Test
    public void test() {
        System.out.println(isRobotBounded("RGL"));
    }

    public boolean isRobotBounded(String instructions) {
        char[] ins = instructions.toCharArray();
        // up 0      left 1     down 2     right 3
        int direction = 0;
        int x = 0, y = 0;
        for (int i = 0; i < ins.length; i++) {
            if ('L' == ins[i]) {
                // 为了确保没有超过3的方向类型出现
                direction = direction == 3 ? 0 : direction + 1;
            } else if ('R' == ins[i]) {
                // 为了确保没有负数方向类型出现
                direction = direction == 0 ? 3 : direction - 1;
            } else {
                if (direction == 0) {
                    y += 1;
                } else if (direction == 1) {
                    x -= 1;
                } else if (direction == 2) {
                    y -= 1;
                } else if (direction == 3) {
                    x += 1;
                }
            }
        }
        // 执行一次指令后，如果能回到原点或者不在原点时，但是方向和初始时不一致，经过足够多次执行同一条指令后，必定构成一个闭环轨迹图
        // 也就是执行指令后，不在原点，并且方向和初始时一致，才能越走越远。
        return (x == 0 && y == 0) || (direction != 0);
    }

}
