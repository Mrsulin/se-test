package com.slc.design.behavioral.memento;

import lombok.*;

/**
 * @author sulin
 */

public class User {

    private String name;
    private Integer sex;
    private String address;
    private Memento memento;

    public User(String name, Integer sex, String address) {
        this.name = name;
        this.sex = sex;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void createMemento() {
       this.memento= new Memento(this.name, sex, address);
    }

    public void restoreMemento() {
        name = memento.getName();
        sex = memento.getSex();
        address = memento.getAddress();
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", sex=" + sex +
                ", address='" + address + '\'' +
                '}';
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private class Memento{
        private String name;
        private Integer sex;
        private String address;
    }
}

class TestUser {

    public static void main(String[] args) {
        User user = new User("zs", 1, "成都");
        user.createMemento();
        System.out.println(user);
        user.setName("nxisanxisn");
        user.setAddress("asdniasndi");
        System.out.println(user);
        user.restoreMemento();
        System.out.println(user);
    }
}