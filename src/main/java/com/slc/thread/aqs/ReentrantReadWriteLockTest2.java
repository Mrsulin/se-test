package com.slc.thread.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.IntStream;

public class ReentrantReadWriteLockTest2 {

    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private static final ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    private static final ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    public static void main(String[] args) throws InterruptedException {
//        Condition condition = readLock.newCondition();
        Condition condition2 = writeLock.newCondition();

//        IntStream.range(1, 3).mapToObj(index -> new Thread(() -> {
//            readLock.lock();
//            try {
//                int i = 0;
//                while (true) {
//                    System.out.println(Thread.currentThread().getName() + " read... " + i);
//                    TimeUnit.SECONDS.sleep(1);
//                    if (index != 3 && ++i == 5) {
//                        break;
//                    }
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                readLock.unlock();
//            }
//        }, "read thread [" + index + "]")).forEach(Thread::start);
//        TimeUnit.SECONDS.sleep(2);
//        System.out.println("main continue");
//        IntStream.range(1, 3).mapToObj(index -> new Thread(() -> {
//            writeLock.lock();
//            try {
//                System.out.println(Thread.currentThread().getName() + " write...");
//                TimeUnit.SECONDS.sleep(1);
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                writeLock.unlock();
//            }
//        }, "write thread [" + index + "]")).forEach(Thread::start);
    }

}
