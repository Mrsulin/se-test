package com.slc.thread.aqs;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockWithConditionTest {
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
//        Condition condition2 = lock.newCondition();
//        for (int i = 1; i < 5; i++) {
//            Thread thread = new Thread(() -> {
//                try {
//                    condition.await();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }, "no lock thread "+i);
//            thread.start();
//        }
//        TimeUnit.SECONDS.sleep(5L);

        System.out.println("----------------");
        System.out.println("----------------");
        System.out.println("----------------");
//        LockSupport.park();
        for (int i = 1; i <= 2; i++) {
            new MyThread(lock, condition, " condition 1st 业务" + i).start();
        }

//        for (int i = 3; i <= 5; i++) {
//            new MyThread(lock, condition2, " condition 2nd 业务" + i).start();
//        }

        lock.lock();
   //     condition.signalAll();
//        condition2.signalAll();
        lock.unlock();
        LockSupport.park();
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
