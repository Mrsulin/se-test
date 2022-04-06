package com.slc.jvm.gc;

import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.concurrent.TimeUnit;

public class ReferenceTest2 {
    @NoArgsConstructor
    @ToString
    public static class Node {
        public String name;
        public Node head;
        public Node next;

        public volatile int state;

        public Node(String name) {
            this.name = name;
        }


        public void setHead(Node head) {
            this.head = head;
        }


        public void setNext(Node next) {
            this.next = next;
        }


        public void setState(int state) {
            this.state = state;
        }

        public void print() throws InterruptedException {
            Node h = head;
            System.out.println(Thread.currentThread().getName() + " getState: " + h.state);
            TimeUnit.SECONDS.sleep(10);
            int headState = h.state;
            if (headState > 0) {
                System.out.println(Thread.currentThread().getName() + " state > 0:::" + headState);
            } else {
                System.out.println(Thread.currentThread().getName() + " state < 0::" + headState);
            }
        }
    }
//    public static class ChangeThread extends Thread{
//
//
//        @Override
//        public void run() {
//            super.run();
//        }
//    }

    public static void main(String[] args) throws InterruptedException {
        Node node = new Node("A");
        Node b = new Node("B");
        b.state = 22;
        node.setHead(b);
        new Thread(() -> {
            try {
                node.print();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> b.state = -1).start();
        TimeUnit.SECONDS.sleep(2);
        System.out.println("main end");

    }

}
