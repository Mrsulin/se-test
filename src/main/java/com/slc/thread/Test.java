package com.slc.thread;

import java.util.concurrent.*;

public class Test {
    static ThreadFactory factory = r -> {
        //创建一个线程
        Thread t = new Thread(r);
        //给创建的线程设置UncaughtExceptionHandler对象 里面实现异常的默认逻辑
        t.setName("ansidnlaisndinlaisd");
        return t;
    };
    static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1,
            0L, TimeUnit.MILLISECONDS,
            new SynchronousQueue<>(), factory);
    static CountDownLatch latch1 = new CountDownLatch(1);
    static CountDownLatch latch2 = new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException {
        print();

        Thread newTask = new NewTask("T1", latch1);
        Future<?> submit = threadPoolExecutor.submit(newTask);
        new Thread(()->{
            boolean flag = true;
            while (flag) {
                try {
                    threadPoolExecutor.submit(new NewTask("otherA", latch2));
                    flag = false;
                } catch (RejectedExecutionException e) {
                    e.printStackTrace();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                }
            }
        }).start();
        new Thread(()->{
            boolean flag = true;
            while (flag) {
                try {
                    threadPoolExecutor.submit(new NewTask("otherB", latch2));
                    flag = false;
                } catch (RejectedExecutionException e) {
                    e.printStackTrace();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                }
            }
        }).start();
        Thread.sleep(3000);
        threadPoolExecutor.setCorePoolSize(2);
        threadPoolExecutor.setMaximumPoolSize(1);
        Thread.sleep(3000);
        threadPoolExecutor.setCorePoolSize(3);
        threadPoolExecutor.setMaximumPoolSize(1);
        Thread.sleep(10000);
        threadPoolExecutor.setCorePoolSize(2);
        threadPoolExecutor.setMaximumPoolSize(1);
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