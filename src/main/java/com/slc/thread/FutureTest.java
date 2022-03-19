package com.slc.thread;

import lombok.SneakyThrows;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class FutureTest {
    static class MyThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        MyThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "slc-" +
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }


    @SneakyThrows
    public static void test1() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 7, 10, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), new MyThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        Future<?> future = executor.submit(() -> {
            int i = 1;
            System.out.println("calculate..");
            i = 1 + 10;
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return i;
        });
        System.out.println(future.get());
    }

    @SneakyThrows
    public static void test2() {

        Callable<Integer> callable = () -> {
            int i = 1;
            System.out.println("calculate..");
            i = 1 + 10;
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return i;
        };
        FutureTask<Integer> futureTask = new FutureTask<>(callable);
        futureTask.run();
        futureTask.cancel(true);
        System.out.println(futureTask.get());
    }

    public static void main(String[] args) {
//        test1();
//        test2();
        test3();
    }

    private static void test4(Condition condition) {
        lock1.lock();
        try {
            condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock1.unlock();
        }
    }

    public static ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 7, 10, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), new MyThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
    public static ReentrantLock lock1 = new ReentrantLock();
    public static Condition condition = lock1.newCondition();
    public static Condition condition2 = lock1.newCondition();
    public static Condition condition3 = lock1.newCondition();
    public static Future<?> submit;

    @SneakyThrows
    public static void test3() {


        submit = executor.submit(new MyJob(lock1, condition, condition2, condition3));
        TimeUnit.SECONDS.sleep(3);
        test4(condition);
        TimeUnit.SECONDS.sleep(3);
        submit.cancel(true);
        test4(condition2);
        TimeUnit.SECONDS.sleep(3);
        test4(condition3);
    }

    public static class MyJob extends Thread {
        private ReentrantLock lock1;
        private Condition condition1;
        private Condition condition2;
        private Condition condition3;

        public MyJob(ReentrantLock lock1, Condition condition1, Condition condition2, Condition condition3) {
            this.lock1 = lock1;
            this.condition1 = condition1;
            this.condition2 = condition2;
            this.condition3 = condition3;
        }

        @Override
        public void run() {
            executeNode1();
        }

        private void executeNode1() {
            lock1.lock();
            try {
                System.out.println("execute node 1");
                condition1.await();
                executeNode2();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock1.unlock();
            }
        }

        private void executeNode2() {
            lock1.lock();
            try {
                System.out.println("execute node 2");
                condition2.await();
                executeNode3();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock1.unlock();
            }
        }

        private void executeNode3() {
            lock1.lock();
            try {
                System.out.println("execute node 3");
                condition3.await();
                System.out.println("end");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock1.unlock();
            }
        }
    }
}
