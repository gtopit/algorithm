package com.gtop.work.demo.algorithm.heap;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 用加强堆实现缓存页功能（LRU算法）
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/11/24 8:49
 */
public class PageCache {

    public static void main(String[] args) throws InterruptedException {
        PageCache pageCache = new PageCache(3);
        PageInfo page1 = new PageInfo("A");
        pageCache.visit(page1);
        Thread.sleep(100);
        PageInfo page2 = new PageInfo("B");
        pageCache.visit(page2);
        Thread.sleep(100);
        PageInfo page3 = new PageInfo("C");
        pageCache.visit(page3);
        Thread.sleep(100);
        PageInfo page4 = new PageInfo("D");
        pageCache.visit(page4);
        Thread.sleep(100);
        pageCache.visit(page3);

        List<PageInfo> allElements = pageCache.cache.getAllElements();

        for (PageInfo allElement : allElements) {
            System.out.println(allElement.timestamp + " " + allElement.data);
        }

    }

    private MyHeap<PageInfo> cache;
    private int size;

    public PageCache(int pageSize) {
        cache = new MyHeap<>((o1, o2) -> Long.valueOf((o1.timestamp - o2.timestamp)).intValue());
        size = pageSize;
    }

    public void visit(PageInfo page) {

        if (cache.isExits(page)) {
            page.timestamp = System.currentTimeMillis();
            cache.resign(page);
        } else {
            cache.add(page);
        }
        while (cache.size > size) {
            cache.poll();
        }
    }

    private static class PageInfo {
        long timestamp;
        String data;

        PageInfo(String data) {
            timestamp = System.currentTimeMillis();
            this.data = data;
        }
    }

    private static class MyHeap<E> {
        private int size;
        private List<E> heap;
        private Map<E, Integer> indexMap;
        private Comparator<? super E> comparator;

        public MyHeap(Comparator<? super E> comparator) {
            size = 0;
            heap = new ArrayList<>();
            indexMap = new HashMap<>();
            this.comparator = comparator;
        }

        boolean isExits(E e) {
            return null != indexMap.get(e);
        }

        List<E> getAllElements() {
            List<E> targetList = new ArrayList<>();

            for (E e : heap) {
                targetList.add(e);
            }

            return targetList.stream().sorted(comparator).collect(Collectors.toList());

        }

        void add(E e) {
            indexMap.put(e, size);
            heap.add(e);
            heapsert(size++);
        }

        int heapsert(int index) {
            int parentIndex;
            while (comparator.compare(heap.get(index), heap.get(parentIndex = (index - 1) / 2)) < 0) {
                swap(index, parentIndex);
                index = parentIndex;
            }
            return index;
        }

        void poll() {
            if (size > 0) {
                indexMap.remove(heap.get(0));
                heap.set(0, heap.get(size - 1));
                heap.remove(--size);
                heapify(0);
            }
        }

        void resign(E e) {
            Integer index = indexMap.get(e);
            index = heapsert(index);
            index = heapify(index);
            indexMap.put(e, index);
        }

        int heapify(int index) {
            int bestIndex = index * 2 + 1;
            while (bestIndex < size) {
                if (bestIndex + 1 < size) {
                    bestIndex = comparator.compare(heap.get(bestIndex), heap.get(bestIndex + 1)) < 0 ? bestIndex : bestIndex + 1;
                }
                bestIndex = comparator.compare(heap.get(index), heap.get(bestIndex)) < 0 ? index : bestIndex;
                if (bestIndex == index) {
                    break;
                }
                swap(bestIndex, index);
                index = bestIndex;
                bestIndex = index * 2 + 1;
            }
            return index;
        }

        private void swap(int curIndex, int parentIndex) {
            E cur = heap.get(curIndex);
            E parent = heap.get(parentIndex);
            indexMap.put(cur, parentIndex);
            indexMap.put(parent, curIndex);
            heap.set(curIndex, parent);
            heap.set(parentIndex, cur);
        }
    }

}
