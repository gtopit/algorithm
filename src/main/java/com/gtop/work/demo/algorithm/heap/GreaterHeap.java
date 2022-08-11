package com.gtop.work.demo.algorithm.heap;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 加强堆（支持反向索引，动态数据调整）
 * 实现方式：
 * 堆的底层数据结构为数组链表表示的完全二叉树（满足：左子节点：i*2 + 1；右子节点：i*2 + 2；父节点：(i - 1)/2），
 * 入堆时（add或offer），在数组最后位置添加一个元素，然后进行调整，
 * 出堆时（poll），删除数组根节点（数组首位），用数组最后一个元素替换根节点，然后进行调整
 * 不支持基本数据类型，原因：反向索引使用的是map，而实际的加强堆使用的是list，
 * map对于普通数据类型是按值存储，所以相同的key值会被覆盖，
 * 而list可存重复的值，这本身就存在矛盾，
 * 这可以解决，如在存入元素时对数据类型进行判断，
 * 如果时基本数据类型，则将值存入map，value为另一张map表引用，额外一张map表，key用来存储基本类型值，value为索引值
 * 如果是普通对象，则直接存入原map
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/10/30 21:10
 */
public class GreaterHeap<E> {

    /**
     * 反向索引表
     */
    private Map<E, Integer> indexMap;

    private List<E> heap;

    private Comparator<? super E> comparator;

    private int heapSize;

    public GreaterHeap(Comparator<? super E> comparator) {
        heap = new ArrayList<>();
        indexMap = new HashMap<>();
        this.comparator = comparator;
        heapSize = 0;
    }

    public boolean isEmpty() {
        return 0 - heapSize > -1;
    }

    public int size() {
        return heapSize;
    }

    public boolean add(E e) {
        return offer(e);
    }

    public boolean offer(E e) {
        indexMap.put(e, heapSize);
        heap.add(e);
        heapsert(heapSize++);
        return true;
    }

    public E poll() {

        if (heapSize > 0) {
            E e = heap.get(0);
            heap.set(0, heap.get(--heapSize));
            heap.remove(heapSize);
            indexMap.remove(e);
            heapify(0);
            return e;
        }
        return null;
    }

    public E peek() {
        if (heapSize > 0) {
            return heap.get(0);
        }
        return null;
    }

    public boolean remove(E e) {
        Integer index = indexMap.get(e);
        if (null != index) {
            E last = heap.get(--heapSize);
            heap.remove(index);
            heap.set(index, last);
            indexMap.remove(e);
            heapify(index);
            return true;
        }

        return false;
    }

    public List<E> getAllElements() {
        List<E> targetList = new ArrayList<>();

        for (E e : heap) {
            targetList.add(e);
        }

        return targetList.stream().sorted(comparator).collect(Collectors.toList());

    }

    public boolean isExits(E e) {
        Integer exits = indexMap.get(e);
        if (null != exits) {
            indexMap.put(e, exits);
            return true;
        }
        return false;
    }

    /**
     * 堆插入（堆的最后一个位置插入）
     *
     * @param index
     */
    private int heapsert(int index) {
        int parentIndex;
        // 当前元素比父节点小，交换（看成小根堆实现方式）
        while (comparator.compare(heap.get(index), heap.get(parentIndex = (index - 1) / 2)) < 0) {
            swap(index, parentIndex);
            index = parentIndex;
        }
        return index;
    }

    /**
     * 堆修改（要和heapsert行为一致，因为heapsert看成小根堆实现方式，因此这里也一样）
     *
     * @param index
     */
    private int heapify(int index) {

        // 表示index的左子节点
        int bestIndex = (index << 1) + 1;

        while (bestIndex < heapSize) {

            // 存在右节点
            if ((bestIndex + 1) < heapSize) {
                // 得到较小的子节点下标（看成小根堆实现方式）
                bestIndex = comparator.compare(heap.get(bestIndex), heap.get(bestIndex + 1)) < 0 ? bestIndex : (bestIndex + 1);
            }
            // 用得到的较小的值跟父节点比较，得到较小的下标
            bestIndex = comparator.compare(heap.get(index), heap.get(bestIndex)) < 0 ? index : bestIndex;
            if (bestIndex == index) {
                // 下标为自己时结束
                break;
            }
            swap(index, bestIndex);
            index = bestIndex;
            // 继续下一次比较
            bestIndex = (bestIndex << 1) + 1;
        }

        return index;
    }

    /**
     * 重新调整堆
     *
     * @param e
     */
    public void resign(E e) {
        Integer index = indexMap.get(e);
        indexMap.put(e, index);
        heap.set(index, e);
        heapsert(index);
        heapify(index);
    }

    private void swap(int index, int parentIndex) {
        E e = heap.get(index);
        E parent = heap.get(parentIndex);
        heap.set(index, parent);
        heap.set(parentIndex, e);
        indexMap.put(e, parentIndex);
        indexMap.put(parent, index);
    }

    public static void main(String[] args) {
        GreaterHeap<User> heap = new GreaterHeap<>(Comparator.comparingInt(User::getId));

        User user1 = new User(1, "faker");
        heap.add(user1);
        User user2 = new User(6, "mirror");
        heap.add(user2);
        User user3 = new User(3, "alex");
        heap.add(user3);
        User user4 = new User(2, "lucy");
        heap.add(user4);
        User user5 = new User(3, "mike");
        heap.add(user5);
        User user6 = new User(4, "tom");
        heap.add(user6);
        System.out.println();
        heap.remove(user4);

        user5.setId(100);
        heap.resign(user5);
        while (!heap.isEmpty()) {
            System.out.print(heap.poll() + ",");
        }

    }

}
