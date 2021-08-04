package com.slc.datastruct.queue;

/**
 * @author slc
 */
public class CircleQueue<T> {

    private int rear;

    private int front;

    private final int capacity;

    private final Object[] array;

    public CircleQueue(int capacity) {
        array = new Object[capacity];
        //空出来一个位置
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

    /**
     * 入队
     * @param t
     */
    public void offer(T t) {
        if (isFull()) {
            throw new RuntimeException("队列已满");
        }
        //直接将数据加入
        array[rear] = t;
        rear = (rear + 1) % capacity;
    }

    /**
     * 出队
     * @return T
     */
    public T poll() {
        if (isEmpty()) {
            throw new RuntimeException("队列空");
        }
        //front直接指向队列的第一个元素
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
            System.out.printf("目前队列中的数-[%d]-[%s]", (i % capacity), array[i % capacity]);
        }
    }

    /**
     * 队列中有效数值
     * @return 队列中有效数值
     */
    public int count() {
        return (rear - front + capacity) % capacity;
    }

    private T headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空，不存在数据");
        }
        @SuppressWarnings("unchecked")
        T t = (T) array[front];
        return t;

    }

    public static void main(String[] args) {
        CircleQueue<Integer> circleQueue = new CircleQueue<>(4);
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
