package com.gtop.work.demo.algorithm.stackAndQueue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 用两个队列实现栈
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/10/29 15:29
 */
public class TwoQueueImplementStack<E> {

    private Queue<E> changeablePointQueue;
    private Queue<E> helpQueue;

    public TwoQueueImplementStack() {
        this.changeablePointQueue = new LinkedList<>();
        this.helpQueue = new LinkedList<>();
    }

    public E push(E item) {
        changeablePointQueue.offer(item);
        return item;
    }

    public synchronized E pop() {
        while (changeablePointQueue.size() > 1) {
            helpQueue.offer(changeablePointQueue.poll());
        }
        E e = changeablePointQueue.poll();
        Queue<E> tempQueue = changeablePointQueue;
        changeablePointQueue = helpQueue;
        helpQueue = tempQueue;
        return e;
    }

    public synchronized E peek() {
        while (changeablePointQueue.size() > 1) {
            helpQueue.offer(changeablePointQueue.poll());
        }
        E e = changeablePointQueue.poll();
        helpQueue.offer(e);
        Queue<E> tempQueue = changeablePointQueue;
        changeablePointQueue = helpQueue;
        helpQueue = tempQueue;
        return e;
    }

    public static void main(String[] args) {
        TwoQueueImplementStack<Integer> stack = new TwoQueueImplementStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.peek());
        System.out.println(stack.peek());
        stack.push(4);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }

}
