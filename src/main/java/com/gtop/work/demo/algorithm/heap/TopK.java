package com.gtop.work.demo.algorithm.heap;

import java.util.*;

/**
 * 实时出现的单词topK问题
 * 要求实时统计出现频率最多的10个单词
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/10/31 15:06
 */
public class TopK {

    private int k;

    private Map<Word, Integer> wordMap;

    private GreaterHeap<Word> topKHeap;


    public static void main(String[] args) {

        TopK topK = new TopK(5);

        Scanner scanner = new Scanner(System.in);

        String word;
        while (!(word = scanner.nextLine()).equals("GG")) {
            Word w = new Word();
            w.name = word;
            w.timestamp = System.currentTimeMillis();
            topK.doing(w);
        }

        List<Word> topK1 = topK.getTopK();

        for (Word word1 : topK1) {
            System.out.println(word1);
        }

    }

    public void doing(Word word) {

        Integer exitsWordCount;
        if (null != (exitsWordCount = wordMap.get(word))) {
            // 当单词存在时
            wordMap.put(word, ++exitsWordCount);
            word.count = exitsWordCount;
            if (topKHeap.isExits(word)) {
                topKHeap.resign(word);
            } else {
                Word peek = topKHeap.peek();
                if (word.count > peek.count) {
                    topKHeap.poll();
                    topKHeap.offer(word);
                }
            }

        } else {
            wordMap.put(word, 1);
            if (topKHeap.size() < k) {
                word.count = 1;
                topKHeap.offer(word);
            }
        }

    }

    public List<Word> getTopK() {

        return topKHeap.getAllElements();

    }

    public TopK(int k) {

        this.k = k;
        wordMap = new HashMap<>();
        topKHeap = new GreaterHeap<>((o1, o2) -> o1.count != o2.count ? (o2.count - o1.count) : (Long.valueOf(o2.timestamp - o1.timestamp).intValue()));

    }

    private static class Word {

        private String name;

        private int count;

        private long timestamp;

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Word word = (Word) o;
            return Objects.equals(name, word.name);
        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }

        @Override
        public String toString() {
            return "Word{" +
                    "name='" + name + '\'' +
                    ", count=" + count +
                    ", timestamp=" + timestamp +
                    '}';
        }
    }


}
