package com.slc.jvm.gc;

/**
 * gc日志查看参数（基于jdk1.8）
 * -verbose:gc -XX:+PrintGC -XX:+PrintGCDetails
 */
public class GcTestForMethodInvoke {

    public static void main(String[] args) throws InterruptedException {
//        byte[] buffer = new byte[64 * 1024 * 1024];
//        buffer = null;
        invoke();
        System.gc();

    }

    private static void invoke() {
        byte[] buffer = new byte[64 * 1024 * 1024];
        System.gc();

    }


}
