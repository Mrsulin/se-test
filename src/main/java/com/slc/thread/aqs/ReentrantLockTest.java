package com.slc.thread.aqs;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    @SneakyThrows
    public static void main(String[] args) {
        Lock lock = new ReentrantLock(true);
        List<MyThread> threadList = new ArrayList<>();
        for (int i = 0; i <= 5; i++) {
            threadList.add(new MyThread("线程" + i + "测试", lock));
        }
        threadList.forEach(Thread::start);
        TimeUnit.SECONDS.sleep(2L);
        threadList.get(4).interrupt();
        LockSupport.park();
    }

    public static class MyThread extends Thread {
        final Lock lock;

        public MyThread(String name, Lock lock) {
            super(name);
            this.lock = lock;
        }


        @Override
        public void run() {
            try {
                lock.lock();
                System.out.println("            " + currentThread().getName() + ": 获得锁 ");
                for (int i = 3; i >= 1; i--) {
                    System.out.println("            " + currentThread().getName() + ": sleep " + i);
                    TimeUnit.SECONDS.sleep(1);
                }
                System.out.println("            " + currentThread().getName() + ": release ");
//                TimeUnit.SECONDS.sleep(3);
//                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (((ReentrantLock) lock).isHeldByCurrentThread()) {
                    lock.unlock();
                }
            }
        }
    }

    public static class FirstLockThread extends Thread {
        final Lock lock;
        final String name;


        public FirstLockThread(Lock lock, String name) {
            this.lock = lock;
            this.name = name;
        }

        @Override
        public void run() {
            currentThread().setName(name);
            lock.lock();
            try {
                System.out.println(name + ": 1st 获得锁");
                for (int i = 5; i >= 1; i--) {
                    System.out.println(name + ": 1st sleep " + i);
                    TimeUnit.SECONDS.sleep(1);
                }
                try {
                    lock.lock();
                    System.out.println(name + ": 2nd  获得锁");
                    for (int i = 5; i >= 1; i--) {
                        System.out.println(name + ": 2nd sleep " + i);
                        TimeUnit.SECONDS.sleep(1);
                    }
                } catch (InterruptedException e) {
                    throw e;
                } finally {
                    lock.unlock();
                }
                for (int i = 5; i >= 1; i--) {
                    System.out.println(name + ": 1st end sleep " + i);
                    TimeUnit.SECONDS.sleep(1);
                }
                System.out.println("            " + currentThread().getName() + ": end！");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
