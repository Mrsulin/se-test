package com.slc.design.behavioral.chainofresponsibility;

import lombok.Data;

@Data
public class Purchase {

    private String purchaseDays;
    private String applyPerson;
}

abstract class Approver {
    protected Approver approver;

    public void setApprover(Approver approver) {
        this.approver = approver;
    }

    abstract void handlePurchase(Purchase purchase);
}

class Approver1 extends Approver {

    @Override
    void handlePurchase(Purchase purchase) {
        System.out.println("Approver1 审批完毕");
        approver.handlePurchase(purchase);
    }
}

class Approver2 extends Approver {


    @Override
    void handlePurchase(Purchase purchase) {
        System.out.println("Approver2 审批完毕");
        approver.handlePurchase(purchase);
    }
}

class Approver3 extends Approver {


    @Override
    void handlePurchase(Purchase purchase) {
        System.out.println("Approver3 审批完毕 ,总流程结束！");
    }
}
class Test{
    public static void main(String[] args) {
        Purchase purchase = new Purchase();
        Approver approver1=new Approver1();
        Approver approver2=new Approver2();
        Approver approver3=new Approver3();
        approver1.setApprover(approver2);
        approver2.setApprover(approver3);
        approver3.setApprover(approver3);
        approver1.handlePurchase(purchase);
    }
}