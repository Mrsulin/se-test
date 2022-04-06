package com.slc.thread.aqs;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PhaserTest {

    public static void main(String[] args) {
        Phaser phaser = new Phaser(5);
        List<Thread> threadList = IntStream.range(1, 11).mapToObj(i -> new Thread(() -> {
            try {
                int wasteTime = new Random().nextInt(5);
                TimeUnit.SECONDS.sleep(wasteTime);
                System.out.println(Thread.currentThread().getName() + " arrive " + wasteTime + " 秒");
                phaser.arriveAndAwaitAdvance();
                System.out.println("                                " + Thread.currentThread().getName() + " continue");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "线程" + i)).collect(Collectors.toList());
        threadList.forEach(t -> {
            t.start();
            try {
                TimeUnit.SECONDS.sleep(1L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

    }
}
