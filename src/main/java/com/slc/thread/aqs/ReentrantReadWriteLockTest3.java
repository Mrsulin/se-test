package com.slc.thread.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.IntStream;

public class ReentrantReadWriteLockTest3 {

    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private static final ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    private static final ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();


    public static void main(String[] args) throws InterruptedException {
        writeIncreaseRead();
        LockSupport.park();
    }

    public static void writeIncreaseRead() throws InterruptedException {
        writeLock.lock();
        writeLock.lock();
        readLock.lock();
        writeLock.unlock();
        writeLock.unlock();
        readLock.unlock();
    }

}
