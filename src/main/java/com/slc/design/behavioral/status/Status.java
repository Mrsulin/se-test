package com.slc.design.behavioral.status;

public abstract class Status {
    Context context;

    Status(Context context) {
        this.context = context;
    }

    abstract void work();

    abstract void game();
}

class MorningStatus extends Status {


    MorningStatus(Context context) {
        super(context);
    }

    @Override
    public void work() {
        System.out.println("早上开始工作...到中午了");
        context.setCurrentStatus(Context.noonStatus);
    }

    @Override
    public void game() {
        throw new RuntimeException("早上不能晚游戏");
    }
}

class NoonStatus extends Status {


    NoonStatus(Context context) {
        super(context);
    }

    @Override
    public void work() {
        System.out.println("中午开始工作");
        context.setCurrentStatus(Context.eveningStatus);
    }

    @Override
    public void game() {
        System.out.println("中午游戏一会儿");

    }
}

class EveningStatus extends Status {

    EveningStatus(Context context) {
        super(context);
    }

    @Override
    public void work() {
        throw new RuntimeException("到晚上了该下班了");
    }

    @Override
    public void game() {
        System.out.println("晚上玩一会儿游戏");
        context.setCurrentStatus(Context.morningStatus);
    }
}

class Context {

    static Status morningStatus;
    static Status noonStatus;
    static Status eveningStatus;
    static Status currentStatus;

    public Context() {
        morningStatus = new MorningStatus(this);
        noonStatus = new NoonStatus(this);
        eveningStatus = new EveningStatus(this);
    }

    public void setCurrentStatus(Status status) {
        currentStatus = status;
    }

    public void work() {
        currentStatus.work();
    }

    public void game() {
        currentStatus.game();
    }
}

class Test {

    public static void main(String[] args) {
        Context context=new Context();
        context.setCurrentStatus(Context.morningStatus);
        context.work();
        context.game();
        context.work();
        context.game();

        context.game();
    }
}