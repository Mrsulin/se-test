package com.slc.thread.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.IntStream;

public class ReentrantReadWriteLockTest2 {

    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private static final ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    private static final ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();


    public static void main(String[] args) throws InterruptedException {
//        new Thread(ReentrantReadWriteLockTest2::write).start();
//        TimeUnit.SECONDS.sleep(5L);
        IntStream.range(1, 13).mapToObj(i -> new Thread(ReentrantReadWriteLockTest2::read, "read-[" + i + "]")).forEach(Thread::start);

        new Thread(() -> {
            for (; ; ) {
                System.out.println(" now :: read :: " + lock.getReadHoldCount());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        LockSupport.park();
    }

    public static int deep = 0;

    public static void read() {
        try {
            TimeUnit.SECONDS.sleep(3L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        readLock.lock();
        try {
            readLock.lock();
            TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
            readLock.unlock();
        }
    }

    public static void write() {
        writeLock.lock();
        try {
            TimeUnit.SECONDS.sleep(10L);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

}
