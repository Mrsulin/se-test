package com.slc.design.creational.prototype;

/**
 * @author slc
 */
public class Test {

    public static void main(String[] args) throws CloneNotSupportedException {
        Address address = new Address("11", "四川", "成都");
        User user = User.builder().name("asd").password("asdsad").asd1(123).asd2(123).asd3(123).address(address).build();
        User clone = (User) user.clone();

        System.out.println("user  " + user);
        System.out.println("clone " + clone);
        System.out.println(user.getAddress()==clone.getAddress());

        address.setArea("北京");
        address.setCity("北京");

        System.out.println("改变原user中address后 --> user   " + user);
        System.out.println("改变原user中address后 --> clone  " + clone);
    }


}
