package com.slc.serial;

import java.io.*;

public class Test {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        serializableMethod();
        reSerializableMethod();
    }

    private static void reSerializableMethod() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("pig.txt"));
        Pig o = (Pig) ois.readObject();
        System.out.println(o);
        System.out.println(o.age);
    }

    private static void serializableMethod() throws IOException {
        Pig pig=new Pig();
        pig.setColor("red");
        pig.setHair("black");
        pig.setName("黑皮红皮猪");
        Car car=new Car("benz");
        pig.setCar(car);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("pig.txt"));
        oos.writeObject(pig);
    }
}
