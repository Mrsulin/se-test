package com.slc.thread.aqs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.IntStream;

public class ReentrantReadWriteLockTest {
    public static class ShareData {
        List<Character> container = new ArrayList<>();

        private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        private final ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        private final ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        private final int length;


        public ShareData(int length) {
            this.length = length;
            for (int i = 0; i < length; i++) {
                container.add(i, 'c');
            }
        }

        public char[] read(boolean flag) throws InterruptedException {
            readLock.lock();
            char[] buffer = new char[length];
            try {
                for (int i = 0; i < length; i++) {
                    buffer[i] = container.get(i);
                }
//                System.out.println(Thread.currentThread().getName() + " read 数据完毕，3s后释放 read lock");
//                TimeUnit.SECONDS.sleep(1);
                if (flag) {
                    LockSupport.park();
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            } finally {
                readLock.unlock();
            }
            return buffer;
        }

        public void write(int i, char c) throws InterruptedException {
            writeLock.lock();
            try {
                container.set(i, c);
//                System.out.println(Thread.currentThread().getName() + " write 数据完毕，3s后释放 write lock");
//                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            } finally {
                writeLock.unlock();
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        ShareData shareData = new ShareData(50);
        read(shareData);
        TimeUnit.SECONDS.sleep(2);
        write(shareData);
        LockSupport.park();
    }

    private static void write(ShareData shareData) {
        final String str = "it is message for ReentrantReadWriteLockTest...";
        IntStream.range(1, 5).mapToObj(index -> new Thread(() -> {
            for (int i = 0; i < str.length(); i++) {
                try {
                    char c = str.charAt(i);
                    shareData.write(i, c);
                    System.out.println(Thread.currentThread().getName() + " write: " + c);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "write thread [" + index + "]")).forEach(Thread::start);
    }

    private static void read(ShareData shareData) {
        IntStream.range(1, 3).mapToObj(index -> new Thread(() -> {
            while (true) {
                try {
                    System.out.println(Thread.currentThread().getName() + " read: " + new String(shareData.read(index == 2)));
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "read thread [" + index + "]")).forEach(Thread::start);
    }
}
