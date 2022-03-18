package com.slc.thread.volatilekey;

import java.util.concurrent.TimeUnit;

public class VolatileTest {
    final static int MAX = 5;
    static int initValue = 0;

    public static synchronized void add() {
        initValue++;
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i < 10; i++) {
            new Thread(() -> {
                while (initValue < MAX) {
                }

                System.out.println(Thread.currentThread().getName() + " end");
            }, "Reader" + i).start();
        }
        TimeUnit.SECONDS.sleep(2);
//

        new Thread(() -> {
            while (initValue < MAX) {
                add();
//                System.out.println("The initValue will be changed to: " + initValue);
//                try {
//                    TimeUnit.SECONDS.sleep(2);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }

        }, "Updater").start();
    }
}
