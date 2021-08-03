package com.slc.datastruct.list;

import lombok.Getter;
import lombok.Setter;

/**
 * @author slc
 */

@Setter
@Getter
public class DoubleLinkedNode<T> {

    private T id;
    private String name;
    public DoubleLinkedNode<T> next;
    public DoubleLinkedNode<T> pre;


    public DoubleLinkedNode(T id) {
        this.id = id;
    }

    public DoubleLinkedNode(T id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "LinkedNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
