package com.slc.serial;

import java.io.Serializable;

public class Car implements Serializable {
    private static final long serialVersionUID = -6083233007797285626L;
    private String color;

    public Car(String color) {
        this.color = color;
    }
}
