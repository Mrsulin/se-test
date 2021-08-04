package com.slc.datastruct.queue;

import java.util.Arrays;

/**
 * @author slc
 */
public class ArrayQueue<T> {

    private int rear;

    private int front;

    private int capacity;

    private Object[] array;

    public ArrayQueue(int capacity) {
        array = new Object[capacity];
        this.capacity = capacity;
        rear = -1;
        front = -1;
    }

    public boolean isFull() {
        return rear == capacity - 1;
    }

    public boolean isEmpty() {
        return rear == front;
    }

    public void offer(T t) {
        if (isFull()) {
            throw new RuntimeException("队列已满");
        }
        ++rear;
        array[rear] = t;
    }

    public T poll() {
        if (isEmpty()) {
            throw new RuntimeException("队列空");
        }
        ++front;
        @SuppressWarnings("unchecked")
        T t = (T) array[front];
        array[front]=null;
        return t;
    }

    /**
     * 打印实际的数组
     */
    public  void forEachArray(){
        System.out.print("实际数组：  ");
        Arrays.stream(array).forEach(o->System.out.print(o+"  "));
        System.out.println();
    }
    public void showQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空，不存在数据");
        }
        System.out.print("队列数据：  ");
        for(int i=front;i<rear;i++){
            System.out.printf("[%s]", array[i+1]);

        }

        System.out.println();
    }

    private T getHead() {
        if (isEmpty()) {
            throw new RuntimeException("队列空，不存在数据");
        }
        @SuppressWarnings("unchecked")
        T t = (T) array[front + 1];
        return t;
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>(4);
        arrayQueue.offer(21);
        arrayQueue.offer(32);
        arrayQueue.offer(13);
        arrayQueue.offer(4);
        arrayQueue.showQueue();
        System.out.printf("取出数据：%s\n",arrayQueue.poll());
        System.out.printf("取出数据：%s\n",arrayQueue.poll());
        arrayQueue.showQueue();
        arrayQueue.forEachArray();
    }
}
