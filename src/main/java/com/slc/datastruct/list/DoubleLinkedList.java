package com.slc.datastruct.list;

import lombok.Data;

/**
 * 单链表
 *
 * @author slc
 */
@Data
public class DoubleLinkedList<T> {

    private DoubleLinkedNode<T> head = new DoubleLinkedNode<>(null);

    public DoubleLinkedList() {

    }

    public boolean add(DoubleLinkedNode<T> newLinkedNode) {
        DoubleLinkedNode<T> temp = this.head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newLinkedNode;
        newLinkedNode.pre = temp;
        return true;
    }

    public boolean update(DoubleLinkedNode<T> updateNode) {
        DoubleLinkedNode<T> temp = this.head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.getId() == updateNode.getId()) {
                updateNode.pre = temp.pre;
                updateNode.next = temp.next;
                temp.pre.next = updateNode;
                temp.next.pre = updateNode;
                flag = true;
                break;
            }
            temp = temp.next;
        }
        return flag;
    }

    public DoubleLinkedNode<T> get(T id) {
        DoubleLinkedNode<T> temp = this.head;
        while (temp != null) {
            if (temp.getId() == id) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }

        DoubleLinkedNode<T> temp = head.next;

        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        list.add(new DoubleLinkedNode<>(11, "*"));
        list.add(new DoubleLinkedNode<>(22, "*"));
        list.add(new DoubleLinkedNode<>(33, "*"));
        list.add(new DoubleLinkedNode<>(44, "*"));
        list.list();

        System.out.println("--------33------");
        System.out.println(list.get(33) + "--pre" + list.get(33).pre + "--next" + list.get(33).next);

        System.out.println("--------------");
        list.update(new DoubleLinkedNode<>(33, "asdsad"));
        list.list();

        System.out.println("--------33------");
        System.out.println(list.get(33) + "--pre" + list.get(33).pre + "--next" + list.get(33).next);

    }
}


