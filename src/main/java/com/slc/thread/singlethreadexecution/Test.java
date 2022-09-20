package com.slc.thread.singlethreadexecution;

public class Test {
    public static void main(String[] args) {
        Bank bank = new Bank(600);
        new OperateThread(bank, 300, "张三").start();
        new OperateThread(bank, 400, "李四").start();
    }
}
