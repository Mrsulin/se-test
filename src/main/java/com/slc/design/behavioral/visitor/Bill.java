package com.slc.design.behavioral.visitor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 访问者模式
 * @author sulin
 */
public interface Bill {

    void accept(AbstractVisitor visitor);
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class BillA implements Bill {

    private String name;

    @Override
    public void accept(AbstractVisitor visitor) {
        visitor.visit(this);
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class BillB implements Bill {
    private String name;

    @Override
    public void accept(AbstractVisitor visitor) {
        visitor.visit(this);
    }
}

interface AbstractVisitor {

    void visit(BillA billA);

    void visit(BillB billB);
}

class VisitorA implements AbstractVisitor {

    @Override
    public void visit(BillA billA) {
        System.out.println(billA.getName()+" va for ba");
    }

    @Override
    public void visit(BillB billB) {
        System.out.println(billB.getName()+" va for bb");
    }
}

class VisitorB implements AbstractVisitor {

    @Override
    public void visit(BillA billA) {
        System.out.println(billA.getName()+" vb for ba");
    }

    @Override
    public void visit(BillB billB) {
        System.out.println(billB.getName()+" vb for bb");
    }
}

class Structure {
    private List<Bill> list;

    public void setList(List<Bill> list) {
        this.list = list;
    }

    public void view(AbstractVisitor abstractVisitor) {
        list.forEach(bill -> bill.accept(abstractVisitor));
    }
}

class Test {

    public static void main(String[] args) {
        Structure structure = new Structure();
        structure.setList(Arrays.asList(new BillA("A1"), new BillB("B1 "),new BillA("A2"),new BillB("B2")));
        structure.view(new VisitorA());
        System.out.println("-------------------");
        structure.view(new VisitorB());
    }
}

