package com.gtop.work.demo.algorithm.stackAndQueue;

import java.util.Stack;

/**
 * 用两个栈实现队列
 *
 * @author hongzw@citycloud.com.cn
 * @Date 2021/10/29 12:37
 */
public class TwoStackImplementQueue<T> {

    private Stack<T> stackPush;
    private Stack<T> stackPop;

    private int size = Integer.MAX_VALUE;


    public TwoStackImplementQueue() {
        this.stackPush = new Stack<>();
        this.stackPop = new Stack<>();
    }

    public TwoStackImplementQueue(int size) {
        this.stackPush = new Stack<>();
        this.stackPop = new Stack<>();
        this.size = size;
    }

    /**
     * push栈向pop栈倒入数据，倒入数据规则：
     * 确保pop队列一定为空，倒入的时候一定要全部倒完
     */
    private void pushToPop() {
        if (stackPop.isEmpty()) {
            while (!stackPush.isEmpty()) {
                stackPop.push(stackPush.pop());
            }
        }
    }

    public boolean add(T element) {

        if (isFull()) {
            throw new RuntimeException("Queue is full");
        }
        stackPush.add(element);
        pushToPop();
        return true;
    }

    public boolean offer(T element) {

        if (isFull()) {
            return false;
        }
        stackPush.add(element);
        pushToPop();
        return true;

    }

    public T remove() {
        if (stackPush.isEmpty() && stackPop.isEmpty()) {
            throw new RuntimeException("Queue is empty!");
        }
        pushToPop();
        return stackPop.pop();
    }

    public T poll() {
        if (stackPush.isEmpty() && stackPop.isEmpty()) {
            return null;
        }
        pushToPop();
        return stackPop.pop();
    }

    public T element() {
        if (stackPush.isEmpty() && stackPop.isEmpty()) {
            throw new RuntimeException("Queue is empty!");
        }
        pushToPop();
        return stackPop.peek();
    }

    public T peek() {
        if (stackPush.isEmpty() && stackPop.isEmpty()) {
            return null;
        }
        pushToPop();
        return stackPop.peek();
    }

    private boolean isFull() {
        return (stackPush.size() + stackPop.size()) > size - 1;
    }

    public static void main(String[] args) {
        TwoStackImplementQueue queue = new TwoStackImplementQueue(2);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        System.out.println(queue.peek());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.peek());
    }

}
