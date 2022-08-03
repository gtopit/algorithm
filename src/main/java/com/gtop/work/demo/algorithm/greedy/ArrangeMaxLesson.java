package com.gtop.work.demo.algorithm.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author hongzw@citycloud.com.cn
 * @Date 2021/12/30 15:12
 */
public class ArrangeMaxLesson {

    public static void main(String[] args) {
        for (int i = 0; i < 1000_000; i++) {
            Lesson[] lessons = generatePrograms(12, 20);
            if (getMaxLesson(lessons) != getMaxLesson2(lessons)) {
                for (int j = 0; j < lessons.length; j++) {
                    System.out.printf("%d ", lessons[j]);
                }
                System.out.println();
            }
        }

    }

    public static int getMaxLesson2(Lesson[] lessons) {
        if (lessons == null || lessons.length == 0) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < lessons.length; i++) {
            ans = Math.max(ans, process(lessons, lessons[i].end));
        }
        return ans + 1;
    }

    private static int process(Lesson[] lessons, int timeLine) {
        int ans = 0;
        for (int i = 0; i < lessons.length; i++) {
            if (lessons[i].begin >= timeLine) {
                ans++;
                timeLine = lessons[i].end;
            }
        }
        return ans;
    }

    public static int getMaxLesson(Lesson[] lessons) {
        if (lessons == null || lessons.length == 0) {
            return 0;
        }
        int ans = 0;
        int timeLine = 0;
        Arrays.sort(lessons, Comparator.comparingInt(a -> a.end));
        for (int i = 0; i < lessons.length; i++) {
            if (lessons[i].begin >= timeLine) {
                ans++;
                timeLine = lessons[i].end;
            }
        }
        return ans;
    }

    static class Lesson {
        int begin;
        int end;

        public Lesson(int begin, int end) {
            this.begin = begin;
            this.end = end;
        }
    }

    public static Lesson[] generatePrograms(int programSize, int timeMax) {
        Lesson[] ans = new Lesson[(int) (Math.random() * (programSize + 1))];
        for (int i = 0; i < ans.length; i++) {
            int r1 = (int) (Math.random() * (timeMax + 1));
            int r2 = (int) (Math.random() * (timeMax + 1));
            if (r1 == r2) {
                ans[i] = new Lesson(r1, r1 + 1);
            } else {
                ans[i] = new Lesson(Math.min(r1, r2), Math.max(r1, r2));
            }
        }
        return ans;
    }

}
