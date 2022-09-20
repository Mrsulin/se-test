package com.slc.thread;

import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WaitTest {

    static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MINUTES, new SynchronousQueue<>(), new ThreadPoolExecutor.DiscardPolicy());
    static final Map<Long, Thread> threadMap = new ConcurrentHashMap<>();
    static final Map<Long, Future<?>> futureMap = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {
        print();
        Thread thread = new MyThread();
        threadMap.put(1L, thread);
        synchronized (futureMap) {
            futureMap.put(1L, threadPoolExecutor.submit(thread));
            TimeUnit.SECONDS.sleep(1);
            System.out.println("结束order");
            futureMap.get(1L).cancel(true);
        }

    }

    private static void print() {
        new Thread(() -> {
            while (true) {
                try {
                    System.out.println("getActiveCount---" + threadPoolExecutor.getActiveCount() + "         getCompletedTaskCount---" + threadPoolExecutor.getCompletedTaskCount());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

class MyThread extends Thread {


    @Override
    public void run() {

        for (int i = 0; i < 2000000; i++) {
            System.out.print(i+"-");
        }

        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            System.out.println("加锁");
            lock.newCondition().await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("解锁");
            lock.unlock();
        }
    }
}