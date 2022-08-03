package com.gtop.work.demo.algorithm.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * 数组拼接
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/10/27 17:48
 */
public class ConcatArrayTest {

    public static void main(String[] args) {
        /*Scanner scanner = new Scanner(System.in);
        int readStringSize = scanner.nextInt();
        scanner.nextLine();
        int readArrayLen = scanner.nextInt();
        scanner.nextLine();

        List<String> list = new ArrayList<>();
        while (!scanner.hasNext("#")) {
            list.add(scanner.nextLine());
        }*/

        int readStringSize = 3;
        int readArrayLen = 3;

        List<String> list = new ArrayList<>();
        list.add("1,2,4,6,0,21,1,2");
        list.add("6,5,8,90,4,5");
        list.add("1,254,6,89,2");
        list.add("1,2,3,4,5,6,7,8");

        String targetString = concatArray(list, readStringSize, readArrayLen);

        System.out.println(targetString);

    }

    public static String concatArray(List<String> list, int readStringSize, int readArrayLen) {

        validate(readStringSize, readArrayLen);

        StringBuilder targetBuilder = new StringBuilder();

        while (!list.isEmpty()) {

            processReadArray(list, targetBuilder, readStringSize, readArrayLen);

        }

        return targetBuilder.deleteCharAt(targetBuilder.lastIndexOf(",")).toString();

    }

    private static void processReadArray(List<String> list, StringBuilder targetBuilder, int readStringSize, int readArrayLen) {

        for (int i = 0; i < readArrayLen; i++) {

            int curIndex = Math.min(i, list.size() - 1);
            readStringToTargetBuilder(list, curIndex, targetBuilder, readStringSize);

        }

    }

    private static void readStringToTargetBuilder(List<String> list, int curIndex, StringBuilder targetBuilder, int readStringSize) {

        if (list.isEmpty()) {
            return;
        }

        StringBuilder curBuilder = new StringBuilder(list.get(curIndex));
        for (int i = 0; i < readStringSize; i++) {
            int valueIndex;
            if ((valueIndex = curBuilder.indexOf(",")) > -1) {
                targetBuilder.append(curBuilder.substring(0, valueIndex + 1));
                curBuilder.delete(0, valueIndex + 1);
            } else {
                if (curBuilder.length() > 0) {
                    targetBuilder.append(curBuilder).append(",");
                    curBuilder.delete(0, curBuilder.length());
                }
            }
        }
        if (curBuilder.length() > 0 && !list.isEmpty()) {
            list.remove(curIndex);
            list.add(curIndex, curBuilder.toString());
        } else {
            list.remove(curIndex);
        }

    }

    private static void validate(int readStringSize, int readArrayLen) {

        if (readStringSize <= 0 || readStringSize >= 10) {
            throw new RuntimeException("读取字符长度为0~10");
        }

        if (readArrayLen <= 0 || readArrayLen >= 1000) {
            throw new RuntimeException("读取数组个数为0~1000");
        }
    }

}
