package com.slc.serial;

import java.io.Serializable;

public class Pig implements Serializable {

    private static final long serialVersionUID = 2142633998552530069L;
    public String name;
    private String color;
    public static String age = "5";

    transient private String hair;
    private Car car;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public static String getAge() {
        return age;
    }

    public static void setAge(String age) {
        Pig.age = age;
    }

    public String getHair() {
        return hair;
    }

    public void setHair(String hair) {
        this.hair = hair;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
