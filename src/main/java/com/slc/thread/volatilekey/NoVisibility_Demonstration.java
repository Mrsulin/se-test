package com.slc.thread.volatilekey;


/**
 * @author mars_jun
 */
public class NoVisibility_Demonstration extends Thread {
    boolean keepRunning = true;

    public static void main(String[] args) throws InterruptedException {
        NoVisibility_Demonstration t = new NoVisibility_Demonstration();
        t.start();
        System.out.println("start: " + t.keepRunning);
//        Thread.sleep(1000);
        t.keepRunning = false;
//        System.out.println("end: " +t.keepRunning);
    }

    public void run() {
        int x = 1;
        while (keepRunning) {
//            System.out.println("如果你不注释这一行，程序会正常停止！");
            x++;

        }
        System.out.println("x:" + x);
    }
}