package com.slc.jvm.gc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * gc日志查看参数（基于jdk1.8）
 * -verbose:gc -XX:+PrintGC -XX:+PrintGCDetails
 */
public class GcTest {

    public static void main(String[] args) throws InterruptedException {
        Node node = new Node("first", new DataSave("1st Data"));
        node.setNext(new Node("second", new DataSave("2nd Data")));
//        Node next = node.next;
        node = null;
        new Thread(()->{
            Node node1 = new Node("inner Node ", new DataSave("inner data save"));
        }).start();
        TimeUnit.SECONDS.sleep(2);
        System.gc();
//        System.out.println("freeMemory:::"+(Runtime.getRuntime().freeMemory())/1024/1024);
//        System.out.println("maxMemory:::"+(Runtime.getRuntime().maxMemory())/1024/1024);
//        System.out.println("totalMemory:::"+(Runtime.getRuntime().totalMemory())/1024/1024);
        LockSupport.park();
    }

    @Data
    @NoArgsConstructor
    public static class Node {
        String name;

        DataSave dataSave;

        Node next;

        public Node(String name, DataSave dataSave) {
            this.name = name;
            this.dataSave = dataSave;
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println("                                                        node被回收-->" + this);
            super.finalize();
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DataSave {
        private String value;
    }
}
