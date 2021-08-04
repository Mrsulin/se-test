package com.slc.datastruct.queue;

/**
 * @author slc
 */
public class CircleQueue2<T> {

    private int rear;

    private int front;

    private final int capacity;

    private final Object[] array;

    public CircleQueue2(int capacity) {
        array = new Object[capacity];
        this.capacity = capacity + 1;
        rear = 0;
        front = 0;
    }

    public boolean isFull() {
        return (rear + 1) % capacity == front;
    }

    public boolean isEmpty() {
        return rear == front;
    }

    public void offer(T t) {
        if (isFull()) {
            throw new RuntimeException("队列已满");
        }
        array[rear] = t;
        rear = (rear + 1) % capacity;
    }

    public T poll() {
        if (isEmpty()) {
            throw new RuntimeException("队列空");
        }
        @SuppressWarnings("unchecked")
        T value = (T) array[front];
        front = (front + 1) % capacity;
        return value;
    }

    public void showQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空，不存在数据");
        }

        for (int i = front; i < front + count(); i++) {
            System.out.printf("当前队列为-[%s]", array[i % capacity]);
        }
    }

    public int count() {

        return (rear + capacity - front) % capacity;
    }

    private T headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空，不存在数据");
        }
        @SuppressWarnings("unchecked")
        T head= (T) array[front];
        return head;
    }

    public static void main(String[] args) {
        CircleQueue2<Integer> circleQueue = new CircleQueue2<>(4);
        circleQueue.offer(111);
        circleQueue.offer(222);
        circleQueue.offer(333);
        circleQueue.offer(444);
        circleQueue.poll();
        circleQueue.poll();
        circleQueue.poll();
        circleQueue.poll();
        System.out.println("头(不移动)--" + circleQueue.headQueue());
//        System.out.println("------------");
//        System.out.println(arrayQueue.poll());
//        System.out.println(arrayQueue.poll());
//        System.out.println(arrayQueue.poll());
    }
}
