package com.slc.thread.tasklifecycle;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SimpleTest {
    @SneakyThrows
    public static void main(String[] args) {
        MyThread<Integer> myThread = new MyThread<>(() -> {
            System.out.println("---->now is callback");
            return 666666;
        });
        myThread.start();
//        System.out.println("result is :" + myThread.getResult());
//        TimeUnit.SECONDS.sleep(2);
        System.out.println("result is :" + myThread.getResult());
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }

    public interface CallBack<T> {
        T call();
    }

    public static class MyThread<T> extends Thread {
        private CallBack<T> callBack;
        private T result;

        public MyThread(CallBack<T> callBack) {
            this.callBack = callBack;
        }

        public T getResult() {
            return result;
        }

        @Override
        public void run() {
            System.out.println("exec thread logic..");
            System.out.println("start invoke");
            callBack.call();

        }
    }


}
