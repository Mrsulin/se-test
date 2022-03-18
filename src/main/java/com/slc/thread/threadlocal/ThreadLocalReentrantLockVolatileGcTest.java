package com.slc.thread.threadlocal;

import lombok.SneakyThrows;
import lombok.ToString;
import org.junit.Test;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.lang.ref.WeakReference;
import java.sql.Time;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ThreadLocalReentrantLockVolatileGcTest {


    public static class Car extends WeakReference<CarBase> {

        public Car(CarBase referent) {
            super(referent);
        }

        public Car(CarBase referent, ReferenceQueue<? super CarBase> q) {
            super(referent, q);
        }
    }

    @ToString
    public static class CarBase {
        private String name;

        CarBase(String name) {
            this.name = name;
        }
    }

    @Test
    @SneakyThrows
    public void testWeakReference2() {
        Map<WeakReference<?>, Object> map = new HashMap<>();
        map.put(new Car(new CarBase("benze1")), 1);
        map.put(new Car(new CarBase("benze2")), 2);
        map.put(new Car(new CarBase("benze3")), 3);
        map.put(new Car(new CarBase("benze4")), 4);
        Iterator<Map.Entry<WeakReference<?>, Object>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<WeakReference<?>, Object> next = iterator.next();
            System.out.println("key: " + next.getKey() + "  value:" + next.getValue());
        }
        System.out.println("execute gc...");
        System.gc();
        TimeUnit.SECONDS.sleep(2);
        Collection<Object> values = map.values();
        Iterator<Map.Entry<WeakReference<?>, Object>> iterator2 = map.entrySet().iterator();
        while (iterator.hasNext()) {
            System.out.println("after gc iterator");
            Map.Entry<WeakReference<?>, Object> next = iterator2.next();
            System.out.print("key: " + next.getKey() + "  value:" + next.getValue());
        }
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }

    @Test
    @SneakyThrows
    public void testWeakReference3() {
        ReferenceQueue<CarBase> referenceQueue = new ReferenceQueue<>();
//        new Thread(()->{
//            while (referenceQueue){
//
//            }
//        }).start();
        Map<Object, WeakReference<CarBase>> map = new HashMap<>();
        for (int i = 1; i <=4; i++) {
            map.put(i, new Car(new CarBase("benze"+i), referenceQueue));
        }

        System.out.println(map.get(1)+"---------"+map.get(1).get());
        System.out.println(map.get(2)+"---------"+map.get(2).get());
        System.out.println(map.get(3)+"---------"+map.get(3).get());
        System.out.println(map.get(4)+"---------"+map.get(4).get());
        System.out.println("execute gc...");
        CarBase reference2 = map.get(2).get();
        System.gc();
        TimeUnit.SECONDS.sleep(2);
        System.out.println("base data...");
        System.out.println(map.get(1).get());
        System.out.println(map.get(2).get());
        System.out.println(map.get(3).get());
        System.out.println(map.get(4).get());
        System.out.println("queue data...");
        System.out.println("reference belong1:"+referenceQueue.remove());
        System.out.println("reference belong2:"+referenceQueue.remove());
        System.out.println("reference belong3:"+referenceQueue.remove());
        System.out.println("reference belong4:"+referenceQueue.remove());
    }


    @Test
    @SneakyThrows
    public void testWeakReference() {
        ReferenceQueue<CarBase> referenceQueue = new ReferenceQueue<>();
        Car car = new Car(new CarBase("benz"), referenceQueue);
        System.out.println(car.get());
        System.gc();
        System.out.println("execute GC");
        TimeUnit.SECONDS.sleep(2);
        System.out.println(car.get());
        Reference<? extends CarBase> poll = referenceQueue.poll();
        System.out.println("queue poll:" + poll.get());
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }

    @org.junit.Test
    public void test5() {
        CarBase[] carBases = new CarBase[16];
    }

    @Test
    public void testThreadLocal() throws InterruptedException {
        ThreadLocal<CarBase> threadLocal = new ThreadLocal<>();
        ThreadLocal<CarBase> threadLocalS2 = new ThreadLocal<>();
        Thread thread = new Thread(() -> {
            threadLocalS2.set(new CarBase("car-s2"));
            threadLocalS2.set(new CarBase("car-s22"));
            threadLocalS2.set(new CarBase("car-s222"));
            threadLocal.set(new CarBase("car-6"));
            threadLocal.set(new CarBase("car-66"));
            threadLocal.set(new CarBase("car-666"));
            threadLocal.set(new CarBase("car-6666"));
            threadLocal.set(new CarBase("car-66666"));
            System.out.println("当前线程 " + Thread.currentThread().getName() + "  sleep before:" + threadLocal.get());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("当前线程 " + Thread.currentThread().getName() + "  sleep after:" + threadLocal.get());
        });
        thread.start();
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }


}
