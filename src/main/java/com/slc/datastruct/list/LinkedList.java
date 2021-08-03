package com.slc.datastruct.list;

/**
 * 单链表
 *
 * @author slc
 */
public class LinkedList<T> {
    public LinkedList() {
    }

    private LinkedNode<T> head = new LinkedNode<>(null);

    public boolean add(T key, LinkedNode<T> newLinkedNode) {

        boolean flag = false;

        LinkedNode<T> tempLinkedNode = head;
        while (true) {
            if (tempLinkedNode.next == null) {
                break;
            }

            if (tempLinkedNode.getId() == key) {
                newLinkedNode.next = tempLinkedNode.next;
                tempLinkedNode.next = newLinkedNode;
                flag = true;
                break;
            }
            tempLinkedNode = tempLinkedNode.next;
        }
        return flag;
    }

    public void add(LinkedNode<T> linkedNode) {
        LinkedNode<T> temp = head;

        while (true) {
            //找到链表的最后
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        //此时temp为最后一个结点，添加结点即可
        temp.next = linkedNode;
    }

    public void getAllNode() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }

        LinkedNode<T> temp = head.next;

        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }


    public boolean update(LinkedNode<T> linkedNode) {
        LinkedNode<T> temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }

            if (temp.next.getId() == linkedNode.getId()) {
                linkedNode.next = temp.next.next;
                temp.next = linkedNode;
                flag = true;
                break;
            }
            temp = temp.next;
        }
        return flag;
    }


    public boolean deleteNode(T id) {
        LinkedNode<T> temp = this.head;
        boolean deleteFlag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }

            if (temp.next.getId() == id) {
                temp.next = null;
                deleteFlag = true;
                break;
            }

            temp = temp.next;
        }
        return deleteFlag;
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(new LinkedNode<>(1));
        list.add(new LinkedNode<>(2));
        list.add(new LinkedNode<>(3));
        list.add(new LinkedNode<>(4));
        list.getAllNode();

        System.out.println("-------有序新增------------");
        list.add(2, new LinkedNode<>(123123));
        list.getAllNode();

        System.out.println("-------修改结点--------");
        list.update(new LinkedNode<>(3, "asdniansdlmnin"));
        list.getAllNode();

        System.out.println("-------删除结点之后---------");
        list.deleteNode(3);
        list.getAllNode();
    }
}


