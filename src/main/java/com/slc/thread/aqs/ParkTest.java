package com.slc.thread.aqs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * park 前后  调用interrupt都会 unPark
 */
public class ParkTest {
    @SneakyThrows
    public static void main(String[] args) {
//        Thread thread = new Thread(() -> {
//
//            System.out.println("    开始线程方法");
//            System.out.println(Thread.currentThread().isInterrupted());
//            LockSupport.park();
//            System.out.println(Thread.currentThread().isInterrupted());
//
//        });
//        thread.start();
//        TimeUnit.SECONDS.sleep(2);
//        LockSupport.unpark(thread);
//        LockSupport.park();

//        int a=1;int b=2; int c=3;
//        int d=a=b=c;
//        System.out.println(a);
//        System.out.println(b);
//        System.out.println(c);
//        System.out.println(d);

        DataSaveReference a = new DataSaveReference("a");
        DataSaveReference b = new DataSaveReference("b");
        DataSaveReference c = new DataSaveReference("c");
        DataSaveReference d = a = b = c;
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
    }

    @Data
    @AllArgsConstructor
    public static class DataSaveReference {
        private String name;
    }
}
