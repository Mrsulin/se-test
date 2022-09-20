package com.slc.thread;

import org.omg.PortableServer.ThreadPolicy;

import java.util.concurrent.*;

public class PoolTest {

    public static void main(String[] args) {
        ThreadPoolExecutor executor=new ThreadPoolExecutor(3,3,3,
                TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(10),
                Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
        executor.submit(()-> System.out.println("123"));
    }
}
