package com.slc.thread.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockWithConditionTest {
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        for (int i = 1; i <= 4; i++) {
            new MyThread(lock, condition, " condition-[" + i + "]-业务").start();
            TimeUnit.MICROSECONDS.sleep(500L);
        }
        new Thread(() -> {
            lock.lock();
            try {
              TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();
        TimeUnit.SECONDS.sleep(1L);
        notify(lock, condition);
        LockSupport.park();

    }

    private static void notify(Lock lock, Condition condition) {
        lock.lock();
        try {
            condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static class MyThread extends Thread {
        final Lock lock;
        final Condition condition;
        final String name;


        public MyThread(Lock lock, Condition condition, String name) {
            this.lock = lock;
            this.condition = condition;
            this.name = name;
        }

        @Override
        public void run() {
            currentThread().setName(name);
            lock.lock();
            try {
                System.out.println("            " + name + ": await ");
                condition.await();
                System.out.println("            " + name + ": continue ");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println("            " + name + ": release ");
            }
        }
    }
}
