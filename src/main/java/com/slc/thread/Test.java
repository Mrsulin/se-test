package com.slc.thread;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author sulin
 */
public class Test {
    static class HasSelfPrivateNum {
        private int num = 0;

        public  void add(String name) {
            try {
                if (name.equals("a")) {
                    num = 100;
                    System.out.println("a set over");
                    Thread.sleep(2000);
                } else {
                    num = 200;
                    System.out.println("b set over");
                }
                System.out.println(name + "  num=" + num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    //线程 A add a
    static class ThreadA extends Thread {
        private HasSelfPrivateNum numRef;

        public ThreadA(HasSelfPrivateNum numRef) {
            super();
            this.numRef = numRef;
        }

        @Override
        public void run() {
            super.run();
            numRef.add("a");
        }
    }
    //线程 B add b
    static class ThreadB extends Thread {
        private HasSelfPrivateNum numRef;

        public ThreadB(HasSelfPrivateNum numRef) {
            super();
            this.numRef = numRef;
        }

        @Override
        public void run() {
            super.run();
            numRef.add("b");
        }
    }

    public static void main(String[] args) {
        HasSelfPrivateNum numRef=new HasSelfPrivateNum();
        ThreadA aThread=new ThreadA(numRef);
        aThread.start();

        ThreadB bThread=new ThreadB(numRef);
        bThread.start();
        AtomicReference<HasSelfPrivateNum>atomicReference=new AtomicReference<>();
        atomicReference.set(numRef);
    }

























}
