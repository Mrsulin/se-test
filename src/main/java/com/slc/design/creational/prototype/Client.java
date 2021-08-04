package com.slc.design.creational.prototype;

public class Client {

    public static void main(String[] args) throws CloneNotSupportedException {
        User user = new User("zs", "qwe");
        System.out.println(user.hashCode());
        System.out.println("------------");
        User clone = (User) user.clone();
        System.out.println(clone);
        System.out.println(clone.hashCode());
        user.setName("lasd");
        System.out.println(user);
        System.out.println(clone);
    }
}
