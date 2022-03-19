package com.slc.thread.singlethreadexecution;

import java.util.concurrent.TimeUnit;

public class OperateThread extends Thread {

    private Bank bank;
    private final int withDrawMoney;
    private String name;

    public OperateThread(Bank bank, int withDrawMoney, String name) {
        this.bank = bank;
        this.withDrawMoney = withDrawMoney;
        this.name = name;
    }

    @Override
    public void run() {
        bank.withdraw(withDrawMoney, name);
    }
}


class Bank {
    //账户余额
    private int totalMoney;
    private Object object=new Object();
    public Bank(int totalMoney) {
        this.totalMoney = totalMoney;
    }

    public void withdraw(int money, String name) {
        synchronized (object) {
            if (totalMoney >= money) {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                totalMoney = totalMoney - money;
                System.out.println(name + "已取款" + money + "，当前余额:" + totalMoney);
            } else {
                throw new RuntimeException(name + "尝试取钱" + money + "余额不足，当前余额为：" + totalMoney);
            }
        }
    }
}