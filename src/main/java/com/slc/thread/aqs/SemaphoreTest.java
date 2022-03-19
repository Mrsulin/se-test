package com.slc.thread.aqs;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class SemaphoreTest {
    static Semaphore semaphore = new Semaphore(3, false);

    public static void main(String[] args) throws InterruptedException {

        createThreeThread();
        TimeUnit.SECONDS.sleep(2);
        createOtherThread();

        LockSupport.park();
    }

    private static void createThreeThread() {
        for (int i = 1; i <= 3; i++) {
            Thread thread = new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " exec");
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + " start in");
                    LockSupport.park();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println(Thread.currentThread().getName() + " 异常");
                }
                System.out.println(Thread.currentThread().getName() + " 释放");
                semaphore.release();
            }, i + " 主要线程");
            thread.start();
        }
    }

    private static void createOtherThread() {
        for (int i = 4; i <= 5; i++) {
            Thread thread = new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " exec");
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + " start in");
                    LockSupport.park();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " 释放");
                semaphore.release();
            }, i + " 备用线程");
            thread.start();
        }
    }

}
