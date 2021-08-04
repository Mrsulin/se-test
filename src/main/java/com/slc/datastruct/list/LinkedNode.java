package com.slc.datastruct.list;

import lombok.Getter;
import lombok.Setter;

/**
 * @author slc
 */

@Setter
@Getter
public class LinkedNode<T> {

    private T id;
    private String name;
    public LinkedNode<T> next;


    public LinkedNode(T id) {
        this.id = id;
    }

    public LinkedNode(T id, String name) {
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
