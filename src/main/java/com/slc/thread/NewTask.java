package com.slc.thread;

import java.util.concurrent.CountDownLatch;

public
class NewTask extends Thread {

    String name;
    CountDownLatch latch;
    public NewTask(String name, CountDownLatch latch) {
        this.latch=latch;
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("当前线程名：" + Thread.currentThread().getName());
        Thread.currentThread().setName(name);
        try {
            System.out.printf("%s进入线程内部\n", Thread.currentThread().getName());
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            System.out.println("error");
            e.printStackTrace();
        }finally {
            latch.countDown();
        }

    }
}