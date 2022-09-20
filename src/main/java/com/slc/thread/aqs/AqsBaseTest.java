package com.slc.thread.aqs;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public class AqsBaseTest {

    static Node _a = new Node(-1, "_a");
    static Node a = new Node(-1, "a");
    static Node b = new Node(-1, "b");
    static Node c = new Node(222, "c");
    static Node d = new Node(-1, "d");
    static Node e = new Node(-1, "e");

    static {
        _a.setNext(a);
        a.setNext(b);
        b.setNext(c);
        c.setNext(d);
        d.setNext(e);

        e.setPre(d);
        d.setPre(c);
        c.setPre(b);
        b.setPre(a);
        a.setPre(_a);
    }

    public static void main(String[] args) {

        Node node = d;
        Node pre = d.pre;
//        get(node, pre);

    }

//    private static void get(Node node) {
//        if (node.pre.status == -1) {
//            do {
//                node.pre = node.pre.pre;
//                node.pre.pre.next = node;
//            }while (node.pre.status>0);
//
//        }
//
//    }

    private static void get(Node node, Node pre) {
        //now d , pre c
        System.out.println("begin");
        do {
//            pre = pre.pre;
//            node.pre = pre;
            node.pre = pre = pre.pre;
        } while (pre.status > 0);
        pre.next = node;

        System.out.println();
    }

    @Setter
    @Getter
    public static class Node {
        public Node(int status, String name) {
            this.status = status;
            this.name = name;
        }

        public int status;
        public String name;
        public Node pre;
        public Node next;
    }

}
