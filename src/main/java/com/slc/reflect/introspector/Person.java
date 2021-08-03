package com.slc.reflect.introspector;

/**
 * @author slc
 */

public class Person implements Human{
    public Person() {
    }
    private String id;
    private String userName;
    private String password;

    public Person(String id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }



    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }
}
