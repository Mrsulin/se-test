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
public class GcTest2 {

    static class CleanThread extends Thread{
        private Node node;

        public CleanThread(Node node) {
            this.node = node;
        }

        @Override
        public void run() {
            Node thisNext=node.next;
            while (true){
                System.out.println("next: "+thisNext);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
      final   Node node = new Node("first", new DataSave("1st Data"));
        node.setNext(new Node("second", new DataSave("2nd Data")));
        Node nextnext=node.next;
//        node.next=null;
        System.out.println("nextnext main: "+nextnext);
                new Thread(()->{
//            Node thisNext = node.next;
//            while (true){
//                System.out.println("next: "+thisNext);
//                try {
//                    TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
        }).start();
                TimeUnit.SECONDS.sleep(2);
        System.gc();
        System.out.println("nextnext main: "+nextnext);


//        new Thread(()->{
//            Node thisNext = node.next;
//            while (true){
//                System.out.println("next: "+thisNext);
//                try {
//                    TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }).start();
////        new CleanThread(node).start();
//        node.next = null;
////        node=null;
//        TimeUnit.SECONDS.sleep(5);
//
//        System.gc();
//        System.out.println(node);
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
