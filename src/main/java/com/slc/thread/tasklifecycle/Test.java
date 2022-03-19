package com.slc.thread.tasklifecycle;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Test {
    @SneakyThrows
    public static void main(String[] args) {
        //定义回调时需要完成的业务
        TaskLifecycle<List<Integer>> lifecycle = new TaskLifecycle<List<Integer>>() {
            @Override
            public void onStart(Thread thread) {
                //eg:资源准备，日志记录等
                System.out.println(" now onStart ");
            }

            @Override
            public void onRunning(Thread thread) {
                //eg:日志记录
                System.out.println(" now onRunning ");
            }

            @Override
            public void onFinish(Thread thread, List<Integer> result) {
                //eg:结果处理，资源回收
                System.out.println(" now onFinish ,the result is " + result);
            }

            @Override
            public void onError(Thread thread, Exception e) {
                //eg:异常通知，异常记录
                System.out.println(" now onError the exception is " + e);
            }
        };
        Observable thread = new ObservableThread<>(lifecycle, () -> {
            List<Integer> list = new ArrayList<>();
            list.add(1);
            TimeUnit.SECONDS.sleep(13);
            return list;
        });
        thread.start();

//        TimeUnit.SECONDS.sleep(3L);
        //测试手动中断线程（模拟异常情况）
//        thread.interrupt();
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }
}