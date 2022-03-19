package com.slc.thread.aqs;

import lombok.SneakyThrows;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class CountDownLatchVolatileGcTest {

    @Test
    public void test1() throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(3);
        for (int i = 1; i <= 3; i++) {
            new CountDownLatchThread(i+" await thread",latch).start();
        }
//        TimeUnit.SECONDS.sleep(5);
//        LockSupport.park();
        latch.countDown();
        latch.countDown();
        latch.countDown();
        System.out.println("[main end!]");
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }


    static class CountDownLatchThread extends Thread {
        final CountDownLatch latch;

        public CountDownLatchThread(CountDownLatch latch) {
            this.latch = latch;
        }

        public CountDownLatchThread(String name, CountDownLatch latch) {
            super(name);
            this.latch = latch;
        }

        @SneakyThrows
        @Override
        public void run() {
            System.out.println("                                       " + currentThread().getName() + " start");
            latch.await();
            System.out.println("                                       " + currentThread().getName() + " continue");
        }
    }
}
