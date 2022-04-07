package com.slc.thread.aqs;

import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CyclicBarrierTest {
    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier barrier = new CyclicBarrier(5, () -> {
            System.out.println("栅栏释放....");
            throw new RuntimeException("asd");
        });
        List<Thread> barrierThreadList = IntStream.range(1, 6).mapToObj(i -> new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " in");
            try {
                if (Thread.currentThread().getName().contains("5")){
//                    barrier.reset();
                }
                barrier.await();
                System.out.println("                  " + Thread.currentThread().getName() + " continue");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }, "线程-[" + i + "]栅栏")).collect(Collectors.toList());
    TimeUnit.SECONDS.sleep(3L);
     List<Thread> barrierThreadList2 = IntStream.range(1, 6).mapToObj(i -> new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " in");
            try {
                barrier.await();
                System.out.println("                  " + Thread.currentThread().getName() + " continue");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }, "other-线程-[" + i + "]栅栏-other")).collect(Collectors.toList());


        barrierThreadList.forEach(t -> {
            t.start();
        });
        barrierThreadList2.forEach(t -> {
            t.start();
        });

    }
}