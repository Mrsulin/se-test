package com.slc.jvm.gc;

import lombok.Data;
import lombok.NoArgsConstructor;

public class  ReferenceTest {
    @NoArgsConstructor
    public static class Node {
        public String name;
        public Node pre;
        public Node next;

        public Node(String name) {
            this.name = name;
        }
    }

    static Node a = new Node("a");
    static Node b = new Node("b");
    static Node c = new Node("c");
    static Node d = new Node("d");
    static Node e = new Node("e");

    static {
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        b.pre = a;
        c.pre = b;
        d.pre = c;
        e.pre = d;
    }

    public static void main(String[] args) {
        // a b d
        c.pre.next = c.next;
        c.next.pre = c.pre;
        c = null;

        System.out.println();

    }

}
